package Domain.Expression;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;

public interface IExpression {
    /**
     * Function that evaluates the IExpression given based on the current symbol table
     * @param symTable = current symbol table
     * @return Returns an IValue corresponding to the evaluation done (ex. bool value for logic expression)
     * @throws MyException Separate cases for every IExpression
     */
    IValue evaluate(MyIDictionary<String, IValue> symTable) throws MyException, ADTException;

    /**
     * Performs a deepcopy on IExpression
     * @return Returns a new IExpression object with the same values
     */
    IExpression deepcopy();
}
