// ===== Member 2: Tree Data Organizer (Node Class) =====
class AVLNode {
    String key;
    int height;
    AVLNode left, right;

    AVLNode(String key) {
        this.key = key;
        height = 1;
    }
}
