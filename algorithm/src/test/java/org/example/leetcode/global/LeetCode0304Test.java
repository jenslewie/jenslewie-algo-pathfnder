package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.stream.Stream;

import static org.example.leetcode.utility.ArrayUtility.matrixToString;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 304: Range Sum Query 2D - Immutable")
class LeetCode0304Test {

    @FunctionalInterface
    interface QueryAlgorithm {
        int query(int[][] matrix, int row1, int col1, int row2, int col2);
    }

    private static final Map<String, QueryAlgorithm> ALGO_VARIANTS = Map.of(
            "2d_prefix_sum",
            (matrix, r1, c1, r2, c2) -> {
                LeetCode0304 leetCode = new LeetCode0304(matrix);
                return leetCode.sumRegion(r1, c1, r2, c2);
            },

            "row_prefix_sum",
            (matrix, r1, c1, r2, c2) -> {
                LeetCode0304 leetCode = new LeetCode0304(matrix, "row_prefix_sum");
                return leetCode.sumRegion2(r1, c1, r2, c2);
            }
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, matrix={2}, region=({3},{4})→({5},{6})")
    @MethodSource("allCombinations")
    void testSumRegion(String caseName, String algoName, int[][] matrix, int row1, int col1, int row2, int col2, int expected) {
        int actual = ALGO_VARIANTS.get(algoName).query(matrix, row1, col1, row2, col2);

        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. region=(%d,%d)→(%d,%d), matrix=%s"
                .formatted(caseName, algoName, row1, col1, row2, col2, matrixToString(matrix)));
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(
                        tc.name, algo, tc.matrix, tc.row1, tc.col1, tc.row2, tc.col2, tc.expected
                ))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                // Example from LeetCode
                new TestCase("example",
                        new int[][]{
                                {3, 0, 1, 4, 2},
                                {5, 6, 3, 2, 1},
                                {1, 2, 0, 1, 5},
                                {4, 1, 0, 1, 7},
                                {1, 0, 3, 0, 5}
                        },
                        2, 1, 4, 3, 8),

                // Single cell
                new TestCase("single_cell",
                        new int[][]{{10}}, 0, 0, 0, 0, 10),

                // Full matrix
                new TestCase("full_matrix",
                        new int[][]{
                                {1, 2},
                                {3, 4}
                        }, 0, 0, 1, 1, 10),

                // First row
                new TestCase("first_row",
                        new int[][]{
                                {1, 2, 3},
                                {4, 5, 6}
                        }, 0, 0, 0, 2, 6),

                // Last column
                new TestCase("last_column",
                        new int[][]{
                                {1, 2},
                                {3, 4},
                                {5, 6}
                        }, 0, 1, 2, 1, 12),

                // 1xN matrix
                new TestCase("single_row_matrix",
                        new int[][]{{1, 2, 3, 4, 5}}, 0, 1, 0, 3, 9),

                // Nx1 matrix
                new TestCase("single_column_matrix",
                        new int[][]{{1}, {2}, {3}, {4}}, 1, 0, 2, 0, 5),

                // Negative numbers
                new TestCase("with_negatives",
                        new int[][]{
                                {-1, 2},
                                {3, -4}
                        }, 0, 0, 1, 1, 0),

                // Zero matrix
                new TestCase("zero_matrix",
                        new int[][]{
                                {0, 0},
                                {0, 0}
                        }, 0, 0, 1, 1, 0),

                // Large values
                new TestCase("large_values",
                        new int[][]{
                                {1000000, -1000000},
                                {1000000, -1000000}
                        }, 0, 0, 1, 1, 0)
        );
    }

    private record TestCase(String name, int[][] matrix, int row1, int col1, int row2, int col2, int expected) {
    }

}