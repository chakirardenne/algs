package algorithm.percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final WeightedQuickUnionUF ids;
    private final int[] gridState;
    private final int size;
    private int nbOfOpenSite = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("can't accept value for grid initialization");
        ids = new WeightedQuickUnionUF(n * n);
        gridState = new int[n * n];
        size = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <0 || row > size || col <0 || col > size)
            throw new IllegalArgumentException("incorrect site coordinates : " + row + " " + col);
        if (!isOpen(row, col)) {
            int id = row * size + col;
            gridState[row * size + col] = 1;
            nbOfOpenSite++;


            if(isOnBorders(row,col)) {
                if(isOnFirstOrLastRow(row))
                    unionOnFirstOrLastRow(row, col, id);
                if(isOnFirstOrLastColumn(col))
                    unionOnFirstOrLastColumn(row, col, id);
            }
            else {
                unionNotOnBordersCells(row, col, id);
            }

        }
    }

    private void unionOnFirstOrLastColumn(int row, int col, int id) {
    }

    private void unionOnFirstOrLastRow(int row, int col, int id) {

    }

    private boolean isOnFirstOrLastColumn(int col) {
        return col == 0 || col == size-1;
    }

    private boolean isOnFirstOrLastRow(int row) {
        return row == 0 || row == size-1;
    }

    private boolean isOnBorders(int row, int col) {
        return row == 0 || col == 0 || row == size - 1 || col == size - 1;
    }

    private void unionNotOnBordersCells(int row, int col, int id) {
        if (isOpen(row + 1, col) && isFull(row + 1, col))
            ids.union((row + 1) * size + col, id);
        if (isOpen(row - 1, col) && isFull(row - 1, col))
            ids.union((row - 1) * size + col, id);
        if (isOpen(row, col + 1) && isFull(row, col + 1))
            ids.union(row * size + col + 1, id);
        if (isOpen(row, col - 1) && isFull(row, col - 1))
            ids.union(row * size + col - 1, id);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <0 || row > size || col <0|| col > size)
            throw new IllegalArgumentException("incorrect site coordinates : " + row + " " + col);
        int id = row * size + col;
        return gridState[id] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        for (int i = 0; i <size ; i++) {
            if(ids.find(row*size+col) == ids.find(i))
                return true;
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nbOfOpenSite;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < size; i++) {
            for (int j = size * size; j < size * size - size; j++) {
                if (ids.find(i) == ids.find(j))
                    return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
    }
}
