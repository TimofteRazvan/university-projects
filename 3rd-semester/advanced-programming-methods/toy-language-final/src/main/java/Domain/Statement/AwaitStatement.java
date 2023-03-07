package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.ADT.Table.MyILatchTable;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Exception.InterpreterException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitStatement implements IStatement {
    private Lock lock = new ReentrantLock();
    private String variable;

    public AwaitStatement(String variable) {
        this.variable = variable;
    }

    @Override
    public String toString() {
        return "await(" + variable + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        lock.lock();
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyILatchTable latchTable = state.getLatchTable();
        if (!symTable.isDefined(variable)) {
            throw new InterpreterException("AWAIT: Variable not in Symbol Table!\n");
        }
        IntValue castInt = (IntValue) symTable.lookUp(variable);
        int foundIndex = castInt.getValue();
        if (!latchTable.contains(foundIndex)) {
            throw new InterpreterException("AWAIT: Found Index not in Latch Table!\n");
        }
        if (latchTable.get(foundIndex) != 0) {
            state.getExeStack().push(this);
        }
        lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        if (!(iTypeEnvironment.lookUp(variable).equals(new IntType()))) {
            throw new InterpreterException("AWAIT: Variable not of IntType!\n");
        }
        return iTypeEnvironment;
    }

    @Override
    public IStatement deepcopy() {
        return new AwaitStatement(variable);
    }
}
