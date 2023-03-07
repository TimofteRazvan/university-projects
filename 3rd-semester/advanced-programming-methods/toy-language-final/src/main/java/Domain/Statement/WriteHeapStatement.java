package Domain.Statement;

import Domain.ADT.Dictionary.MyIDictionary;
import Domain.ADT.Heap.MyIHeap;
import Domain.Expression.IExpression;
import Domain.ProgramState.PrgState;
import Domain.Type.IType;
import Domain.Type.RefType;
import Domain.Value.IValue;
import Domain.Value.RefValue;
import Exception.*;

public class WriteHeapStatement implements IStatement {
    private final String variable;
    private final IExpression expression;

    /**
     * Constructor for WriteHeapStatement
     * @param variable the name of the variable that will be updated within the heap
     * @param expression The expression with which to update the variable within the heap
     */
    public WriteHeapStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    /**
     * Simple toString function
     * @return WriteHeap(variable_name, expression)
     */
    @Override
    public String toString() {
        return String.format("WriteHeap(%s, %s)", variable, expression);
    }

    /**
     * Checks if Variable within symTable. Checks if value of it is RefValue. Checks if Heap contains its address.
     * Updates it with the evaluated expression.
     * @param state = the current program state
     * @return null
     * @throws InterpreterException Should any ADT operation fail | Any of the types not be correct | Any of the values not be in tables
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (!symTable.isDefined(variable))
            throw new InterpreterException(variable + " not present in the symTable");
        IValue value = symTable.lookUp(variable);
        if (!(value instanceof RefValue refValue))
            throw new InterpreterException(value + " not of RefType");
        if (!heap.containsKey(refValue.getAddress())) {
            throw new InterpreterException(String.format("Ref Value %s not defined in heap.", value));
        }
        IValue newValue = expression.evaluate(symTable, heap);
        if (!newValue.getType().equals(refValue.getLocationType()))
            throw new InterpreterException(String.format("%s not of %s", newValue, refValue.getLocationType()));
        heap.update(refValue.getAddress(), newValue);
        state.setHeap(heap);
        return null;
    }

    /**
     * Checks if the types of the inputs are appropriate to the wanted operation, returns a likewise appropriate output
     * @param iTypeEnvironment the type environment
     * @return The type environment, changed only for VarDeclareStatement
     * @throws InterpreterException Should any input not be appropriate to the statement
     */
    @Override
    public MyIDictionary<String, IType> typeCheck(MyIDictionary<String, IType> iTypeEnvironment) throws InterpreterException {
        IType typeVariable = iTypeEnvironment.lookUp(variable);
        IType typeExpression = expression.typeCheck(iTypeEnvironment);
        if (typeVariable.equals(new RefType(typeExpression))) {
            return iTypeEnvironment;
        }
        else {
            throw new InterpreterException("WriteHeap: RHV and LHV types do not match.\n");
        }
    }

    /**
     * Creates a deepcopy of the WriteHeapStatement
     * @return A deepcopy of WriteHeapStatement
     */
    @Override
    public IStatement deepcopy() {
        return new WriteHeapStatement(variable, expression.deepcopy());
    }
}
