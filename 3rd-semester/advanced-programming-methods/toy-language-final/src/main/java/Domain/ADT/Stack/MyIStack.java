package Domain.ADT.Stack;

import java.util.List;
import java.util.Stack;
import Exception.InterpreterException;

public interface MyIStack<T> {
    /**
     * Pushes element at the top of the stack
     * @param element = any element of type T
     */
    void push(T element);

    /**
     * Pops the top-most element
     * @return Returns a T-type element from the top
     * @throws InterpreterException If there is no element at the top
     */
    T pop() throws InterpreterException;

    /**
     * Allows peeking to the top of the stack, returning the first value of the stack
     * @return Returns the first value accessible within the stack
     */
    T peek();

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
