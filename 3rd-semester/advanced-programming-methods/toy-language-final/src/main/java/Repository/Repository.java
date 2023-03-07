package Repository;

import Domain.ProgramState.PrgState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import Exception.InterpreterException;

public class Repository implements IRepository {
    private List<PrgState> prgStateList;
    private String filePath;

    /**
     * Constructor for Repository
     * @param programState = the program state
     * @param filePath = the log file where we will output
     */
    public Repository(PrgState programState, String filePath) throws IOException {
        this.filePath = filePath;
        this.prgStateList = new ArrayList<>();
        this.addProgram(programState);
        this.emptyLogFile();
    }

    /**
     * Getter for the list of program states
     * @return Returns the list of program states
     */
    @Override
    public List<PrgState> getProgramList() {
        return this.prgStateList;
    }

    /**
     * Setter for the list of program states
     * @param prgStateList = list of program states to replace the old one
     */
    @Override
    public void setProgramList(List<PrgState> prgStateList) {
        this.prgStateList = prgStateList;
    }

    /**
     * Adds a program state to the list of program states
     * @param program = program state to be added
     */
    @Override
    public void addProgram(PrgState program) {
        this.prgStateList.add(program);
    }

    /**
     * Prints the program state in a log file
     * @throws InterpreterException If fullToString() throws exception
     * @throws IOException If any of the Writers throw an exception
     */
    @Override
    public void logPrgStateExec(PrgState prgState) throws IOException, InterpreterException {
        PrintWriter file;
        file = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));
        file.println(prgState.fullToString());
        file.close();
    }

    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter file;
        file = new PrintWriter(new BufferedWriter(new FileWriter(filePath, false)));
        file.close();
    }
}
