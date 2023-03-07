package Domain.Value;

import Domain.Type.IType;
import Domain.Type.StringType;

import java.util.Objects;

public class StringValue implements IValue {
    private String value;

    /**
     * Constructor for StringValue
     * @param value = the value of the String
     */
    public StringValue(String value) {
        this.value = value;
    }

    /**
     * Stringifies the StringValue
     * @return "value"
     */
    @Override
    public String toString() {
        return "\"" + this.value + "\"";
    }

    /**
     * Getter for the Type
     * @return StringType
     */
    @Override
    public IType getType() {
        return new StringType();
    }

    /**
     * Getter for the value
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * Equalizes string values
     * @param valueRight = other StringValue
     * @return True if equal; False otherwise
     */
    @Override
    public boolean equals(Object valueRight) {
        if (!(valueRight instanceof StringValue toCastValue))
            return false;
        return this.value.equals(toCastValue.value);
    }

    /**
     * Deepcopies StringValue
     * @return Deepcopied string
     */
    @Override
    public IValue deepcopy() {
        return new StringValue(value);
    }
}
