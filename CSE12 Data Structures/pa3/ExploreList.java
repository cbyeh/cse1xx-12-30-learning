/** An interface for BFS and DFS.
* CSE 12 HW3
* Fall 2018
* @author Christine Alvarado, Prafulla Pallal
*/

// This is not a generic type as it is designed specifically
// to work with Squares from a Sea.
public interface ExploreList
{
    /** Add a Square to the worklist, as appropriate 
    * @param "The Square to add"
    */
    public void add(Square s);

    /** Removes and returns the next Square to be explored 
    * @return The next Square to explore */
    public Square getNext();

    /** isEmpty
    * @return true if the worklist is empty, false otherwise
    */
    public boolean isEmpty();
    
    /** size of the worklist
    * @return The number of elements in the worklist
    */
    public int size();
}