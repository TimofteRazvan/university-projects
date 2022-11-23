package Domain.ProgramState;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.List.MyIList;
import Domain.ADT.Stack.MyIStack;
import Domain.Statement.IStatement;
import Domain.Statement.VarDeclareStatement;
import Domain.Type.IntType;
import Domain.Value.IValue;

import Exception.MyException;
import Exception.ADTException;

import java.util.List;

public class PrgState {
    MyIStack<IStatement> exeStack;
    MyIDictionary<String, IValue> symTable;
    MyIList<IValue> out;
    IStatement originalProgram;

    /**
     * Constructor for PrgState
     * @param exeStack = a stack upon which we will push the statements to execute them
     * @param symTable = a dictionary where we will keep the variable names and their assigned values
     * @param out = a list where we append everything that needs to be printed
     * @param originalProgram = the beginning IStatement, which we deepcopy before pushing it onto the exeStack
     */
    public PrgState(MyIStack<IStatement> exeStack, MyIDictionary<String, IValue> symTable, MyIList<IValue> out, IStatement originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram.deepcopy();
        this.exeStack.push(this.originalProgram);
    }

    /**
     * Simple toString function
     * @return Returns PrgState{exeStack='exestack', symTable='symTable', out='out'}
     */
    @Override
    public String toString() {
        return "PrgState{" +
                "exeStack=" + exeStack.toString() +
                ", symTable=" + symTable.toString() +
                ", out=" + out.toString() +
                '}';
    }

    /**
     * Comprehensive toString function for the exeStack
     * Uses StringBuilder to keep a builder where we put all the statements as strings and ORDERED BY PRIORITY
     * @return Returns the completed string builder
     */
    public String exeStackToString() {
        StringBuilder exeStackStringBuilder = new StringBuilder();
        List<IStatement> stack = exeStack.getStatementList();
        for (IStatement statement: stack) {
            exeStackStringBuilder.append(statement.toString());
            exeStackStringBuilder.append("\n");
        }

        return exeStackStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the symTable
     * Uses StringBuilder to keep a builder where we put all the keys and their values
     * Looks through a for through the set of keys in the symTable, and for each key it inputs its value
     * @return Returns the completed string builder
     * @throws ADTException If symTable.lookUp(key) does not find a value
     */
    public String symTableToString() throws ADTException {
        StringBuilder symTableStringBuilder = new StringBuilder();
        for (String key: symTable.keySet()) {
            symTableStringBuilder.append(String.format("%s -> %s\n", key, symTable.lookUp(key).toString()));
        }
        return symTableStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the out
     * Uses StringBuilder to keep a builder where we put all the prints
     * Looks through a for through the 'out' list and appends every line
     * @return Returns the completed string builder
     */
    public String outToString() {
        StringBuilder outStringBuilder = new StringBuilder();
        for (IValue elem: out.getList()) {
            outStringBuilder.append(String.format("%s\n", elem.toString()));
        }
        return outStringBuilder.toString();
    }

    /**
     * Comprehensive toString function for the PrgState
     * @return Returns the combination of exeStackToString, symTableToString and outToString
     * @throws ADTException If symTableToString() throws an exception
     */
    public String fullToString() throws ADTException {
        return "Execution stack: \n" + exeStackToString() + "Symbol table: \n" +
                symTableToString() + "Output list: \n" + outToString();
    }

    /**
     * Simple getter for exeStack
     * @return Returns the exeStack
     */
    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    /**
     * Simple setter for exeStack
     * @param exeStack = new exeStack
     */
    public void setExeStack(MyIStack<IStatement> exeStack) {
        this.exeStack = exeStack;
    }

    /**
     * Simple getter for symTable
     * @return Returns the symTable
     */
    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }

    /**
     * Simple setter for symTable
     * @param symTable = new symTable
     */
    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    /**
     * Simple getter for out
     * @return Returns the out
     */
    public MyIList<IValue> getOut() {
        return out;
    }

    /**
     * Simple setter for out
     * @param out = new out
     */
    public void setOut(MyIList<IValue> out) {
        this.out = out;
    }
}
