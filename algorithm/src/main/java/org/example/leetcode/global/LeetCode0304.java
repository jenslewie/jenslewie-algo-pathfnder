package org.example.leetcode.global;

public class LeetCode0304 {

    private final int[][] preSum;

    /**
     *                   0  0  0  0  0  0
     * 3 0 1 4 2         0  3  3  4  8 10
     * 5 6 3 2 1 =====>  0  8 14 18 24 27
     * 1 2 0 1 5         0  9 17 21 28 36
     * 4 1 0 1 7         0 13 22 26 34 49
     * 1 0 3 0 5         0 14 23 30 38 58
     * @param matrix
     */
    public LeetCode0304(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
    }

    /**
     * 3 0 1 4 2         0  3  3  4  8 10
     * 5 6 3 2 1        10 15 21 24 26 27
     * 1 2 0 1 5 =====> 27 28 30 30 31 36
     * 4 1 0 1 7        36 40 41 41 42 49
     * 1 0 3 0 5        49 50 50 53 53 58
     * @param matrix
     * @param antherVersion
     */
    public LeetCode0304(int[][] matrix, String antherVersion) {
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m][n + 1];
        for (int i = 0; i < m; i++) {
            preSum[i][0] = 0;
        }
        for (int i = 0; i < m; i++) {
            if (i > 0) {
                preSum[i][0] = preSum[i - 1][n];
            }
            for (int j = 0; j < n; j++) {
                preSum[i][j + 1] = preSum[i][j] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return preSum[row2 + 1][col2 + 1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1] + preSum[row1][col1];
    }

    public int sumRegion2(int row1, int col1, int row2, int col2) {
        int result = 0;
        for (int i = row1; i <= row2; i++) {
            result += (preSum[i][col2 + 1] - preSum[i][col1]);
        }
        return result;
    }

}
