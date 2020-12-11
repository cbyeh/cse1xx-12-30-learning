import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
/**
 * Class containing Helper Functions
 */
public class Helpers {

    /**
     * Load data on to an array list from the given data file.
     * @param n         Number of elements to load.
     * @param offset    Number elements to skip from the beginning before loading n elements.
     */
    public static ArrayList<Entry> load_data(int n, int offset) throws IOException {
        BufferedReader inputStrem;
        String fname = "data.txt";
        String line;
        String[] k;
        ArrayList<Entry> data = new ArrayList<Entry>();
        inputStrem = new BufferedReader(new FileReader(fname));

        for(int i=0; i<n+offset; i++) {
            line = inputStrem.readLine();
            k = line.split(" +");
            if (i>=offset) data.add(new Entry(k[0], (int)(Float.parseFloat(k[2]) * 1000)));
        }
        return data;
    }
}
