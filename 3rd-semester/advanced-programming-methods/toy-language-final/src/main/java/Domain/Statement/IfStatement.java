package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Stack.MyIStack;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Exception.MyException;
import Exception.ADTException;
import Exception.InterpreterException;
import Exception.EvaluateException;

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
     * @throws InterpreterException If 'expression' does not provide a BoolValue
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IValue resultValue = this.expression.evaluate(state.getSymTable(), state.getHeap());
        if (resultValue instanceof BoolValue boolResult) {
            IStatement statement;
            if (boolResult.getValue()) {
                statement = thenStatement;
            }
            else {
                statement = elseStatement;
            }
            MyIStack<IStatement> exeStack = state.getExeStack();
            exeStack.push(statement);
            state.setExeStack(exeStack);
            return null;
        }
        else {
            throw new InterpreterException("Non-boolean expression in an if statement!\n");
        }
    }

    /**
     * Checks if the types of the inputs are appropriate to the wanted operation, returns a likewise appropriate output
     * @param iTypeEnvironment the type environment
     * @return The type environment, changed only for VarDeclareStatement
     * @throws InterpreterException Should any input not be appropriate to the statement
     */
    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType typeExpression = expression.typeCheck(iTypeEnvironment);
        if (typeExpression.equals(new BoolType())) {
            thenStatement.typeCheck(iTypeEnvironment.deepcopy());
            elseStatement.typeCheck(iTypeEnvironment.deepcopy());
            return iTypeEnvironment;
        }
        else {
            throw new InterpreterException("IF condition does not have boolean type.\n");
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
