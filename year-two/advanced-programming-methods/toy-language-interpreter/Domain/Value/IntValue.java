package Domain.Value;

import Domain.Type.BoolType;
import Domain.Type.IType;
import Domain.Type.IntType;

public class IntValue implements IValue {
    private int value;

    /**
     * Constructor for IntValue with a given integer value
     * @param value = integer
     */
    public IntValue(int value) {
        this.value = value;
    }

    /**
     * Simple toString function for the IntValue
     * @return Returns the integer in string form
     */
    @Override
    public String toString() {
        return String.format("%d", value);
    }

    /**
     * Simple getter for the IType (IntType)
     * @return Returns the IType (IntType) of the IntValue
     */
    @Override
    public IType getType() {
        return new IntType();
    }

    /**
     * Simple getter for the actual value
     * @return Returns the actual value of IntValue
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Allows the equals operation between two IntValues
     * First checks if the Right-hand parameter is an IntValue
     * Second creates a cast value to take the Right-hand parameter
     * @param valueRight = the Object that we want to equate with the current IntValue
     * @return Returns 'false' if valueRight is NOT an IntValue OR if the values are not equal, 'true' otherwise
     */
    @Override
    public boolean equals(Object valueRight) {
        if (!(valueRight instanceof IntValue))
            return false;
        IntValue toCastValue = (IntValue) valueRight;
        return this.value == toCastValue.value;
    }

    /**
     * Performs a deepcopy on the IntValue
     * @return Returns a new IntValue object with the same actual integer value
     */
    @Override
    public IValue deepcopy() {
        return new IntValue(value);
    }
}
