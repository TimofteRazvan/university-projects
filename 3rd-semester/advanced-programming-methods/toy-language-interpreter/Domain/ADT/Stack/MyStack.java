package Domain.ADT.Stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import Exception.ADTException;

public class MyStack<T> implements MyIStack<T> {
    Stack<T> stack;

    /**
     * Constructor for MyStack
     */
    public MyStack() {
        this.stack = new Stack<>();
    }

    /**
     * Simple toString function
     * @return Returns stack.toString()
     */
    @Override
    public String toString() {
        return this.stack.toString();
    }

    /**
     * Pushes the element to the top of MyStack
     * @param element = any element of type T
     */
    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    /**
     * Pops an element from the top of MyStack
     * @return Returns the popped element
     * @throws ADTException If stack is empty
     */
    @Override
    public T pop() throws ADTException {
        if (this.stack.isEmpty()) {
            throw new ADTException("Stack is empty!");
        }
        return this.stack.pop();
    }

    /**
     * Checks if stack is empty
     * @return Returns 'true' if stack is empty, 'false' otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    /**
     * Getter for the stack
     * @return Returns the stack
     */
    @Override
    public Stack<T> getStack() {
        return stack;
    }

    /**
     * Getter for the stack in list form, reversed
     * @return Returns the stack as a list of elements and reversed in order
     */
    @Override
    public List<T> getStatementList() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
