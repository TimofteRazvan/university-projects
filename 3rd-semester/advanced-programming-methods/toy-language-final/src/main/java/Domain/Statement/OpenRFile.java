package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Domain.Type.StringType;
import Domain.Value.IValue;
import Domain.Value.StringValue;
import Exception.EvaluateException;
import Exception.ADTException;
import Exception.InterpreterException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFile implements IStatement {
    public IExpression expression;

    /**
     * Constructor for OpenRFile
     * @param expression the expression that should yield fileName
     */
    public OpenRFile(IExpression expression) {
        this.expression = expression;
    }

    /**
     * Simple toString function
     * @return OpenReadFile(expression)
     */
    @Override
    public String toString() {
        return String.format("OpenReadFile(%s)", expression.toString());
    }

    /**
     * Gets the fileName from the expression. Checks if its already opened in the fileTable. Tries to open and creates
     * a new BufferedReader for it. Puts its name inside the fileTable and sets the new fileTable.
     * @param state = the current program state
     * @return null
     * @throws InterpreterException Should the file not be found | file be already opened | expression not return String
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
        if (value.getType().equals(new StringType())) {
            StringValue fileName = (StringValue) value;
            MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
            if (!fileTable.isDefined(fileName.getValue())) {
                BufferedReader bufferedReader;
                try {
                    bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
                } catch (FileNotFoundException e) {
                    throw new InterpreterException("File not found!");
                }
                fileTable.put(fileName.getValue(), bufferedReader);
                state.setFileTable(fileTable);
            } else {
                throw new InterpreterException("File is already opened!");
            }
        } else {
            throw new InterpreterException("Expression not StringType!");
        }
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
        IType typeExpression = expression.typeCheck(iTypeEnvironment);
        if (typeExpression.equals(new StringType())) {
            return iTypeEnvironment;
        }
        else {
            throw new InterpreterException("OpenRFile: Not a string expression.\n");
        }
    }

    /**
     * Deepcopies Statement OpenRFile
     * @return A deepcopy of the Statement OpenRFile
     */
    @Override
    public IStatement deepcopy() {
        return new OpenRFile(expression.deepcopy());
    }
}
