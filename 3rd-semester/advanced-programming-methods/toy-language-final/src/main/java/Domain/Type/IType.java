package Domain.Type;

import Domain.Value.IValue;

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

    /**
     * Function that associates a default value depending on IType
     * @return 0 for integer | "" for String | false for bool | (0, inner) for reference
     */
    IValue defaultValue();
}
