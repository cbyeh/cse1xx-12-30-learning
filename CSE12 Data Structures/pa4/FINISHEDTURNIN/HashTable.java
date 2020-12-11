import java.io.*;
import java.util.*;

public class HashTable implements IHashTable {

    private LinkedList<String>[] tableLists;
    private LinkedList<String> statistics;
    private int nelems;  // Number of element stored in the hash table
    private int expand;  // Number of times that the table has been expanded
    private int collision;  // Number of collisions since last expansion
    private String statsFileName;     // FilePath for the file to write statistics upon every rehash
    private boolean printStats = false;   // Boolean to decide whether to write statistics to file or not after rehashing
    private BufferedWriter fileWriter;

    public static void main(String[] args) {
        HashTable ht = new HashTable(2, "statistics.txt");
        try (Scanner scan = new Scanner(new File("dicts/long.dict.txt"))) {
            while (scan.hasNext())
                ht.insert(scan.next());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        ht.printTable();
        ht.printStatistics();
        String s;
    }

    /**
     * Constructor for hash table
     * Default prime 11
     */
    public HashTable() {
        this(11);
    }

    /**
     * Constructor for hash table
     *
     * @param size size of the hash table
     */

    @SuppressWarnings("unchecked")
    public HashTable(int size) {
        tableLists = new LinkedList[size];
        statistics = new LinkedList<>();
        nelems = 0;
        expand = 0;
        collision = 0;
        for (int i = 0; i < tableLists.length; i++)
            tableLists[i] = new LinkedList<>();
    }

    /**
     * Constructor for hash table
     *
     * @param size     size of the hash table
     * @param fileName path to write statistics
     */
    public HashTable(int size, String fileName) {
        this(size);
        try {
            fileWriter = new BufferedWriter(new FileWriter(fileName));
            printStats = true;
            statsFileName = fileName;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(String value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException();
        if ((double) nelems / tableLists.length >= (double) 2 / 3) // Our load factor
            rehash();
        if (!this.contains(value)) {
            if (!tableLists[this.hash(value)].isEmpty())
                collision++;
            tableLists[this.hash(value)].add(value);
            nelems++;
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException();
        if (this.contains(value)) {
            tableLists[this.hash(value)].remove(value);
            nelems--;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String value) throws NullPointerException {
        if (value == null)
            throw new NullPointerException();
        return tableLists[hash(value)].contains(value);
    }

    @Override
    public void printTable() {
        System.out.println(this.toString());
    }

    @Override
    public int getSize() {
        return nelems;
    }

    // Taken from String class's hashCode() method principles, modified for our program
    private int hash(String value) {
        int hashValue = 0;
        int off = 0;
        char val[] = value.toCharArray();
        for (int i = 0; i < value.length(); i++) {
            hashValue = 31 * hashValue + val[off++];
        }
        return Math.abs(hashValue) % tableLists.length;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        expand++;
        if (printStats) {
            String stats = currentStats();
            statistics.add(stats);
            writeStatistics(stats);
        }
        LinkedList<String>[] oldLists = tableLists;
        tableLists = new LinkedList[tableLists.length * 2];
        collision = 0;
        for (int i = 0; i < tableLists.length; i++)
            tableLists[i] = new LinkedList<>();
        for (LinkedList<String> list : oldLists)
            for (String s : list)
                insert(s);
    }

    private String currentStats() {
        double loadFactor = (double) nelems / tableLists.length;
        return (expand + " resizes," + " load factor " +
                String.format("%.2f", loadFactor) + ", " + collision + " collisions, " +
                largestBucket() + " longest chain ");
    }

    // Helper method to find current list's largest chain
    private int largestBucket() {
        int largestBucket = tableLists[0].size(); // initial value
        for (LinkedList<String> list : tableLists) {
            if (list.size() > largestBucket)
                largestBucket = list.size();
        }
        return largestBucket;
    }

    private void writeStatistics(String stats) {
        try {
            fileWriter.write(stats);
            fileWriter.newLine();
            fileWriter.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // Prints to console entire history of rehash
    private void printStatistics() {
        for (String s : this.statistics)
            System.out.println(s);
    }

    @Override
    public String toString() {
        StringBuilder table = new StringBuilder();
        int index = 0;
        for (LinkedList<String> list : tableLists) {
            table.append(index);
            table.append(": ");
            StringBuilder bucket = new StringBuilder();
            for (String s : list) {
                if (bucket.length() > 0)
                    bucket.append(", ");
                bucket.append(s);
            }
            table.append(bucket);
            table.append("\n");
            index++;
        }
        return table.toString();
    }


}
