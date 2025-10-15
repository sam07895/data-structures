package assn06;

public class Main {

    public static void testRemoveTwoChildren() {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] elements = {20, 11, 50, 4, 6, 15, 3, 16, 17, 2};
        for (int elem : elements) {
            tree = (AVLTree<Integer>) tree.insert(elem);
        }

        tree = (AVLTree<Integer>) tree.remove(20);
        tree = (AVLTree<Integer>) tree.remove(4);
        tree = (AVLTree<Integer>) tree.remove(3);

        int[] expected = {2, 6, 11, 15, 16, 17, 50};
        for (int val : expected) {
            if (!tree.contains(val)) {
                System.out.println("Failed: Missing value " + val);
            }
        }
        System.out.println("Passed: Remove two children test.");
    }


    public static void testRemoveLeafNode() {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] elements = {20, 11, 50, 4, 6, 15, 3, 16, 17};
        for (int elem : elements) {
            tree = (AVLTree<Integer>) tree.insert(elem);
        }

        tree = (AVLTree<Integer>) tree.remove(15);

        if (!tree.contains(15)) {
            System.out.println("Passed: Leaf node 15 is removed.");
        } else {
            System.out.println("Failed: Leaf node 15 was not removed.");
        }

        System.out.println("Tree size after removal: " + tree.size());
        System.out.println("Tree height after removal: " + tree.height());
    }

    public static void testInsert() {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] elements = {20, 11, 50, 4, 6, 15, 3, 16, 17};
        for (int elem : elements) {
            tree = (AVLTree<Integer>) tree.insert(elem);
        }

        boolean allPresent = true;
        for (int elem : elements) {
            if (!tree.contains(elem)) {
                allPresent = false;
                System.out.println("Failed: Element " + elem + " not found in the tree.");
            }
        }
        if (allPresent) {
            System.out.println("Passed: All elements are present in the tree.");
        }

    }

    public static void testRemoveNodeWithOneChild() {
        AVLTree<Integer> tree = new AVLTree<>();
        int[] elements = {20, 11, 50, 4, 6, 15, 3, 16, 17, 2};
        for (int elem : elements) {
            tree = (AVLTree<Integer>) tree.insert(elem);
        }

        tree = (AVLTree<Integer>) tree.remove(3);

        if (!tree.contains(3)) {
            System.out.println("Passed: Node 3 is removed.");
        } else {
            System.out.println("Failed: Node 3 was not removed.");
        }

        // Additional checks
        System.out.println("Tree size after removal: " + tree.size());
        System.out.println("Tree height after removal: " + tree.height());
    }

    public static void testRemoveNullElement() {
        AVLTree<Integer> tree = new AVLTree<>();
        tree = (AVLTree<Integer>) tree.insert(10);

        tree = (AVLTree<Integer>) tree.remove(null);

        if (tree.contains(10)) {
            System.out.println("Passed: Tree remains unchanged after attempting to remove null.");
        } else {
            System.out.println("Failed: Tree was altered when removing null.");
        }
    }


    public static void main(String[] args) {
        testRemoveNullElement();
        testRemoveTwoChildren();
        testRemoveNodeWithOneChild();
        testInsert();
        testRemoveLeafNode();
        AVLTree<Integer> tree = new AVLTree<>();
        int[] elements = {20, 11, 50, 4, 6, 15, 3, 16, 17, 2};
        for (int elem : elements) {
            tree = (AVLTree<Integer>) tree.insert(elem);
        }

        tree = (AVLTree<Integer>) tree.remove(20);
        tree = (AVLTree<Integer>) tree.remove(4);
        tree = (AVLTree<Integer>) tree.remove(3);

        if (!tree.contains(20) && !tree.contains(4) && !tree.contains(3)) {
            System.out.println("Passed: Nodes 20, 4, and 3 are removed.");
        } else {
            System.out.println("Failed: One or more nodes were not removed.");
        }

        System.out.println("Tree size after removals: " + tree.size());
        System.out.println("Tree height after removals: " + tree.height());
    }
}