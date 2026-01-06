package org.example.leetcode.global;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/number-of-recent-calls">...</a>
 */
public class LeetCode0933 {

    static final int TIME = 3000;
    Queue<Integer> queue;

    public LeetCode0933() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);
        while (queue.peek() < t - TIME) {
            queue.poll();
        }
        return queue.size();
    }

}
