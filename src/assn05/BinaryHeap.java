package assn05;

/**
 * Interface for a BinaryHeap
 * @param <V> value
 * @param <P> priority
 */
public interface BinaryHeap<V,P extends Comparable<P>> {
    /**
     *
     * @return number of elements in heap
     */
    int size();

    /**
     * Create new a new item with given value and priority and enqueue it into the heap
     * @param value
     * @param priority
     */
    void enqueue(V value, P priority);

    /**
     * Dequeue the element with the highest priority from the heap
     * @return the value of the removed element
     */
    V dequeue();

    /**
     * return the largest value in the heap without removing it
     * @return the largest value in the heap
     */
    V getMax();

    /**
     * Retrieves contents of heap as an array.
     * @return array of Prioritized elements in the order stored in the heap
     */
    Prioritized<V,P>[] getAsArray();

    /**
     * Change the priority of the patient corresponding to the given value.
     * if newPriority is <0 then remove the element with given value
     * (In case value is not matching and existing Priority < 0, nothing is to be done.)
     * @param value
     * @param newPriority
     */
    void updatePriority(V value, P newPriority);
}
