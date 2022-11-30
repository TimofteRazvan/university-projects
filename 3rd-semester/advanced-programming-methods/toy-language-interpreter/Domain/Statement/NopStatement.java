package Domain.Statement;

import Domain.ProgramState.PrgState;

import Exception.MyException;
import Exception.ADTException;
public class NopStatement implements IStatement {
    public NopStatement() {
    }

    @Override
    public String toString() {
        return "NopStatement{}";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, ADTException {
        return null;
    }

    @Override
    public IStatement deepcopy() {
        return new NopStatement();
    }
}
