package Domain.Type;

import Domain.Value.IValue;
import Domain.Value.IntValue;

public class IntType implements IType {

    /**
     * Simple toString function
     * @return Returns "int" for IntType
     */
    @Override
    public String toString() {
        return "int";
    }

    /**
     * Function that equates any two ITypes
     * @param rightType = any IType
     * @return Returns true if rightValue is IntType, false otherwise
     */
    @Override
    public boolean equals(IType rightType) {
        return rightType instanceof IntType;
    }

    /**
     * Performs a deepcopy on IntType
     * @return Returns a new IntType object
     */
    @Override
    public IType deepcopy() {
        return new IntType();
    }

    @Override
    public IValue defaultValue() {
        return new IntValue(0);
    }
}
