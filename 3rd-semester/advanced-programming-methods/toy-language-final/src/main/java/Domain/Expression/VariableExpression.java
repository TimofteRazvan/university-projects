package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Value.IValue;

import Exception.ADTException;
import Exception.InterpreterException;

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
     * Checks if the type given to the variable is within the IType environment
     * @param iTypeEnvironment the type environment
     * @return true, if the type exists
     * @throws InterpreterException should lookUp() fail
     */
    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        return iTypeEnvironment.lookUp(key);
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
     * @throws InterpreterException If symTable.lookUp(key) throws an exception
     * @throws ADTException If symTable throws an exception
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
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
