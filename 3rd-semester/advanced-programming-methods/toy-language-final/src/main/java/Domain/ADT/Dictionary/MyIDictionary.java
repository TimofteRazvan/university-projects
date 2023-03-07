package Domain.ADT.Dictionary;

import Exception.InterpreterException;
import Exception.InterpreterException;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MyIDictionary<T1, T2> {
    /**
     * Checks if a key is defined already
     * @param key = key to check
     * @return Returns 'true' if defined, 'false' otherwise
     */
    boolean isDefined(T1 key);

    /**
     * Puts the value for the key
     * @param key = key to take the value
     * @param value = value to put in key
     */
    void put(T1 key, T2 value);

    /**
     * Looks up the value of the key
     * @param key = key to look up
     * @return Returns the value of the key
     * @throws InterpreterException If the key has no value
     */
    T2 lookUp(T1 key) throws InterpreterException;

    /**
     * Updates the value of a key
     * @param key = key to be updated
     * @param value = new value
     * @throws InterpreterException If the key doesn't exist
     */
    void update(T1 key, T2 value) throws InterpreterException;

    /**
     * Removes a key
     * @param key = key to be removed
     * @throws InterpreterException If key doesn't exist
     */
    void remove(T1 key) throws InterpreterException;

    /**
     * Getter for the set of keys
     * @return Returns a set of keys
     */
    Set<T1> keySet();

    /**
     * Returns a collection made up of the values within the dictionary
     * @return Collection of values within dictionary
     */
    Collection<T2> values();

    /**
     * Deepcopies the whole dictionary
     * @return A perfect copy of the dictionary
     * @throws InterpreterException If any keys are somehow not defined
     */
    MyIDictionary<T1, T2> deepcopy() throws InterpreterException;

    /**
     * Returns a map of the content within the dictionary
     * @return A map of the dictionary
     */
    Map<T1, T2> getContent();

    /**
     * Clears the dictionary
     */
    void clear();

    /**
     * Wait implemented for dictionary along with synchronicity, in case it is needed
     * @throws InterruptedException If wait() fails
     */
    void waitDictionary() throws InterruptedException;

    /**
     * Easier way of putting values for keys, as this checks for me.
     * @param key: id to check
     * @param value: value to be inserted for key
     * @return NULL if key doesn't exist / is mapped to null | T2 Value if key already exists
     */
    T2 putIfAbsent(T1 key, T2 value);
}
