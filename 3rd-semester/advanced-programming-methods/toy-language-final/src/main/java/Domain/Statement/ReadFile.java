package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Type.StringType;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Domain.Value.StringValue;
import Exception.InterpreterException;
import Exception.EvaluateException;
import Exception.ADTException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression expression;
    private String variable;

    /**
     * Constructor for ReadFile Statement
     * @param expression the expression that should yield the file name
     * @param variable the variable that will be read into
     */
    public ReadFile(IExpression expression, String variable) {
        this.expression = expression;
        this.variable = variable;
    }

    /**
     * Simple toString function
     * @return Readfile(expression, variable)
     */
    @Override
    public String toString() {
        return String.format("ReadFile(%s, %s)", expression.toString(), variable);
    }

    /**
     * Executes the ReadFile Statement. Checks if variable within symTable. Checks if expression yields fileName that
     * exists within fileTable. Reads from fileName into the variable.
     * @param state = the current program state
     * @return
     * @throws InterpreterException
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (symTable.isDefined(variable)) {
            IValue value = symTable.lookUp(variable);
            if (value.getType().equals(new IntType())) {
                value = expression.evaluate(symTable, state.getHeap());
                if (value.getType().equals(new StringType())) {
                    StringValue castValue = (StringValue) value;
                    if (fileTable.isDefined(castValue.getValue())) {
                        BufferedReader bufferedReader = fileTable.lookUp(castValue.getValue());
                        try {
                            String line = bufferedReader.readLine();
                            if (line == null) {
                                line = "0";
                            }
                            symTable.put(variable, new IntValue(Integer.parseInt(line)));
                        } catch (IOException exception) {
                            throw new InterpreterException(String.format("Wrong file from %s!", castValue));
                        }
                    } else {
                        throw new InterpreterException(String.format("symTable doesn't contain %s!", castValue));
                    }
                } else {
                    throw new InterpreterException("Does not evaluate to StringType!");
                }
            } else {
                throw new InterpreterException("Value not of type IntType!");
            }
        } else {
            throw new InterpreterException("Variable not in symTable!");
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
        IType typeVariable = iTypeEnvironment.lookUp(variable);
        if (typeExpression.equals(new StringType())) {
            if (typeVariable.equals(new IntType())) {
                return iTypeEnvironment;
            }
            else {
                throw new InterpreterException("ReadFile: Variable must be integer!\n");
            }
        }
        else {
            throw new InterpreterException("ReadFile: Expression must be string!\n");
        }
    }

    /**
     * Deepcopies ReadFile Statement
     * @return A deepcopy of ReadFile Statement
     */
    @Override
    public IStatement deepcopy() {
        return new ReadFile(expression.deepcopy(), variable);
    }
}
