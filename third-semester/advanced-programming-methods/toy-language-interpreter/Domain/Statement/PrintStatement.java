package Domain.Statement;

import Domain.ADT.List.MyIList;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Value.IValue;
import Exception.MyException;
import Exception.ADTException;
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
     * @throws MyException Unused
     */
    @Override
    public PrgState execute(PrgState state) throws MyException, ADTException {
        MyIList<IValue> out = state.getOut();
        out.add(expression.evaluate(state.getSymTable()));
        state.setOut(out);
        return state;
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
