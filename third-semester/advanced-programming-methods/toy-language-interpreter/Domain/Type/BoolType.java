package Domain.Type;

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
}
