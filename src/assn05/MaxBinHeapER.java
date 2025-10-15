package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V, P>> _heap;

    /**
     * 1st constructor that creates an empty max heap of prioritized elements.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    /**
     * @return size of heap
     */
    @Override
    public int size() {
        return _heap.size();
    }

    /**
     * TODO (Task 2A): enqueue using value and priority
     *
     * @param value
     * @param priority
     */
    @Override
    public void enqueue(V value, P priority) {
        Prioritized<V, P> newElement = new Patient<>(value, priority);
        _heap.add(newElement);
        heapifyUp(_heap.size() - 1);
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (_heap.get(index).compareTo(_heap.get(parentIndex)) > 0) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Prioritized<V, P> temp = _heap.get(i);
        _heap.set(i, _heap.get(j));
        _heap.set(j, temp);
    }

    /**
     * TODO (Task 2A): enqueue (overloaded) using only value
     *
     * @param value
     */
    public void enqueue(V value) {
        Random rand = new Random();
        P priority = (P) Integer.valueOf(rand.nextInt(1000000));
        enqueue(value, priority);
    }

    /**
     * TODO (Task 2A): dequeue from maxBinHeapER
     *
     * @return element with highest priority
     */
    @Override
    public V dequeue() {
        if (_heap.size() == 0) {
            return null;
        }
        Prioritized<V, P> root = _heap.get(0);
        _heap.set(0, _heap.get(_heap.size() - 1));
        _heap.remove(_heap.size() - 1);
        heapifyDown(0);
        return root.getValue();
    }

    private void heapifyDown(int index) {
        int largest = index;
        int leftChildIdx = 2 * index + 1;
        int rightChildIdx = 2 * index + 2;

        if (leftChildIdx < _heap.size() && _heap.get(leftChildIdx).compareTo(_heap.get(largest)) > 0) {
            largest = leftChildIdx;
        }

        if (rightChildIdx < _heap.size() && _heap.get(rightChildIdx).compareTo(_heap.get(largest)) > 0) {
            largest = rightChildIdx;
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    /**
     * TODO (Task 2A): getMax
     *
     * @return return value of element with the highest priority
     */
    @Override
    public V getMax() {
        if (_heap.size() == 0) {
            return null;
        }
        return _heap.get(0).getValue();
    }
    /**
     * TODO (Task 2B): updatePriority
     * Change the priority of the patient with the given value.
     * if newPriority is <0 then remove the element with given value
     * (In case value is not matching and existing Priority < 0, nothing is to be done.)
     * @param value
     * @param newPriority
     */
    public void updatePriority(V value, P newPriority) {
        int index = -1;
        for (int i = 0; i < _heap.size(); i++) {
            if (_heap.get(i).getValue().equals(value)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }

        if (newPriority.compareTo((P) Integer.valueOf(0)) < 0) {
            _heap.remove(index);
            heapifyDown(index);
            return;
        }

        P oldPriority = _heap.get(index).getPriority();
        _heap.get(index).setPriority(newPriority);

        if (newPriority.compareTo(oldPriority) > 0) {
            heapifyUp(index);
        } else if (newPriority.compareTo(oldPriority) < 0) {
            heapifyDown(index);
        }
    }

    /**
     * TODO (Task 3): MaxBinHeapER
     * 2nd constructor that builds a heap given an initial array of prioritized elements.
     * @param initialEntries This is an initial ArrayList of patients
     */
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
        _heap = new ArrayList<>(Arrays.asList(initialEntries));
        buildHeap();
    }

    private void buildHeap() {
        int startIndex = parent(_heap.size() - 1);
        for (int i = startIndex; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    /**
     * Retrieves contents of heap as an array.
     * @return array of Prioritized elements in the order stored in the heap
     */
    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
}

