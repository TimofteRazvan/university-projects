package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;
public class ValueExpression implements IExpression {
    IValue value;

    /**
     * Constructor for ValueExpression
     * @param value = the value of the expression
     */
    public ValueExpression(IValue value) {
        this.value = value;
    }

    /**
     * Simple toString function
     * @return Returns 'value' in string form
     */
    @Override
    public String toString() {
        return this.value.toString();
    }

    /**
     * Evaluates the ValueExpression
     * @param symTable = current symbol table
     * @return Returns 'value', whatever it may be
     * @throws MyException Unused
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable) throws MyException, ADTException {
        return this.value;
    }

    /**
     * Performs a deepcopy on ValueExpression
     * @return Returns a new ValueExpression object with the same value
     */
    @Override
    public IExpression deepcopy() {
        return new ValueExpression(value);
    }
}
