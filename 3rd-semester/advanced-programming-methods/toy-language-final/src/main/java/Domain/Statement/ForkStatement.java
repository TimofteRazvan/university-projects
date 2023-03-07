package Domain.Statement;

import Domain.ADT.Dictionary.MyDictionary;
import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Stack.MyIStack;
import Domain.ADT.Stack.MyStack;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;

import Domain.Value.IValue;
import Exception.*;

import java.util.Map;
import java.util.Stack;

public class ForkStatement implements IStatement {
    private final IStatement statement;

    /**
     * ForkStatement constructor
     * @param statement An IStatement to be processed alongside the rest of the interpreter (can be compound)
     */
    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    /**
     * Simple toString function
     * @return Fork(Statement_Content)
     */
    @Override
    public String toString() {
        return String.format("Fork(%s)", statement.toString());
    }

    /**
     * Executes the ForkStatement. Creates a new exeStack that begins with the statement given. Creates a new symTable,
     * populates it with copies of the values within the main interpreter's symTable. Returns new Program State.
     * @param state = the current program state
     * @return New program state that contains only the IStatement given to Fork()
     * @throws InterpreterException Should any of the inner functions throw exceptions
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIStack<IStatement> newExeStack = new MyStack<>();
        newExeStack.push(statement);
        MyIDictionary<String, IValue> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, IValue> entry: state.getSymTable().getContent().entrySet()) {
            newSymTable.put(entry.getKey(), entry.getValue().deepcopy());
        }
        return new PrgState(newExeStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap(), state.getLatchTable());
    }

    /**
     * Checks if the types of the inputs are appropriate to the wanted operation, returns a likewise appropriate output
     * @param iTypeEnvironment the type environment
     * @return The type environment, changed only for VarDeclareStatement
     * @throws InterpreterException Should any input not be appropriate to the statement
     */
    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        statement.typeCheck(iTypeEnvironment.deepcopy());
        return iTypeEnvironment;
    }

    /**
     * Creates a deepcopy of the Fork() statement
     * @return the deepcopied statement
     */
    @Override
    public IStatement deepcopy() {
        return new ForkStatement(statement.deepcopy());
    }
}
