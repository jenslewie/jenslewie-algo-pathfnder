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

@DisplayName("LeetCode 622: Design Circular Queue - Algorithm Variants")
class LeetCode0622Test {

    @FunctionalInterface
    interface CircularQueueFunction {
        List<Object> apply(List<String> ops, List<List<Integer>> args);
    }

    private static final Map<String, CircularQueueFunction> ALGO_VARIANTS = Map.of(
            "array_with_size", LeetCode0622Test::executeV1,
            "array_with_head_tail", LeetCode0622Test::executeV2,
            "linked_list_based", LeetCode0622Test::executeV3
    );

    @ParameterizedTest(name = "[{index}] {0}, algo={1}, ops={2}, values={3}")
    @MethodSource("allCombinations")
    void testCircularQueue(String caseName, String algoName, List<String> ops, List<List<Integer>> args, List<Object> expected) {
        List<Object> actual = ALGO_VARIANTS.get(algoName).apply(ops, args);
        assertEquals(expected, actual, () -> "Case '%s' with algo='%s' failed. ops=%s, args=%s"
                .formatted(caseName, algoName, ops, args));
    }

    private static List<Object> executeV1(List<String> ops, List<List<Integer>> args) {
        LeetCode0622_1 queue = null;
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("MyCircularQueue".equals(op)) {
                queue = new LeetCode0622_1(args.get(i).get(0));
                result.add(null);
            } else if ("enQueue".equals(op)) {
                result.add(queue.enQueue(args.get(i).get(0)));
            } else if ("deQueue".equals(op)) {
                result.add(queue.deQueue());
            } else if ("Front".equals(op)) {
                result.add(queue.Front());
            } else if ("Rear".equals(op)) {
                result.add(queue.Rear());
            } else if ("isEmpty".equals(op)) {
                result.add(queue.isEmpty());
            } else if ("isFull".equals(op)) {
                result.add(queue.isFull());
            }
        }
        return result;
    }

    private static List<Object> executeV2(List<String> ops, List<List<Integer>> args) {
        LeetCode0622_2 queue = null;
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("MyCircularQueue".equals(op)) {
                queue = new LeetCode0622_2(args.get(i).get(0));
                result.add(null);
            } else if ("enQueue".equals(op)) {
                result.add(queue.enQueue(args.get(i).get(0)));
            } else if ("deQueue".equals(op)) {
                result.add(queue.deQueue());
            } else if ("Front".equals(op)) {
                result.add(queue.Front());
            } else if ("Rear".equals(op)) {
                result.add(queue.Rear());
            } else if ("isEmpty".equals(op)) {
                result.add(queue.isEmpty());
            } else if ("isFull".equals(op)) {
                result.add(queue.isFull());
            }
        }
        return result;
    }

    private static List<Object> executeV3(List<String> ops, List<List<Integer>> args) {
        LeetCode0622_3 queue = null;
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < ops.size(); i++) {
            String op = ops.get(i);
            if ("MyCircularQueue".equals(op)) {
                queue = new LeetCode0622_3(args.get(i).get(0));
                result.add(null);
            } else if ("enQueue".equals(op)) {
                result.add(queue.enQueue(args.get(i).get(0)));
            } else if ("deQueue".equals(op)) {
                result.add(queue.deQueue());
            } else if ("Front".equals(op)) {
                result.add(queue.Front());
            } else if ("Rear".equals(op)) {
                result.add(queue.Rear());
            } else if ("isEmpty".equals(op)) {
                result.add(queue.isEmpty());
            } else if ("isFull".equals(op)) {
                result.add(queue.isFull());
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
                        List.of("MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue", "Rear", "isFull", "deQueue", "enQueue", "Rear"),
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
                        Arrays.asList(null, true, true, true, false, 3, true, true, true, 4)
                ),

                new TestCase("empty_ops",
                        List.of("MyCircularQueue", "Front", "Rear", "deQueue", "isEmpty", "isFull"),
                        List.of(
                                List.of(2),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of()
                        ),
                        Arrays.asList(null, -1, -1, false, true, false)
                ),

                new TestCase("full_ops",
                        List.of("MyCircularQueue", "enQueue", "enQueue", "isFull", "enQueue", "deQueue", "enQueue", "isFull"),
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

                new TestCase("circular_wrap",
                        List.of("MyCircularQueue", "enQueue", "enQueue", "deQueue", "deQueue", "enQueue", "enQueue", "Rear", "Front"),
                        List.of(
                                List.of(2),
                                List.of(100),
                                List.of(200),
                                List.of(),
                                List.of(),
                                List.of(300),
                                List.of(400),
                                List.of(),
                                List.of()
                        ),
                        Arrays.asList(null, true, true, true, true, true, true, 400, 300)
                ),

                new TestCase("single_capacity",
                        List.of("MyCircularQueue", "enQueue", "Rear", "isFull", "deQueue", "isEmpty", "enQueue", "Front"),
                        List.of(
                                List.of(1),
                                List.of(5),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(6),
                                List.of()
                        ),
                        Arrays.asList(null, true, 5, true, true, true, true, 6)
                ),

                new TestCase("capacity_8_full_lifecycle",
                        List.of("MyCircularQueue", "enQueue", "enQueue", "enQueue", "enQueue",
                                "deQueue", "deQueue", "isEmpty", "isEmpty", "Rear", "Rear", "deQueue"),
                        List.of(
                                List.of(8),
                                List.of(3),
                                List.of(9),
                                List.of(5),
                                List.of(0),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of(),
                                List.of()
                        ),
                        Arrays.asList(null, true, true, true, true, true, true, false, false, 0, 0, true)
                )
        );
    }

    private record TestCase(String name, List<String> ops, List<List<Integer>> args, List<Object> expected) {
    }

}
