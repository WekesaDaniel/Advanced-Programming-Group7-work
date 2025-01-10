public class CRUDOperations {
    private final RedBlackTree tree;

    public CRUDOperations() {
        this.tree = new RedBlackTree();
    }

    // Add a node to the tree
    public void addNode(int value) {
        tree.insert(value);
    }

    // Find a node in the tree
    public TreeNode findNode(int value) {
        return tree.search(value);
    }

    // Remove a node from the tree
    public void removeNode(int value) {
        tree.delete(value); 
    }

    // Get tree contents in in-order traversal format
    public String getTreeContents() {
        return tree.inOrderTraversal();
    }

    // Get the root of the tree 
    public TreeNode getRoot() {
        return tree.getRoot();
    }
}
