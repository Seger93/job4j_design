package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        int hashCode = key.hashCode();
        int bucket = indexFor(hashCode);
        boolean rls = table[bucket] == null;
        if (rls) {
            MapEntry<K, V> newTable = new MapEntry<>(key, value);
            table[bucket] = newTable;
            modCount++;
            count++;
        }
        return rls;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> keySet : table) {
            if (keySet != null) {
                K currentKey = keySet.key;
                int currentHash = hash(currentKey.hashCode());
                int index = indexFor(currentHash);
                newTable[index] = keySet;
            }
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int bucket = indexFor(hashCode);
        return table[bucket] == null
                || !key.equals(table[bucket].key)
                ? null : table[bucket].value;
    }

    @Override
    public boolean remove(K key) {
        int hashCode = key.hashCode();
        int bucket = indexFor(hashCode);
        boolean result = table[bucket] != null && key.equals(table[bucket].key);
        if (result) {
            table[bucket] = null;
            modCount++;
            count--;
        }
        return result;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int bucket = 0;
            final int expectedModCount = modCount;

            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (bucket < capacity && table[bucket] == null) {
                    bucket++;
                }
                return bucket < capacity && table[bucket] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[bucket++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
