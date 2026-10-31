package bst;

import java.util.function.Consumer;

/**
 * This interface represents all the operations that a binary search tree
 * should support. It can store any type of data that implements the
 * Comparable interface
 *
 * @param <T> the type of data stored in the tree be comparable.
 */
public interface Bst<T extends Comparable<T>> {
  /**
   * Add data to the binary search tree. This is ignored if the data item is
   * already present
   *
   * @param data the data to be added
   */
  void insert(T data);

  /**
   * Find if this data is present in the binary search tree.
   *
   * @param data the data to be searched
   * @return true if the data is present, false otherwise
   */
  boolean present(T data);

  /**
   * Determine and return the minimum data in the tree as defined by its ordering.
   *
   * @return the minimum data if it exists, null otherwise
   * @throws NothingThereException if the tree does not have any data
   */
  T minimum() throws NothingThereException;

  /**
   * Determine and return the maximum data in the tree as defined by its ordering.
   *
   * @return the maximum data if it exists, null otherwise
   * @throws NothingThereException if the tree does not have any data
   */
  T maximum() throws NothingThereException;

  /**
   * toString() is used to receive the string itself.
   *
   * @return the to string.
   */
  String toString();

  /**
   * The method is for preorder.
   *
   * @param consumer preorder.
   */
  void preorder(Consumer<T> consumer);

  /**
   * Method for postorder.
   *
   * @param consumer for the postorder.
   */
  void postorder(Consumer<T> consumer);

  /**
   * Method for inorder.
   *
   * @param consumer for inorder.
   */
  void inorder(Consumer<T> consumer);

  /**
   * Method for balancing.
   *
   * @return to get the balanced method value.
   */
  boolean isBalanced();
}
