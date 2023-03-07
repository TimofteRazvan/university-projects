package Domain.ADT.Stack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import Exception.InterpreterException;

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
     * @throws InterpreterException If stack is empty
     */
    @Override
    public T pop() throws InterpreterException {
        if (this.stack.isEmpty()) {
            throw new InterpreterException("Stack is empty!");
        }
        return this.stack.pop();
    }

    /**
     * Allows peeking to the top of the stack, returning the first value of the stack
     * @return Returns the first value accessible within the stack
     */
    @Override
    public T peek() {
        return this.stack.peek();
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
     * Getter for the stack in list form, reversed (bottom first)
     * @return Returns the stack as a list of elements and reversed in order
     */
    @Override
    public List<T> getStatementList() {
        List<T> list = Arrays.asList((T[]) stack.toArray());
        Collections.reverse(list);
        return list;
    }
}
