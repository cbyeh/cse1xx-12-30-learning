import java.io.IOException;
import java.util.ArrayList;

/**
 * Class containing different strategies for sort.
 */
public class Sort {

    /** The delay signifying the cost of the operation work */
    private int work_time;

    /** The copy of the data structure which stores all the values */
    private ArrayList<Entry> elements;

    /** The copy of the data structure on which we operate */
    private ArrayList<Entry> elements_to_be_sorted;

    /** Counter to keep track of the number of time work is called by the algorithm */
    private int work_counter = 0;

    /**
     * Initialises all the data structures.
     * @param work_time The delay signifying the cost of the operation work.
     * @param offset The number of entries in the data file it should skip before it starts loading.
     * @param n The total number of elements through which search has to be performed.
     */
    public Sort(int work_time, int n, int offset) throws IOException {
        this.work_time = work_time;
        this.elements = Helpers.load_data(n, offset);
    }

    public void set_elements(ArrayList<Entry> elements) {
        this.elements = elements;
    }

    public ArrayList<Entry> get_elements() {
        return this.elements_to_be_sorted;
    }

    public int get_work_counter() {
        return work_counter;
    }

    /**
     * Initializing some data structures
     */
    private void init() {
        this.elements_to_be_sorted = new ArrayList<Entry>();
        this.elements_to_be_sorted.addAll(this.elements);
        this.work_counter = 0;
    }

    /**
     * Work signifies the most basic and significant unit of work. In this case it is the work of comparison.
     * @param i Index of the entry being compared.
     * @param j Index of the entry being compared.
     */
    private int work(int i, int j){
        try {
            Thread.sleep(this.work_time);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        this.work_counter++;
        return this.elements_to_be_sorted.get(i).key.compareTo(this.elements_to_be_sorted.get(j).key);
    }

    /**
     * Swap these to elements in the given array list.
     * @param i Index of the entry being swapped.
     * @param j Index of the entry being swapped.
     * @param arr The array.
     */
    private void swap(ArrayList<Entry> arr, int i, int j){
        Entry tmp;
        tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    public void sort_strategy_1() {
        this.init();
        int mi;
        for(int i=0; i< this.elements_to_be_sorted.size(); i++) {
            mi = i;
            for(int j=i; j<this.elements_to_be_sorted.size(); j++){
                if(this.work(mi, j)>0) {
                    mi = j;
                }
            }
            this.swap(this.elements_to_be_sorted, i, mi);
        }
    }

    public void sort_strategy_2() {
        this.init();
        boolean already_sorted;
        for(int i=0; i< this.elements_to_be_sorted.size();i++) {
            already_sorted = true;
            for(int j=1; j<this.elements_to_be_sorted.size()-i; j++){
                if(this.work(j, j-1)<0) {
                    this.swap(this.elements_to_be_sorted, j-1, j);
                    already_sorted = false;
                }
            }
            if (already_sorted) {
                break;
            }
        }
    }

    private void strategy_3_helper(int s, int e) {
        if (s < e) {
            int pi = strategy_3_helper_2(s, e);
            strategy_3_helper(s, pi - 1);  // Before pi
            strategy_3_helper(pi + 1, e); // After pi
        }
    }

    private int strategy_3_helper_2(int s, int e) {
        int i = (s - 1);  // Index of smaller element
        for (int j = s; j <= e - 1; j++) {
            if (this.work(j, e)<0) {
                i++;
                this.swap(this.elements_to_be_sorted, i, j);
            }
        }
        this.swap(elements_to_be_sorted, i+1, e);
        return (i + 1);
    }

    public void sort_strategy_3() {
        this.init();
        this.strategy_3_helper(0, this.elements_to_be_sorted.size()-1);
    }
}
