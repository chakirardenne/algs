package algorithm.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF ids;
    private final int[] gridState;
    private final int size;
    private int nbOfOpenSite = 0;
    private final int virtualTop;
    private final int virtualBottom;


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("can't accept value for grid initialization");
        ids = new WeightedQuickUnionUF(n * n + 2);
        gridState = new int[n * n];
        size = n;
        virtualTop = size*size;
        virtualBottom = size*size +1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateCoordinates(row, col);
        if (isOpen(row, col))
            return;
        if (row == 0)
            ids.union(virtualTop, to1dArray(row, col));
        if (row == size - 1)
            ids.union(virtualBottom, to1dArray(row, col));

        int id = to1dArray(row, col);
        gridState[id] = 1;
        nbOfOpenSite++;

        if (isOnGrid(row + 1, col) && isOpen(row + 1, col))
            ids.union((row + 1) * size + col, id);
        if (isOnGrid(row - 1, col) && isOpen(row - 1, col))
            ids.union((row - 1) * size + col, id);
        if (isOnGrid(row, col + 1) && isOpen(row, col + 1))
            ids.union(row * size + col + 1, id);
        if (isOnGrid(row, col - 1) && isOpen(row, col - 1))
            ids.union(row * size + col - 1, id);
    }

    private void validateCoordinates(int row, int col) {
        if (!isOnGrid(row, col))
            throw new IllegalArgumentException("incorrect site coordinates : " + row + " " + col);
    }

    private int to1dArray(int row, int col) {
        return row * size + col;
    }

    private boolean isOnGrid(int row, int col) {
        return row >= 0 && col >= 0 && row < size && col < size;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateCoordinates(row, col);
        int id = to1dArray(row, col);
        return gridState[id] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateCoordinates(row, col);
        return ids.find(virtualTop) == ids.find(to1dArray(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nbOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        return ids.find(virtualTop) == ids.find(virtualBottom);
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
