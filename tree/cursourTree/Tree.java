package cursourTree;

import java.util.NoSuchElementException;

public class Tree {

    private int root;
    private static final Node[] nodes;
    private static int space;
    private  final static int LENGTH = 10;

    private static class Node {
        private int leftMostChild; //Для SPACE следующий свободный
        private char label; //Значение
        private int rightSibling; //Ссылка на дочерний эл-т

        public Node(int leftMostChild, char label, int rightSibling) {
            this.leftMostChild = leftMostChild;
            this.label = label;
            this.rightSibling = rightSibling;
        }

        public Node() {
            this(-1, ' ',-1);
        }
    }

    static{
        nodes = new Node[LENGTH];

        for (int i = 0; i < LENGTH-1; i++)
            nodes[i] = new Node(-1,' ',i+1);

        nodes[LENGTH-1] = new Node(-1,' ',-1);
        space = 0;
    }

    public Tree(){
        root = -1;
    }

    public Tree(int root) {
        this.root = root;
    }

    //Любая операция должна проверять в том ли дереве узел, если он подается. Это делается обходом (прямым, симметриченым, обратным). Возвращается родитель.

    //Возвращает родителя, так как чтобы не пришлось еще раз обходить. (для методов parent и rightSibling).
    private int findParent(int localRoot, int n){
        Node cur = nodes[root];

        if (cur.leftMostChild == -1)
            return -1;

        int leftMostChild = cur.leftMostChild;
        if (leftMostChild == n)
            return root;


        int p = findParent(n, cur.leftMostChild);
        if (p != -1)
            return p;


        int sibling = nodes[cur.leftMostChild].rightSibling;
        while (sibling != -1) {
            if (sibling == n)
                return root;
            p = findParent(n, sibling);
            if (p != -1)
                return p;
            sibling = nodes[sibling].rightSibling;
        }
        return -1;
    }

    /*public void makeNull(){
        if (root == -1) return; //Если дерево уже и так занулено
        makeNull(root); //Зануление всего кроме корня
        root =-1; //Зануление корня
    }*/

    //Делает дерево пустое (обратный обход) - снизу вверх
    private void makeNull(int localRoot){
        Node cur = nodes[localRoot];

        if (cur.leftMostChild != -1)
            makeNull(cur.leftMostChild);
        else {
            nodes[localRoot].leftMostChild = -1;
            nodes[localRoot].rightSibling = space;
            space = localRoot;
            return;
        }

        int sibling = cur.rightSibling;
        while (sibling != -1) {
            makeNull(sibling);
            sibling = nodes[sibling].rightSibling;
        }

        nodes[localRoot].label = ' ';
        nodes[localRoot].leftMostChild = -1;
        nodes[localRoot].rightSibling = space;
        space = localRoot;
    }

    //Возвращает дерево с 1 корнем с 1 помеченой меткой
    public Tree create(char label){
        if (space == -1)
            return this;

        root = space;
        space = nodes[space].rightSibling;
        nodes[root].leftMostChild = -1;
        nodes[root].label = label;
        nodes[root].rightSibling = -1;
        return this;
    }

    public Tree create(char label, Tree t){
        if (root != -1)
            throw new IllegalArgumentException();

        if (this == t) return create(label);

        if (space == -1) return this;

        root = space;
        space = nodes[space].rightSibling;

        nodes[root].leftMostChild = t.root;
        nodes[root].label = label;
        nodes[root].rightSibling = -1;

        t.root = -1;
        t = null;
        return this;
    }

    //Для помеченного дерева Т возвращает метку узла n
    public char label(int n){
        if (n == root)
            return nodes[root].label;

        if (findParent(n, root()) == -1)
            throw new NoSuchElementException();
        return nodes[n].label;
    }

    //Операция возвращает родителя n в дереве T. Если n-корень, то возвр-ся пустое дерево.
    public int parent(int n){
        if(n == root)
            return -1;
        return findParent(root,n);
    }

    //Возвращает самый левый (то есть первый) сын узла n в дереве T, если n-лист, возвращается пустое дерево.
    public int leftMostChild(int n){
        if (n == root)
            return nodes[root].leftMostChild;

        int parent = findParent(n, root);
        if (parent == -1 || nodes[n].leftMostChild == -1) return -1;

        return nodes[n].leftMostChild;
    }

    //Возвращается правый брат узла n в дереве T. Если нет правого брата, то возвращается.
    public int rightSibling(int n){
        if (n == root)
            return -1;
        int parent = findParent(n, root);
        if (parent == -1 || nodes[n].rightSibling == -1) return -1;

        int sibling = nodes[n].rightSibling;
        while (sibling != n)
            sibling = nodes[sibling].rightSibling;

        if (nodes[sibling].rightSibling == -1)
            return -1;

        return nodes[sibling].rightSibling;
    }

    //Возвращает корень дерева, если дерево пустое, то возвращается лямбда.
    public int root(){
        return root;
    }
}
