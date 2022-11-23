package Controller;

import Domain.ADT.Stack.MyIStack;
import Domain.ProgramState.PrgState;
import Domain.Statement.IStatement;
import Repository.IRepository;

import Exception.MyException;
import Exception.ADTException;

import java.io.IOException;

public class Controller {
    IRepository repository;
    boolean displayFlag = false;

    /**
     * Constructor for Controller
     * @param repository = the repository which we operate on
     */
    public Controller(IRepository repository) {
        this.repository = repository;
    }

    /**
     * Setter for the displayFlag (used to check if user wants the program to be shown step-by-step)
     * @param value = the displayFlag's new value
     */
    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    /**
     * One step of the program state execution
     * First makes a copy of the exeStack
     * Second checks if the stack isn't empty
     * Third creates an IStatement to keep a hold of the top-most statement/first statement
     * Fourth updates the exeStack without the first statement that we popped
     * Fifth executes the popped statement
     * @param state = the program state
     * @return Returns the execution of the popped/first statement
     * @throws ADTException If the stack is empty
     * @throws MyException If execute throws exception
     */
    public PrgState oneStep(PrgState state) throws ADTException, MyException {
        MyIStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty())
            throw new ADTException("Program state stack is empty.");
        IStatement currentStatement = stack.pop();
        state.setExeStack(stack);
        return currentStatement.execute(state);
    }

    /**
     * All the steps of the program state execution
     * First gets the current program state out of the program state list
     * Second prints it to the log file
     * Third displays
     * Fourth enters a loop that, while the stack is NOT empty, keeps repeating oneStep and printing into the log file
     * As such, every statement will be popped from within oneStep one-by-one and executed until stack is empty
     * @throws MyException If oneStep() throws Exception
     * @throws IOException If logPrgStateExec() throws IOException
     */
    public void allSteps() throws MyException, IOException, ADTException {
        PrgState program = this.repository.getCurrentState();
        this.repository.logPrgStateExec();
        display();
        while(!program.getExeStack().isEmpty()) {
            oneStep(program);
            this.repository.logPrgStateExec();
            display();
        }
    }

    /**
     * Checks if the displayFlag is set, in which case we print the current program state to the user step-by-step
     */
    private void display() {
        if (displayFlag) {
            System.out.println(this.repository.getCurrentState().toString());
        }
    }
}
