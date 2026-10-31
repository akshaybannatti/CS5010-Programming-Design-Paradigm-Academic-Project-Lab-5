package bst;

/**
 * This node represents an empty node in the binary search tree.
 * The binary tree will insert the data.
 */
public class BstEmptyNode<T extends Comparable<T>> extends BstNode<T> {
  @Override
  public BstNode<T> insert(T data) {
    return new BstElementNode(data, new BstEmptyNode(), new BstEmptyNode());
  }

  /**
   * This inserts data in element.
   *
   * @return bst element.
   * @throws NothingThereException tree does not have data.
   */
  @Override
  public T minimum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  @Override
  public T maximum() throws NothingThereException {
    throw new NothingThereException("Tree does not have any data");
  }

  @Override
  public boolean contains(T data) {
    return false;
  }

  @Override
  public String toString() {
    return "";
  }


}
