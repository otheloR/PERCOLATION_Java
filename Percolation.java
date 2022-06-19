/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] id;
    private int counterOpen = 0;
    private int systemSize = 0;
    private WeightedQuickUnionUF uf;


    //constructor, create n by n grid with all sites initially blocked
    public Percolation(int n) {
        id = new int[n][n];
        uf = new WeightedQuickUnionUF((n * n + 2));

        systemSize = n;
        //ENTER HERE
    }

    private int encode(int x, int y) {
        return systemSize * x + y + 1;

    }

    //opens the site (row, col) if it is not open already

    public void open(int row, int col) {
        int a = row - 1;
        int b = col - 1;
        int x = encode(a, b);

        if (id[a][b] != 1) {
            id[a][b] = 1;
            counterOpen++;

        }
        if (a == 0) {
            uf.union(x, 0);
        }
        if (a == systemSize - 1) {
            uf.union(x, systemSize * systemSize + 1);
        }

        if (a - 1 >= 0) {
            if (id[a - 1][b] == 1) {
                uf.union(x, encode(a - 1, b));
            }
        }
        if (a + 1 < systemSize) {
            if (id[a + 1][b] == 1) {
                uf.union(x, encode(a + 1, b));
            }
        }
        if (b - 1 >= 0) {
            if (id[a][b - 1] == 1) {
                uf.union(x, encode(a, b - 1));
            }
        }
        if (b + 1 < systemSize) {
            if (id[a][b + 1] == 1) {
                uf.union(x, encode(a, b + 1));
            }
        }


    }


    private boolean westE(int col) {
        boolean flip = true;
        if (col < 0 || col >= systemSize) {
            flip = false;
        }
        return flip;
    }

    private boolean northS(int row) {
        boolean flip = true;
        if (row < 0 || row >= systemSize) {
            flip = false;
        }
        return flip;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return id[row - 1][col - 1] == 1;
        //ENTER HERE
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return id[row - 1][col - 1] == 1 && uf
                .connected(encode(row - 1, col - 1), 0);

        //ENTER HERE
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return counterOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(systemSize * systemSize + 1, 0);
        //ENTER HERE
    }


    // test client (optional)
    public static void main(String[] args) {

        Percolation perc = new Percolation(3);
        perc.open(1, 2);
        perc.open(2, 2);
        perc.open(2, 3);
        perc.open(3, 3);

        perc.isFull(3, 3);

        //boolean c1 = perc.uf.connected(perc.ijTo1D(1, 1), perc.ijTo1D(2, 1));
        //boolean c2 = perc.percolates();

        //StdOut.println(c1);
        //StdOut.println(c2);

    }

}

