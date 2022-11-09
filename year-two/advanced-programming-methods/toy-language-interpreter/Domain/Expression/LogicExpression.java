package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Type.BoolType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;
import java.util.Objects;

public class LogicExpression implements IExpression {
    IExpression e1,e2;
    char operation;

    /**
     * Constructor for LogicExpression
     * @param e1 = left-hand expression
     * @param e2 = right-hand expression
     * @param operation = the operation to compare between e1 and e2
     */
    public LogicExpression(IExpression e1, IExpression e2, char operation) {
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
     * Evaluates the LogicExpression
     * First gets the value from the left-hand expression
     * Second gets the value from the right-hand expression
     * Third casts the values value1/value2 to bool1/bool2
     * Fourth gets the ACTUAL boolean values from the BoolValues bool1/bool2
     * Fifth based on the operation (and/or), returns the truth between the left-hand value and the right-hand value
     * @param symTable = current symbol table
     * @return Returns a boolean value true/false based on the truth of the operation between e1 and e2
     * @throws MyException If the first or second operand isn't a BoolType
     */
    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable) throws MyException, ADTException {
        IValue value1, value2;
        value1 = this.e1.evaluate(symTable);
        if (value1.getType().equals(new BoolType())) {
            value2 = this.e2.evaluate(symTable);
            if (value2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;
                boolean b1, b2;
                b1 = bool1.getValue();
                b2 = bool2.getValue();
                if (Objects.equals(this.operation, "and")) {
                    return new BoolValue(b1 && b2);
                } else if (Objects.equals(this.operation, "or")) {
                    return new BoolValue(b1 || b2);
                }
            } else {
                throw new MyException("Second expression not boolean.");
            }
        } else {
            throw new MyException("First expression not boolean.");
        }
        return null;
    }

    /**
     * Performs a deepcopy on LogicExpression
     * @return Returns a new LogicExpression object with the same e1, e2 and operation values
     */
    @Override
    public IExpression deepcopy() {
        return new LogicExpression(e1, e2, operation);
    }
}
