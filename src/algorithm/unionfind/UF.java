package algorithm.unionfind;

public interface UF {
    boolean connected(int rightNode, int leftNode);
    void union(int rightNode, int leftNode);
}
