public class TritonBlock {
    // Class variables, all the attributes of the block*/
    private int index;
    private long timestamp;
    private TritonData data;
    private String prev_hash;
    private String self_hash;

    // Constructor, builds a block with passed in variables, then creates a hash for curr block*/
    public TritonBlock(int index, long timestamp, TritonData data, String prev_hash) {
        this.index = index;
        this.timestamp = timestamp;
        this.data = data;
        this.prev_hash = prev_hash;
        this.self_hash = hashBlock();
    }

    // Taken from String class's hashCode() method principles, modified for our program
    private String hashBlock() {
        String value = Long.toString(timestamp) +
                Integer.toString(index) + prev_hash;
        int hashValue = 0;
        int off = 0;
        char val[] = value.toCharArray();
        for (int i = 0; i < value.length(); i++) {
            hashValue = 31 * hashValue + val[off++];
        }
        return Integer.toString(hashValue);
    }

    // Get index*/
    public int getIndex() {
        return index;
    }

    // Get timestamp*/
    public long getTimestamp() {
        return timestamp;
    }

    // Get data block*/
    public TritonData getData() {
        return data;
    }

    // Get previous hash*/
    public String getPrev_hash() {
        return prev_hash;
    }

    // Get current hash*/
    public String getSelf_hash() {
        return self_hash;
    }

    // Print the block*/
    public String toString() {
        return "\nTritonBlock " + getIndex() + "\nIndex: " + getIndex() + "\nTimestamp: " +
                getTimestamp() + "\nPrev Hash: " + getPrev_hash() +
                "\nHash: " + getSelf_hash() + getData().toString() + "\n";
    }
}
