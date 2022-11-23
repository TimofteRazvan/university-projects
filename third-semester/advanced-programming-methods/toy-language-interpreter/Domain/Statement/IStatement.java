package Domain.Statement;

import Domain.ProgramState.PrgState;
import Exception.MyException;
import Exception.ADTException;

public interface IStatement {
    /**
     * Function that executes the IStatement based on what it is meant to do & the current program state
     * @param state = the current program state
     * @return Returns the updated program state
     * @throws MyException If execute is falsely called
     */
    PrgState execute(PrgState state) throws MyException, ADTException;

    /**
     * Performs a deepcopy on the IStatement
     * @return Returns a new IStatement object with the same values
     */
    IStatement deepcopy();
}
