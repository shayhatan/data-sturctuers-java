package com.company.HashMapLinearProbing;

import com.company.BinarySearchTree.Person;

// without it, we are not able to instantiate generic array
@SuppressWarnings("unchecked")
public class HashTable<Key, Value> {
    private Key[] keys;
    private Value[] values;
    private int num_of_items;
    private int capacity;

    public HashTable() {
        keys = (Key[]) new Object[Constants.TABLE_SIZE];
        values = (Value[]) new Object[Constants.TABLE_SIZE];
        capacity = Constants.TABLE_SIZE;
        num_of_items = 0;
    }

    public HashTable(int new_capacity) {
        keys = (Key[]) new Object[new_capacity];
        values = (Value[]) new Object[new_capacity];
        capacity = new_capacity;
        num_of_items = 0;
    }

    public int size() {
        return num_of_items;
    }

    public boolean isEmpty() {
        return num_of_items == 0;
    }

    public Value get(Key key) {

        if (key == null) {
            return null;
        }

        int cnt = 0;
        int index = hash(key);

        while (keys[index] != null && cnt < capacity) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            ++cnt;
            index = (index + 1) % capacity;
        }
        return null;
    }

    public void put(Key key, Value value) {
        if (key == null || value == null) {
            return;
        }
        if (num_of_items >= (capacity * 0.75)) {
            resize(2 * capacity);
        }

        int cnt = 0;
        int index = hash(key);

        while (keys[index] != null && cnt < capacity) {
            if (keys[index].equals(key)) {
                values[index] = value;
                return;
            }
            ++cnt;
            index = (index + 1) % capacity;
        }
        if (cnt < capacity) {
            keys[index] = key;
            values[index] = value;
            ++num_of_items;
        }

    }

    void remove(Key key) {
        if (key == null) {
            return;
        }
        int cnt = 0;
        int index = hash(key);
        int i = index;

        while (!keys[index].equals(key) && cnt < capacity) {
            index = (index + 1) % capacity;
            ++cnt;
        }

        if (cnt < capacity) {
            keys[index] = null;
            values[index] = null;
            --num_of_items;

            index = index + 1 % capacity;

            while (keys[index] != null) {
                Key temp_key = keys[index];
                Value temp_val = values[index];

                keys[index] = null;
                values[index] = null;

                --num_of_items;
                put(temp_key, temp_val);

                index = index + 1 % capacity;
            }

            if (num_of_items <= (capacity / 3)) {
                resize(capacity / 2);
            }
        }
    }

    private void resize(int new_capacity) {
        HashTable<Key, Value> new_table = new HashTable<>(new_capacity);
        for (int i = 0; i < capacity; ++i) {
            if (keys[i] != null) {
                new_table.put(keys[i], values[i]);
            }
        }
        this.keys = new_table.keys;
        this.values = new_table.values;
        capacity = new_capacity;
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode()) % capacity;
    }


/*    private HashItem[] hashTable;

    HashTable() {
        hashTable = new HashItem[Constants.TABLE_SIZE];
    }

    public void put(int key, int value) {
        int index = hashFunction(key);
        int count = 0;
        while (hashTable[index] != null && count < Constants.TABLE_SIZE) {
            index = (index + 1) % Constants.TABLE_SIZE;
            ++count;
        }

        if (count == Constants.TABLE_SIZE) {
            throw new RuntimeException("HashTable is full");
        }

        hashTable[index] = new HashItem(key, value);
    }

    public int get(int key) {
        int index = hashFunction(key);
        int count = 0;
        while (hashTable[index] != null && hashTable[index].getKey() != key && count < Constants.TABLE_SIZE) {
            index = (index + 1) % 10;
            ++count;
        }

        if (count == Constants.TABLE_SIZE || hashTable[index] == null) {
            throw new RuntimeException("Key does not exist...");
        }
        return hashTable[index].getData();
    }

    // could be better hash function...
    private int hashFunction(int key) {
        return key % Constants.TABLE_SIZE;
    }*/

}
