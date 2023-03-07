package Domain.ProgramState;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Table.MyILatchTable;
import Domain.ADT.Heap.MyIHeap;
import Domain.ADT.List.MyIList;
import Domain.ADT.Stack.MyIStack;
import Domain.Statement.IStatement;
import Domain.Value.IValue;

import Exception.*;

import java.io.BufferedReader;
import java.util.List;

public class PrgState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, IValue> symTable;
    private MyIList<IValue> out;
    private MyIDictionary<String, BufferedReader> fileTable;
    private MyIHeap heap;
    private IStatement originalProgram;
    private int id;
    private static int lastId = 0;
    private MyILatchTable latchTable;

    /**
     * Constructor for PrgState
     * @param exeStack = a stack upon which we will push the statements to execute them
     * @param symTable = a dictionary where we will keep the variable names and their assigned values
     * @param out = a list where we append everything that needs to be printed
     * @param fileTable = a dictionary where we keep the opened files
     * @param heap = the heap for referenced values
     * @param originalProgram = the beginning IStatement, which we deepcopy before pushing it onto the exeStack
     */
    public PrgState(MyIStack<IStatement> exeStack, MyIDictionary<String, IValue> symTable, MyIList<IValue> out,
                    MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, IStatement originalProgram,
                    MyILatchTable latchTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
        this.originalProgram = originalProgram.deepcopy();
        this.exeStack.push(this.originalProgram);
        this.id = setId();
    }

    public PrgState(MyIStack<IStatement> exeStack, MyIDictionary<String, IValue> symTable, MyIList<IValue> out,
                    MyIDictionary<String, BufferedReader> fileTable, MyIHeap heap, MyILatchTable latchTable) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.latchTable = latchTable;
        this.id = setId();
    }

    public int getId() {
        return this.id;
    }

    public synchronized int setId() {
        lastId++;
        return lastId;
    }

    public PrgState oneStep() throws InterpreterException {
        if (exeStack.isEmpty()) {
            throw new InterpreterException("Execution Stack is empty.\n");
        }
        IStatement current = exeStack.pop();
        return current.execute(this);
    }

    /**
     * Simple toString function
     * @return Returns the stringified program state with every component it has
     */
    @Override
    public String toString() {
        return "Id: " + id +
                "\nExecution stack: \n" + exeStack.getStatementList() +
                "\nSymbol table: \n" + symTable.toString() +
                "\nOutput list: \n" + out.toString() +
                "\nFile table:\n" + fileTable.toString() +
                "\nHeap:\n" + heap.toString() +
                "\nLatch Table:\n" + latchTable.toString() + "\n";
    }

    /**
     * Comprehensive toString function for the exeStack
     * Uses StringBuilder to keep a builder where we put all the statements as strings and ORDERED BY PRIORITY
     * @return Returns the completed string builder
     */
    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = exeStack.getStatementList();
        for (IStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString());
            exeStackStringBuilder.append("\n");
        }

        return exeStackStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the symTable
     * Uses StringBuilder to keep a builder where we put all the keys and their values
     * Looks through a for through the set of keys in the symTable, and for each key it inputs its value
     * @return Returns the completed string builder
     * @throws ADTException If symTable.lookUp(key) does not find a value
     */
    public String symTableToString() throws InterpreterException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the out
     * Uses StringBuilder to keep a builder where we put all the prints
     * Looks through a for through the 'out' list and appends every line
     * @return Returns the completed string builder
     */
    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (IValue elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the fileTable
     * Uses StringBuilder to keep a builder where we put all the prints
     * Looks through a for through the 'fileTable' dictionary (key set) and appends every key found
     * @return The completed string builder
     */
    public String fileTableToString() {
        StringBuilder fileTableStringBuilder = new StringBuilder();
        for (String key: fileTable.keySet()) {
            fileTableStringBuilder.append(String.format("%s\n", key));
        }
        return fileTableStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the Heap
     * Uses StringBuilder to keep a builder where we put all the prints
     * Looks through a for through the 'heap' hash map (key set) and appends every key-value pair found
     * @return The completed string builder
     * @throws ADTException If 'get' throws an exception
     */
    public String heapToString() throws InterpreterException {
        StringBuilder heapStringBuilder = new StringBuilder();
        for (int key: heap.keySet()) {
            heapStringBuilder.append(String.format("%d -> %s\n", key, heap.get(key)));
        }
        return heapStringBuilder.toString();
    }

    public String latchTableToString() throws InterpreterException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int key: latchTable.keySet()) {
            stringBuilder.append(String.format("%d -> %d\n", key, latchTable.get(key)));
        }
        return stringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the PrgState
     * @return Returns the combination of exeStackToString, symTableToString and outToString
     * @throws ADTException If symTableToString() OR heapToString() throws an exception
     */
    public String fullToString() throws InterpreterException {
        return "Id: " + id + "\nExecution stack: \n" + exeStackToString() + "Symbol table: \n" +
                symTableToString() + "Output list: \n" + outToString() + "File table: \n" + fileTableToString()
                + "Heap: \n" + heapToString() + "Latch Table: \n" + latchTableToString();
    }

    /**
     * Simple getter for exeStack
     * @return Returns the exeStack
     */
    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    /**
     * Simple setter for exeStack
     * @param exeStack = new exeStack
     */
    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    /**
     * Simple getter for symTable
     * @return Returns the symTable
     */
    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    /**
     * Simple setter for symTable
     * @param symTable = new symTable
     */
    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    /**
     * Simple getter for out
     * @return Returns the out
     */
    public MyIList<IValue> getOut() {
        return out;
    }

    /**
     * Simple setter for out
     * @param out = new out
     */
    public void setOut(MyIList<IValue> out) {
        this.out = out;
    }

    /**
     * Getter for the fileTable
     * @return The fileTable dictionary
     */
    public MyIDictionary<String, BufferedReader> getFileTable() {
        return fileTable;
    }

    /**
     * Setter for the fileTable
     * @param fileTable The fileTable to replace the current one
     */
    public void setFileTable(MyIDictionary<String, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    /**
     * Getter for the heap
     * @return The heap hashMap/Heap ADT
     */
    public MyIHeap getHeap() {
        return heap;
    }

    /**
     * Setter for the heap
     * @param heap The heap with which to replace the current one
     */
    public void setHeap(MyIHeap heap) {
        this.heap = heap;
    }

    public boolean isNotCompleted() {
        return exeStack.isEmpty();
    }

    public MyILatchTable getLatchTable() {
        return latchTable;
    }

    public void setLatchTable(MyILatchTable latchTable) {
        this.latchTable = latchTable;
    }
}
