import java.util.Collection;

/**
 * @author Shai Malik
 * 
 * This work complies with the JMU Honor Code. References and Acknowledgments: I received no outside
 * help with this programming assignment. 
 */


public interface Multiset<T> extends Collection<T> {


  public int count(T item);

  public String toString();


}
