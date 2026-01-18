package org.example.learning.queue;

import java.util.LinkedList;

public class MonotonicQueue<E extends Comparable<E>> {

    LinkedList<E> queue = new LinkedList<>();
    LinkedList<E> maxQueue = new LinkedList<>();
    LinkedList<E> minQueue = new LinkedList<>();

    public void push(E e) {
        queue.offerLast(e);

        while (!maxQueue.isEmpty() && maxQueue.getLast().compareTo(e) < 0) {
            maxQueue.pollLast();
        }
        maxQueue.offerLast(e);

        while (!minQueue.isEmpty() && minQueue.getLast().compareTo(e) > 0) {
            minQueue.pollLast();
        }
        minQueue.offerLast(e);
    }

    public E max() {
        return maxQueue.getFirst();
    }

    public E min() {
        return minQueue.getFirst();
    }

    public E pop() {
        E deleteValue = queue.pollFirst();
        assert deleteValue != null;

        if (deleteValue.equals(maxQueue.getFirst())) {
            maxQueue.pollFirst();
        }
        if (deleteValue.equals(minQueue.getFirst())) {
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
