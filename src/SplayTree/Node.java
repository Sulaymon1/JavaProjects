package SplayTree;

/**
 * Created by Sulaymon on 15.06.2017.
 */
public class Node {
    private Node left;
    private Node right;
    private Comparable key;

    public Node (Comparable key){
        this.key = key;
        left = null;
        right = null;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Comparable getKey() {
        return key;
    }

    public void setKey(Comparable key) {
        this.key = key;
    }
}
