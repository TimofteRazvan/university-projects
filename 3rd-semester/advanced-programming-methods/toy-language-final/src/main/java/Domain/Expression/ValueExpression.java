package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;
import Exception.InterpreterException;

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
     * Performs no checls, as ValueExpression has no checks needed
     * @param iTypeEnvironment the type environment
     * @return the type of the value given to ValueExpression
     * @throws InterpreterException Never
     */
    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        return value.getType();
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
     * @throws InterpreterException Unused
     * @throws ADTException Unused
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
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
