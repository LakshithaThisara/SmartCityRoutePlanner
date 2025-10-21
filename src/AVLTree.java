// ===== Member 2: Tree Data Organizer (AVL Tree Structure) =====
import java.util.*;

public class AVLTree {
    private AVLNode root;

    public boolean insert(String key) {
        if (contains(root, key)) return false;
        root = insertRec(root, key);
        return true;
    }

    public boolean delete(String key) {
        if (!contains(root, key)) return false;
        root = deleteRec(root, key);
        return true;
    }

    public List<String> inorder() {
        List<String> res = new ArrayList<>();
        inorderRec(root, res);
        return res;
    }

    // ==== Private helper methods ====
    private AVLNode insertRec(AVLNode node, String key) {
        if (node == null) return new AVLNode(key);
        if (key.compareTo(node.key) < 0)
            node.left = insertRec(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = insertRec(node.right, key);
        return balance(node);
    }

    private AVLNode deleteRec(AVLNode node, String key) {
        if (node == null) return null;
        if (key.compareTo(node.key) < 0)
            node.left = deleteRec(node.left, key);
        else if (key.compareTo(node.key) > 0)
            node.right = deleteRec(node.right, key);
        else {
            if (node.left == null || node.right == null)
                node = (node.left != null) ? node.left : node.right;
            else {
                AVLNode minNode = findMin(node.right);
                node.key = minNode.key;
                node.right = deleteRec(node.right, minNode.key);
            }
        }
        return balance(node);
    }

    private void inorderRec(AVLNode node, List<String> res) {
        if (node != null) {
            inorderRec(node.left, res);
            res.add(node.key);
            inorderRec(node.right, res);
        }
    }

    private boolean contains(AVLNode node, String key) {
        if (node == null) return false;
        if (key.equals(node.key)) return true;
        return key.compareTo(node.key) < 0 ? contains(node.left, key) : contains(node.right, key);
    }

    private AVLNode balance(AVLNode node) {
        if (node == null) return null;
        updateHeight(node);
        int balanceFactor = getBalance(node);
        if (balanceFactor > 1) {
            if (getBalance(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        } else if (balanceFactor < -1) {
            if (getBalance(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private void updateHeight(AVLNode n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int height(AVLNode n) {
        return n == null ? 0 : n.height;
    }

    private int getBalance(AVLNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    private AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode t2 = x.right;
        x.right = y;
        y.left = t2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode t2 = y.left;
        y.left = x;
        x.right = t2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    private AVLNode findMin(AVLNode node) {
        while (node.left != null) node = node.left;
        return node;
    }
}
