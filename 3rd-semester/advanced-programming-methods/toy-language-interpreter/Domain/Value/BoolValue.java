package Domain.Value;

import Domain.Type.BoolType;
import Domain.Type.IType;

public class BoolValue implements IValue {
    private boolean value;

    /**
     * Constructor for BoolValue with a given boolean value
     * @param value = boolean true/false
     */
    public BoolValue(boolean value) {
        this.value = value;
    }

    /**
     * Simple toString function for the BoolValue
     * @return Returns "true" string if the boolean is true, or "false" otherwise
     */
    @Override
    public String toString() {
        if (this.value) {
            return "true";
        }
        return "false";
    }

    /**
     * Simple getter for the IType (BoolType)
     * @return Returns the IType (BoolType) of the BoolValue
     */
    @Override
    public IType getType() {
        return new BoolType();
    }

    /**
     * Simple getter for the actual value
     * @return Returns the actual value of BoolValue
     */
    public boolean getValue() {
        return this.value;
    }

    /**
     * Allows the equals operation between two BoolValues
     * First checks if the Right-hand parameter is a BoolValue
     * Second creates a cast value to take the Right-hand parameter
     * @param valueRight = the Object that we want to equate with the current BoolValue
     * @return Returns 'false' if valueRight is NOT a BoolValue OR if the values are not equal, 'true' otherwise
     */
    @Override
    public boolean equals(Object valueRight) {
        if (!(valueRight instanceof BoolValue))
            return false;
        BoolValue toCastValue = (BoolValue) valueRight;
        return this.value == toCastValue.value;
    }

    /**
     * Performs a deepcopy on the BoolValue
     * @return Returns a new BoolValue object with the same actual boolean value
     */
    @Override
    public IValue deepcopy() {
        return new BoolValue(value);
    }
}
