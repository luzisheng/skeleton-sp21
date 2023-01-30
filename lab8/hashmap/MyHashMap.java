package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Mr. Lu
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int sizeOfNode = 0;
    private int sizeOfBucket = 16;
    private double loadFactor = 0.75;

    /** Constructors */
    public MyHashMap() {
        buckets = createTable(sizeOfBucket);
    }

    public MyHashMap(int initialSize) {
        sizeOfBucket = initialSize;
        buckets = createTable(sizeOfBucket);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        sizeOfBucket = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(sizeOfBucket);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection[] newTable = new Collection[tableSize];
        for (int i = 0; i < tableSize; i += 1) {
            newTable[i] = createBucket();
        }
        return newTable;
    }

    @Override
    public void clear() {
        buckets = createTable(sizeOfBucket);
        sizeOfNode = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % sizeOfBucket);
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key);

        for (Node x : buckets[hash]) {
            if (x.key.equals(key)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public V get(K key) {
        int hash = hash(key);

        for (Node x : buckets[hash]) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return sizeOfNode;
    }

    @Override
    public void put(K key, V value) {
        int hash = hash(key);
        if (containsKey(key)) {
            remove(key);
        }
        Node node = createNode(key, value);
        buckets[hash].add(node);
        sizeOfNode += 1;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new TreeSet<>();
        for (Collection<Node> c : buckets) {
            for (Node n : c) {
                keySet.add(n.key);
            }
        }
        return keySet;
    }

    @Override
    public V remove(K key) {
        int hash = hash(key);
        for (Node x : buckets[hash]) {
            if (x.key.equals(key)) {
                sizeOfNode -= 1;
                V returnVal = x.value;
                buckets[hash].remove(x);
                return returnVal;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (get(key) == value) {
            remove(key);
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new IteratorOfMyHashMap<>();
    }

    private class IteratorOfMyHashMap<K> implements Iterator<K> {

        int indexOfBucket = 0;
        boolean hasNext = true;

        @Override
        public boolean hasNext() {
            return hasNext;
        }

        @Override
        public K next() {

            throw new UnsupportedOperationException();

        }
    }
}
