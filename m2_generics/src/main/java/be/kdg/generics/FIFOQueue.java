package be.kdg.generics;

/**
 * The FIFOQueue interface represents a generic First-In-First-Out (FIFO) queue with priority.
 * It provides methods to enqueue (add) elements with a priority, dequeue (remove and return) elements,
 * search for elements, and get the size of the queue.
 *
 * @param <T> the type of elements held in this queue
 */
public interface FIFOQueue<T> {

    /**
     * Inserts the specified element into this queue with the given priority.
     *
     * @param element the element to add
     * @param priority the priority of the element
     * @return true if the element was added to this queue, else false
     */
    boolean enqueue(T element, int priority);

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     *
     * @return the head of this queue, or null if this queue is empty
     */
    T dequeue();

    /**
     * Returns the position of the first occurrence of the specified element in this queue,
     * or -1 if this queue does not contain the element.
     *
     * @param element the element to search for
     * @return the position of the first occurrence of the specified element in this queue,
     *         or -1 if this queue does not contain the element
     */
    int search(T element);

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    int getSize();
}