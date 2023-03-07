package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Stack.MyIStack;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Exception.*;

public class WhileStatement implements IStatement {
    private final IExpression expression;
    private final IStatement statement;

    /**
     * Constructor for WhileStatement
     * @param expression check condition for continuing the while
     * @param statement the statement(s) to be repeated while
     */
    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    /**
     * Simple toString function
     * @return while(expression){statement}
     */
    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }

    /**
     * If the expression evaluates to TRUE, pushes a deepcopy of the while on the stack, followed by the statement within.
     * As such, the statement will be executed, then the exeStack hits the while again and repeats the process until FALSE.
     * @param state = the current program state
     * @return null
     * @throws InterpreterException should any of the types not be appropriate
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IValue ivalue = expression.evaluate(state.getSymTable(), state.getHeap());
        MyIStack<IStatement> exeStack = state.getExeStack();
        if (!ivalue.getType().equals(new BoolType()))
            throw new InterpreterException(String.format("%s is not of BoolType", ivalue));
        if (!(ivalue instanceof BoolValue boolValue))
            throw new InterpreterException(String.format("%s is not a BoolValue", ivalue));
        if (boolValue.getValue()) {
            exeStack.push(this.deepcopy());
            exeStack.push(statement);
        }
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
        IType typeExpression = expression.typeCheck(iTypeEnvironment);
        if (typeExpression.equals(new BoolType())) {
            statement.typeCheck(iTypeEnvironment.deepcopy());
            return iTypeEnvironment;
        }
        else {
            throw new InterpreterException("While: Condition must be of boolean type.\n");
        }
    }

    /**
     * Deepcopies the WhileStatement
     * @return A deepcopy of the WhileStatement
     */
    @Override
    public IStatement deepcopy() {
        return new WhileStatement(expression.deepcopy(), statement.deepcopy());
    }
}
