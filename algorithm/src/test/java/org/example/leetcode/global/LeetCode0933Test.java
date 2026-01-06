package org.example.leetcode.global;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("LeetCode 933: Number of Recent Calls - Algorithm Variants")
class LeetCode0933Test {

    @FunctionalInterface
    interface RecentCallsFunction {
        List<Object> apply(List<String> ops, List<List<Integer>> args);
    }

    private static final Map<String, RecentCallsFunction> ALGO_VARIANTS = Map.of(
            "sliding_window_queue", LeetCode0933Test::executeOperations
    );

    @ParameterizedTest(name = "[{index}] case={0}, algo={1}, ops={2}, values={3}")
    @MethodSource("allCombinations")
    void testRecentCalls(String caseName, String algoName, List<String> ops, List<List<Integer>> args, List<Object> expected) {
        List<Object> actual = ALGO_VARIANTS.get(algoName).apply(ops, args);

        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. ops=%s, args=%s"
                .formatted(caseName, algoName, ops, args));
    }

    private static List<Object> executeOperations(List<String> ops, List<List<Integer>> args) {
        LeetCode0933 counter = null;
        List<Object> result = new ArrayList<>();

        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("RecentCounter".equals(op)) {
                counter = new LeetCode0933();
                result.add(null);
            } else if ("ping".equals(op)) {
                int t = args.get(i).get(0);
                result.add(counter.ping(t));
            }
        }
        return result;
    }

    private static Stream<Arguments> allCombinations() {
        return testCases().flatMap(tc -> ALGO_VARIANTS.keySet().stream()
                .map(algo -> Arguments.of(tc.name, algo, tc.ops, tc.args, tc.expected))
        );
    }

    private static Stream<TestCase> testCases() {
        return Stream.of(
                new TestCase("example",
                        List.of("RecentCounter", "ping", "ping", "ping", "ping"),
                        List.of(
                                List.of(),        // 替换 new int[]{}
                                List.of(1),       // 替换 new int[]{1}
                                List.of(100),     // 替换 new int[]{100}
                                List.of(3001),    // 替换 new int[]{3001}
                                List.of(3002)     // 替换 new int[]{3002}
                        ),
                        Arrays.asList(null, 1, 2, 3, 3)
                ),

                new TestCase("dense_calls",
                        List.of("RecentCounter", "ping", "ping", "ping", "ping"),
                        List.of(
                                List.of(),
                                List.of(1),
                                List.of(2),
                                List.of(3),
                                List.of(4)
                        ),
                        Arrays.asList(null, 1, 2, 3, 4)
                ),

                new TestCase("sparse_calls",
                        List.of("RecentCounter", "ping", "ping", "ping"),
                        List.of(
                                List.of(),
                                List.of(1),
                                List.of(3002),
                                List.of(6005)
                        ),
                        Arrays.asList(null, 1, 1, 1)
                ),

                new TestCase("boundary_3000",
                        List.of("RecentCounter", "ping", "ping", "ping"),
                        List.of(
                                List.of(),
                                List.of(0),
                                List.of(3000),
                                List.of(6000)
                        ),
                        Arrays.asList(null, 1, 2, 2)
                )
        );
    }

    private record TestCase(String name, List<String> ops, List<List<Integer>> args, List<Object> expected) {
    }

}
