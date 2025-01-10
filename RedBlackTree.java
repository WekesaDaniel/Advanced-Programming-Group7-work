
public class RedBlackTree {

    private TreeNode root;
    private TreeNode TNULL; // Null node to represent leaves

    public RedBlackTree() {
        TNULL = new TreeNode(0);
        TNULL.color = false; // Black
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }

    // Insert method
    public void insert(int value) {
        TreeNode newNode = new TreeNode(value);
        newNode.parent = null;
        newNode.left = TNULL;
        newNode.right = TNULL;

        TreeNode temp = null;
        TreeNode current = root;

        while (current != TNULL) {
            temp = current;
            if (newNode.value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        newNode.parent = temp;
        if (temp == null) {
            root = newNode;
        } else if (newNode.value < temp.value) {
            temp.left = newNode;
        } else {
            temp.right = newNode;
        }

        newNode.color = true;
        balanceInsert(newNode);
    }

    // Balance the tree after insertion
    private void balanceInsert(TreeNode node) {
        TreeNode uncle;
        while (node.parent.color == true) {
            if (node.parent == node.parent.parent.right) {
                uncle = node.parent.parent.left;
                if (uncle.color == true) {
                    uncle.color = false;
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.left) {
                        node = node.parent;
                        rotateRight(node);
                    }
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    rotateLeft(node.parent.parent);
                }
            } else {
                uncle = node.parent.parent.right;
                if (uncle.color == true) {
                    uncle.color = false;
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    node = node.parent.parent;
                } else {
                    if (node == node.parent.right) {
                        node = node.parent;
                        rotateLeft(node);
                    }
                    node.parent.color = false;
                    node.parent.parent.color = true;
                    rotateRight(node.parent.parent);
                }
            }
            if (node == root) {
                break;
            }
        }
        root.color = false;
    }

    private void rotateLeft(TreeNode node) {
        TreeNode temp = node.right;
        node.right = temp.left;
        if (temp.left != TNULL) {
            temp.left.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.left) {
            node.parent.left = temp;
        } else {
            node.parent.right = temp;
        }
        temp.left = node;
        node.parent = temp;
    }

    private void rotateRight(TreeNode node) {
        TreeNode temp = node.left;
        node.left = temp.right;
        if (temp.right != TNULL) {
            temp.right.parent = node;
        }
        temp.parent = node.parent;
        if (node.parent == null) {
            root = temp;
        } else if (node == node.parent.right) {
            node.parent.right = temp;
        } else {
            node.parent.left = temp;
        }
        temp.right = node;
        node.parent = temp;
    }

    // Search for a value
    public TreeNode search(int value) {
        TreeNode current = root;
        while (current != TNULL) {
            if (current.value == value) {
                return current;
            }
            current = value < current.value ? current.left : current.right;
        }
        return null;
    }

    // In-Order Traversal
    public String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        inOrderHelper(root, sb);
        return sb.toString();
    }

    private void inOrderHelper(TreeNode node, StringBuilder sb) {
        if (node != TNULL) {
            inOrderHelper(node.left, sb);
            sb.append(node.value).append(" ");
            inOrderHelper(node.right, sb);
        }
    }

    // Delete method
    public void delete(int value) {
        deleteNodeHelper(root, value);
    }

    private void deleteNodeHelper(TreeNode node, int key) {
        TreeNode z = TNULL;
        TreeNode x, y;
        while (node != TNULL) {
            if (node.value == key) {
                z = node;
            }
            if (node.value <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            return;
        }

        y = z;
        boolean yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == false) {
            balanceDelete(x);
        }
    }

    private void transplant(TreeNode u, TreeNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private TreeNode minimum(TreeNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    private void balanceDelete(TreeNode x) {
        TreeNode w;
        while (x != root && x.color == false) {
            if (x == x.parent.left) {
                w = x.parent.right;
                if (w.color == true) {
                    w.color = false;
                    x.parent.color = true;
                    rotateLeft(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == false && w.right.color == false) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.right.color == false) {
                        w.left.color = false;
                        w.color = true;
                        rotateRight(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.right.color = false;
                    rotateLeft(x.parent);
                    x = root;
                }
            } else {
                w = x.parent.left;
                if (w.color == true) {
                    w.color = false;
                    x.parent.color = true;
                    rotateRight(x.parent);
                    w = x.parent.left;
                }
                if (w.right.color == false && w.left.color == false) {
                    w.color = true;
                    x = x.parent;
                } else {
                    if (w.left.color == false) {
                        w.right.color = false;
                        w.color = true;
                        rotateLeft(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    x.parent.color = false;
                    w.left.color = false;
                    rotateRight(x.parent);
                    x = root;
                }
            }
        }
        x.color = false;
    }

    // Get the root of the tree
    public TreeNode getRoot() {
        return root;
    }
}
