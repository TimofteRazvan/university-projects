package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.ADT.Table.MyILatchTable;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;
import Domain.Type.IntType;
import Domain.Value.IValue;
import Domain.Value.IntValue;
import Exception.InterpreterException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements IStatement {
    private Lock lock = new ReentrantLock();
    private String variable;
    private IExpression expression;

    public NewLatchStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "newLatch(" + variable + ", " + expression + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        lock.lock();
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        MyILatchTable latchTable = state.getLatchTable();
        IntValue intCast = (IntValue) expression.evaluate(symTable, heap);
        int value = intCast.getValue();
        int freeAddr = latchTable.getFreeAddr();
        latchTable.put(freeAddr, value);
        if (!symTable.isDefined(variable)) {
            throw new InterpreterException("NEW LATCH: Variable not in Symbol Table!\n");
        }
        symTable.update(variable, new IntValue(freeAddr));
        lock.unlock();
        return null;
    }

    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        if (!(iTypeEnvironment.lookUp(variable).equals(new IntType()))) {
            throw new InterpreterException("NEW LATCH: Variable not of IntType!\n");
        }
        if (!(expression.typeCheck(iTypeEnvironment).equals(new IntType()))) {
            throw new InterpreterException("NEW LATCH: Expression not of IntType!\n");
        }
        return iTypeEnvironment;
    }

    @Override
    public IStatement deepcopy() {
        return new NewLatchStatement(variable, expression.deepcopy());
    }
}
