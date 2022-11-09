package Domain.ADT.Dictionary;

import Exception.ADTException;
import Exception.ADTException;

import java.util.HashMap;
import java.util.Set;

public class MyDictionary<T1, T2> implements MyIDictionary<T1, T2> {
    HashMap<T1, T2> dictionary;

    /**
     * Constructor for MyDictionary
     */
    public MyDictionary() {
        this.dictionary = new HashMap<>();
    }

    /**
     * Simple toString function
     * @return Returns dictionary.toString()
     */
    @Override
    public String toString() {
        return this.dictionary.toString();
    }

    /**
     * Checks if the key is already defined
     * @param key = key to check
     * @return Returns 'true' if defined, 'false' otherwise
     */
    @Override
    public boolean isDefined(T1 key) {
        return this.dictionary.containsKey(key);
    }

    /**
     * Puts a value into a key
     * @param key = key to take the value
     * @param value = value to put in key
     */
    @Override
    public void put(T1 key, T2 value) {
        this.dictionary.put(key, value);
    }

    /**
     * Looks up the value of a key
     * @param key = key to look up
     * @return Returns the value
     * @throws ADTException If the key is not defined
     */
    @Override
    public T2 lookUp(T1 key) throws ADTException {
        if (!isDefined(key)) {
            throw new ADTException("Key not defined!");
        }
        return this.dictionary.get(key);
    }

    /**
     * Updates the key with a new value
     * @param key = key to be updated
     * @param value = new value
     * @throws ADTException If the key is not defined
     */
    @Override
    public void update(T1 key, T2 value) throws ADTException {
        if (!isDefined(key)) {
            throw new ADTException("Key not defined!");
        }
        this.dictionary.put(key, value);
    }

    /**
     * Removes a key
     * @param key = key to be removed
     * @throws ADTException If the key is not defined
     */
    @Override
    public void remove(T1 key) throws ADTException {
        this.dictionary.remove(key);
    }

    /**
     * Getter for the set of keys
     * @return Returns a set of keys
     */
    @Override
    public Set<T1> keySet() {
        return dictionary.keySet();
    }
}
