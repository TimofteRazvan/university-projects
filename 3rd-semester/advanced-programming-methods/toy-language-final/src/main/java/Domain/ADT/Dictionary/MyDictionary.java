package Domain.ADT.Dictionary;

import Exception.InterpreterException;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
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
        synchronized (this) {
            return this.dictionary.containsKey(key);
        }
    }

    /**
     * Puts a value into a key
     * @param key = key to take the value
     * @param value = value to put in key
     */
    @Override
    public void put(T1 key, T2 value) {
        synchronized (this) {
            this.dictionary.put(key, value);
        }
    }

    /**
     * Looks up the value of a key
     * @param key = key to look up
     * @return Returns the value
     * @throws InterpreterException If the key is not defined
     */
    @Override
    public T2 lookUp(T1 key) throws InterpreterException {
        synchronized (this) {
            if (!isDefined(key))
                throw new InterpreterException(key + " is not defined.");
            return this.dictionary.get(key);
        }
    }

    /**
     * Updates the key with a new value
     * @param key = key to be updated
     * @param value = new value
     * @throws InterpreterException If the key is not defined
     */
    @Override
    public void update(T1 key, T2 value) throws InterpreterException {
        synchronized (this) {
            if (!isDefined(key))
                throw new InterpreterException(key + " is not defined.");
            this.dictionary.put(key, value);
        }
    }

    /**
     * Removes a key
     * @param key = key to be removed
     * @throws InterpreterException If the key is not defined
     */
    @Override
    public void remove(T1 key) throws InterpreterException {
        synchronized (this) {
            if (!isDefined(key))
                throw new InterpreterException(key + " is not defined.");
            this.dictionary.remove(key);
        }
    }

    /**
     * Getter for the set of keys
     * @return Returns a set of keys
     */
    @Override
    public Set<T1> keySet() {
        synchronized (this) {
            return dictionary.keySet();
        }
    }

    @Override
    public Collection<T2> values() {
        synchronized (this) {
            return this.dictionary.values();
        }
    }

    @Override
    public MyIDictionary<T1, T2> deepcopy() throws InterpreterException {
        MyIDictionary<T1, T2> toReturn = new MyDictionary<>();
        for (T1 key: keySet())
            try {
                toReturn.put(key, lookUp(key));
            } catch (InterpreterException e) {
                throw new InterpreterException(e.getMessage());
            }
        return toReturn;
    }

    @Override
    public Map<T1, T2> getContent() {
        synchronized (this) {
            return dictionary;
        }
    }

    /**
     * Clears the dictionary
     */
    @Override
    public void clear() {
        synchronized (this) {
            this.dictionary.clear();
        }
    }

    /**
     * Wait implemented for dictionary along with synchronicity, in case it is needed
     * @throws InterruptedException If wait() fails
     */
    @Override
    public void waitDictionary() throws InterruptedException {
        synchronized (this) {
            try {
                this.dictionary.wait();
            }
            catch (InterruptedException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    /**
     * Easier way of putting values for keys, as this checks for me.
     * @param key: id to check
     * @param value: value to be inserted for key
     * @return NULL if key doesn't exist / is mapped to null | T2 Value if key already exists
     */
    @Override
    public T2 putIfAbsent(T1 key, T2 value) {
        synchronized (this) {
            return this.dictionary.putIfAbsent(key, value);
        }
    }

}
