package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;
import Exception.InterpreterException;

public interface IStatement {
    /**
     * Function that executes the IStatement based on what it is meant to do & the current program state
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws InterpreterException If execute is falsely called
     */
    PrgState execute(PrgState state) throws InterpreterException;

    /**
     * Checks if the types of the inputs are appropriate to the wanted operation, returns a likewise appropriate output
     * @param iTypeEnvironment the type environment
     * @return The type environment, changed only for VarDeclareStatement
     * @throws InterpreterException Should any input not be appropriate to the statement
     */
    MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException;

    /**
     * Performs a deepcopy on the IStatement
     * @return Returns a new IStatement object with the same values
     */
    IStatement deepcopy();
}