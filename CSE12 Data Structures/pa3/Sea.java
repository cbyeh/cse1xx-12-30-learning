import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class Sea {
    private Square[][] sea;
    private int numRows;
    private int numCols;

    /** 0-arg constructor
    */
    public Sea() {
        numRows = 0;
        numCols = 0;
    }

    public Sea(int n, int m) {
        numRows = n;
        numCols = m;
        generateSea(0.2);
    }

    /** loads a sea from a file to a 2d array
     * @param rocks - 0-1 Likeliness of occurrence of rocks.
     * @return true if load was a success
     */
    public boolean generateSea(double rocks) {

        sea = new Square[numRows][numCols];
        if (numRows == 0 && numCols == 0) return false;

        Random random = new Random();
        for(int i=0; i<numRows; i++) {
            for(int j=0; j<numCols; j++) {
                int t;
                if (random.nextDouble() < rocks) {
                    t = Square.getRockType();
                } else {
                    t = Square.getSpaceType();
                }
                sea[i][j] = new Square(i, j, t);
            }
        }

        int c = random.nextInt(numCols);
        int r = random.nextInt(numRows);
        int nr=0, nc=0;
        do {
            nc = random.nextInt(numCols);
            nr = random.nextInt(numRows);
        } while ((nr == r) && (nc == c));

        sea[r][c] = new Square(r, c, Square.getStartType());
        sea[nr][nc] = new Square(nr, nc, Square.getNemoType());

        return true;
    }

    /** loads a sea from a file to a 2d array
    * @param fname pathname to sea to load
    * @return true if load was a success
    */
    public boolean loadSea(String fname) {
        String line;
        BufferedReader inputStrem;
        StringTokenizer st;

        try {
            int currentRow = 0;

            inputStrem = new BufferedReader(new FileReader(fname));
            line = inputStrem.readLine();
            if (line != null) {
                st = new StringTokenizer(line);
                numRows = Integer.parseInt(st.nextToken());
                numCols = Integer.parseInt(st.nextToken());
                sea = new Square[numRows][numCols];
            } else {
                inputStrem.close();
                return false;
            }

            while ((line = inputStrem.readLine()) != null) {
                if (numRows == 0) {  // true if reading first line in file, containing  numRows numColums
                    st = new StringTokenizer(line);
                    numRows = Integer.parseInt(st.nextToken());
                    numCols = Integer.parseInt(st.nextToken());
                    sea = new Square[numRows][numCols];
                } else if (line.length() == 1) {
                    break; 
                } else {
                    int col = 0;
                    for (int c = 0; c < line.length(); c++) {
                        if (line.charAt(c) == ' ') continue;
                        sea[currentRow][col] = new Square(currentRow, col, Character.getNumericValue(line.charAt(c)));
                        col++;
                    }
                    currentRow++;
                }
            }
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Could not find file " + fname);
        } 

        return true;
    }

    /** find list of valid neighbors of square
    * @param sq to find neigbors of
    * @return list of square's neighbors
    */
    public ArrayList<Square> getAdjacentArea(Square sq) {
        ArrayList<Square> neighbors = new ArrayList<>();
        int down = sq.getRow() + 1;
        int left = sq.getCol() - 1;
        int up = sq.getRow() - 1; 
        int right = sq.getCol() + 1;

        if (up >= 0 && sea[up][sq.getCol()].isValid()) {
            neighbors.add(sea[up][sq.getCol()]);
        }

        if (right < numCols && sea[sq.getRow()][right].isValid()) {
            neighbors.add(sea[sq.getRow()][right]);
        }

        if (down < numRows && sea[down][sq.getCol()].isValid()) {
            neighbors.add(sea[down][sq.getCol()]);
        }
        
        if (left >= 0 && sea[sq.getRow()][left].isValid()) {
            neighbors.add(sea[sq.getRow()][left]);
        }
        return neighbors;
    }

    /** finds starts cell in sea
    * @return Square which corresponds to the start cell
    */
    public Square getStart() {
        for (int c = 0; c < numCols; c++)  {
            for (int r = 0; r < numRows; r++) {
                if (sea[r][c].isStart()) {
                    return sea[r][c];
                }
            }
        }
        return null;
    }

    /** finds end cell in sea
    * @return Square which corresponds to the end cell
    */
    public Square getNemo() {
        for (int c = 0; c < numCols; c++) {
            for (int r = 0; r < numRows; r++) {
                if (sea[r][c].hasNemo()) {
                    return sea[r][c];
                }
            }
        }
        return null;
    }

    /** marks Square at particular location as visited
    * @param row -0 indexed row index
    * @param col -0 indexed cell index
    */
    public void setVisit(int row, int col) {
        sea[row][col].setVisited();
    }

    /** remove all linked squares and sets visited flags to false
    */
    public void clearMaze() {
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                sea[i][j].clearFinalPath();
                sea[i][j].clearVisited();
                sea[i][j].setPrevious(null);
            }
        }
    }

    /** getSea
    * @return 2d array of sea
    */
    public Square[][] getSea() {
        return this.sea;
    }

    /** toString
    * @return string representation of sea
    */
    @Override
    public String toString() {
        String s = "";
        for (int r = 0; r < numRows; r++)  {
            for (int c = 0; c < numCols; c++)  {
                s = s + sea[r][c].toString();
            }
            s = s + "\n";
        }
        return s;  
    }

    public int countVisited() {
        int count = 0;
        for (int r = 0; r < numRows; r++)  {
            for (int c = 0; c < numCols; c++)  {
                if (sea[r][c].isVisited()) count++;
            }
        }
        return count;
    }
}
