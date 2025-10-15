package assn04;

public class Main {
  public static void main(String[] args) {
    /*
    This is a basic example of how to create a BST and add values to it.
    You should add more examples and use this class to debug your code
    */
    BST<Integer> bst = new NonEmptyBST<Integer>(3);
      bst = bst.insert(8);
      bst = bst.insert(1);
      bst = bst.insert(9);
      bst.printPreOrderTraversal();


    // Example 1: Original Tree: 20, 15, 30, 10, 18, 25, 35
    // replace_range (T start 15, T end 30, T newValue 39)
    BST<Integer> bst1 = new NonEmptyBST<>(20);
    bst1 = bst1.insert(15);
    bst1 = bst1.insert(30);
    bst1 = bst1.insert(10);
    bst1 = bst1.insert(18);
    bst1 = bst1.insert(25);
    bst1 = bst1.insert(35);

    System.out.println("Original Tree 1 (Pre-Order):");
    bst1.printPreOrderTraversal();
    System.out.println("\nAfter replace_range (15, 30, 39):");
    bst1 = ((NonEmptyBST<Integer>) bst1).replace_range(15, 30, 39);
    bst1.printPreOrderTraversal();
    System.out.println();

    // Example 2: Original Tree: 20, 15, 30, 10, 18, 25, 35
    // replace_range (T start 15, T end 30, T newValue 28)
    BST<Integer> bst2 = new NonEmptyBST<>(20);
    bst2 = bst2.insert(15);
    bst2 = bst2.insert(30);
    bst2 = bst2.insert(10);
    bst2 = bst2.insert(18);
    bst2 = bst2.insert(25);
    bst2 = bst2.insert(35);

    System.out.println("Original Tree 2 (Pre-Order):");
    bst2.printPreOrderTraversal();
    System.out.println("\nAfter replace_range (15, 30, 28):");
    bst2 = ((NonEmptyBST<Integer>) bst2).replace_range(15, 30, 28);
    bst2.printPreOrderTraversal();
    System.out.println();




  }

}
