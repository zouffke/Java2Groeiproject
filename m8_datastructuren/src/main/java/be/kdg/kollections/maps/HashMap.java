package be.kdg.kollections.maps;

import be.kdg.kollections.lists.ArrayList;
import be.kdg.kollections.lists.List;
import be.kdg.kollections.sets.ArraySet;
import be.kdg.kollections.sets.Set;

public class HashMap<K, V> implements Map<K, V> {
    //region vars
    private static final int DEFAULT_CAPACITY = 100;

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] buckets;
    private int size = 0;
    //endregion

    //region constructors
    public HashMap() {
        this(DEFAULT_CAPACITY);
    }

    public HashMap(int capacity) {
        buckets = new Node[capacity];
    }    //endregion

    //region ownFunctions
    private int hash(K key) {
        return Math.abs(key.hashCode() % buckets.length);
    }
    //endregion

    //region Override
    //region Map interface
    @Override
    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> node = buckets[index];
        if (node == null) {
            buckets[index] = new Node<>(key, value);
            size++;
        } else {
            while (true) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
                if (node.next == null) {
                    node.next = new Node<>(key, value);
                    size++;
                    return;
                }
                node = node.next;
            }
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Node<K, V> node = buckets[index];
        if (node == null) return null;
        while (node != null) {
            if (node.key.equals(key)) return node.value;
            node = node.next;
        }
        return null;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>(size);
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                values.add(node.value);
                node = node.next;
            }
        }
        return values;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new ArraySet<>();
        for (Node<K, V> bucket : buckets) {
            Node<K, V> node = bucket;
            while (node != null) {
                keySet.add(node.key);
                node = node.next;
            }
        }
        return keySet;
    }
    //endregion
    //endregion
}
