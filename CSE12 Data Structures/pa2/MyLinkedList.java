/**
 * MyLinkedList is an implementation of a circular doubly linked list
 * This particular Linked List uses one sentinel node variable @dummy
 * It is also generically implemented, storing data of any Object type.
 *
 * @author Christopher Yeh
 * @PID A15720503
 * @email cyeh@ucsd.edu
 */

import java.util.*;

public class MyLinkedList<E> extends AbstractList<E> {

    private int nelems;
    private Node dummy; // aka me

    public static void main(String[] args) {
        MyLinkedList<Integer> mL = new MyLinkedList<>();
    }

    protected class Node {
        E data;
        Node prev;
        Node next;

        /**
         * Constructor to create singleton Node
         */
        public Node(E element) {
            this(element, null, null);
        }

        /**
         * Constructor to create singleton link it between previous and next
         *
         * @param element  Element to add, can be null
         * @param prevNode predecessor Node, can be null
         * @param nextNode successor Node, can be null
         */
        public Node(E element, Node prevNode, Node nextNode) {
            data = element;
            prev = prevNode;
            next = nextNode;
        }

        /**
         * Remove this node from the list. Update previous and next nodes
         */
        public void remove() {
            this.getPrev().setNext(this.getNext());
            this.getNext().setPrev(this.getPrev());
        }

        /**
         * Set the previous node in the list
         *
         * @param p new previous node
         */
        public void setPrev(Node p) {
            prev = p;
        }

        /**
         * Set the next node in the list
         *
         * @param n new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Set the element
         *
         * @param e new element
         */
        public void setElement(E e) {
            data = e;
        }

        /**
         * Accessor to get the next Node in the list
         */
        public Node getNext() {
            return next;
        }

        /**
         * Accessor to get the prev Node in the list
         */
        public Node getPrev() {
            return prev;
        }

        /**
         * Accessor to get the Nodes Element
         */
        public E getElement() {
            return data;
        }
    }

    /**
     * ListIterator implementation
     */
    protected class MyListIterator implements ListIterator<E> {

        private boolean forward;
        private boolean canRemove;
        private Node left, right; // Cursor sits between these two nodes
        private int idx;      // Tracks current position, what next would return

        public MyListIterator() {
            forward = true;
            canRemove = false;
            left = dummy;
            right = dummy.getNext();
            idx = 0;
        }

        @Override
        public void add(E e) throws NullPointerException {
            if (e == null)
                throw new NullPointerException("Cannot put null Object in list");
            Node newNode = new Node(e, left, right);
            left.setNext(newNode);
            right.setPrev(newNode);
            canRemove = true;
            nelems++;
        }

        @Override
        public boolean hasNext() {
            return idx < nelems;
        }

        @Override
        public boolean hasPrevious() {
            return left != dummy;
        }

        @Override
        public E next() throws NoSuchElementException {
            if (!hasNext()) {
                throw new NoSuchElementException("No such element exists");
            }
            left = right; // Moving cursor forward
            right = right.getNext(); // Moving cursor forward
            idx++;
            forward = true;
            canRemove = true;
            return left.getElement();
        }

        @Override
        public int nextIndex() {
            return idx;
        }

        @Override
        public E previous() throws NoSuchElementException {
            if (!hasPrevious()) {
                throw new NoSuchElementException("No such element exists");
            }
            right = left; // Moving cursor backwards
            left = left.getPrev(); // Moving cursor backwards
            idx--;
            forward = false;
            canRemove = true;
            return right.getElement();
        }

        @Override
        public int previousIndex() {
            if (!hasPrevious())
                return -1; // Iterator is between dummy and 0th node
            return idx - 1;
        }

        @Override
        public void remove() throws IllegalStateException {
            if (!canRemove || isEmpty())
                throw new IllegalStateException("Cannot remove, " +
                        "or neither next nor previous was called");
            if (forward) {
                if (!hasPrevious()) {
                    canRemove = false;
                    throw new IllegalStateException("Cannot remove");
                }
                Node leftNode = left.getPrev();
                left.remove();
                left = leftNode;
                idx--;
            } else {
                if (!hasNext()) { // If user tries to remove dummy
                    canRemove = false;
                    throw new IllegalStateException("Cannot remove");
                }
                Node rightNode = right.getNext();
                right.remove();
                right = rightNode;
            }
            nelems--;
        }

        @Override
        public void set(E e)
                throws NullPointerException, IllegalStateException {
            if (e == null)
                throw new NullPointerException("Cannot put null Object in list");
            if (!canRemove)
                throw new IllegalStateException("Neither next " +
                        "nor previous was called");
            if (forward)
                left.setElement(e);
            else
                right.setElement(e);
        }
    }

    //  Implementation of the MyLinkedList Class

    /**
     * Only 0-argument constructor is defined
     */
    public MyLinkedList() {
        // Initialize a new list with just the dummy node
        dummy = new Node(null);
        dummy.setPrev(dummy);
        dummy.setNext(dummy);
        nelems = 0;
    }

    @Override
    public int size() { // Return the number of nodes being stored
        return nelems;
    }

    /**
     * Get data within the node at position index. The input
     * index can be any integer in interval[Integer.MIN_VALUE,
     * [Integer.MAX_VALUE]  due to our LinkedList's circular nature.
     */
    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if ((isEmpty())) {
            throw new IndexOutOfBoundsException("Invalid input, list is empty");
        }
        Node currentNode = getNth(index);
        return currentNode.getElement();
    }

    /**
     * add a node into this list by index. The input index
     * can be any integer in interval [Integer.MIN_VALUE,
     * Integer.MAX_VALUE]
     */
    @Override
    public void add(int index, E data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot put null Object in list");
        Node currentNode = dummy.getNext();
        if (index >= 0) {
            for (int i = 0; i < index; i++)
                currentNode = currentNode.getNext();
        } else { // index < 0
            for (int i = 0; i > index; i--)
                currentNode = currentNode.getPrev();
        }
        Node newNode = new Node(data, currentNode.getPrev(), currentNode);
        currentNode.getPrev().setNext(newNode);
        currentNode.setPrev(newNode);
        nelems++;
    }

    @Override
    // Add an element into this list at the end
    public boolean add(E data) throws NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot put null Object in list");
        Node newNode = new Node(data, dummy.getPrev(), dummy);
        dummy.getPrev().setNext(newNode);
        dummy.setPrev(newNode);
        nelems++;
        return true;
    }

    /**
     * set the value at index i to data. The input index can
     * be any integer in interval [Integer.MIN_VALUE,
     * Integer.MAX_VALUE]
     */
    @Override
    public E set(int index, E data)
            throws IndexOutOfBoundsException, NullPointerException {
        if (data == null)
            throw new NullPointerException("Cannot put null Object in list");
        if ((isEmpty()))
            throw new IndexOutOfBoundsException("Invalid input, list is empty");
        Node currentNode = getNth(index);
        currentNode.setElement(data);
        return currentNode.getElement();
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if ((isEmpty()))
            throw new IndexOutOfBoundsException("Invalid input, list is empty");
        Node currentNode = getNth(index);
        currentNode.remove();
        nelems--;
        return currentNode.getElement();
    }

    @Override
    public void clear() { // Remove all nodes from the list
        dummy.setPrev(dummy);
        dummy.setNext(dummy);
        nelems = 0;
    }

    @Override
    public boolean isEmpty() { // Determine if the list is empty
        return (this.size() == 0);
    }

    // Helper method to get the Node at the Nth index
    private Node getNth(int index) {
        /* int mid = nelems / 2; // efficiency var,
        // i.e. iterate backwards for last node */
        if (isEmpty())
            return dummy;
        Node currentNode = dummy.getNext();
        // int trueIndex = index - (index % nelems); // Accounting for dummy
        if (index > 0) {
            for (int i = 0; i < index; i++)
                if (currentNode.getNext() == dummy)
                    // Skipping dummy
                    currentNode = currentNode.getNext().getNext();
                else
                    currentNode = currentNode.getNext();
        } else if (index < 0) {
            for (int i = 0; i > index; i--)
                if (currentNode.getPrev() == dummy)
                    // Skipping dummy
                    currentNode = currentNode.getPrev().getPrev();
                else
                    currentNode = currentNode.getPrev();
        }
        return currentNode; // Returns dummy.getNext() if not looped
    }

    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }

    @Override
    public String toString() {
        String result = "[  ";
        Node currentNode = dummy.getNext();
        while (currentNode != dummy) {
            result = result + currentNode.getElement() + ",  ";
            currentNode = currentNode.getNext();
        }
        return result + "]";
    }

}


// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4

