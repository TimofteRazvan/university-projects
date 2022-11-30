package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Domain.Value.BoolValue;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Exception.MyException;
import Exception.ADTException;
import java.util.Objects;

public class VarDeclareStatement implements IStatement {
    String name;
    IType type;

    /**
     * Constructor for VarDeclareStatement
     * @param name = the name of the new variable
     * @param type = the type of the new variable
     */
    public VarDeclareStatement(String name, IType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Simple toString function
     * @return Returns 'type name'
     */
    @Override
    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }

    /**
     * Executes the VarDeclareStatement
     * First gets the symTable from the current program state
     * Second checks if the variable has already been declared, throws exception if so
     * Third checks the IType and assigns the IValue as the default for the IType
     * Fourth updates the symTable, therefore updating the program state
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws MyException If the variable name is already declared
     */
    @Override
    public PrgState execute(PrgState state) throws MyException, ADTException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (symTable.isDefined(name)) {
            throw new MyException("Variable " + name + " already exists in the symTable.");
        }
        if (Objects.equals(type.toString(), "int")) {
            symTable.put(name, new IntValue(0));
        }
        else {
            symTable.put(name, new BoolValue(false));
        }
        state.setSymTable(symTable);
        return state;
    }

    /**
     * Performs a deepcopy on VarDeclareStatement
     * @return Returns a new VarDeclareStatement object with the same name and type values
     */
    @Override
    public IStatement deepcopy() {
        return new VarDeclareStatement(name, type);
    }
}
