package bst;

/**
 * This class represents a data-containing node of the binary search tree.
 * It mutates on all relevant operations.
 */
public class BstElementNode<T extends Comparable<T>> extends BstNode<T> {
  private BstNode<T> left;
  private BstNode<T> right;
  private T data;

  /**
   * Constructor for Bst element node.
   *
   * @param data for the Bst element node.
   * @param left child node.
   * @param right child node.
   */
  public BstElementNode(T data, BstNode<T> left, BstNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }

  @Override
  public BstNode insert(T data) {
    if (data.compareTo(this.data) < 0) {
      this.left = this.left.insert(data);
    } else if (data.compareTo(this.data) > 0) {
      this.right = this.right.insert(data);
    }
    return this;
  }

  @Override
  public T minimum() {
    T minimum;

    try {
      minimum = this.left.minimum();
    } catch (NothingThereException e) {
      minimum = this.data;
    }
    return minimum;
  }

  @Override
  public T maximum() {
    T maximum;

    try {
      maximum = this.right.maximum();
    } catch (NothingThereException e) {
      maximum = this.data;
    }

    return maximum;
  }

  @Override
  public boolean contains(T data) {
    int compareResult = data.compareTo(this.data);

    if (compareResult == 0) {
      return true;
    } else if (compareResult < 0) {
      return this.left.contains(data);
    } else {
      return this.right.contains(data);
    }
  }

  @Override
  public String toString() {
    String left;
    String right;
    String middle;

    middle = this.data.toString();
    left = this.left.toString();
    right = this.right.toString();
    if (left.length() > 0) {
      left = left + " ";
    }
    if (right.length() > 0) {
      right = " " + right;
    }
    return left + middle + right;
  }
}
