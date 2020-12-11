public class Square {
    private static final int SPACE = 0; // ' '
    private static final int ROCK  = 1; // '#'
    private static final int START = 2; // 'S'
    private static final int NEMO  = 3; // 'E'

    private int cellType;       // space, wall, start or exit
    private int cellRow;        // location row
    private int cellCol;        // location col;
    private boolean visited;    // if visited;
    private boolean onFinalPath;
    Square previous;            // linked structure of nodes.

    public Square() {}

    /** 3-arg constructor
    * @param row creates Square at this row
    * @param col creates Square at this column
    * @param type creates Square of this type
    */
    public Square(int row, int col, int type) {
        cellRow = row;
        cellCol = col;
        cellType = type;
        previous = null;
        onFinalPath = false;
        visited = false; // nothing was added to the storage;
    }

    public static int getSpaceType(){
        return SPACE;
    }

    public static int getRockType(){
        return ROCK;
    }

    public static int getStartType(){
        return START;
    }

    public static int getNemoType(){
        return NEMO;
    }

    /** getPrevious
    * @return linked previous square
    */
    public Square getPrevious() {
        return previous;
    }

    /** links the previous square to this one
    * @param prev the square to set
    */
    public void setPrevious(Square prev) {
        previous = prev;
    }

    /** sets onFinalPath flag to true
    */
    public void setFinalPath() {
        onFinalPath = true;
    }

    /** sets onFinalPath flag to false
    */
    public void clearFinalPath() {
        onFinalPath = false;
    }

    /** converts square into one character string corresponding to type
    * @return string representation of square
    */
    public String toString() {
        char converted = ' ';
        switch (cellType) {
            case SPACE:
                if(onFinalPath) converted = 'x';
                else if(visited) converted = '.';
//                else if(previous != null) converted='_';
                else converted = '_';
                break;
            case ROCK:  converted = '#';
                  break;
            case START:  converted = 'S';
                  break;
            case NEMO:  converted = 'N';
                  break;
                 
            default: System.out.println("bad");
                break;
        }
        return "" + converted;
    }

    // Methods to check status of the square
    /** hasNemo
    * @return true if the square is the end of the maze
    */
    public boolean hasNemo() {
        return cellType == NEMO;
    }

    /** isValid
    * @return true if it isn't wall or the start location
    */
    public boolean isValid() {
        return (cellType == SPACE) || (cellType == NEMO);
    }

    /** isStart
    * @return true if the square is the start of the maze
    */
    public boolean isStart() {
        return cellType == START;
    }

    /** isVisited
    * @return true if the square has been visited
    */
    public boolean isVisited() {
        return visited;
    }

    // Accessor methods
    /** getRow
    * @return row the square is on in the maze (0-indexed)
    */
    public int getRow() {
        return cellRow;
    }

    /** getCol
    * @return column the square is on in the maze (0-indexed)
    */
    public int getCol() {
        return cellCol;
    }

    /** getType
    * @return int value (0-3 inclusive) corresponding to the type of cell it is
    */
    public int getType() {
        return cellType;
    }

    // Setters
    /** sets visited flag to true
    */
    public void setVisited() {
        visited = true;
    }

    /** sets visited flag to false
    */
    public void clearVisited() {
        visited = false;
    }
	
	/** Overridden to compare Square objects */
	@Override
    public boolean equals(Object obj) {
		Square s = (Square) obj;
		if(cellRow == s.cellRow && cellCol == s.cellCol && cellType == s.cellType)
			return true;
		else
			return false;
    }
}
