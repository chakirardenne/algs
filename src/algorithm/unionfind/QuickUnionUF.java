package algorithm.unionfind;

public class QuickUnionUF implements UF{
    private int[] id;

    public QuickUnionUF(int size) {
        id = new int[size];
        for(int i = 0; i < size; i++){
            this.id[i] = i;
        }
    }

    public int root(int i) {
        if(this.id[i] == i)
            return i;
        return root((this.id[i]));
    }

    @Override
    public boolean connected(int rightNode, int leftNode) {
        return root(rightNode) == root(leftNode);
    }

    @Override
    public void union(int rightNode, int leftNode) {
        int pidRoot = root(rightNode);
        int lidRoot = root(leftNode);
        id[pidRoot] = id[lidRoot];
    }
}
