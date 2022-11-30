package Domain.Type;

public interface IType {
    /**
     * Function that allows equating two ITypes
     * @param rightType = any IType
     * @return Returns the truth value between their equation
     */
    boolean equals(IType rightType);

    /**
     * Performs a deepcopy on the IType
     * @return Returns a new IType object of the same IType
     */
    IType deepcopy();
}
