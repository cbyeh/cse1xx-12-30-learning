/**
 * Entry Class to store information
 */
public class Entry {

    /**
     * Identifier of a block. Used to store last names.
     */
    String key;

    /**
     * Representative of information belonging data block represented by the key.
     */
    int value;

    Entry(String k, int v) {
        this.key = k;
        this.value = v;
    }

    public String getKey() {
        return this.key;
    }
}
