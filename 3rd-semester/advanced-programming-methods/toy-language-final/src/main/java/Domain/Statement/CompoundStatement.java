package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Stack.MyIStack;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Exception.InterpreterException;
public class CompoundStatement implements IStatement {
    private final IStatement firstStatement;
    private final IStatement secondStatement;

    /**
     * Constructor for CompoundStatement
     * @param firstStatement = the firstStatement IStatement in the CompoundStatement
     * @param secondStatement = the secondStatement IStatement in the CompoundStatement (the rest of the Statements)
     */
    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    /**
     * Simple toString function
     * @return Returns (firstStatement;secondStatement)
     */
    @Override
    public String toString() {
        return "(" + firstStatement.toString() +
                ";" + secondStatement.toString() +
                ')';
    }

    /**
     * Executes the CompoundStatement
     * firstStatement gets the exeStack
     * secondStatement pushes the rest of the IStatements to the bottom (secondStatement)
     * Third pushes the firstStatement IStatement to the top (firstStatement)
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws InterpreterException Unused
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIStack<IStatement> exeStack = state.getExeStack();
        exeStack.push(secondStatement);
        exeStack.push(firstStatement);
        state.setExeStack(exeStack);
        return null;
    }

    /**
     * Checks if the types of the inputs are appropriate to the wanted operation, returns a likewise appropriate output
     * @param iTypeEnvironment the type environment
     * @return The type environment, changed only for VarDeclareStatement
     * @throws InterpreterException Should any input not be appropriate to the statement
     */
    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        return secondStatement.typeCheck(firstStatement.typeCheck(iTypeEnvironment));
    }

    /**
     * Performs a deepcopy on CompoundStatement
     * @return Returns a new CompoundStatement object with the same firstStatement and secondStatement IStatements
     */
    @Override
    public IStatement deepcopy() {
        return new CompoundStatement(firstStatement.deepcopy(), secondStatement.deepcopy());
    }
}
