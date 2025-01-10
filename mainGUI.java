import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private final CRUDOperations crud;
    private final JTextField inputField;
    private final TreeDisplayPanel treeDisplayPanel; // Panel to display the tree

    public MainGUI() {
        crud = new CRUDOperations();

        // Main GUI components
        setTitle("Red-Black Tree CRUD Operations");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Value:");
        inputField = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton searchButton = new JButton("Search");
        JButton deleteButton = new JButton("Delete");
        JButton updateButton = new JButton("Update");

        inputPanel.add(label);
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(searchButton);
        inputPanel.add(deleteButton);
        inputPanel.add(updateButton);

        // Tree Display Panel
        treeDisplayPanel = new TreeDisplayPanel();

        // Add components to main frame
        add(inputPanel, BorderLayout.NORTH);
        add(treeDisplayPanel, BorderLayout.CENTER);

        // Button Actions
        addButton.addActionListener(e -> handleAddNode());
        searchButton.addActionListener(e -> handleSearchNode());
        deleteButton.addActionListener(e -> handleDeleteNode());
        updateButton.addActionListener(e -> handleUpdateNode());
    }

    // Add Node Handler
    private void handleAddNode() {
        try {
            int value = Integer.parseInt(inputField.getText());
            crud.addNode(value);
            inputField.setText("");
            treeDisplayPanel.repaint();
            JOptionPane.showMessageDialog(this, "Node " + value + " added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    // Search Node Handler
    private void handleSearchNode() {
        try {
            int value = Integer.parseInt(inputField.getText());
            TreeNode node = crud.findNode(value);
            if (node != null) {
                JOptionPane.showMessageDialog(this, "Node " + value + " found in the tree.");
            } else {
                JOptionPane.showMessageDialog(this, "Node " + value + " not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    // Delete Node Handler
    private void handleDeleteNode() {
        try {
            int value = Integer.parseInt(inputField.getText());
            crud.removeNode(value);
            inputField.setText("");
            treeDisplayPanel.repaint();
            JOptionPane.showMessageDialog(this, "Node " + value + " deleted successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer value.");
        }
    }

    // Update Node Handler
    private void handleUpdateNode() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter new value for the node:");
            if (input != null) {
                int oldValue = Integer.parseInt(inputField.getText());
                int newValue = Integer.parseInt(input);
                crud.removeNode(oldValue); // Remove the old value
                crud.addNode(newValue);   // Add the new value
                inputField.setText("");
                treeDisplayPanel.repaint();
                JOptionPane.showMessageDialog(this, "Node " + oldValue + " updated to " + newValue + " successfully.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid integer values.");
        }
    }

    // Tree Display Panel
    class TreeDisplayPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            if (crud.getRoot() != null) {
                drawTree(g, crud.getRoot(), getWidth() / 2, 30, getWidth() / 4);
            }
        }

        private void drawTree(Graphics g, TreeNode node, int x, int y, int xOffset) {
            if (node == null) return;

            // Draw the current node
            g.setColor(node.color ? Color.RED : Color.BLACK); // RED or BLACK node
            g.fillOval(x - 15, y - 15, 30, 30);
            g.setColor(Color.WHITE);
            g.drawString(String.valueOf(node.value), x - 7, y + 5);

            // Draw left child
            if (node.left != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x - xOffset, y + 50);
                drawTree(g, node.left, x - xOffset, y + 50, xOffset / 2);
            }

            // Draw right child
            if (node.right != null) {
                g.setColor(Color.BLACK);
                g.drawLine(x, y, x + xOffset, y + 50);
                drawTree(g, node.right, x + xOffset, y + 50, xOffset / 2);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI();
            gui.setVisible(true);
   });
}
}