package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Type.RefType;
import Domain.Value.IValue;

import Domain.Value.RefValue;
import Exception.ADTException;
import Exception.InterpreterException;

public class ReadHeapExpression implements IExpression {
    private final IExpression expression;

    /**
     * Constructor for ReadHeapExpression
     * @param expression The expression to find the pointed-to value of
     */
    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    /**
     * Stringifies the expression
     * @return ReadHeap(expression)
     */
    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }

    /**
     * Checks that the IType returned for ReadHeapExpression is of refType
     * @param iTypeEnvironment The IType environment
     * @return IType of the Inner
     * @throws InterpreterException Should the IType be wrong
     */
    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType type = expression.typeCheck(iTypeEnvironment);
        if (!(type instanceof RefType refType)) {
            throw new InterpreterException("ReadHeap: Value is not Ref Type.\n");
        }
        return refType.getInner();
    }

    /**
     * Evaluates the expression:
     * 1. Evaluates and returns the value of the expression;
     * 2. Checks if it is of RefType;
     * 3. Returns the actual value referenced
     * @param symTable = current symbol table
     * @param heap = the current heap
     * @return The value that is referenced
     * @throws ADTException If heap throws exception
     * @throws InterpreterException If, after evaluation, the expression does not yield a value of RefType
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
        IValue value = expression.evaluate(symTable, heap);
        if (!(value instanceof RefValue refValue)) {
            throw new InterpreterException(String.format("%s not of RefType", value));
        }
        return heap.get(refValue.getAddress());
    }

    /**
     * Deepcopies the ReadHeapExpression
     * @return The deepcopied expression
     */
    @Override
    public IExpression deepcopy() {
        return new ReadHeapExpression(expression.deepcopy());
    }
}
