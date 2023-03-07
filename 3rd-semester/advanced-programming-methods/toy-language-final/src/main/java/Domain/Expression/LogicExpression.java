package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;

import Exception.ADTException;
import Exception.InterpreterException;

import java.util.Objects;

public class LogicExpression implements IExpression {
    IExpression expressionLeft,expressionRight;
    char operation;

    /**
     * Constructor for LogicExpression
     * @param expressionLeft = left-hand expression
     * @param expressionRight = right-hand expression
     * @param operation = the operation to compare between expressionLeft and expressionRight
     */
    public LogicExpression(IExpression expressionLeft, IExpression expressionRight, char operation) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
        this.operation = operation;
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType t1, t2;
        t1 = expressionLeft.typeCheck(iTypeEnvironment);
        t2 = expressionRight.typeCheck(iTypeEnvironment);
        if (t1.equals(new BoolType())) {
            if (t2.equals(new BoolType())) {
                return new BoolType();
            }
            else {
                throw new InterpreterException("Logic: RHV is not a boolean.\n");
            }
        }
        else {
            throw new InterpreterException("Logic: LHV is not a boolean.\n");
        }
    }

    /**
     * Simple toString function
     * @return Returns 'expression1 operation expression2'
     */
    @Override
    public String toString() {
        return expressionLeft + " " + operation + " " + expressionRight;
    }

    /**
     * Evaluates the LogicExpression:
     * 1. Gets the value from the left-hand expression;
     * 2. Gets the value from the right-hand expression;
     * 3. Casts the values valueLeft/valueRight to bool1/bool2;
     * 4. Gets the ACTUAL boolean values from the BoolValues bool1/bool2;
     * 5. Based on the operation (and/or), returns the truth between the left-hand value and the right-hand value;
     * @param symTable = current symbol table
     * @return Returns a boolean value true/false based on the truth of the operation between expressionLeft and expressionRight
     * @throws InterpreterException If the first or second operand isn't a BoolType
     * @throws ADTException If heap throws an exception
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
        IValue valueLeft, valueRight;
        valueLeft = this.expressionLeft.evaluate(symTable, heap);
        if (valueLeft.getType().equals(new BoolType())) {
            valueRight = this.expressionRight.evaluate(symTable, heap);
            if (valueRight.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) valueLeft;
                BoolValue bool2 = (BoolValue) valueRight;
                boolean left, right;
                left = bool1.getValue();
                right = bool2.getValue();
                if (Objects.equals(this.operation, "and")) {
                    return new BoolValue(left && right);
                } else if (Objects.equals(this.operation, "or")) {
                    return new BoolValue(left || right);
                }
            } else {
                throw new InterpreterException("Second expression not boolean.");
            }
        } else {
            throw new InterpreterException("First expression not boolean.");
        }
        return null;
    }

    /**
     * Performs a deepcopy on LogicExpression
     * @return Returns a new LogicExpression object with the same expressionLeft, expressionRight and operation values
     */
    @Override
    public IExpression deepcopy() {
        return new LogicExpression(expressionLeft, expressionRight, operation);
    }
}
