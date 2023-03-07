package Domain.ADT.List;

import Exception.InterpreterException;

import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    List<T> list;

    /**
     * Constructor for MyList
     */
    public MyList() {
        this.list = new ArrayList<>();
    }

    /**
     * Simple toString function
     * @return Returns list.toString()
     */
    @Override
    public String toString() {
        return this.list.toString();
    }

    /**
     * Adds an element to the list
     * @param element = element to be added
     */
    @Override
    public void add(T element) {
        synchronized (this) {
            this.list.add(element);
        }
    }

    /**
     * Pops an element from the list
     * @return Returns the popped element
     * @throws InterpreterException If list is empty
     */
    @Override
    public T pop() throws InterpreterException {
        synchronized (this) {
            if (list.isEmpty()) {
                throw new InterpreterException("List is empty!");
            }
            return this.list.remove(0);
        }
    }

    /**
     * Checks if list is empty
     * @return Returns 'true' if empty, 'false' otherwise
     */
    @Override
    public boolean isEmpty() {
        synchronized (this) {
            return this.list.isEmpty();
        }
    }

    /**
     * Getter for the list
     * @return Returns the list
     */
    @Override
    public List<T> getList() {
        synchronized (this) {
            return list;
        }
    }
}
