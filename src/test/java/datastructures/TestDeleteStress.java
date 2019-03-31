    package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

import static org.junit.Assert.assertTrue;

/**
 * This file should contain any tests that check and make sure your
 * delete method is efficient.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteStress extends TestDoubleLinkedList {
    @Test(timeout = 30 * SECOND)
    public void testDeleteIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 10000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 5);
        }
        for (int i = cap - 1; i >= 0; i--) {
            list.delete(i);
        }
        assertEquals(0, list.size());
        
        for (int i = 0; i < cap; i++) {
            list.add(i * 5);
        }
        assertEquals(cap, list.size());

        for (int i = 0; i < cap; i++) {
            list.delete(0);
        }
        assertEquals(0, list.size());
    }
}
