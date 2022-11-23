package Domain.ADT.List;

import Exception.ADTException;

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
        this.list.add(element);
    }

    /**
     * Pops an element from the list
     * @return Returns the popped element
     * @throws ADTException If list is empty
     */
    @Override
    public T pop() throws ADTException {
        if (list.isEmpty()) {
            throw new ADTException("List is empty!");
        }
        return this.list.remove(0);
    }

    /**
     * Checks if list is empty
     * @return Returns 'true' if empty, 'false' otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Getter for the list
     * @return Returns the list
     */
    @Override
    public List<T> getList() {
        return list;
    }
}
