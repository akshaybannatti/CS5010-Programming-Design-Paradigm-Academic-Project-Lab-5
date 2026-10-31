import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import betterbst.BstImpl;
import bst.Bst;
import bst.NothingThereException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Before;
import org.junit.Test;

/**
 * Remember to import BstImpl from betterbst packge and not the bst package.
 */
public class BinarySearchTreeTest {

  private Bst<Integer> tree;

  /**
   * function for setup.
   */
  @Before
  public void setup() {
    tree = new BstImpl<Integer>();
  }

  @Test
  public void testInsertions() {

    Set<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }

    StringBuilder sb = new StringBuilder();
    for (Integer e : expected) {
      sb.append(e + " ");

    }
    String output = sb.toString();
    output = "[" + output.substring(0, output.length() - 1) + "]";


    assertEquals(output, tree.toString());
  }

  @Test
  public void testMinMax() {
    TreeSet<Integer> expected = new TreeSet<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }


    assertEquals(expected.first(), tree.minimum());
    assertEquals(expected.last(), tree.maximum());
  }

  @Test(expected = NothingThereException.class)
  public void testMinWhenEmpty() {
    new BstImpl<Integer>().minimum();
  }

  @Test(expected = NothingThereException.class)
  public void testMaxWhenEmpty() {
    new BstImpl<Integer>().maximum();
  }

  @Test
  public void testContains() {
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 1000; i++) {
      expected.add((int) (Math.random() * 2000 - 1000));
    }

    for (Integer e : expected) {
      tree.insert(e);
    }


    for (int i = -1000; i <= 1000; i++) {
      assertEquals(expected.contains(i), tree.present(i));
    }
  }

  /*
                              10
                   5                   15
           1             7                      20
                2     6.      8               19
   */

  @Test
  public void testPreorder() {

    tree.insert(10);
    tree.insert(15);
    tree.insert(5);
    tree.insert(1);
    tree.insert(2);
    tree.insert(7);
    tree.insert(6);
    tree.insert(8);
    tree.insert(20);
    tree.insert(19);

    List<Integer> expectedList = Arrays.asList(10, 5, 1, 2, 7, 6, 8, 15, 20, 19);

    List<Integer> actualList = new ArrayList<Integer>();

    tree.preorder(i -> actualList.add(i));


    assertEquals(expectedList, actualList);


  }

  /*
                              10
                   5                   15
           1             7                      20
                2     6.      8               19
   */
  @Test
  public void testInorder() {

    tree.insert(10);
    tree.insert(15);
    tree.insert(5);
    tree.insert(1);
    tree.insert(2);
    tree.insert(7);
    tree.insert(6);
    tree.insert(8);
    tree.insert(20);
    tree.insert(19);

    List<Integer> expectedList = Arrays.asList(1, 2, 5, 6, 7, 8, 10, 15, 19, 20);

    List<Integer> actualList = new ArrayList<Integer>();

    tree.inorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);


  }

  /*
                              10
                   5                   15
           1             7                      20
                2     6.      8               19
   */
  @Test
  public void testPostorder() {

    tree.insert(10);
    tree.insert(15);
    tree.insert(5);
    tree.insert(1);
    tree.insert(2);
    tree.insert(7);
    tree.insert(6);
    tree.insert(8);
    tree.insert(20);
    tree.insert(19);

    List<Integer> expectedList = Arrays.asList(2, 1, 6, 8, 7, 5, 19, 20, 15, 10);

    List<Integer> actualList = new ArrayList<Integer>();

    tree.postorder(i -> actualList.add(i));

    assertEquals(expectedList, actualList);


  }

}