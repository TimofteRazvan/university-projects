package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;
import Domain.Value.IValue;

import Exception.EvaluateException;
import Exception.ADTException;
import Exception.InterpreterException;

import java.util.Objects;

public class VarDeclareStatement implements IStatement {
    String variable;
    IType type;

    /**
     * Constructor for VarDeclareStatement
     * @param variable = the variable of the new variable
     * @param type = the type of the new variable
     */
    public VarDeclareStatement(String variable, IType type) {
        this.variable = variable;
        this.type = type;
    }

    /**
     * Simple toString function
     * @return Returns 'type variable'
     */
    @Override
    public String toString() {
        return String.format("%s %s", type.toString(), variable);
    }

    /**
     * Executes the VarDeclareStatement
     * First gets the symTable from the current program state
     * Second checks if the variable has already been declared, throws exception if so
     * Third checks the IType and assigns the IValue as the default for the IType
     * Fourth updates the symTable, therefore updating the program state
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws InterpreterException If the variable variable is already declared
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (symTable.isDefined(variable)) {
            throw new InterpreterException("Variable " + variable + " already exists in the symTable.");
        }
        // Changed with defaultValue method here
        symTable.put(variable, type.defaultValue());
        state.setSymTable(symTable);
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
        iTypeEnvironment.put(variable, type);
        return iTypeEnvironment;
    }

    /**
     * Performs a deepcopy on VarDeclareStatement
     * @return Returns a new VarDeclareStatement object with the same variable and type values
     */
    @Override
    public IStatement deepcopy() {
        return new VarDeclareStatement(variable, type);
    }
}
