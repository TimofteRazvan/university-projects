package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;

public class VariableExpression implements IExpression {
    String key;

    /**
     * Constructor for VariableExpression
     * @param key = key/id to look for in the symbol table
     */
    public VariableExpression(String key) {
        this.key = key;
    }

    /**
     * Simple toString function
     * @return Returns 'key' string
     */
    @Override
    public String toString() {
        return key;
    }

    /**
     * Evaluates the VariableExpression
     * @param symTable = current symbol table
     * @return Returns the value found in SymTable according to 'key'
     * @throws MyException If symTable.lookUp(key) throws an exception
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable) throws MyException, ADTException {
        return symTable.lookUp(key);
    }

    /**
     * Performs a deepcopy on VariableExpression
     * @return Returns a new VariableExpression object with the same key value
     */
    @Override
    public IExpression deepcopy() {
        return new VariableExpression(key);
    }
}
