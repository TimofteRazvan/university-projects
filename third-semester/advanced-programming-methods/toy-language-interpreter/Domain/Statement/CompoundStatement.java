package Domain.Statement;

import Domain.ADT.Stack.MyIStack;
import Domain.ProgramState.PrgState;

import Exception.MyException;
import Exception.ADTException;
public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;

    /**
     * Constructor for CompoundStatement
     * @param first = the first IStatement in the CompoundStatement
     * @param second = the second IStatement in the CompoundStatement (the rest of the Statements)
     */
    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Simple toString function
     * @return Returns (first;second)
     */
    @Override
    public String toString() {
        return "(" + first.toString() +
                ";" + second.toString() +
                ')';
    }

    /**
     * Executes the CompoundStatement
     * First gets the exeStack
     * Second pushes the rest of the IStatements to the bottom (second)
     * Third pushes the first IStatement to the top (first)
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws MyException Unused
     */
    @Override
    public PrgState execute(PrgState state) throws MyException, ADTException {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(second);
        stack.push(first);
        return state;
    }

    /**
     * Performs a deepcopy on CompoundStatement
     * @return Returns a new CompoundStatement object with the same first and second IStatements
     */
    @Override
    public IStatement deepcopy() {
        return new CompoundStatement(first.deepcopy(), second.deepcopy());
    }
}
