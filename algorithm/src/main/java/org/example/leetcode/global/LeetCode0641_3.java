package org.example.leetcode.global;

import org.example.model.linkedlist.ListNode;

/**
 * <a href="https://leetcode.cn/problems/design-circular-deque">...</a>
 */
public class LeetCode0641_3 {

    ListNode head;
    ListNode tail;
    int size, capacity;

    public LeetCode0641_3(int k) {
        capacity = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null || tail == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        ListNode node = new ListNode(value);
        if (head == null || tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        size++;
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        ListNode node = head;
        head = head.next;
        if (head != null) {
            head.prev = null;
        }
        node.next = null;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        ListNode node = tail;
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        }
        node.prev = null;
        size--;
        return true;
    }

    public int getFront() {
        return isEmpty() ? -1 : head.val;
    }

    public int getRear() {
        return isEmpty() ? -1 : tail.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

}
