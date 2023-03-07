package Domain.Type;

import Domain.Value.IValue;
import Domain.Value.RefValue;

public class RefType implements IType{
    private final IType inner;

    /**
     * Constructor for RefType
     * @param inner = the type referenced within
     */
    public RefType(IType inner) {
        this.inner = inner;
    }

    /**
     * Stringifies the RefType
     * @return Ref(inner)
     */
    @Override
    public String toString() {
        return String.format("Ref(%s)", inner);
    }

    /**
     * Getter for inner IType
     * @return inner
     */
    public IType getInner() {
        return this.inner;
    }

    /**
     * Equalizes RefType with another instance of IType
     * @param anotherType = any IType
     * @return True if same type; False otherwise
     */
    @Override
    public boolean equals(IType anotherType) {
        if (anotherType instanceof RefType)
            return inner.equals(((RefType) anotherType).getInner());
        else
            return false;
    }

    /**
     * Assigns a default value for RefTypes
     * @return A new RefValue with default address (0)
     */
    @Override
    public IValue defaultValue() {
        return new RefValue(0, inner);
    }

    /**
     * Deepcopies RefType
     * @return Deepcopied type
     */
    @Override
    public IType deepcopy() {
        return new RefType(inner.deepcopy());
    }
}