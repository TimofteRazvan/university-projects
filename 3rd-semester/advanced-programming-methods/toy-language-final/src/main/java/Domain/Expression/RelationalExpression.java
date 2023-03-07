package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Exception.InterpreterException;
import Exception.ADTException;

import java.util.Objects;

public class RelationalExpression implements IExpression {
    IExpression expressionLeft;
    IExpression expressionRight;
    String operator;

    /**
     * Constructor for RelationalExpression
     * @param operator The logical operator
     * @param expressionLeft The left-hand expression
     * @param expressionRight The right-hand expression
     */
    public RelationalExpression(String operator, IExpression expressionLeft, IExpression expressionRight) {
        this.operator = operator;
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
    }

    /**
     * Checks that RelationalExpression returns a BoolType from 2 IntType inputs
     * @param iTypeEnvironment the IType environment
     * @return BoolType if true, exception thrown otherwise
     * @throws InterpreterException Should any of the values not be IntType
     */
    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType t1, t2;
        t1 = expressionLeft.typeCheck(iTypeEnvironment);
        t2 = expressionRight.typeCheck(iTypeEnvironment);
        if (t1.equals(new IntType())) {
            if (t2.equals(new IntType())) {
                return new BoolType();
            }
            else {
                throw new InterpreterException("Relational: RHV not an integer.\n");
            }
        }
        else {
            throw new InterpreterException("Relational: LHV not an integer.\n");
        }
    }

    /**
     * Stringifies the RelationalExpression
     * @return expressionLeft + Operator + expressionRight
     */
    @Override
    public String toString() {
        return this.expressionLeft.toString() + " " + this.operator + " " + this.expressionRight.toString();
    }

    /**
     * Evaluates the RelationalExpression:
     * 1. Checks if both expression yield appropriate values of IntType;
     * 2. Casts them to IntValue;
     * 3. Checks the logical operations between them and returns the truth value.
     * @param symTable = current symbol table
     * @param heap = current heap
     * @return True if logical expression is true; False otherwise
     * @throws ADTException If heap or symTable throws exception
     * @throws InterpreterException If values are not IntTypes
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
        IValue valueLeft, valueRight;
        valueLeft = this.expressionLeft.evaluate(symTable, heap);
        if (valueLeft.getType().equals(new IntType())) {
            valueRight = this.expressionRight.evaluate(symTable, heap);
            if (valueRight.getType().equals(new IntType())) {
                IntValue cast1 = (IntValue) valueLeft;
                IntValue cast2 = (IntValue) valueRight;
                int left, right;
                left = cast1.getValue();
                right = cast2.getValue();
                if (Objects.equals(this.operator, "<"))
                    return new BoolValue(left < right);
                else if (Objects.equals(this.operator, "<="))
                    return new BoolValue(left <= right);
                else if (Objects.equals(this.operator, "=="))
                    return new BoolValue(left == right);
                else if (Objects.equals(this.operator, "!="))
                    return new BoolValue(left != right);
                else if (Objects.equals(this.operator, ">"))
                    return new BoolValue(left > right);
                else if (Objects.equals(this.operator, ">="))
                    return new BoolValue(left >= right);
            } else
                throw new InterpreterException("Second operand not int type!");
        } else
            throw new InterpreterException("First operand not int type!");
        return null;
    }

    /**
     * Deepcopies RelationalExpression
     * @return Returns the deepcopied expression
     */
    @Override
    public IExpression deepcopy() {
        return new RelationalExpression(operator, expressionLeft.deepcopy(), expressionRight.deepcopy());
    }
}
