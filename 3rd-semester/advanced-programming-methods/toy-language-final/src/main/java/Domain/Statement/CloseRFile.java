package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Domain.Type.StringType;
import Domain.Value.IValue;
import Domain.Value.StringValue;
import Exception.ADTException;
import Exception.InterpreterException;
import Exception.EvaluateException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private IExpression expression;

    /**
     * Constructor for CloseReadFile Class
     * @param expression What to close
     */
    public CloseRFile(IExpression expression) {
        this.expression = expression;
    }

    /**
     * Stringifies for the stringBuilder and eventual output into log file
     * @return CloseReadFile(expression)
     */
    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", expression.toString());
    }

    /**
     * Executes the Statement;
     * 1. Checks if the expression given, after evaluation, yields a TypeString (file name);
     * 2. Casts to StringValue, checks if it is defined within the fileTable;
     * 3. Gets the associated file should it be within the fileTable, and closes it, then removes it from fileTable.
     * @param state = the current program state
     * @return Null
     * @throws EvaluateException If 'evaluate' throws an exception
     * @throws ADTException If fileTable throws an exception
     * @throws InterpreterException If the expression yields a non-StringType, or if it is not present within the fileTable
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType()))
            throw new InterpreterException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new InterpreterException(String.format("%s is not present in the FileTable", value));
        BufferedReader bufferedReader = fileTable.lookUp(fileName.getValue());
        try {
            bufferedReader.close();
        } catch (IOException exception) {
            throw new InterpreterException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getValue());
        state.setFileTable(fileTable);
        return null;
    }

    /**
     * Checks if the types of the inputs are appropriate to the wanted operation, returns a likewise appropriate output
     * @param iTypeEnvironment the type environment
     * @return The type environment, changed only for VarDeclareStatement
     * @throws InterpreterException Should any input not be appropriate to the statement
     */
    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        if (expression.typeCheck(iTypeEnvironment).equals(new StringType()))
            return iTypeEnvironment;
        else
            throw new InterpreterException("CloseReadFile requires a string expression.");
    }

    /**
     * Deepcopies the statemenet
     * @return The deepcopied statement
     */
    @Override
    public IStatement deepcopy() {
        return new CloseRFile(expression.deepcopy());
    }
}
