package Domain.ADT.Stack;

import java.util.List;
import java.util.Stack;
import Exception.ADTException;

public interface MyIStack<T> {
    /**
     * Pushes element at the top of the stack
     * @param element = any element of type T
     */
    void push(T element);

    /**
     * Pops the top-most element
     * @return Returns a T-type element from the top
     * @throws ADTException If there is no element at the top
     */
    T pop() throws ADTException;

    /**
     * Checks if the stack is empty
     * @return Returns 'true' if empty, 'false' otherwise
     */
    boolean isEmpty();

    /**
     * Getter for the stack
     * @return Returns the whole stack
     */
    Stack<T> getStack();

    /**
     * Getter for the stack in list form & reversed for readability
     * @return Returns a list with all the stack's elements, reversed
     */
    List<T> getStatementList();
}
