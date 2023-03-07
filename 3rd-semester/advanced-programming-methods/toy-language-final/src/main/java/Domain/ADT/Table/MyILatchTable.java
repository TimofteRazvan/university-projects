package Domain.ADT.Table;

import Exception.InterpreterException;

import java.util.HashMap;
import java.util.Set;

public interface MyILatchTable {
    void put(int key, int value) throws InterpreterException;
    int get(int key) throws InterpreterException;
    void update(int key, int value) throws InterpreterException;
    boolean contains(int key);
    HashMap<Integer, Integer> getLatchTable();
    void setLatchTable(HashMap<Integer, Integer> latchTable);
    int getFreeAddr();
    void setFreeAddr(int freeAddr);
    Set<Integer> keySet();
}
