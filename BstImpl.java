package bst;

import java.util.function.Consumer;

/**
 * This class represents a binary search tree. It implements the BSTADT
 * interface
 */
public class BstImpl<T extends Comparable<T>>
    implements Bst<T> {
  private BstNode<T> root;

  /**
   * constructs a new empty bst.
   */

  public BstImpl() {
    root = new BstEmptyNode<T>(); //no tree
  }

  @Override
  public void insert(T data) {
    root = root.insert(data);
  }

  @Override
  public boolean present(T data) {
    return root.contains(data);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public T maximum() {
    return root.maximum();
  }

  /**
   * Returns string representation of the bst tree.
   *
   * @return string with all elements.
   */

  public String toString() {
    return "[" + root.toString() + "]";
  }

  @Override
  public void preorder(Consumer<T> consumer) {

  }

  @Override
  public void postorder(Consumer<T> consumer) {

  }

  @Override
  public void inorder(Consumer<T> consumer) {

  }

  @Override
  public boolean isBalanced() {
    return false;
  }
}
