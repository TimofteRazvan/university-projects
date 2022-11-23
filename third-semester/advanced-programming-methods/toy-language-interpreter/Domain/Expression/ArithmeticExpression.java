package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Type.IntType;
import Domain.Value.IValue;

import Domain.Value.IntValue;
import Exception.MyException;
import Exception.ADTException;

public class ArithmeticExpression implements IExpression {
    IExpression e1, e2;
    char operation;

    /**
     * Constructor for ArithmeticExpression
     * @param operation = type of operation to perform between e1 and e2
     * @param e1 = left-hand expression
     * @param e2 = right-hand expression
     */
    public ArithmeticExpression(char operation, IExpression e1, IExpression e2) {
        this.e1 = e1;
        this.e2 = e2;
        this.operation = operation;
    }

    /**
     * Simple toString function
     * @return Returns 'expression1 operation expression2'
     */
    @Override
    public String toString() {
        return e1 + " " + operation + " " + e2;
    }

    /**
     * Evaluates the ArithmeticExpression
     * First evaluates left-hand expression and gets the value from it
     * Second evaluates the right-hand expression and gets the value from it
     * Third casts the IntValues value1/value2 to int1/int2
     * Fourth gets the ACTUAL integers from int1 and int2 via getValue()
     * Fifth based on 'operation', does arithmetic operations with the integers and returns the result
     * @param symTable = current symbol table
     * @return Returns an IntValue that is the result of the operation between e1 and e2
     * @throws MyException If the first or second operand isn't an IntType
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable) throws MyException, ADTException {
        IValue value1, value2;
        value1 = this.e1.evaluate(symTable);
        if (value1.getType().equals(new IntType())) {
            value2 = this.e2.evaluate(symTable);
            if (value2.getType().equals(new IntType())) {
                IntValue int1 = (IntValue) value1;
                IntValue int2 = (IntValue) value2;
                int n1, n2;
                n1 = int1.getValue();
                n2 = int2.getValue();
                if (this.operation == '+')
                    return new IntValue(n1 + n2);
                else if (this.operation == '-')
                    return new IntValue(n1 - n2);
                else if (this.operation == '*')
                    return new IntValue(n1 * n2);
                else if (this.operation == '/')
                    return new IntValue(n1 / n2);
            } else
                throw new MyException("Second operand is not an integer.");
        } else
            throw new MyException("First operand is not an integer.");
        return null;
    }

    /**
     * Performs a deepcopy on ArithmeticExpression
     * @return Returns a new ArithmeticExpression object with the same operation, e1 and e2 values
     */
    @Override
    public IExpression deepcopy() {
        return new ArithmeticExpression(operation, e1.deepcopy(), e2.deepcopy());
    }
}
