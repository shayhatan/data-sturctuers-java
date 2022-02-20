package com.company.HashMapChaining;

public class HashTable {
    private HashItem[] hash_table;

    public void put(int key, int value) {
        int hash_index = hash(key);
        if (hash_table[hash_index] != null) {
            hash_table[hash_index] = new HashItem(key, value);
        } else {
            HashItem it = hash_table[hash_index];
            while (it.getNext() != null) {
                it = it.getNext();
            }
            it.setNext(new HashItem(key, value));
        }
    }

    public int get(int key) {
        int index = hash(key);
        if (hash_table[index] == null) {
            throw new RuntimeException("Item do not exist");
        }
        HashItem it = hash_table[index];
        while (it != null && it.getKey() != key) {
            it = it.getNext();
        }
        if (it == null) {
            throw new RuntimeException("Item do not exist");
        }
        return it.getData();
    }

    // could be better hash function....
    private int hash(int key) {
        return key % Constants.TABLE_SIZE;
    }

}
