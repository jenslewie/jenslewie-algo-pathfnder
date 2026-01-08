package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/design-circular-deque">...</a>
 */
public class LeetCode0641_1 {

    int[] queue;
    int head, tail, size, capacity;

    public LeetCode0641_1(int k) {
        queue = new int[k];
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        for (int i = tail; i > head; i--) {
            queue[i % capacity] = queue[(i - 1) % capacity];
        }
        tail++;
        queue[head % capacity] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        queue[tail % capacity] = value;
        tail++;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head++;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail--;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : queue[head % capacity];
    }

    public int getRear() {
        return isEmpty() ? -1 : queue[(tail - 1) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
