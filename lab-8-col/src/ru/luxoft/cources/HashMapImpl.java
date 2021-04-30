package ru.luxoft.cources;

import java.util.Objects;

class Entry<K, V> {
    // 1
    K key;
    V val;
    Entry<K, V> next;          //**

    public Entry(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public V setValueAndReturnOld(V val) {
        V tmp = this.val;
        this.val = val;
        return tmp;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getVal() {
        return val;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public Entry<K, V> getNext() {
        return next;
    }

    public void setNext(Entry<K, V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Entry<?, ?> e = (Entry<?, ?>) o;
        return this.key == e.key;
    }

    @Override
    public int hashCode() {
        return key != null ? 13 * 11 + key.hashCode() : 0;
    }
}

public class HashMapImpl<K, V> {
    // 3
    private static final int CAPACITY = 100;
    @SuppressWarnings("unchecked")
    private final Entry<K, V>[] table = new Entry[CAPACITY];
    private int size = 0;
    private Entry<K, V> nullTable = null;

    // 4
    private int hashing(int hashCode) {
        int location = hashCode % CAPACITY;
        System.out.println("Location :" + location);
        return location;
    }

    // 5
    public int size() {
        return this.size;
    }

    // 6
    public boolean isEmpty() {
        return this.size == 0;
    }

    // 7
    public boolean containsKey(K key) {

        if (key == null) {
            return nullTable != null;
        }
        return getEntryByKeyFromTable(key) != null;
    }

    private Entry<K, V> getEntryByKeyFromTable(K key) {
        int location = hashing(key.hashCode());
        Entry<K, V> element = table[location];
        while (element != null && !element.getKey().equals(key)) {
            element = element.getNext();
        }
        return element;
    }

    private Entry<K, V> getEntryParentByKeyFromTable(K key) {
        int location = hashing(key.hashCode());
        Entry<K, V> element = table[location];
        if (element == null || element.getNext() == null) {
            return null;
        } else {
            while (element.getNext() != null && !element.getNext().getKey().equals(key)) {
                element = element.getNext();
            }
        }
        return element;
    }


    // 8
    public boolean containsValue(V value) {
        for (int i = 0; i < table.length; i++) {
            if (isValueInTable(i, value)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValueInTable(int location, V value) {
        Entry<K, V> element = table[location];
        while (element != null) {
            if (Objects.equals(value, element.getVal())) {
                return true;
            } else {
                element = element.getNext();
            }
        }
        return false;
    }


    // 9

    public V get(K key) {
        if (key == null) {
            return nullTable == null ? null : nullTable.getVal();
        } else {
            Entry<K, V> entry = getEntryByKeyFromTable(key);
            return entry == null ? null : entry.getVal();
        }
    }

    private Entry<K, V> getLastElementInTable(int location) {
        if (table[location] == null) {
            return null;
        } else {
            Entry<K, V> element = table[location];
            while (element.getNext() != null) {
                element = element.getNext();
            }
            return element;
        }
    }

    // 10
    public V put(K key, V val) {
        if (size >= CAPACITY) {
            System.out.println("Rehashing required");
            return null;
        }

        if (key == null) {
            if (nullTable == null) {
                nullTable = new Entry<>(null, val);
                size++;
                return null;
            } else {
                return nullTable.setValueAndReturnOld(val);
            }
        } else {
            Entry<K, V> entry = getEntryByKeyFromTable(key);
            if (entry == null) {
                int location = hashing(key.hashCode());
                size++;
                entry = getLastElementInTable(location);
                Entry<K, V> newEntry = new Entry<>(key, val);

                if (entry == null) {
                    table[location] = newEntry;
                } else {
                    entry.setNext(newEntry);
                }
                return null;
            } else {
                return entry.setValueAndReturnOld(val);
            }
        }
    }

    public V remove(K key) {
        if (key == null) {
            if (nullTable != null) {
                V retValue = nullTable.getVal();
                nullTable = null;
                size--;
                return retValue;
            } else {
                return null;
            }
        }

        int location = hashing(key.hashCode());
        Entry<K, V> entry = getEntryParentByKeyFromTable(key);
        if (entry == null) {
            if (table[location] != null && table[location].getKey().equals(key)) {
                V retValue = table[location].getVal();
                table[location] = table[location].getNext();
                size--;
                return retValue;
            } else {
                return null;
            }
        } else {
            V retValue = entry.getNext().getVal();
            entry.setNext(entry.getNext().getNext());
            size--;
            return retValue;
        }
    }


}
