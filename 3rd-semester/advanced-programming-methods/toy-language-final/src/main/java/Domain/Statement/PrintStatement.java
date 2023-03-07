package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.List.MyIList;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Domain.Value.IValue;
import Exception.ADTException;
import Exception.InterpreterException;
import Exception.EvaluateException;

public class PrintStatement implements IStatement {
    IExpression expression;

    /**
     * Constructor for PrintStatement
     * @param expression = the expression that we will evaluate in order to find what to print
     */
    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    /**
     * Simple toString function
     * @return Returns print(expression)
     */
    @Override
    public String toString() {
        return "print(" +
                expression.toString() +
                ')';
    }

    /**
     * Executes the PrintStatement
     * First gets the 'out' of the program state
     * Second adds to the out the result of evaluating 'expression'
     * Third updates the out, therefore updating program state
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws EvaluateException If evaluate throws exception
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIList<IValue> out = state.getOut();
        out.add(expression.evaluate(state.getSymTable(), state.getHeap()));
        state.setOut(out);
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
        expression.typeCheck(iTypeEnvironment);
        return iTypeEnvironment;
    }

    /**
     * Performs a deepcopy on PrintStatement
     * @return Returns a new PrintStatement object with the same expression value
     */
    @Override
    public IStatement deepcopy() {
        return new PrintStatement(expression.deepcopy());
    }
}
