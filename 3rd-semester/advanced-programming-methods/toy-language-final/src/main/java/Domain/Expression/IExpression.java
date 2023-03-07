package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.InterpreterException;

public interface IExpression {
    /**
     * Function that evaluates the IExpression given based on the current symbol table
     * @param symTable = current symbol table
     * @return Returns an IValue corresponding to the evaluation done (ex. bool value for logic expression)
     * @throws InterpreterException Separate cases for every IExpression
     */
    IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException;

    /**
     * Checks the types of the inputs and returns the appropriate type of the output
     * @param iTypeEnvironment the type environment
     * @return The appropriate type based on the expression
     * @throws InterpreterException Should either of the inputs not be of appropriate type to the expression
     */
    IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException;

    /**
     * Performs a deepcopy on IExpression
     * @return Returns a new IExpression object with the same values
     */
    IExpression deepcopy();
}

/*
//MUL EXPRESSION

package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Value.IValue;
import Exception.InterpreterException;

public class MULExpression implements IExpression {
    private IExpression expressionLeft;
    private IExpression expressionRight;

    public MULExpression(IExpression expressionLeft, IExpression expressionRight) {
        this.expressionLeft = expressionLeft;
        this.expressionRight = expressionRight;
    }

    @Override
    public String toString() {
        return "MULExpression{" + expressionLeft + "," + expressionRight + '}';
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
        IExpression mul = new ArithmeticExpression('-',
                new ArithmeticExpression('*', expressionLeft, expressionRight),
                new ArithmeticExpression('+', expressionLeft, expressionRight));
        return mul.evaluate(symTable, heap);

    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType typeLeft = expressionLeft.typeCheck(iTypeEnvironment);
        IType typeRight = expressionRight.typeCheck(iTypeEnvironment);
        if (!(typeLeft.equals(new IntType()) && typeRight.equals(new IntType()))) {
            throw new InterpreterException("MUL EXCEPTION: LHV or RHV not of Type IntType\n");
        }
        return new IntType();
    }

    @Override
    public IExpression deepcopy() {
        return new MULExpression(expressionLeft.deepcopy(), expressionRight.deepcopy());
    }
}
 */

/*
// NOT EXPRESSION (FOR REPEAT UNTIL)

package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Type.IType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Exception.InterpreterException;

public class NotExpression implements IExpression {
    private IExpression expression;

    public NotExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "!" + expression;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap heap) throws InterpreterException {
        BoolValue castBool = (BoolValue) expression.evaluate(symTable, heap);
        if (!castBool.getValue()) {
            return new BoolValue(true);
        }
        return new BoolValue(false);
    }

    @Override
    public IType typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        return expression.typeCheck(iTypeEnvironment);
    }

    @Override
    public IExpression deepcopy() {
        return new NotExpression(expression.deepcopy());
    }
}
 */