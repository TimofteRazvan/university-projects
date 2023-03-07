package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Value.IValue;

import Domain.Value.IntValue;
import Exception.MyException;
import Exception.InterpreterException;

public class ArithmeticExpression implements IExpression {
    IExpression expressionLeft, expressionRight;
    char operation;

    /**
     * Constructor for ArithmeticExpression
     * @param operation = type of operation to perform between expressionLeft and expressionRight
     * @param expressionLeft = left-hand expression
     * @param expressionRight = right-hand expression
     */
    public ArithmeticExpression(char operation, IExpression expressionLeft, IExpression expressionRight) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
        this.operation = operation;
    }

    /**
     * Checks that left & right expressions are both IntType, returns an IntType
     * @param iTypeEnvironment the type environment
     * @return IntType, should left expression & right expression be IntType
     * @throws InterpreterException Should left expression / right expression not be IntType
     */
    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType t1, t2;
        t1 = expressionLeft.typeCheck(iTypeEnvironment);
        t2 = expressionRight.typeCheck(iTypeEnvironment);
        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new IntType();
            }
            else {
                throw new InterpreterException("Arithmetic: RHV not an integer.\n");
            }
        }
        else {
            throw new InterpreterException("Arithmetic: LHV not an integer.\n");
        }
    }

    /**
     * Simple toString function
     * @return Returns 'expressioleft operation expressioright'
     */
    @Override
    public String toString() {
        return expressionLeft + " " + operation + " " + expressionRight;
    }

    /**
     * Evaluates the ArithmeticExpression
     * 1. Evaluates left-hand expression and gets the value from it
     * 2. Evaluates the right-hand expression and gets the value from it
     * 3. Casts the IntValues valuexpressionLeft/valuexpressionRight to int1/int2
     * 4. Gets the ACTUAL integers from int1 and int2 via getValue()
     * 5. Based on 'operation', does arithmetic operations with the integers and returns the result
     * @param symTable = current symbol table
     * @return Returns an IntValue that is the result of the operation between expressionLeft and expressionRight
     * @throws InterpreterException() If the first or second operand isn't an IntType
     * @throws InterpreterException If the heap throws an exception
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
        IValue valueLeft, valueRight;
        valueLeft = this.expressionLeft.evaluate(symTable, heap);
        if (valueLeft.getType().equals(new IntType())) {
            valueRight = this.expressionRight.evaluate(symTable, heap);
            if (valueRight.getType().equals(new IntType())) {
                IntValue int1 = (IntValue) valueLeft;
                IntValue int2 = (IntValue) valueRight;
                int left, right;
                left = int1.getValue();
                right = int2.getValue();
                if (this.operation == '+')
                    return new IntValue(left + right);
                else if (this.operation == '-')
                    return new IntValue(left - right);
                else if (this.operation == '*')
                    return new IntValue(left * right);
                else if (this.operation == '/')
                    return new IntValue(left / right);
            } else
                throw new InterpreterException("Second operand is not an integer.");
        } else
            throw new InterpreterException("First operand is not an integer.");
        return null;
    }

    /**
     * Performs a deepcopy on ArithmeticExpression
     * @return Returns a new ArithmeticExpression object with the same operation, expressionLeft and expressionRight values
     */
    @Override
    public IExpression deepcopy() {
        return new ArithmeticExpression(operation, expressionLeft.deepcopy(), expressionRight.deepcopy());
    }
}
