import java.util.ArrayList;
import java.util.LinkedList;

public class StackExploreList implements ExploreList {
    private LinkedList<Square> myList;

    public StackExploreList() {
        myList = new LinkedList<>();
    }

    /**
     * Add a Square to the worklist, as appropriate
     *
     * @param "The Square to add"
     */
    public void add(Square s) {
        myList.add(s);
    }

    /**
     * Removes and returns the next Square to be explored
     *
     * @return The next Square to explore
     */
    public Square getNext() {
        return (myList.removeLast());
    }

    /**
     * isEmpty
     *
     * @return true if the worklist is empty, false otherwise
     */
    public boolean isEmpty() {
        return (myList.size() == 0);
    }

    /**
     * size of the worklist
     *
     * @return The number of elements in the worklist
     */
    public int size() {
        return (myList.size());
    }


}
