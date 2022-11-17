package algorithm.unionfind;

public class SuccessorWithDelete implements UF{
    private int[] id;

    public SuccessorWithDelete(int[] id) {
        this.id = id;
    }

    public int root(int i) {
        int root = i;
        while(this.id[i] != i) {
            this.id[i] = this.id[this.id[i]];
            root = this.id[i];
        }
        return root;
    }

    @Override
    public boolean connected(int rightNode, int leftNode) {
        return root(rightNode) == root(leftNode);    }

    @Override
    public void union(int rightNode, int leftNode) {

    }
}
