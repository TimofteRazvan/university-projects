package Domain.Value;

import Domain.Type.IType;
import Domain.Type.RefType;

public class RefValue implements IValue{
    private final int address;
    private final IType locationType;

    /**
     * Constructor for RefValue
     * @param address = address of the referred-to value
     * @param locationType = the type of the referred-to value
     */
    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    /**
     * Stringifies RefValue
     * @return (address, locationType)
     */
    @Override
    public String toString() {
        return String.format("(%d, %s)", address, locationType);
    }

    /**
     * Getter for address
     * @return address
     */
    public int getAddress() {
        return address;
    }

    /**
     * Getter for type
     * @return RefType(locationType)
     */
    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    /**
     * Getter for locationType
     * @return locationType
     */
    public IType getLocationType() {
        return locationType;
    }

    /**
     * Deepcopies RefValue
     * @return Deepcopied value
     */
    @Override
    public IValue deepcopy() {
        return new RefValue(address, locationType.deepcopy());
    }
}
