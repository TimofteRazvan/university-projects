package Domain.ADT.Heap;

import Exception.InterpreterException;

import Domain.Value.IValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyHeap implements MyIHeap {
    HashMap<Integer, IValue> heap;
    Integer emptyLocation;

    /**
     * Constructor for MyHeap
     */
    public MyHeap() {
        this.heap = new HashMap<>();
        emptyLocation = 1;
    }

    /**
     * Simple toString function
     * @return Returns heap.toString()
     */
    @Override
    public String toString() {
        return this.heap.toString();
    }

    /**
     * Getter for the heap contents
     * @return The heap contents
     */
    @Override
    public HashMap<Integer, IValue> getHeap() {
        synchronized (this) {
            return heap;
        }
    }

    /**
     * Setter for the heap
     * @param newMap The new heap with which to replace
     */
    @Override
    public void setHeap(HashMap<Integer, IValue> newMap) {
        synchronized (this) {
            this.heap = newMap;
        }
    }

    /**
     * Finds the next empty location for the hash map
     * @return The first empty location to be found
     */
    public int newValue() {
        emptyLocation += 1;
        while (emptyLocation == 0 || heap.containsKey(emptyLocation))
            emptyLocation += 1;
        return emptyLocation;
    }

    /**
     * Getter for the empty location
     * @return The empty location
     */
    @Override
    public int getEmptyLocation() {
        synchronized (this) {
            return emptyLocation;
        }
    }

    /**
     * Adds a value in the current empty location
     * @param value The value to be added
     * @return
     */
    @Override
    public int add(IValue value) {
        synchronized (this) {
            heap.put(emptyLocation, value);
            Integer toReturn = emptyLocation;
            emptyLocation = newValue();
            return toReturn;
        }
    }

    /**
     * Updates the value at the specified position
     * @param position Where to replace
     * @param value Element with which to replace
     * @throws InterpreterException If there is no such position already mentioned
     */
    @Override
    public void update(Integer position, IValue value) throws InterpreterException {
        synchronized (this) {
            if (!heap.containsKey(position))
                throw new InterpreterException(String.format("%d is not present in the heap", position));
            heap.put(position, value);
        }
    }

    /**
     * Removes the key from the heap
     * @param key The key to be removed
     * @throws InterpreterException If there is no such key
     */
    @Override
    public void remove(Integer key) throws InterpreterException {
        synchronized (this) {
            if (!containsKey(key))
                throw new InterpreterException(key + " is not defined.");
            emptyLocation = key;
            this.heap.remove(key);
        }
    }

    /**
     * Getter for the value at the specified position
     * @param position The position from which to get the value
     * @return The value from the position
     * @throws InterpreterException If there is no value at the position
     */
    @Override
    public IValue get(Integer position) throws InterpreterException {
        synchronized (this) {
            if (!heap.containsKey(position))
                throw new InterpreterException(String.format("%d is not present in the heap", position));
            return heap.get(position);
        }
    }

    /**
     * Checks if the heap contains the key
     * @param position The position/key to be checked
     * @return True if contained; False otherwise
     */
    @Override
    public boolean containsKey(Integer position) {
        synchronized (this) {
            return this.heap.containsKey(position);
        }
    }

    /**
     * Getter for the heap's key set
     * @return The key set
     */
    @Override
    public Set<Integer> keySet() {
        synchronized (this) {
            return heap.keySet();
        }
    }

    /**
     * Clears the heap
     */
    @Override
    public void clear() {
        synchronized (this) {
            this.heap.clear();
        }
    }

    /**
     * Waits, implemented along with synchronicity in case it's needed
     * @throws InterruptedException If wait() fails
     */
    @Override
    public void waitHeap() throws InterruptedException {
        synchronized (this) {
            try {
                this.heap.wait();
            }
            catch (InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

}