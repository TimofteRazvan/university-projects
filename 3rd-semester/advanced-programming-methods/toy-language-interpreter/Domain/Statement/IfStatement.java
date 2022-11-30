package Domain.Statement;

import Domain.ADT.Stack.MyIStack;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Exception.MyException;
import Exception.ADTException;
public class IfStatement implements IStatement {
    IExpression expression;
    IStatement thenStatement, elseStatement;

    /**
     * Constructor for IfStatement
     * @param expression = the expression to be evaluated
     * @param thenStatement = the statement that will be executed if expression is evaluated favourably
     * @param elseStatement = the statement that will be executed if expression is NOT evaluated favourably
     */
    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    /**
     * Simple toString function
     * @return Returns IF(expression) THEN (thenStatement) ELSE (elseStatement)
     */
    @Override
    public String toString() {
        return "(IF("+ expression.toString()+") THEN (" +thenStatement.toString() +
                ") ELSE ("+elseStatement.toString()+"))";
    }

    /**
     * Executes the IfStatement
     * First evaluates the expression
     * Second checks if the result is a BoolValue
     * Third checks if the BoolValue was positive, then the thenStatement gets pushed onto the exeStack
     * Fourth checks if otherwise, in which case the elseStatement gets pushed onto the exeStack instead
     * Fifth updates the exeStack with the new exeStack
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws MyException If 'expression' does not provide a BoolValue
     */
    @Override
    public PrgState execute(PrgState state) throws MyException, ADTException {
        IValue result = this.expression.evaluate(state.getSymTable());
        if (result instanceof BoolValue boolResult) {
            IStatement statement;
            if (boolResult.getValue()) {
                statement = thenStatement;
            }
            else {
                statement = elseStatement;
            }

            MyIStack<IStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return state;
        }
        else {
            throw new MyException("Please provide a boolean expression in an if statement.");
        }
    }

    /**
     * Performs a deepcopy on IfStatement
     * @return Returns a new IfStatement object with the same expression, thenStatement and elseStatement
     */
    @Override
    public IStatement deepcopy() {
        return new IfStatement(expression.deepcopy(), thenStatement.deepcopy(), elseStatement.deepcopy());
    }
}
