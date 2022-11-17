package algorithm.unionfind;

public class WeightedQuickUnionUF implements UF{
    private final int[] id;
    private final int[] weights;

    public WeightedQuickUnionUF(int size) {
        id = new int[size];
        weights = new int[size];
        for(int i = 0; i < size; i++){
            this.id[i] = i;
            this.weights[i] = 1;
        }
    }
    public int root(int i) {
        int root = i;
        while(this.id[i] != i) {
            this.id[i] = this.id[this.id[i]];
            root = this.id[i];
        }
        return root;
    }

    public int find(int i) {
        int max = i;
        for(int x = 0; x< id.length; x++) {
            if(!connected(i, id[x]))
                continue;
            if(max < id[x])
                max = id[x];
        }
        return max;
    }

    @Override
    public boolean connected(int rightNode, int leftNode) {
        return root(rightNode) == root(leftNode);
    }

    @Override
    public void union(int rightNode, int leftNode) {
        int ridRoot = root(rightNode);
        int lidRoot = root(leftNode);
        if(ridRoot == lidRoot)
            return;
        if(weights[ridRoot] < weights[lidRoot]) {
            id[ridRoot] = id[lidRoot];
            weights[lidRoot]+=weights[ridRoot];
        }
        else {
            id[lidRoot] = id[ridRoot];
            weights[ridRoot]+=weights[ridRoot];
        }
    }
}
