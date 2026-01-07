package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/design-circular-queue">...</a>
 */
public class LeetCode0622_1 {

    int[] queue;
    int head;
    int tail;
    int size;

    public LeetCode0622_1(int k) {
        queue = new int[k];
        head = 0;
        tail = -1;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        tail = (tail + 1) % queue.length;
        queue[tail] = value;
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        queue[head] = -1;
        head = (head + 1) % queue.length;
        size--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == queue.length;
    }

}
