package be.kdg.generics;

import java.util.Collections;
import java.util.LinkedList;
import java.util.TreeMap;

/**
 * The PriorityQueue class represents a generic priority queue.
 * It implements the FIFOQueue interface and uses a TreeMap to store elements with their priorities.
 * The TreeMap is initialized with a reverse order comparator, so higher priorities come first.
 *
 * @param <T> the type of elements held in this queue
 */
public class PriorityQueue<T> implements FIFOQueue<T> {
    //region vars

    /**
     * The TreeMap used to store elements with their priorities.
     * Higher priorities come first.
     */
    private final TreeMap<Integer, LinkedList<T>> QUEUE;

    //endregion

    //region constructors

    /**
     * Constructor for the PriorityQueue class.
     * Initializes a new PriorityQueue object with an empty TreeMap.
     */
    public PriorityQueue() {
        QUEUE = new TreeMap<>(Collections.reverseOrder());
    }

    //endregion

    /**
     * Inserts the specified element into this queue with the given priority.
     * The priority must be between 1 and 5 (inclusive).
     * If the element is already in the queue, it will not be added again.
     *
     * @param element the element to add
     * @param priority the priority of the element
     * @return true if the element was added to this queue, else false
     * @throws IllegalArgumentException if the priority is not between 1 and 5
     */
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

    LinkedList<T> list = QUEUE.get(priority);
    if (list == null) {
        list = new LinkedList<>();
        QUEUE.put(priority, list);
    }
    list.addLast(element);

    return true;
}

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     * The head of the queue is the element with the highest priority that was added first.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    @Override
    public T dequeue() {
        for (int i = 5; i >= 1; i--) {
            if (QUEUE.get(i) != null && !QUEUE.get(i).isEmpty()) {
                return QUEUE.get(i).remove();
            }
        }
        return null;
    }

    /**
     * Returns the position of the first occurrence of the specified element in this queue,
     * or -1 if this queue does not contain the element.
     * The position is the priority of the element.
     *
     * @param element the element to search for
     * @return the position of the first occurrence of the specified element in this queue,
     *         or -1 if this queue does not contain the element
     */
    @Override
    public int search(T element) {
        for (int i = 1; i <= QUEUE.size(); i++) {
            if (QUEUE.get(i) != null && QUEUE.get(i).contains(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
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

    /**
     * Returns a string representation of this queue.
     * The string representation consists of each element in this queue and its priority,
     * in the order they would be dequeued (from highest priority to lowest, and from first added to last added within each priority).
     *
     * @return a string representation of this queue
     */
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
