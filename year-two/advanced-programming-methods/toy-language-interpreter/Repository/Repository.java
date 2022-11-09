package Repository;

import Domain.ProgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Exception.ADTException;

public class Repository implements IRepository {
    private List<PrgState> programStateList;
    private int currentPosition;
    private String logFilePath;

    /**
     * Constructor for Repository
     * @param programState = the program state
     * @param logFilePath = the log file where we will output
     */
    public Repository(PrgState programState, String logFilePath){
        this.logFilePath = logFilePath;
        this.programStateList = new ArrayList<>();
        this.currentPosition = 0;
        this.addProgram(programState);
    }

    /**
     * Getter for the list of program states
     * @return Returns the list of program states
     */
    @Override
    public List<PrgState> getProgramList() {
        return this.programStateList;
    }

    /**
     * Setter for the list of program states
     * @param programStateList = list of program states to replace the old one
     */
    @Override
    public void setProgramList(List<PrgState> programStateList) {
        this.programStateList = programStateList;
    }

    /**
     * Getter for the current position
     * @return Returns the current position in the list of program states
     */
    public int getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Setter for the current position
     * @param currentPosition = the new position in the list of program states
     */
    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Getter for the current program state
     * @return Returns the current program state at currentPosition
     */
    @Override
    public PrgState getCurrentState() {
        return this.programStateList.get(this.currentPosition);
    }

    /**
     * Adds a program state to the list of program states
     * @param program = program state to be added
     */
    @Override
    public void addProgram(PrgState program) {
        this.programStateList.add(program);
    }

    /**
     * Prints the program state in a log file
     * @throws ADTException If fullToString() throws exception
     * @throws IOException If any of the Writers throw an exception
     */
    @Override
    public void logPrgStateExec() throws ADTException, IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(this.programStateList.get(0).fullToString());
        logFile.close();
    }
}
