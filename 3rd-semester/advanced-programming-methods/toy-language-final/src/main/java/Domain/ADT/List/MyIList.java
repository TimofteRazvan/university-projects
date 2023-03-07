package Domain.ADT.List;

import Exception.InterpreterException;

import java.util.List;

public interface MyIList<T> {
    /**
     * Adds an element to the list
     * @param element = element to be added
     */
    void add(T element);

    /**
     * Pops an element from the list
     * @return Returns the popped element
     * @throws InterpreterException If list is empty
     */
    T pop() throws InterpreterException;

    /**
     * Checks if the list is empty
     * @return Returns 'true' if empty, 'false' otherwise
     */
    boolean isEmpty();

    /**
     * Getter for the list
     * @return Returns the list
     */
    List<T> getList();
}
