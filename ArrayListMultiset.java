import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;


/**
 * @author Shai Malik 
 *  This work complies with the JMU Honor Code. References and Acknowledgments: I received no outside
 *  help with this programming assignment.
 */
public class ArrayListMultiset<F> extends AbstractCollection<F> implements Multiset<F> {

  private ArrayList<F> elements = new ArrayList<>();


  /**
   * ArrayListMultiset constructor
   * 
   * 
   */
  public ArrayListMultiset() {
    elements = new ArrayList<F>();

  }

  /**
   * Add an item.
   * 
   * @param item The item to add
   */
  public boolean add(F item) {
    elements.add(item);
    return true;
  }

  public void clear() {
    elements.clear();
  }


  /**
   * Return a count of the number of occurrences of the provided item in the MultiSet.
   * 
   * @param item The item to count
   * 
   * @return The number of occurrences
   */
  public int count(Object item) {
    int total = 0;

    for (F element : elements) {
      if (element.equals(item)) {
        total++;
      }
    }
    return total;
  }


  /**
   * Return true if the provided object is equal to this MultiSet. Two Multisets are considered to
   * be equal if they contain the same elements with the same counts.
   * 
   * @param other The object to check for equality
   * @return true if the object is equal to this MultiSet
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    if (!(other instanceof Multiset) || ((Multiset<?>) other).size() != size()) {
      return false;
    }

    for (F element : elements) {
      if (this.count(element) != ((Multiset<F>) other).count(element)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Return an iterator for this MultiSet. Repeated elements will be returned the appropriate number
   * of times by the iterator.
   * 
   * @return The iterator
   */
  public Iterator<F> iterator() {
    return elements.iterator();
  }

  /**
   * get the size of elements
   * 
   * 
   * 
   */
  public int size() {
    return elements.size();
  }

  /**
   * Return a String representation of this MultiSet. A string representation of each element will
   * be included along with the count for that element. For example, the MultiSet [a, a, b] could be
   * represented by the String "[a x 2, b x 1]". The order of the elements is not specified.
   * 
   * @return A string representation of the MultiSet
   */
  public String toString() {
    HashSet<F> added = new HashSet<>();
    String result = "[";

    for (F element : elements) {

      if (!added.contains(element)) {

        if (added.size() > 0) {
          result += ", ";
        }

        result += element.toString() + " x " + this.count(element);
      }
      added.add(element);
    }
    return result + "]";
  }

  /**
   * 
   * * @throws UnsupportedOperationException
   */
  @Override
  public boolean removeAll(Collection<?> collect) {
    throw new UnsupportedOperationException();
  }

  /**
   * 
   * 
   * @throws UnsupportedOperationException
   */
  @Override
  public boolean retainAll(Collection<?> collect) {
    throw new UnsupportedOperationException();
  }

  /**
   * The role of the hashCode method will be addressed later in the semester.
   *
   * @return 1, always 1.
   */
  public int hashCode() {
    return 1;
  }
}

