package Domain.ADT.Table;

import java.util.HashMap;
import java.util.Set;
import Exception.InterpreterException;

public class MyLatchTable implements MyILatchTable {
    private HashMap<Integer, Integer> latchTable;
    private int freeAddr;

    public MyLatchTable() {
        this.latchTable = new HashMap<>();
    }

    @Override
    public String toString() {
        return latchTable.toString();
    }

    @Override
    public void put(int key, int value) throws InterpreterException {
        synchronized (this) {
            if (latchTable.containsKey(key)) {
                throw new InterpreterException("LATCH TABLE: Key already in table!\n");
            }
            latchTable.put(key, value);
        }
    }

    @Override
    public int get(int key) throws InterpreterException {
        synchronized (this) {
            if (!latchTable.containsKey(key)) {
                throw new InterpreterException("LATCH TABLE: Key not in table! Can't get!\n");
            }
            return latchTable.get(key);
        }
    }

    @Override
    public void update(int key, int value) throws InterpreterException {
        synchronized (this) {
            if (!latchTable.containsKey(key)) {
                throw new InterpreterException("LATCH TABLE: Key not in table! Can't update!\n");
            }
            latchTable.replace(key, value);
        }
    }

    @Override
    public boolean contains(int key) {
        synchronized (this) {
            return latchTable.containsKey(key);
        }
    }

    @Override
    public HashMap<Integer, Integer> getLatchTable() {
        synchronized (this) {
            return latchTable;
        }
    }

    @Override
    public void setLatchTable(HashMap<Integer, Integer> latchTable) {
        synchronized (this) {
            this.latchTable = latchTable;
        }
    }

    @Override
    public int getFreeAddr() {
        synchronized (this) {
            freeAddr++;
            return freeAddr;
        }
    }

    @Override
    public void setFreeAddr(int freeAddr) {
        synchronized (this) {
            this.freeAddr = freeAddr;
        }
    }

    @Override
    public Set<Integer> keySet() {
        synchronized (this) {
            return latchTable.keySet();
        }
    }
}
