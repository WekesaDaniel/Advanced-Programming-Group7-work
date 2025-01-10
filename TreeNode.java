public class TreeNode {
    int value;
    TreeNode left, right, parent;
    boolean color; // true for red, false for black

    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = true; 
    }
}