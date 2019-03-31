// Danfeng Yang 1762478
// Created on 11/10/2018
// CSE 373 AF with TA: Sherdil Niyaz
// HW2 - Hawk with Partner: Tiancheng Xu(tichx)
// Keeps track of unordered key-value pairs with unique keys.
package datastructures.concrete.dictionaries;
import datastructures.interfaces.IDictionary;
import misc.exceptions.NoSuchKeyException;

/**
 * See IDictionary for more details on what this class should do
 */
public class ArrayDictionary<K, V> implements IDictionary<K, V> {
    // Store pairs of key-value mapping
    private Pair<K, V>[] pairs;
    // Store the size of pairs
    private int size;

    // Construct the ArrayDictionary object with initial values.
    public ArrayDictionary() {
        pairs = makeArrayOfPairs(5);
        size = 0;
        
    }

    // This method will return a new, empty array of the given size
    // that can contain Pair<K, V> objects.
    // Each element in the array will initially be null.
    @SuppressWarnings("unchecked")
    private Pair<K, V>[] makeArrayOfPairs(int arraySize) {
        // It turns out that creating arrays of generic objects in Java
        // is complicated due to something known as 'type erasure'.
        //
        // We've given you this helper method to help simplify this part of
        // your assignment. Use this helper method as appropriate when
        // implementing the rest of this class.
        //
        // You are not required to understand how this method works, what
        // type erasure is, or how arrays and generics interact. Do not
        // modify this method in any way.
        return (Pair<K, V>[]) (new Pair[arraySize]);

    }

    // Returns the value corresponding to the given key.
    // throws NoSuchKeyException if the dictionary does not contain 
    // the given key.
    public V get(K key) {
        if (!containsKey(key)) {
            throw new NoSuchKeyException();
        }
        V valDefault = null;
        for (int i = 0; i < size; i++) {
            if (equalsKey(pairs[i].key, key)) {
                return pairs[i].value;
            }
        }  
        return valDefault;
    }


    // Adds the given key-value pair to the dictionary. If the key 
    // already exists in the dictionary,
    // replace its value with the given one.
    public void put(K key, V value) {
        if (containsKey(key)) { 
            for (int i = 0; i < size; i++) {
                if (equalsKey(pairs[i].key, key)) {
                    pairs[i].value = value;                
                }
            }
        } else {
            if (size >= pairs.length) {
                Pair<K, V>[] newPairs = makeArrayOfPairs(2 * pairs.length);
                for (int i = 0; i < size; i++) {
                    newPairs[i] = pairs[i];
                }
                pairs = newPairs;
            }
            pairs[size] = new Pair<K, V>(key, value);
            size++;
        }   
    }

    // Remove the key-value pair corresponding to the given key from the 
    // dictionary.
    // throws NoSuchKeyException if the dictionary does not contain the 
    // given key.
    public V remove(K key) {
        if (!containsKey(key)) {
            throw new NoSuchKeyException();
        }
        V value = null;
        for (int i = 0; i < size; i++) {
            if (equalsKey(pairs[i].key, key)) {
                value = pairs[i].value;
                pairs[i] = null;
                pairs[i] = pairs[size - 1];
                pairs[size - 1] = null;
                size--;    
                return value;
            }
        }  
        return value;   
    }

    // Returns 'true' if the dictionary contains the 
    // given key and 'false' otherwise.
    public boolean containsKey(K key) {
        for (int i = 0; i < size; i++) {
            if (equalsKey(pairs[i].key, key)) {
                return true;
            }
        }
        return false;
    }

    // Returns the number of key-value pairs stored in this dictionary.
    public int size() {
        return size;
    }
    
    // returns true if the given two keys are equaled and false otherwise
    private boolean equalsKey(K key1, K key2) {
        return ((key1 == null && key2 == null) || key1.equals(key2));
    }

    private static class Pair<K, V> {
        public K key;
        public V value;

        
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
