package Repository;

import Domain.ProgramState.PrgState;

import Exception.ADTException;

import java.io.IOException;
import java.util.List;

public interface IRepository {
    /**
     * Getter for the program states
     * @return Returns a list of program states
     */
    List<PrgState> getProgramList();

    /**
     * Setter for the program states list
     * @param programStateList = list of program states to replace the old one
     */
    void setProgramList(List<PrgState> programStateList);

    /**
     * Getter for the current program state
     * @return Returns the current program state
     */
    PrgState getCurrentState();

    /**
     * Adds a program state to the list of program states
     * @param program = program state to be added
     */
    void addProgram(PrgState program);

    /**
     * Prints the program state in a log file
     * @throws ADTException If fullToString() throws an exception
     * @throws IOException If any of the writers throw an exception
     */
    void logPrgStateExec() throws ADTException, IOException;
}
