import java.util.ArrayList;
import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * HeapPQ12 class that implements an unbounded array-backed heap structure and is
 * an extension of the Java Collections AbstractQueue class
 * <p>
 * The elements of the heap are ordered according to their natural
 * ordering,  HeapPQ12 does not permit null elements.
 * The top of this heap is the minimal or maximal element (called min/max)
 * with respect to the specified natural ordering.
 * If multiple elements are tied for min/max value, the top is one of
 * those elements -- ties are broken arbitrarily.
 * The queue retrieval operations poll and  peek
 * access the element at the top of the heap.
 * <p>
 * A HeapPQ12 is unbounded, but has an internal capacity governing the size of
 * an array used to store the elements on the queue. It is always at least as
 * large as the queue size. As elements are added to a HeapPQ12, its capacity
 * grows automatically. The details of the growth policy are not specified.
 * <p>
 * This class and its iterator implements the optional methods of the
 * Iterator interface (including remove()). The Iterator provided in method
 * iterator() is not guaranteed to traverse the elements of the HeapPQ12 in
 * any particular order.
 * <p>
 * Note that this implementation is not synchronized. Multiple threads
 * should not access a HeapPQ12 instance concurrently if any of the
 * threads modifies the HeapPQ12.
 */
public class HeapPQ12<E extends Comparable<? super E>> extends AbstractQueue<E> {
    /* -- Define any private member variables here -- */
    /* In particular, you will need an ArrayList<E> to hold the elements
     * in the heap.  You will also want many more variables */
    private ArrayList<E> heap;
    private boolean maxHeap;
    private int nelems, capacity;
    private Iterator<E> iterator;

    public int roundTo(int n, int toRoundTo) {
        while (n % toRoundTo != 0)
            n++;
        return n;
    }










    public static void main(String[] args) {
        HeapPQ12<String> testMin = new HeapPQ12<String>(true);
        testMin.offer("a");
        testMin.offer("c");
        testMin.offer("d");
        testMin.offer("f");
        testMin.offer("h");
        testMin.offer("g");
        System.out.println(testMin);
        testMin.poll();
        System.out.println(testMin);
        testMin.clear();
        System.out.println(testMin);
    }

    private void initNull(int capacity) {
        for (int i = 0; i < capacity; i++)
            heap.add(null);
    }

    public HeapPQ12() {
        this(5, false);
    }

    public HeapPQ12(boolean isMaxHeap) {
        this(5, isMaxHeap);
    }

    public HeapPQ12(int capacity, boolean isMaxHeap) {
        heap = new ArrayList<E>();
        maxHeap = isMaxHeap;
        nelems = 0;
        this.capacity = capacity;
        initNull(capacity);
        iterator = iterator();
    }

    public HeapPQ12(HeapPQ12<E> toCopy) {
        ArrayList<E> deepCopy = new ArrayList<>();
        deepCopy.addAll(toCopy.heap);
        /* for (E ele : toCopy.heap) {
            deepCopy.add(ele);
            if (ele != null)
                nelems++;
        } */
        /* for (int i = toCopy.nelems; i < toCopy.capacity; i++)
            deepCopy.add(null); // Adding null elements skipped over */
        nelems = toCopy.nelems;
        capacity = toCopy.heap.size();
        heap = deepCopy;
        iterator = toCopy.iterator;
    }

    /* The following are defined "stub" methods that provide degenerate
     * implementations of methods defined as abstract in parent classes.
     * These need to be coded properly for HeapPQ12 to function correctly
     */

    /**
     * Size of the heap
     *
     * @return the number of
     * elements stored in the heap
     */
    public int size() {
        return nelems;
    }

    /**
     * @return an Iterator for the
     * heap
     */
    public Iterator<E> iterator() {
        return new HeapPQ12Iterator();
    }

    /**
     * peek - Retrieves, but does not
     * remove, the head of this heap,
     * or returns null if this heap is
     * empty.
     *
     * @return Element at top of
     * heap. Do not remove.
     */
    public E peek() {
        if (isEmpty())
            return null;
        return heap.get(0);
    }

    /**
     * poll - Retrieves and removes
     * the head of this queue, or
     * returns null if this queue is
     * empty.
     *
     * @return Element at top of
     * heap. And remove it from the
     * heap.
     * return null if the heap is
     * empty
     */
    public E poll() {
        if (isEmpty())
            return null;
        E deleted = this.peek();
        heap.set(0, heap.get(nelems - 1));
        heap.set(nelems - 1, null);
        nelems--;
        int currentPos = 0;
        int leftChildPos = leftChildPos(currentPos);
        int rightChildPos = rightChildPos(currentPos);
        if (isMaxHeap()) {
            while (hasLeftChild(currentPos)) {
                int biggerChildPos = -1;
                if (heap.get(currentPos).compareTo(heap.get(leftChildPos)) < 0)
                    biggerChildPos = leftChildPos;
                if (hasRightChild(currentPos))
                    if (heap.get(leftChildPos).compareTo(heap.get(rightChildPos)) < 0)
                        biggerChildPos = rightChildPos;
                if (biggerChildPos != -1) {
                    E tmp = heap.get(currentPos);
                    heap.set(currentPos, heap.get(biggerChildPos));
                    heap.set(biggerChildPos, tmp);
                } else break;
                currentPos = biggerChildPos;
            }
        }
        if (!isMaxHeap()) {
            while (hasLeftChild(currentPos)) {
                int smallerChildPos = -1;
                if (heap.get(currentPos).compareTo(heap.get(leftChildPos)) > 0)
                    smallerChildPos = leftChildPos;
                if (hasRightChild(currentPos))
                    if (heap.get(leftChildPos).compareTo(heap.get(rightChildPos)) > 0)
                        smallerChildPos = rightChildPos;
                if (smallerChildPos != -1) {
                    E tmp = heap.get(currentPos);
                    heap.set(currentPos, heap.get(smallerChildPos));
                    heap.set(smallerChildPos, tmp);
                } else break;
                currentPos = smallerChildPos;
            }
        }
        return deleted;
    }

    /**
     * offer -- Inserts the specified
     * element into this heap if it is
     * possible to do so immediately
     * without violating capacity
     * restrictions. If violate the
     * capacity restriction, double
     * the size of the arraylist and
     * insert.
     *
     * @return true if no exception
     * @throws NullPointerException if the specified element is nul
     */
    public boolean offer(E e) throws NullPointerException {
        if (e == null)
            throw new NullPointerException();
        if (nelems == capacity)
            doubleCapacity();
        heap.set(nelems, e);
        int currentPos = nelems;
        nelems++;
        int parentPos = parentPos(currentPos);
        if (isMaxHeap()) {
            // While parent is less than inserted node
            while (hasParent(currentPos)
                    && heap.get(currentPos).compareTo(heap.get(parentPos)) > 0) {
                E tmp = heap.get(currentPos);
                heap.set(currentPos, heap.get(parentPos(currentPos)));
                heap.set(parentPos(currentPos), tmp);
                currentPos = parentPos(currentPos);
                parentPos = parentPos(currentPos);
            }
        }
        if (!isMaxHeap()) { // MinHeap
            // While parent is more than inserted node
            while (hasParent(currentPos)
                    && heap.get(currentPos).compareTo(heap.get(parentPos)) < 0) {
                E tmp = heap.get(currentPos);
                heap.set(currentPos, heap.get(parentPos(currentPos)));
                heap.set(parentPos(currentPos), tmp);
                currentPos = parentPos(currentPos);
                parentPos = parentPos(currentPos);
            }
        }
        return true;
    }

    // Check whether the heap is empty.
    public boolean isEmpty() {
        return nelems == 0;
    }

    // Clear all elements in the heap
    public void clear() {
        while (poll() != null)
            poll();
    }

    /* ------ Private Helper Methods ----
     *  DEFINE YOUR HELPER METHODS HERE
     */

    private int leftChildPos(int pos) {
        return 2 * pos + 1;
    }

    private int rightChildPos(int pos) {
        return 2 * pos + 2;
    }

    private boolean hasLeftChild(int pos) {
        return (leftChildPos(pos) < nelems);
    }

    private boolean hasRightChild(int pos) {
        return (rightChildPos(pos) < nelems);
    }

    private int parentPos(int pos) {
        return (pos - 1) / 2; // Parent of root is -1
    }

    private boolean hasParent(int pos) {
        return pos > 0;
    }

    private void doubleCapacity() {
        ArrayList<E> newHeap = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newHeap.add(heap.get(i));
        }
        for (int i = capacity; i < 2 * capacity; i++) {
            newHeap.add(null);
        }
        heap = newHeap;
        capacity = 2 * capacity;
    }

    public boolean isMaxHeap() {
        return maxHeap;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    /**
     * Inner Class for an Iterator
     **/
    /* stub routines */

    private class HeapPQ12Iterator implements Iterator<E> {
        private int idx;

        private HeapPQ12Iterator() {
            idx = -1;
        }

        // Returns true if the iteration has more elements.
        public boolean hasNext() {
            return idx < nelems - 1;
        }

        // Returns the next element in the iteration.
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            idx++;
            return heap.get(idx);
        }
    }
}
