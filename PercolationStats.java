/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int t = 0;
    private double[] p;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        t = trials;
        p = new double[t];

        for (int a = 0; a <= trials - 1; a++) {
            Percolation perc = new Percolation(n);
            double counter = 0.0;
            while (!perc.percolates()) {
                int x = StdRandom.uniform(n) + 1;
                int y = StdRandom.uniform(n) + 1;
                if (!perc.isOpen(x, y)) {
                    perc.open(x, y);
                    counter++;
                }
            }
            double percolateT = (counter / (n * n));
            p[a] = percolateT;
        }


    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(p);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (t == 1) {
            return Double.NaN;
        }
        return StdStats.stddev(p);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (1.96 * stddev() / Math.sqrt(t));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (1.96 * stddev() / Math.sqrt(t));
    }

    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        PercolationStats percStats = new PercolationStats(x, y);
        System.out.println("mean = " + percStats.mean());
        System.out.println("Standard deviation = " + percStats.stddev());
        System.out.println(
                "95% confidence interval = [" + percStats.confidenceLo() + ", " + percStats
                        .confidenceHi() + "]");

    }
}
