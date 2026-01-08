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

@DisplayName("LeetCode 641: Design Circular Deque - Algorithm Variants")
class LeetCode0641Test {

    @FunctionalInterface
    interface CircularDequeFunction {
        List<Object> apply(List<String> ops, List<List<Integer>> args);
    }

    private static final Map<String, CircularDequeFunction> ALGO_VARIANTS = Map.of(
            "array_with_shift", LeetCode0641Test::executeV1,
            "array_with_mod", LeetCode0641Test::executeV2,
            "doubly_linked_list", LeetCode0641Test::executeV3
    );

    @ParameterizedTest(name = "[{index}] {0}, algo={1}, ops={2}, args={3}")
    @MethodSource("allCombinations")
    void testCircularDeque(String caseName, String algoName, List<String> ops, List<List<Integer>> args, List<Object> expected) {
        List<Object> actual = ALGO_VARIANTS.get(algoName).apply(ops, args);
        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. ops=%s, args=%s"
                .formatted(caseName, algoName, ops, args));
    }

    // --- V1: array + shift on insertFront ---
    private static List<Object> executeV1(List<String> ops, List<List<Integer>> args) {
        LeetCode0641_1 deque = null;
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("MyCircularDeque".equals(op)) {
                deque = new LeetCode0641_1(args.get(i).get(0));
                result.add(null);
            } else if ("insertFront".equals(op)) {
                result.add(deque.insertFront(args.get(i).get(0)));
            } else if ("insertLast".equals(op)) {
                result.add(deque.insertLast(args.get(i).get(0)));
            } else if ("deleteFront".equals(op)) {
                result.add(deque.deleteFront());
            } else if ("deleteLast".equals(op)) {
                result.add(deque.deleteLast());
            } else if ("getFront".equals(op)) {
                result.add(deque.getFront());
            } else if ("getRear".equals(op)) {
                result.add(deque.getRear());
            } else if ("isEmpty".equals(op)) {
                result.add(deque.isEmpty());
            } else if ("isFull".equals(op)) {
                result.add(deque.isFull());
            }
        }
        return result;
    }

    // --- V2: array + mod arithmetic ---
    private static List<Object> executeV2(List<String> ops, List<List<Integer>> args) {
        LeetCode0641_2 deque = null;
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("MyCircularDeque".equals(op)) {
                deque = new LeetCode0641_2(args.get(i).get(0));
                result.add(null);
            } else if ("insertFront".equals(op)) {
                result.add(deque.insertFront(args.get(i).get(0)));
            } else if ("insertLast".equals(op)) {
                result.add(deque.insertLast(args.get(i).get(0)));
            } else if ("deleteFront".equals(op)) {
                result.add(deque.deleteFront());
            } else if ("deleteLast".equals(op)) {
                result.add(deque.deleteLast());
            } else if ("getFront".equals(op)) {
                result.add(deque.getFront());
            } else if ("getRear".equals(op)) {
                result.add(deque.getRear());
            } else if ("isEmpty".equals(op)) {
                result.add(deque.isEmpty());
            } else if ("isFull".equals(op)) {
                result.add(deque.isFull());
            }
        }
        return result;
    }

    // --- V3: doubly linked list ---
    private static List<Object> executeV3(List<String> ops, List<List<Integer>> args) {
        LeetCode0641_3 deque = null;
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("MyCircularDeque".equals(op)) {
                deque = new LeetCode0641_3(args.get(i).get(0));
                result.add(null);
            } else if ("insertFront".equals(op)) {
                result.add(deque.insertFront(args.get(i).get(0)));
            } else if ("insertLast".equals(op)) {
                result.add(deque.insertLast(args.get(i).get(0)));
            } else if ("deleteFront".equals(op)) {
                result.add(deque.deleteFront());
            } else if ("deleteLast".equals(op)) {
                result.add(deque.deleteLast());
            } else if ("getFront".equals(op)) {
                result.add(deque.getFront());
            } else if ("getRear".equals(op)) {
                result.add(deque.getRear());
            } else if ("isEmpty".equals(op)) {
                result.add(deque.isEmpty());
            } else if ("isFull".equals(op)) {
                result.add(deque.isFull());
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
                new TestCase("specified_case",
                        List.of("MyCircularDeque", "insertFront", "deleteLast", "getRear", "getFront",
                                "getFront", "deleteFront", "insertFront", "insertLast", "insertFront", "getFront", "insertFront"),
                        List.of(
                                List.of(4),
                                List.of(9),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(6),
                                List.of(5),
                                List.of(9),
                                List.of(),
                                List.of(6)
                        ),
                        Arrays.asList(null, true, true, -1, -1, -1, false, true, true, true, 9, true)
                ),

                // LeetCode Example 1
                new TestCase("example_1",
                        List.of("MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear",
                                "isFull", "deleteLast", "insertFront", "getFront"),
                        List.of(
                                List.of(3),
                                List.of(1),
                                List.of(2),
                                List.of(3),
                                List.of(4),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(4),
                                List.of()
                        ),
                        Arrays.asList(null, true, true, true, false, 2, true, true, true, 4)
                ),

                // Empty deque operations
                new TestCase("empty_ops",
                        List.of("MyCircularDeque", "getFront", "getRear", "deleteFront", "deleteLast", "isEmpty", "isFull"),
                        List.of(
                                List.of(2),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of()
                        ),
                        Arrays.asList(null, -1, -1, false, false, true, false)
                ),

                // Full deque operations
                new TestCase("full_ops",
                        List.of("MyCircularDeque", "insertFront", "insertFront", "isFull", "insertLast", "deleteFront", "insertLast", "isFull"),
                        List.of(
                                List.of(2),
                                List.of(10),
                                List.of(20),
                                List.of(),
                                List.of(30),
                                List.of(),
                                List.of(30),
                                List.of()
                        ),
                        Arrays.asList(null, true, true, true, false, true, true, true)
                ),

                // Mixed operations
                new TestCase("mixed_ops",
                        List.of("MyCircularDeque", "insertLast", "insertFront", "getFront", "getRear", "deleteLast",
                                "getRear", "deleteFront", "isEmpty"),
                        List.of(
                                List.of(3),
                                List.of(1),
                                List.of(2),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of()
                        ),
                        Arrays.asList(null, true, true, 2, 1, true, 2, true, true)
                )
        );
    }

    private record TestCase(String name, List<String> ops, List<List<Integer>> args, List<Object> expected) {
    }

}
