package org.example.leetcode.lcr;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * <a href="https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof">...</a>
 */
public class LeetCode0183 {

    public int[] maxAltitude(int[] heights, int limit) {
        int n = heights.length;
        if (n == 0 || limit == 0) {
            return new int[0];
        }

        Deque<Integer> queue = new ArrayDeque<>();
        int[] ans = new int[n - limit + 1];

        for (int i = 1 - limit, j = 0; j < n; i++, j++) {
            if (i > 0 && queue.peekFirst() == heights[i - 1]) {
                queue.removeFirst();
            }
            while (!queue.isEmpty() && queue.peekLast() < heights[j]) {
                queue.removeLast();
            }
            queue.offer(heights[j]);
            if (i >= 0) {
                ans[i] = queue.peekFirst();
            }
        }

        return ans;
    }

    public int[] maxAltitude2(int[] heights, int limit) {
        int n = heights.length;
        if (n == 0 || limit == 0) {
            return new int[0];
        }

        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1]);
        int[] ans = new int[n - limit + 1];

        for (int i = 0; i < limit; i++) {
            queue.offer(new int[]{heights[i], i});
        }
        ans[0] = queue.peek()[0];

        for (int i = limit; i < n; i++) {
            queue.offer(new int[]{heights[i], i});
            while (queue.peek()[1] <= i - limit) {
                queue.poll();
            }
            ans[i - limit + 1] = queue.peek()[0];
        }

        return ans;
    }

}
