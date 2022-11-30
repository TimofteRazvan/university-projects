package Domain.Value;

import Domain.Type.IType;

public interface IValue {
    /**
     * Simple getter for the Type
     * @return Returns the IType of the IValue
     */
    IType getType();

    /**
     * Performs a deepcopy on the IValue
     * @return Returns a copy of the IValue
     */
    IValue deepcopy();
}
