package algorithm;

import algorithm.percolation.Percolation;
import algorithm.unionfind.QuickUnionUF;
import edu.princeton.cs.algs4.StdRandom;

import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) {
//      quickUnionFind();
        percolation();
    }

    private static void quickUnionFind() {
        QuickUnionUF quickUnionUF = new QuickUnionUF(10);
        quickUnionUF.union(5, 2);
        quickUnionUF.union(3, 2);
        quickUnionUF.union(9,5);
        quickUnionUF.union(8,7);

        System.out.println(quickUnionUF.root(7));
    }

    private static void percolation() {
        Percolation percolation = new Percolation(5);
        while(!percolation.percolates()) {
            percolation.open(StdRandom.uniformInt(5), StdRandom.uniformInt(5));
            System.out.println(percolation.numberOfOpenSites());
        }
    }
}
