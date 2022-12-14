package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Stack.MyIStack;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;
public class AssignStatement implements IStatement {
    String id;
    IExpression expression;

    /**
     * Constructor for AssignStatement
     * @param id = the key/id for which we will assign the value
     * @param expression = the expression to evaluate in order to get the value
     */
    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    /**
     * Simple toString function
     * @return Returns the AssignStatement in the form "var = expression".
     */
    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }

    /**
     * Executes the AssignStatement, which checks if the variable-to-be-changed has been declared beforehand:
     * - if the variable HAS been declared, we move on to check if it can accept the given expression
     * - if it can accept the given expression, we update the Symbol Table with the new variable value
     * @param state: The current program state, from which we use the Stack and Symbol Table
     * @return Returns the updated program state
     * @throws MyException if the variable-to-be-changed wasn't declared beforehand in the Symbol Table
     * @throws MyException if the variable-to-be-changed doesn't match with the expression-to-be-assigned
     */
    @Override
    public PrgState execute(PrgState state) throws MyException, ADTException {
        MyIStack<IStatement> stack = state.getExeStack();
        MyIDictionary<String, IValue> symTable = state.getSymTable();

        if (symTable.isDefined(id)) {
            IValue val = expression.evaluate(symTable);
            IType typeID = (symTable.lookUp(id)).getType();
            if ((val.getType()).equals(typeID)) {
                symTable.update(id, val);
            }
            else {
                throw new MyException("Declared type of variable " + id +
                        " and type of assigned expression don't match!");
            }
        }
        else {
            throw new MyException("The used variable " + id + " was not declared before!");
        }

        return state;
    }

    /**
     * Performs a deepcopy on AssignStatement
     * @return Returns a new AssignStatement object with the same id and expression
     */
    @Override
    public IStatement deepcopy() {
        return new AssignStatement(id, expression.deepcopy());
    }
}
