public class HashTable implements IHashTable {

    //You will need a HashTable of LinkedLists.

    private LinkedList[] tableLists;
    private int nelems;  //Number of element stored in the hash table
    private int expand;  //Number of times that the table has been expanded
    private int collision;  //Number of collisions since last expansion
    private String statsFileName;     //FilePath for the file to write statistics upon every rehash
    private boolean printStats = false;   //Boolean to decide whether to write statistics to file or not after rehashing

    //You are allowed to add more :)

    /**
     * Constructor for hash table
     * @param Initial size of the hash table
     */

    //Initialize
}

    /**
     * Constructor for hash table
     *
     * @param Initial size of the hash table
     * @param File    path to write statistics
     */
    public HashTable(int size, String fileName) {

        //Set printStats to true and statsFileName to fileName
    }

    @Override
    public boolean insert(String value) {

        //TODO

    }

    @Override
    public boolean delete(String value) {

        //TODO
    }

    @Override
    public boolean contains(String value) {

        //TODO
    }

    @Override
    public void printTable() {
        //TODO
    }

    @Override
    public int getSize() {
        //TODO
    }

    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;
        for (; !isPrime(n); n += 2) ;
        return n;
    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;
        if (n == 1 || n % 2 == 0)
            return false;
        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;
        return true;
    }

    //TODO - Helper methods can make your code more efficient and look neater.
    //We expect AT LEAST the follwoing helper methods in your code
    //rehash() to expand and rehash the items into the table when load factor goes over the threshold.
    //printStatistics() to print the statistics after each expansion. This method will be called from insert/rehash only if printStats=true

}
