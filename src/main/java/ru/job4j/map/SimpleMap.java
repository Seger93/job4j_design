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
        int hashCode = key.hashCode();
        int bucket = indexFor(hashCode);
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
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
        return (table.length - 1) & hash;
    }

    private void expand() {
        MapEntry<K, V>[] tableNew = new MapEntry[capacity * 2];
        capacity *= 2;
        for (K key : this) {
            if (key == null) {
                continue;
            }
            V value = get(key);
            int hashCode = hash(key.hashCode());
            int bucket = indexFor(hashCode);
            MapEntry<K, V> newValueAndKey = new MapEntry<>(key, value);
            tableNew[bucket] = newValueAndKey;
        }
        table = tableNew;
    }

    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int bucket = indexFor(hashCode);
        if (table[bucket] != null) {
            return table[bucket].value;
        } else {
            return null;
        }
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (get(key) != null) {
            int k = key.hashCode();
            int b = indexFor(k);
            table[b].key = null;
            table[b].value = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int bucket = 0;
            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                return bucket < count;
            }

            @Override
            public K next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                while (table[bucket] == null) {
                    bucket++;
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
