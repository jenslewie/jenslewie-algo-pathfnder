package org.example.learning.queue;

import java.util.LinkedList;

public class MonotonicQueue<E extends Comparable<E>> {

    LinkedList<E> queue = new LinkedList<>();
    LinkedList<E> maxQueue = new LinkedList<>();
    LinkedList<E> minQueue = new LinkedList<>();

    public void push(E e) {
        queue.offerLast(e);

        while (!maxQueue.isEmpty() && maxQueue.peekLast().compareTo(e) < 0) {
            maxQueue.pollLast();
        }
        maxQueue.offerLast(e);

        while (!minQueue.isEmpty() && minQueue.peekLast().compareTo(e) > 0) {
            minQueue.pollLast();
        }
        minQueue.offerLast(e);
    }

    public E max() {
        return maxQueue.peekFirst();
    }

    public E min() {
        return minQueue.peekFirst();
    }

    public E pop() {
        E deleteValue = queue.pollFirst();
        assert deleteValue != null;

        if (deleteValue.equals(maxQueue.peekFirst())) {
            maxQueue.pollFirst();
        }
        if (deleteValue.equals(minQueue.peekFirst())) {
            minQueue.pollFirst();
        }
        return deleteValue;
    }

    public int size() {
        return queue.size();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

}
