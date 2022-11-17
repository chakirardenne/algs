package algorithm.unionfind;

public class QuickFindUF implements UF {
    private int[] id;

    public QuickFindUF(int size) {
        id = new int[size];
        for(int i = 0; i < size; i++){
            this.id[i] = i;
        }
    }

    @Override
    public boolean connected(int rightNode, int leftNode) {
        return id[rightNode] == id[leftNode];
    }

    @Override
    public void union(int rightNode, int leftNode) {
        int pid = id[rightNode];
        int lid = id[leftNode];
        for(int i = 0; i< id.length; i++) {
            if(id[i] == pid)
                id[i] = lid;
        }
    }
}
