package be.kdg.generics;

import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

public class PriorityQueue<T> implements FIFOQueue<T> {
    private final TreeMap<Integer, LinkedList<T>> QUEUE;

    public PriorityQueue() {
        QUEUE = new TreeMap<>(Collections.reverseOrder());

    }

    @Override
    public boolean enqueue(T element, int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("Index has to be from 1 to 5");
        }

        for (int i = 1; i <= QUEUE.size(); i++) {
            if (QUEUE.get(i) != null) {
                for (int j = 0; j < QUEUE.get(i).size(); j++) {
                    if (QUEUE.get(i).get(j).equals(element)) {
                        return false;
                    }
                }
            }
        }
        QUEUE.computeIfAbsent(priority, k -> new LinkedList<>());
        QUEUE.get(priority).addLast(element);

        return true;
    }

    @Override
    public T dequeue() {
        for (int i = 5; i >= 1; i--) {
            if (QUEUE.get(i) != null && !QUEUE.get(i).isEmpty()) {
                return QUEUE.get(i).remove();
            }
        }
        return null;
    }

    @Override
    public int search(T element) {
        for (int i = 1; i <= QUEUE.size(); i++) {
            if (QUEUE.get(i) != null && QUEUE.get(i).contains(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int getSize() {
        int counter = 0;

        for (int i = 1; i <= QUEUE.size(); i++) {
            if (QUEUE.get(i) != null) {
                counter += QUEUE.get(i).size();
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 5; i > 0; i--) {
            if (QUEUE.get(i) != null && !QUEUE.get(i).isEmpty()) {
                for (int j = 0; j < QUEUE.get(i).size(); j++) {
                    stringBuilder.append(String.format("%d: %s\n", i, QUEUE.get(i).get(j)));
                }
            }
        }
        return stringBuilder.toString();
    }
}
