package org.example.leetcode.global;

import org.example.model.linkedlist.ListNode;

/**
 * <a href="https://leetcode.cn/problems/design-circular-queue">...</a>
 */
public class LeetCode0622_3 {

    ListNode head;
    ListNode tail;
    int capacity;
    int size;

    public LeetCode0622_3(int k) {
        capacity = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null) {
            tail = head = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        ListNode temp = head;
        head = head.next;
        temp.next = null;
        size--;
        return true;
    }

    public int Front() {
        return isEmpty() ? -1 : head.val;
    }

    public int Rear() {
        return isEmpty() ? -1 : tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
