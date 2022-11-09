package Domain.ADT.Dictionary;

import Exception.ADTException;
import Exception.ADTException;

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
     * @throws ADTException If the key has no value
     */
    T2 lookUp(T1 key) throws ADTException;

    /**
     * Updates the value of a key
     * @param key = key to be updated
     * @param value = new value
     * @throws ADTException If the key doesn't exist
     */
    void update(T1 key, T2 value) throws ADTException;

    /**
     * Removes a key
     * @param key = key to be removed
     * @throws ADTException If key doesn't exist
     */
    void remove(T1 key) throws ADTException;

    /**
     * Getter for the set of keys
     * @return Returns a set of keys
     */
    Set<T1> keySet();
}
