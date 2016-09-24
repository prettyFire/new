import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;


import org.junit.Test;

/**
 * Unit tests for the MultiSet interface. Implementing classes will need their
 * own test class that will override makeMultiset to return a MultiSet of the
 * appropriate type.
 * 
 * @author Nathan Sprague
 * @version V1, 09/2016
 *
 */
public abstract class MultisetTest implements Multiset{

  public abstract <T> Multiset<T> makeMultiset();

  // --------------------------------------------
  // TESTS FOR SIZE
  // --------------------------------------------
  @Test
  public void testEmptySizeZero() {
    Multiset<String> set =  makeMultiset();
    assertEquals(set.getClass().getName(), 0, set.size());
  }
  @Test
  public void testSizeDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    assertEquals(set.getClass().getName(), 3, set.size());
  }

  @Test
  public void testSizeNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    assertEquals(set.getClass().getName(), 3, set.size());
  }

  @Test
  public void testSizeRemoveDuplicate() {
    Multiset<String> set = makeMultiset();

    set.add("a");
    set.add("a");
    set.add("b");
    set.remove("a");
    assertEquals(set.getClass().getName(), 2, set.size());
  }

  @Test
  public void testSizeRemoveNonDuplicate() {
    Multiset<String> set = makeMultiset();

    set.add("a");
    set.add("b");
    set.add("c");
    set.remove("a");
    assertEquals(set.getClass().getName(), 2, set.size());
  }

  // --------------------------------------------
  // TESTS FOR CONTAINS
  // --------------------------------------------

  @Test
  public void testContainsSetEmpty() {
    Multiset<String> set = makeMultiset();

    assertFalse(set.getClass().getName(), set.contains("a"));
  }

  @Test
  public void testContainsTrueOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    assertTrue(set.getClass().getName(), set.contains("a"));
  }

  @Test
  public void testContainsFalseOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    assertFalse(set.getClass().getName(), set.contains("tree"));
    assertFalse(set.getClass().getName(), set.contains("house"));
  }

  @Test
  public void testContainsMultipleElementsNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");

    assertTrue(set.getClass().getName(), set.contains("a"));
    assertTrue(set.getClass().getName(), set.contains("b"));
    assertTrue(set.getClass().getName(), set.contains("c"));
    assertTrue(set.getClass().getName(), set.contains("d"));
    assertFalse(set.getClass().getName(), set.contains("q"));
  }

  @Test
  public void testContainsMultipleElementsWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    set.add("b");
    set.add("c");
    set.add("c");

    assertTrue(set.getClass().getName(), set.contains("a"));
    assertTrue(set.getClass().getName(), set.contains("b"));
    assertTrue(set.getClass().getName(), set.contains("c"));
    assertFalse(set.getClass().getName(), set.contains("q"));
  }

  @Test
  public void testContainsAfterRemovalNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");
    set.remove("a");
    set.remove("c");
    set.remove("e");

    assertFalse(set.getClass().getName(), set.contains("a"));
    assertFalse(set.getClass().getName(), set.contains("c"));
    assertFalse(set.getClass().getName(), set.contains("e"));
    assertTrue(set.getClass().getName(), set.contains("b"));
    assertTrue(set.getClass().getName(), set.contains("d"));
  }

  @Test
  public void testContainsAfterRemovalWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("c");
    set.add("c");
    set.add("e");
    set.add("e");

    set.remove("a");
    set.remove("c");
    set.remove("e");
    assertTrue(set.getClass().getName(), set.contains("a"));
    assertTrue(set.getClass().getName(), set.contains("c"));
    assertTrue(set.getClass().getName(), set.contains("e"));

    set.remove("a");
    set.remove("c");
    set.remove("e");
    assertFalse(set.getClass().getName(), set.contains("a"));
    assertFalse(set.getClass().getName(), set.contains("c"));
    assertFalse(set.getClass().getName(), set.contains("e"));

  }

  // --------------------------------------------
  // TESTS FOR COUNT
  // --------------------------------------------
  @Test
  public void testCountSetEmpty() {
    Multiset<String> set = makeMultiset();

    assertEquals(0, set.count("A"));
  }

  @Test
  public void testCountZeroNonEmpty() {
    Multiset<String> set = makeMultiset();

    set.add("B");
    assertEquals(0, set.count("A"));
  }

  @Test
  public void testCountOneOneElement() {
    Multiset<String> set = makeMultiset();

    set.add("C");
    assertEquals(1, set.count("C"));
  }

  @Test
  public void testCountMultipleElementsNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");


    assertEquals(1, set.count("a"));
    assertEquals(1, set.count("b"));
    assertEquals(1, set.count("c"));
    assertEquals(1, set.count("d"));
    assertEquals(0, set.count("q"));
  }

  @Test
  public void testCountMultipleElementsWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("b");
    set.add("b");
    set.add("c");
    set.add("c");

    assertEquals(2, set.count("a"));
    assertEquals(2, set.count("b"));
    assertEquals(2, set.count("c"));
    assertEquals(0, set.count("q"));
  }

  @Test
  public void testCountAfterRemovalNoDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("b");
    set.add("c");
    set.add("d");
    set.add("e");
    set.remove("a");
    set.remove("c");
    set.remove("e");

    assertEquals(0, set.count("a"));
    assertEquals(1, set.count("b"));
    assertEquals(0, set.count("c"));
    assertEquals(1, set.count("d"));
    assertEquals(0, set.count("e"));
  }

  @Test
  public void testCountAfterRemovalWithDuplicates() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.add("a");
    set.add("c");
    set.add("c");
    set.add("e");
    set.add("e");

    set.remove("a");
    set.remove("c");
    set.remove("e");

    assertEquals(1, set.count("a"));
    assertEquals(1, set.count("c"));
    assertEquals(1, set.count("e"));
  }

  // --------------------------------------------
  // TESTS FOR EQUALS
  // --------------------------------------------

  @Test
  public void testEqualsNonMultiset() {
    Multiset<String> set = makeMultiset();

    assertFalse(set.equals("HELLO"));
  }

  @Test
  public void testEqualsEmptyMultisetsAfterRemoval() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();
    set1.add("a");
    set1.remove("a");

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
  }

  @Test
  public void testEqualsNoRepeats() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();
    set1.add("a");
    set1.add("b");
    set1.add("c");
    set1.add("d");

    set2.add("d");
    set2.add("b");
    set2.add("c");
    set2.add("a");

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
  }

  @Test
  public void testEqualsWithRepeats() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();
    set1.add("a");
    set1.add("b");
    set1.add("a");
    set1.add("b");
    set1.add("c");
    set1.add("d");

    set2.add("d");
    set2.add("b");
    set2.add("b");
    set2.add("c");
    set2.add("a");
    set2.add("a");

    assertTrue(set1.equals(set2));
    assertTrue(set2.equals(set1));
  }

  // --------------------------------------------
  // TESTS FOR ITERATOR
  // --------------------------------------------

  @Test
  public void testIteratorEmptyHasNext() {
    Multiset<String> set1 = makeMultiset();
    Iterator<String> it = set1.iterator();


    assertFalse(it.hasNext());
  }

  @Test(expected = NoSuchElementException.class)
  public void testIteratorEmptyNextException() {
    Multiset<String> set1 = makeMultiset();
    Iterator<String> it = set1.iterator();

    it.next();
  }

  @Test(expected = NoSuchElementException.class)
  public void testIteratorNonEmptyNextException() {
    Multiset<String> set1 = makeMultiset();
    set1.add("a");
    Iterator<String> it = set1.iterator();
    it.next();
    it.next();
  }

  private void testIteration(ArrayList<String> items) {

    // Create a hashmap with item counts
    HashMap<String, Integer> map = new HashMap<>();
    for (String item : items) {
      if (map.containsKey(item)) {
        map.put(item, map.get(item) + 1);
      } else {
        map.put(item, 1);
      }
    }

    // Add all of the items to the multiset
    Multiset<String> set = makeMultiset();
    for (String item : items) {
      set.add(item);
    }

    // Iterate through the multiset, decrementing counts
    for (String item : set) {
      if (map.containsKey(item)) {
        map.put(item, map.get(item) - 1);
      } else {
        fail();
      }
    }

    // all counts should be 0.
    for (String key : map.keySet()) {
      assertEquals(0, (int) map.get(key));
    }

  }

  @Test
  public void testIteratorNoRepeats() {
    ArrayList<String> items = new ArrayList<>();
    items.add("a");
    items.add("b");
    items.add("c");
    items.add("d");
    items.add("e");
    items.add("f");
    testIteration(items);
  }

  @Test
  public void testIteratorWithRepeatsAndRemovals() {
    ArrayList<String> items = new ArrayList<>();
    items.add("a");
    items.add("b");
    items.add("c");
    items.add("d");
    items.add("e");
    items.add("f");

    items.add("a");
    items.add("b");
    items.add("c");
    items.add("d");
    items.add("e");

    items.add("a");
    items.add("b");

    items.remove("a");

    testIteration(items);
  }

  // --------------------------------------------
  // TESTS FOR REMOVEALL and RETAINALL
  // --------------------------------------------

  @Test(expected = UnsupportedOperationException.class)
  public void testRemoveAllUnsupported() {
    Multiset<String> set1 = makeMultiset();
    set1.removeAll(set1);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void testReteainAllUnsupported() {
    Multiset<String> set1 = makeMultiset();
    set1.retainAll(set1);
  }

  // --------------------------------------------
  // TESTS FOR ADDALL
  // --------------------------------------------
  @Test
  public void testAddAll() {
    Multiset<String> set = makeMultiset();
    ArrayList<String> list = new ArrayList<>();
    list.add("a");
    list.add("a");
    list.add("c");
    list.add("e");
    list.add("e");

    set.addAll(list);

    assertEquals(5, set.size());
    assertEquals(2, set.count("a"));
    assertEquals(1, set.count("c"));
    assertEquals(2, set.count("e"));
  }


  // --------------------------------------------
  // TESTS FOR CLEAR
  // --------------------------------------------

  @Test
  public void testClear() {
    Multiset<String> set = makeMultiset();

    set.add("a");
    set.add("a");
    set.add("c");

    set.clear();

    assertEquals(0, set.size());
    assertEquals(0, set.count("a"));
    assertEquals(0, set.count("c"));
  }

  // --------------------------------------------
  // TESTS FOR HASH CODE
  // --------------------------------------------

  @Test
  public void testHashCodeEmptySets() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();

    assertTrue(set1.hashCode() == set2.hashCode());
  }

  @Test
  public void testHashCodeEqualNonEmptySets() {
    Multiset<String> set1 = makeMultiset();
    Multiset<String> set2 = makeMultiset();

    set1.add("a");
    set1.add("b");

    set2.add("b");
    set2.add("a");

    assertTrue(set1.hashCode() == set2.hashCode());
  }

  // --------------------------------------------
  // TESTS FOR ISEMPTY
  // --------------------------------------------

  @Test
  public void testIsEmptyEmpty() {
    Multiset<String> set = makeMultiset();

    assertTrue(set.isEmpty());
  }

  @Test
  public void testIsEmptyNotEmpty() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    assertFalse(set.isEmpty());
  }

  @Test
  public void testIsEmptyEmptyAfterRemoval() {
    Multiset<String> set = makeMultiset();
    set.add("a");
    set.remove("a");
    assertTrue(set.isEmpty());
  }

  // --------------------------------------------
  // TESTS FOR REMOVE
  // (Note that remove is implicitly covered by lots of other tests. These
  // are mostly intended to test the return value.)
  // --------------------------------------------

  @Test
  public void testRemoveFromEmptyReturnsFalse() {
    Multiset<String> set = makeMultiset();
    assertFalse(set.remove("A"));
  }

  @Test
  public void testRemoveSingletonReturnsTrue() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    assertTrue(set.remove("A"));
  }

  @Test
  public void testRemoveDuplicateReturnsTrue() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    set.add("A");
    set.remove("A");
    assertTrue(set.remove("A"));
  }

  // --------------------------------------------
  // TESTS FOR toArray()
  // --------------------------------------------

  @Test
  public void testToObjectArrayEmptySet() {
    Multiset<String> set = makeMultiset();
    Object[] array = set.toArray();
    assertEquals(0, array.length);
  }

  @Test
  public void testToObjectArrayOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    Object[] array = set.toArray();
    assertEquals(1, array.length);
    assertEquals("A", (String) array[0]);
  }

  @Test
  public void testToObjectArrayRepeatedElements() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    set.add("A");
    Object[] array = set.toArray();
    assertEquals(2, array.length);
    assertEquals("A", (String) array[0]);
    assertEquals("A", (String) array[1]);
  }

  // --------------------------------------------
  // TESTS FOR toArray() (generic)
  // --------------------------------------------

  @Test
  public void testToGenericArrayEmptySet() {
    Multiset<String> set = makeMultiset();
    String[] array = set.toArray(new String[0]);
    assertEquals(0, array.length);
  }

  @Test
  public void testToGenericArrayOneElement() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    String[] array = set.toArray(new String[0]);
    assertEquals(1, array.length);
    assertEquals("A", array[0]);
  }

  @Test
  public void testToGenericArrayRepeatedElements() {
    Multiset<String> set = makeMultiset();
    set.add("A");
    set.add("A");
    String[] array = set.toArray(new String[0]);
    assertEquals(2, array.length);
    assertEquals("A", array[0]);
    assertEquals("A", array[1]);
  }

  // --------------------------------------------
  // TESTS FOR toString()
  // --------------------------------------------

  @Test
  public void testToStringEmpty() {
    Multiset<String> set = makeMultiset();

    assertEquals("[]", set.toString());
  }

  @Test
  public void testToSTringSingleElement() {
    Multiset<String> set = makeMultiset();
    set.add("C");
    set.add("C");

    assertEquals("[C x 2]", set.toString());
  }

  @Test
  public void testToStringDuplicateElement() {
    Multiset<String> set = makeMultiset();
    set.add("C");
    set.add("C");

    assertEquals("[C x 2]", set.toString());
  }

  @Test
  public void testToStrinMultipleElements() {
    Multiset<String> set = makeMultiset();
    set.add("B");
    set.add("C");
    set.add("C");

    boolean ok = "[C x 2, B x 1]".equals(set.toString()) || "[B x 1, C x 2]".equals(set.toString());
    assertTrue(ok);
  }

  // --------------------------------------------
  // TESTS FOR proper use of generics
  // --------------------------------------------
  @Test
  public void testMethodsWorkWithIntegerElements() {
    Multiset<Integer> set = makeMultiset();
    set.add(1);
    set.add(2);
    set.add(5);
    set.add(5);

    assertEquals(4, set.size());
    assertEquals(1, set.count(1));
    assertEquals(1, set.count(2));
    assertEquals(2, set.count(5));

    set.clear();

    set.add(1);
    assertEquals("[1 x 1]", set.toString());

  }
}
