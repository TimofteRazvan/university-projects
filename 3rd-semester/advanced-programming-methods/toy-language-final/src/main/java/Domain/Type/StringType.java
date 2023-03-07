package Domain.Type;

import Domain.Value.IValue;
import Domain.Value.StringValue;

public class StringType implements IType{
    /**
     * Stringifies StringType
     * @return "string"
     */
    @Override
    public String toString() {
        return "string";
    }

    /**
     * Equalizes the StringType with another IType instance
     * @param anotherType = any IType
     * @return True if same type; False otherwise
     */
    @Override
    public boolean equals(IType anotherType) {
        return anotherType instanceof StringType;
    }

    /**
     * Deepcopies the StringType
     * @return The deepcopied type
     */
    @Override
    public IType deepcopy() {
        return new StringType();
    }

    /**
     * Assigns a default value for StringTypes
     * @return A default value StringType ("") empty string
     */
    @Override
    public IValue defaultValue() {
        return new StringValue("");
    }
}
