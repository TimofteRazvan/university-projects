package Domain.ADT.Heap;

import Domain.Value.IValue;

import Exception.InterpreterException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface MyIHeap{
    /**
     * Returns the heap content
     * @return The heap content
     */
    HashMap<Integer, IValue> getHeap();

    /**
     * Updates the heap content
     * @param newMap The new heap with which to replace
     */
    void setHeap(HashMap<Integer, IValue> newMap);

    /**
     * Gets the first empty location within HashMap
     * @return The current 'empty location'
     */
    int getEmptyLocation();

    /**
     * Adds an element to the HashMap
     * @param value The value to be added
     * @return The location on which the value was placed
     */
    int add(IValue value);

    /**
     * Updates an element at a position with another element
     * @param position Where to replace
     * @param value Element with which to replace
     * @throws InterpreterException If there is no element there
     */
    void update(Integer position, IValue value) throws InterpreterException;

    /**
     * Removes the key specified
     * @param key The key to be removed
     * @throws InterpreterException If there is no such key
     */
    void remove(Integer key) throws InterpreterException;

    /**
     * Gets the value at the specified position
     * @param position The position from which to get the value
     * @return The value at the position
     * @throws InterpreterException If there is no such value there
     */
    IValue get(Integer position) throws InterpreterException;

    /**
     * Checks if the heap contains the key already
     * @param position The position/key to be checked
     * @return True if contained, False otherwise
     */
    boolean containsKey(Integer position);

    /**
     * Returns the key set of the heap
     * @return The key set
     */
    Set<Integer> keySet();

    /**
     * Clears the heap
     */
    void clear();

    /**
     * Waits, implemented along with synchronicity in case it's needed
     * @throws InterruptedException If wait() fails
     */
    void waitHeap() throws InterruptedException;
}
