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

public class NewStatement implements IStatement {
    private final String variable;
    private final IExpression expression;

    /**
     * Constructor for NewStatement
     * @param variable the variable name for which we want to give new value
     * @param expression expression that will determine the value
     */
    public NewStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    /**
     * Simple toString function
     * @return New(variable_name, expression)
     */
    @Override
    public String toString() {
        return String.format("New(%s, %s)", variable, expression);
    }

    /**
     * Changes the value of the variable on the heap, should it pass all the checks of its existence.
     * @param state = the current program state
     * @return null
     * @throws InterpreterException should any of the ADT operations fail, or should the variable not be defined
     */
    @Override
    public PrgState execute(PrgState state) throws InterpreterException {
        MyIHeap heap = state.getHeap();
        MyIDictionary<String, IValue> symTable = state.getSymTable();
        if (!symTable.isDefined(variable))
            throw new InterpreterException("Not in symTable!");
        IValue value = symTable.lookUp(variable);
        if (!(value.getType() instanceof RefType))
            throw new InterpreterException("Not refType!");
        IValue newValue = expression.evaluate(symTable, heap);
        IType locationType = ((RefValue)value).getLocationType();
        if (!locationType.equals(newValue.getType()))
            throw new InterpreterException("Variable not of specified type!");
        int newPosition = heap.add(newValue);
        symTable.put(variable, new RefValue(newPosition, locationType));
        state.setSymTable(symTable);
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
            throw new InterpreterException("New: RHV type does not match LHV.\n");
        }
    }

    /**
     * Deepcopies the NewStatement
     * @return A deepcopy of NewStatement
     */
    @Override
    public IStatement deepcopy() {
        return new NewStatement(variable, expression.deepcopy());
    }
}
