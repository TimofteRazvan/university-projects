package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ProgramState.PrgState;

import Domain.Type.IType;
import Exception.MyException;
import Exception.ADTException;
import Exception.InterpreterException;
import Exception.EvaluateException;

public class NopStatement implements IStatement {
    public NopStatement() {
    }

    @Override
    public String toString() {
        return "NopStatement{}";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        return iTypeEnvironment;
    }

    @Override
    public IStatement deepcopy() {
        return new NopStatement();
    }
}
