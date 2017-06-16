package SplayTree;

/**
 * Created by Sulaymon on 15.06.2017.
 */
public class SplayTree {
    Node root;

    public SplayTree(){
        root = null;
    }

    public void add(Comparable key){
        Node node;
        int com;
        if(root == null) {
            root = new Node(key);
            return;
        }
        splay(key);
        if ((com = key.compareTo(root.getKey())) == 0)
            return;
        node = new Node(key);
        if (com < 0){
            node.setLeft(root.getLeft());
            node.setRight(root);
            root.setLeft(null);
        }else {
            node.setRight(root.getRight());
            node.setLeft(root);
            root.setRight(null);
        }
        root = node;
    }

    public void remove (Comparable key){
        Node x;
        splay(key);
        if (key.compareTo(root.getKey()) != 0)
            return;
        if (root.getLeft() == null)
            root = root.getRight();
        else {
            x = root.getRight();
            root = root.getLeft();
            splay(key);
            root.setRight(x);
        }
    }

    public Comparable findMin (){
        Node min = root;
        if (root == null)
            return null;
        while (min.getLeft() != null)min = min.getLeft();
        splay(min.getKey());
        return min.getKey();
    }

    public Comparable findMax (){
        Node min = root;
        if (root == null)
            return null;
        while (min.getRight() != null)min = min.getRight();
        splay(min.getKey());
        return min.getKey();
    }

    public Comparable find (Comparable key){
        if (root.getKey() == null) return  null;
        splay(key);
        if (root.getKey().compareTo(key) !=0) return null;
        return root.getKey();
    }

    public void moveToRoot (Comparable key){
        Node l, r, t;
        l = r = header;
        t = root;
        header.setLeft(null);
        header.setRight(null);
        for (;;){
            if (key.compareTo(t.getKey()) < 0){
                if (t.getLeft() == null) break;
                r.setLeft(t);
                r = t;
                t = t.getLeft();
            } else if (key.compareTo(t.getKey()) > 0){
                if (t.getRight() == null)break;
                l.setRight(t);
                l = t;
                t = t.getRight();
            }else
                break;
        }
        l.setRight(t.getLeft());
        r.setLeft(t.getRight());
        t.setLeft(header.getRight());
        t.setRight(header.getLeft());
        root = t;
    }

    private void splay (Comparable key){
        Node l, r, t, y;
        l = r = header;
        t = root;
        header.setLeft(null);
        header.setRight(null);
        for (;;){
            if (key.compareTo(t.getKey()) < 0){
                if (t.getLeft() == null) break;
                if (key.compareTo(t.getLeft().getKey()) < 0){
                    y = t.getLeft();
                    t.setLeft(y.getRight());
                    y.setRight(t);
                    t = y;
                    if (t.getLeft() == null) break;
                }
                r.setLeft(t);
                r = t;
                t = t.getLeft();
            } else if (key.compareTo(t.getKey()) > 0){
                if (t.getRight() == null)break;
                if (key.compareTo(t.getRight().getKey()) > 0){
                    y = t.getRight();
                    t.setRight(y.getLeft());
                    y.setLeft(t);
                    y = t;
                    if (t.getRight() == null) break;
                }
                l.setRight(t);
                l = t;
                t = t.getRight();
            }else
                break;
        }
        l.setRight(t.getLeft());
        r.setLeft(t.getRight());
        t.setLeft(header.getRight());
        t.setRight(header.getLeft());
        root = t;
    }
    public boolean isEmpty (){
        return root == null;
    }

    private static Node header = new Node(null);

}
