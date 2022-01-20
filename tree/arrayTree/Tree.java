package arrayTree;

import java.util.NoSuchElementException;

public class Tree {

    private int root;
    private static final List[] lists;
    private static int space;
    private  final static int LENGTH = 10;

    private static class List{
        private int nextSpace; //Для SPACE следующий свободный
        private char label; //Значение
        private Node child; //Ссылка на дочерний эл-т

        public List(int nextSpace, Node child, char label) {
            this.nextSpace = nextSpace;
            this.child = child;
            this.label = label;
        }

        public List(Node child, char label) {
            this.child = child;
            this.label = label;
        }

        public List(int nextSpace) {
            this.nextSpace = nextSpace;
        }
    }

    private static class Node{
        private int index;
        private Node siblingNext;  //Следующий дочерний эл-т

        public Node(int index, Node next) {
            this.index = index;
            this.siblingNext = next;
        }

        public Node(int index) {
            this.index = index;
        }
    }

    static{
        lists = new List[LENGTH];
        for (int i = 0; i < LENGTH-1; i++)
            lists[i] = new List(i+1);
        lists[LENGTH-1] = new List(-1);
        space=0;
    }

    public Tree(){
        root = -1;
    }

    //Любая операция должна проверять в том ли дереве узел, если он подается. Это делается обходом (прямым, симметриченым, обратным). Возвращается родитель.

    //Возвращает родителя, так как чтобы не пришлось еще раз обходить. (для методов parent и rightSibling).
    private int findParent(int localRoot, int n){
        List cur = lists[localRoot];

        if(cur.child == null)
            return -1;

        int leftMostChild = cur.child.index;
        if (leftMostChild == n)
            return localRoot;

        int p = findParent(n, cur.child.index);
        if(p != -1)
            return p;

        Node sibling = cur.child.siblingNext;
        while (sibling != null ){

            if(sibling.index == n)
                return localRoot;

            p = findParent(n, sibling.index);

            if(p != -1)
                return p;

            sibling = sibling.siblingNext;
        }
        return -1;
    }


    public void makeNull(){
        if (root == -1) return; //Если дерево уже и так занулено
        makeNull(root); //Зануление всего кроме корня
        root =-1; //Зануление корня
    }

    //Делает дерево пустое (обратный обход) - снизу вверх
    private void makeNull(int localRoot){
        List cur = lists[localRoot];

        if (cur.child != null)
            makeNull(cur.child.index); //Если есть дети то сначала необходимо занулить их
        else{
            lists[localRoot].child = null;
            lists[localRoot].nextSpace = space;
            space = root;
            return;
        }

        Node sibling = cur.child.siblingNext;
        while(sibling != null) {
            makeNull(sibling.index);
            sibling = sibling.siblingNext;
        }

        lists[localRoot].child = null;
        lists[localRoot].nextSpace = space;
        space = root;
    }



    //Возвращает дерево с 1 корнем с 1 помеченой меткой
    public Tree create(char label){
        if(space == -1) //Если нет свободного места для выполнения действия.
            return this;

        root = space;//Просто выделяем место под корень и перемещаем указатель SPACE на новое место
        space = lists[space].nextSpace;

        lists[root].label = label;
        return this;
    }

    public Tree create(char label, Tree t){
        if(root != -1) throw new IllegalArgumentException();

        if(this == t) create(label); //Если дерево уже и так соответствует нужному резальтату

        if(space ==-1) return this;

        root = space;
        space = lists[space].nextSpace;

        lists[space].label = label;
        lists[space].child = new Node(t.root);

        t.root = -1;
        t = null;

        return this;
    }

    //Для помеченного дерева Т возвращает метку узла n
    public char label(int n){
        if(n==-1 || findParent(root,n)==-1)
            throw new NoSuchElementException();
        return lists[n].label;
    }

    //Операция возвращает родителя n в дереве T. Если n-корень, то возвр-ся пустое дерево.
    public int parent(int n){
        if(n == root)
            return -1;
        return findParent(root,n);
    }

    //Возвращает самый левый (то есть первый) сын узла n в дереве T, если n-лист, возвращается пустое дерево.
    public int leftMostChild(int n){
        if(lists[n].child == null || findParent(root,n) ==-1)
            return -1;

        return lists[n].child.index;
    }

    //Возвращается правый брат узла n в дереве T. Если нет правого брата, то возвращается.
    public int rightSibling(int n){
        if(n == root || findParent(root,n) == -1)
            return -1;

        Node sibling = lists[findParent(root,n)].child;
        while(sibling.index != n)
            sibling = sibling.siblingNext;

        if(sibling.siblingNext == null)
            return -1;

        return sibling.siblingNext.index;
    }

    //Возвращает корень дерева, если дерево пустое, то возвращается лямбда.
    public int root(){
        return root;
    }
}
