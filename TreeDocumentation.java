import javax.swing.*;

public class TreeDocumentation {
    public static void showDocs() {
        JOptionPane.showMessageDialog(null,
            "A Red-Black Tree is a self-balancing binary search tree with the following properties:\n" +
            "1. Each node is either red or black.\n" +
            "2. The root is always black.\n" +
            "3. Red nodes cannot have red children (no double red).\n" +
            "4. Every path from a node to a null reference has the same number of black nodes.\n" +
            "\nCRUD Operations:\n" +
            "1. Add: Insert a node and fix violations.\n" +
            "2. Search: Locate a node by value.\n" +
            "3. Delete: Remove a node and fix violations.\n",
            "Red-Black Tree Documentation",
            JOptionPane.INFORMATION_MESSAGE);
    }
}
