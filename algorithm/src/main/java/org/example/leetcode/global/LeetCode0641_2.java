package org.example.leetcode.global;

/**
 * <a href="https://leetcode.cn/problems/design-circular-deque">...</a>
 */
public class LeetCode0641_2 {

    int[] queue;
    int head, tail, size, capacity;

    public LeetCode0641_2(int k) {
        queue = new int[k];
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        head = (head + capacity - 1) % capacity;
        queue[head % capacity] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        queue[tail++] = value;
        tail %= capacity;
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        head = (head + 1) % capacity;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        tail = (tail + capacity - 1) % capacity;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : queue[head];
    }

    public int getRear() {
        return isEmpty() ? -1 : queue[(tail + capacity - 1) % capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
