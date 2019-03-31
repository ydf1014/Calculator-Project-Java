package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.interfaces.IList;

import static org.junit.Assert.fail;

/**
 * This class should contain all the tests you implement to verify that
 * your 'delete' method behaves as specified.
 *
 * This test _extends_ your TestDoubleLinkedList class. This means that when
 * you run this test, not only will your tests run, all of the ones in
 * TestDoubleLinkedList will also run.
 *
 * This also means that you can use any helper methods defined within
 * TestDoubleLinkedList here. In particular, you may find using the
 * 'assertListMatches' and 'makeBasicList' helper methods to be useful.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteFunctionality extends TestDoubleLinkedList {

    @Test(timeout = SECOND)
    public void testInsertAndDeleteMultiple() {
        IList<String> list = this.makeBasicList();
        list.add("d");
        this.assertListMatches(new String[] {"a", "b", "c", "d"}, list);
        assertEquals("c", list.delete(2));
        this.assertListMatches(new String[] {"a", "b", "d"}, list);
        assertEquals("b", list.delete(1));
        this.assertListMatches(new String[] {"a", "d"}, list);
        assertEquals("a", list.delete(0));
        this.assertListMatches(new String[] {"d"}, list);
        assertEquals("d", list.delete(0));
        this.assertListMatches(new String[] {}, list);
    }
    
    @Test(timeout = SECOND)
    public void testAddAndDeleteMultiple() {
        IList<String> list = this.makeBasicList();

        assertEquals("a", list.delete(0));
        this.assertListMatches(new String[] {"b", "c"}, list);

        assertEquals("c", list.delete(1));
        this.assertListMatches(new String[] {"b"}, list);
        
        list.add("f");
        list.add("z");
        
        assertEquals("f", list.delete(1));
        this.assertListMatches(new String[] {"b", "z"}, list);
    }

    @Test(timeout = SECOND)
    public void testDeleteOutOfBoundsThrowsException() {
        IList<String> list = this.makeBasicList();
        try {
            list.delete(list.size()+1);
            fail("Should throw IndexOutOfBoundsException().");
        } catch (IndexOutOfBoundsException e) {
        }


        try {
            list.delete(-999);
            fail("Should throw IndexOutOfBoundsException().");
        } catch (IndexOutOfBoundsException e) {
            
        }
        
        list.delete(0);
        list.delete(0);
        list.delete(0);
        try {
            list.delete(0);
            fail("Should throw IndexOutOfBoundsException().");
        } catch (IndexOutOfBoundsException e) {
            
        }
    }

}
