import javax.swing.*;
import java.awt.*;

public class VisualizationPanel extends JPanel {
    private RedBlackTree tree;

    public VisualizationPanel(RedBlackTree tree) {
        this.tree = tree;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, tree.getRoot(), getWidth() / 2, 30, 40);
    }

    private void drawTree(Graphics g, TreeNode node, int x, int y, int xOffset) {
        if (node != null && node.value != 0) { // TNULL check
            g.drawOval(x - 15, y - 15, 30, 30);
            g.drawString(String.valueOf(node.value), x - 10, y + 5);

            if (node.left != null) {
                g.drawLine(x, y, x - xOffset, y + 50);
                drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2);
            }
            if (node.right != null) {
                g.drawLine(x, y, x + xOffset, y + 50);
                drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2);
            }
        }
    }
}