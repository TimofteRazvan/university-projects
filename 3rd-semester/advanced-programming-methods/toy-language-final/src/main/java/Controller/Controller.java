package Controller;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.ADT.Stack.MyIStack;
import Domain.ProgramState.PrgState;
import Domain.Statement.IStatement;
import Domain.Value.IValue;
import Domain.Value.RefValue;
import Repository.IRepository;

import Exception.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Pair {
    final PrgState first;
    final InterpreterException second;

    public Pair(PrgState first, InterpreterException second) {
        this.first = first;
        this.second = second;
    }
}

public class Controller {
    IRepository repository;
    boolean displayFlag = false;
    ExecutorService executorService;

    /**
     * Constructor for Controller
     * @param repository = the repository which we operate on
     */
    public Controller(IRepository repository) {
        this.repository = repository;
    }

    /**
     * Setter for the displayFlag (used to check if user wants the program to be shown step-by-step.
     * Obsolete with GUI. still used if activated via Interpreter.
     * @param value = the displayFlag's new value
     */
    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    /**
     * Similar to allStep, only used in the GUI class. Removes completed programs before each iteration, then proceeds
     * to call oneStepThreads so that all active program states may progress at once. Collects Garbage at all times.
     * @throws InterpreterException --
     * @throws InterruptedException --
     */
    public void oneStep() throws InterpreterException, InterruptedException {
        executorService = Executors.newFixedThreadPool(2);
        List<PrgState> prgStateList = removeCompletedProgram(repository.getProgramList());
        oneStepThreads(prgStateList);
        ThreadsGarbageCollector(prgStateList);
        //prgStateList = removeCompletedPrg(repository.getProgramList());
        executorService.shutdownNow();
        //repository.setprgStateList(prgStateList);
    }

    /**
     * The method that allows the controller to synchronise all active program states and make them run in concurrency.
     * @param prgStateList The list of program states that we want to run together.
     * @throws InterpreterException --
     * @throws InterruptedException --
     */
    public void oneStepThreads(List<PrgState> prgStateList) throws InterpreterException, InterruptedException {
        // Writes to log files
        prgStateList.forEach(prgState -> {
            try {
                repository.logPrgStateExec(prgState);
                display(prgState);
            } catch (IOException | InterpreterException exception) {
                System.out.println(exception.getMessage());
            }
        });
        // Gets a list of callable program state
        List<Callable<PrgState>> callList = prgStateList.stream()
                .map((PrgState p) -> (Callable<PrgState>) (p::oneStep))
                .collect(Collectors.toList());

        List<Pair> newPrgStateList;
        // Invokes all from the callable list.
        // Maps pairs of PrgState, Exception (loosely to avoid crashing should only one thread mess up)
        newPrgStateList = executorService.invokeAll(callList).stream()
                .map(prgStateFuture -> {
                    try {
                        return new Pair(prgStateFuture.get(), null);
                    } catch (ExecutionException | InterruptedException exception) {
                        if (exception.getCause() instanceof InterpreterException)
                            return new Pair(null, (InterpreterException) exception.getCause());
                        System.out.println(exception.getMessage());
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .filter(pair -> pair.first != null || pair.second != null)
                .collect(Collectors.toList());

        // Should the second element of the (PrgState, Exception) pair be non-null, means we had an exception, so we throw it.
        for (Pair exception: newPrgStateList)
            if (exception.second != null)
                throw exception.second;
        // Adds the first element of the pairs (PrgState) to the master prgStateList
        prgStateList.addAll(newPrgStateList.stream().map(pair -> pair.first).toList());

        // Executes logPrgStateExec AGAIN for every programState
        // Done because otherwise it doesn't show the very last call, whereas without the first it doesn't show the
        // very first call.
        prgStateList.forEach(prgState -> {
            try {
                repository.logPrgStateExec(prgState);
                display(prgState);
            } catch (IOException | InterpreterException exception) {
                System.out.println(exception.getMessage());
            }
        });
        repository.setProgramList(prgStateList);
    }

    /**
     * Allsteps used that now calls oneStepThreads instead of simple oneStep.
     * Via executorService, has a thread pool so that it may work in synchronicity.
     * Removes completed programs before beginning, then keeps doing oneStepThreads & collecting Garbage until there
     * are no more program states active.
     * Obsolete as GUI now uses oneStep, but still used should the program be started via Interpreter class.
     * @throws InterruptedException --
     * @throws InterpreterException --
     */
    public void allSteps() throws InterruptedException, InterpreterException {
        executorService = Executors.newFixedThreadPool(2);
        List<PrgState> prgStateList = removeCompletedProgram(repository.getProgramList());
        while (prgStateList.size() > 0) {
            oneStepThreads(prgStateList);
            ThreadsGarbageCollector(prgStateList);
            //prgStateList = removeCompletedPrg(repository.getProgramList());
        }
        executorService.shutdownNow();
        //repository.setprgStateList(prgStateList);
    }

    /**
     * Fixed, safe GarbageCollector. No missed cases. Incompatibility with threading.
     * @param symTableAddressList List of symTable addresses
     * @param heapAddressList List of heap addresses
     * @param heap Current heap
     * @return Key-value pairs within heap contained in EITHER heap or symTable
     */
    public Map<Integer, IValue> safeGarbageCollector(List<Integer> symTableAddressList, List<Integer> heapAddressList,
                                                     Map<Integer, IValue> heap) {
        return heap.entrySet().stream()
                .filter(e -> ( symTableAddressList.contains(e.getKey()) || heapAddressList.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Final GarbageCollector. Makes use of SafeGarbageCollector and works on all threads.
     * In essence, it does safeGarbageCollector for each program state from the program state list.
     * @param prgStateList list of program states
     */
    public void ThreadsGarbageCollector(List<PrgState> prgStateList) {
        List<Integer> symTableAddressList = Objects.requireNonNull(prgStateList.stream()
                        .map(p -> getAddressFromSymTable(p.getSymTable().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        prgStateList.forEach(p -> p.getHeap().setHeap((HashMap<Integer, IValue>) safeGarbageCollector(symTableAddressList,
                getAddressFromHeap(p.getHeap().getHeap().values()), p.getHeap().getHeap())));
    }

    /**
     * Professor-given GarbageCollector that has some issues, such as missed cases (takes only symTable addresses)
     * @param symTableAddressList List of addresses
     * @param heap Current heap
     * @return The key-value pairs within heap, but only those that are contained in symTableAddresses
     */
    private Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddressList, Map<Integer, IValue> heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddressList.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Getter for addresses of symTable RefValues
     * 1. Filters so that only RefValues are considered;
     * 2. Maps the RefValues to only take their addresses;
     * 3. Collect them into one List of Integers
     * @param symTableValues = collection of IValues present in symTable
     * @return List of Integers containing the address of every RefValue in symTable
     */
    List<Integer> getAddressFromSymTable(Collection<IValue> symTableValues) {
        return symTableValues.stream()
                .filter(v->v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    /**
     * Getter for addresses of heap RefValues
     * 1. Filters so that only RefValues are considered;
     * 2. Maps the RefValues to only take their addresses;
     * 3. Collect them into one List of Integers
     * @param heapValues = collection of IValues present in heap
     * @return List of Integer containing the address of every RefValue in heap
     */
    public List<Integer> getAddressFromHeap(Collection<IValue> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    /**
     * Checks if the displayFlag is set, in which case we print the current program state to the user step-by-step
     */
    private void display(PrgState prgState) {
        if (displayFlag) {
            System.out.println(prgState.toString());
        }
    }

    public List<PrgState> removeCompletedProgram(List<PrgState> prgStateList) {
        return prgStateList.stream().filter(p -> !p.isNotCompleted()).collect(Collectors.toList());
    }

    public void setProgramStateList(List<PrgState> prgStateList) {
        this.repository.setProgramList(prgStateList);
    }
    public List<PrgState> getProgramStateList() {
        return this.repository.getProgramList();
    }
}
