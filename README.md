# Objectives
 
The objectives of this lab are: 

  * Practice writing methods on trees.

  * Write JUnit tests to verify that implementation matches the specification.
 
You are allowed to work with another person on this lab. However both of you must submit individually to the server for it to count towards your grade. 


# 1 The Binary Search Tree Design
 
The starter code contains a design and implementation for a binary search tree, using our "recursive categorical data" design. Specifically the `BstNode` interface represents a single node of the tree. It is implemented by the `BstEmptyNode` and `BstElementNode` classes, representing an empty node and a node with a single element of data respectively. Finally the `Bst` interface represents an ADT, while the `BstImpl` class is its implementation. Some methods have been implemented that allow you to create a binary search tree.  Lastly a test class has been provided to you. 

**Create a new package called betterbst and copy all the files except the `Bst` interface into that package. Work only in this package for this lab.**

Run these tests in an attempt to understand the design and code, before proceeding. Note that some of the tests will fail, because some of the methods are empty.

**Note**: A binary search tree is a standard data structure. This means that solutions to each operation in this lab are likely available online. **It is recommended that you attempt to design and implement them yourself, without looking online.** You will learn close to nothing by finding a solution online and simply pasting/re-typing it in your code.


# 2 What to do

## 2.1 Easy: Traversal

Implement the `preorder`, `inorder` and `postorder` methods documented in the interface. Look at the provided test to see the expected output. 

  * The pre-order traversal of a binary tree first processes the data at the node, then proceeds to its left sub-tree and finally to its right sub-tree.

  * The in-order traversal of a binary tree first proceeds to its left sub-tree, then processes the data at the node, and finally proceeds to its right sub-tree.

  * The post-order traversal of a binary tree first proceeds to its left sub-tree, then to its right sub-tree and finally processes the data at the node.  
    
  * Many different operations can be implemented by using the appropriate tree traversals. These operations would differ largely in the specific processing of data. In order to capture this, the above methods have been written to accept a `Consumer<T>` object. When the client calls this method, they specify exactly how each data item should be processed. Look at how the test uses this.
    
  * Write more tests for this method **before** you implement it. Write at least one test that uses a different consumer, so that you can practice how to use such higher-order functions.
 

**Do not submit to the submission server yet!**
 
## 2.2 Questions to ponder and discuss

Discuss with the person next to you (or the student you chose to work with) the following questions:

  * Could you formulate this problem in the traditional recursive way?
  
  * What are the limitations of a recursive approach? How practical (as supposed to theoretical) are these limitations?

We will address this later...

## 2.3 Tricky: Height and Balance

The height of a binary search tree is the number of edges from the root of the tree to the deepest leaf of the tree. Here is another way to look at it: *given two binary trees S and T, create a new binary tree U by creating a root, whose children are S and T. What is the height of U compared to S and T?*

The height of a binary search tree is important because most of its operations are proportional to its height. Therefore we wish for a binary search tree to be *balanced* (i.e. not lop-sided). One way to define balance is that the *for every node in the tree, the difference between the heights of its children is at most 1*. 

Implement the `isBalanced` method in `betterbst.BstImpl`. This may require determining the height of the tree as well, for which you are free to write helpers in the nodes. 

**Do not submit to the submission server yet!**
    
## 2.4 Trickier: Non-recursive traversals

Any recursive method can be re-formulated as an iterative method. A more straightforward way to do this is to pay attention to how the call stack is being used by the recursive method, and then simulate that by maintaining our own stack to store relevant data.

Consider the pre-order traversal. When it reaches a node, it immediately processes the data at that node. The "processing of its children" is what needs to be remembered to be done later. We can remember this by putting the children into a stack. We note that we must process the left-subtree *before* the right one. If we put the children into a stack, they need to come out in the order in which they should be processed. 

So we can formulate an implementation as follows:

```java

1. Create a stack S of nodes, that is initially empty.
2. Push the root into S.
3. If S is not empty, go to step 4, else end.
4. Pop an element from S, and call it C.
5. Process the data at C.
6. Push the right child of C into S.
7. Push the left child of C into S. //so that it comes out before the right child
8. Go to Step 3.

```

This iterative implementation will have to be implemented in the ADT rather than in the nodes. 

### 2.4.1 Iterative Preorder: Take 1

Implement the above algorithm in `betterbst.BstImpl`. What problems do you encounter?

The last two steps require access to the children of C. A bigger problem is that we do not know if C even has any children (only the `BstElementNode` is supposed to have children, according to our data definition)!

This leaves us with two choices, both imperfect:

  1. Determine the type of C and if it is `BSTElementNode` then typecast it to retrieve its children. This will also require us to relax the access modifiers of these fields. 

  2. Add a method to `betterbst.BstNode` that will return us the children. This will require both `BstElementNode` and `BstEmptyNode` to implement this method, even though it makes no sense for the latter. But then we can freely call this method from the ADT without knowing the node's type.

This "fork-in-the-road" is typical in such designs. Let us generalize this problem: *what happens when one has to add operations that make sense for only some, but not all categorical types?*

Approach 1 emphasizes *type safety*: a type only offers operations that make sense for it. Design-wise this is cleaner, but this means one has to know the type before calling the operation. Approach 2 emphasizes *generality*: one can freely call the operation without knowing the type, but this means that it has to be implemented by types for which it makes no sense.

In this lab, we will choose Option 2 to keep our code clean. 

Add a method `List<BstNode<T>> getChildren()` to `betterbst.BstNode` and implement it in `betterbst.BstElementNode` and `betterbst.BstEmptyNode`. The latter implementation simply returns an empty list (since an empty node, by definition, has no children).

### Questions to Discuss and Ponder

Discuss with the person next to you (or the student you chose to work with) the following questions:

  * Design iterative versions of in-order and post-order traversals. Do you run into issues?
  
  * Why exactly are they more difficult than pre-order?

### 2.4.2 Iterative Preorder: Take 2

We find that "reordering" the processing and recurring parts is not nearly as easy in the iterative versions as it was in the recursive versions! When a node pops from the stack, how do we know whether it has been already processed, or must be processed now?

We will now design an iterative implementation that still uses a stack, but reproduces these operations more "faithfully". The stack should remember not only a node but also what must be done with it.

Implement the following algorithm. You may design your own representations of the data needed by just this algorithm.

```java

1. Create a stack S, that is initially empty.
2. Push ("traverse",root) into S.
3. If S is not empty, go to step 4, else end.
4. Pop an element from S, and call it C.
5. If C is "process", process the corresponding node.
6. If C is "traverse", then push ("traverse",C.node.right), ("traverse",C.node.left) and ("process",C.node) in that order into S.
7. Go to Step 3. 

```

### 2.4.3 Iterative Inorder and Postorder

Discuss with the person next to you (or the student you chose to work with):

  * Design iterative versions of in-order and post-order traversals by modifying the above algorithm.
  
  * What was easy and what was tricky?


**Fix the style, and submit on the submission server.** To submit, select the src/ and test/ folders (in Finder/Explorer) and compress them. Submit this zip file to the server. When you view your submission, you should see src/ and test/ as the two top-level folders.


## 2.5 Even Trickier: Balancing a Tree (Optional)

This part is optional. If you attempt and solve it, do not submit it to the server. This is for your own practice and "algorithmic stimulation".

Earlier in this lab we wrote an operation that checked whether a binary search tree is balanced or not. But how does one balance a binary search tree?

Many standard versions of balanced binary search trees exist, which are commonly used in most BST-based data structures (such as `TreeSet` in Java). Examples include red-black trees, AVL trees, etc. Each of them add more logic to the insert and remove operations so that the tree's balance is *maintained*. 

But given a binary search tree that is already created, can one *balance* it? The answer is yes, but it involves several "rotations" of individual branches. In this optional part, you are asked to ponder about and design an algorithm that would start with *any* binary search tree and then return a balanced version of it.

Here are some hints to get started:

  1. Consider a list of numbers that is sorted in increasing order. If you add these numbers in that order to an initially empty binary search tree, what kind of a tree would you get?

  2. Now consider the same sorted list of numbers. Can you figure out a specific order, in which if you add them to an initially empty binary search tree, you would get a balanced tree? Keep in mind that the tree implementation itself does not have any balancing capability. **Start with small examples of lists, perhaps 4-5 elements long**. Be sure to consider lists of odd and even sizes.

  3. Can you devise an algorithm that will give you the numbers in that specific order? (Hint: a well-known sorting algorithm can provide some inspiration)

  4. If you cracked the code for Step 2-3, how would you obtain these numbers from an existing binary search tree?

# 3 Testing

The provided test class `BinarySearchTreeTest` contains some tests for the methods you are to implement. You are strongly encourage to write more tests for each method, and ensure that all tests pass before submission.
You should use coverage and mutation testing to ensure that your tests are adequate. As usual for labs, your grade will not depend on coverage or mutation score.

# 4 Pawtograder Submission Notes

Note the following when submitting to pawtograder:

- Pushing code to your lab's repository automatically submits it to pawtograder.
- Submission won't count unless all style checks pass. Run style check locally before pushing.
- The following files are required for submission:
    - `src/main/java/betterbst/BstImpl.java`
    - `src/main/java/bst/Bst.java`
    - `src/test/java/BinarySearchTreeTest.java`

Files in src and test other than required files are allowed.

There is no limit on the number of times you can push but always run gradle build locally before pushing.

You can submit multiple times. The last submission before the deadline counts. There is no partial credit on autograder tests.