package Domain.Type;

import Domain.Value.BoolValue;
import Domain.Value.IValue;

public class BoolType implements IType {

    /**
     * Simple toString function
     * @return Returns "boolean" for BoolType
     */
    @Override
    public String toString() {
        return "boolean";
    }

    /**
     * Function that equates any two ITypes
     * @param rightType = any IType
     * @return Returns true if rightValue is BoolType, false otherwise
     */
    @Override
    public boolean equals(IType rightType) {
        return rightType instanceof BoolType;
    }

    /**
     * Performs a deepcopy on BoolType
     * @return Returns a new BoolType object
     */
    @Override
    public IType deepcopy() {
        return new BoolType();
    }

    @Override
    public IValue defaultValue() {
        return new BoolValue(false);
    }
}
