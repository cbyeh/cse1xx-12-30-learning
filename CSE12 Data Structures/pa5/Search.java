import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class containing different strategies for search.
 */
public class Search {

    /** The delay signifying the cost of the operation work */
    private int work_time;

    /** Elements are stored in these data structures suitable for each strategy */
    private ArrayList<Entry> elements;
    private ArrayList<Entry> sorted_elements;
    private ArrayList<ArrayList<Entry>> hash_based;

    private int hash_size = 20;

    /** The delay signifying the cost of the operation hash */
    private int hash_time;

    /** Counter to keep track of the number of time work is called by the algorithm */
    private int work_counter = 0;


    /**
     * Initialises all the data structures.
     * @param work_time The delay signifying the cost of the operation work.
     * @param hash_time The delay signifying the cost of the operation hash.
     * @param n The total number of elements through which search has to be performed.
     */
    public Search(int work_time, int hash_time, int n) throws IOException {
        this.hash_time = hash_time;
        this.work_time = work_time;
        this.elements = Helpers.load_data(n, 0);
        this.set_sorted_elements();
        this.set_hash_elements();
    }

    public void set_elements(ArrayList<Entry> elements) {
        this.elements = elements;
    }

    /**
     * Initialises the data structures for strategy_3.
     */
    public void set_hash_elements() {
        int hash;
        this.hash_based = new ArrayList<ArrayList<Entry>>();
        for(int i=0; i<this.hash_size; i++) {
            this.hash_based.add(new ArrayList<Entry>());
        }
        for(Entry en: this.sorted_elements) {
            hash = this.hash(en.key, true);
            this.hash_based.get(hash).add(en);
        }
    }

    /**
     * Initialises the data structures for strategy_2.
     */
    public void set_sorted_elements() {
        this.sorted_elements = new ArrayList<Entry>(this.elements);
        this.sorted_elements.sort(Comparator.comparing(Entry::getKey));
    }

    /**
     * Function to compute a hash
     * @param k Thw String to be hashed
     * @param skip_wait A flag indicating to skip the delay being introduced.
     */
    private int hash(String k, boolean skip_wait) {

        if (!skip_wait) {
            try {
                Thread.sleep(this.hash_time);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

        int s = 0;
        for (char c: k.toCharArray()) {
            s += (int)c;
        }
        return s % this.hash_size;
    }

    public int get_work_counter() {
        return work_counter;
    }

    /**
     * Work signifies the most basic and significant unit of work. In this case it is the work of comparison.
     * @param k The string to be checked as to if its the key of the entry.
     * @param e The entry with which the string is checked
     */
    private int work(Entry e, String k){
        try {
            Thread.sleep(this.work_time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.work_counter++;
        return e.key.compareTo(k);
    }

	// Linear SEARCH
	// Big O(n)
	// Big Omega(1)
	// Big Theta(n/2) within Big Theta(n)
    public int search_strategy_1(String k) {
        this.work_counter = 0;
        for(Entry en: this.elements) {
            if(this.work(en, k) == 0) {
                return en.value;
            }
        }
        return -1;
    }

	// Big O(log_2(n))
	// Big OMEGA(1)
	// Big THETA(1)
    public int search_strategy_2(String k) {
        this.work_counter = 0;
        int s, e, m, tmp;
        Entry en;
        s = 0;
        e = this.sorted_elements.size();
        while(s < e) {
            m = (s + e) / 2;
            en = this.sorted_elements.get(m);
            tmp = this.work(en, k);
            if(tmp == 0) {
                return en.value;
            } else if (tmp > 0) {
                e = m;
            } else {
                if (s == m) break;
                s = m;
            }
        }
        return -1;
    }

    public int search_strategy_3(String k) {
        this.work_counter = 0;
        int hash_index = this.hash(k, false);
        for(Entry en: this.hash_based.get(hash_index)) {
            if(this.work(en, k) == 0) {
                return en.value;
            }
        }
        return -1;
    }

}
