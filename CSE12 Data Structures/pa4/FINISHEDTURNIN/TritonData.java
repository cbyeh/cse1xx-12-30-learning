import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TritonData {

    private List<String> transactions;
    private int proofId;

    /**
     * Triton Data Constructor
     *
     * @param None
     */
    public TritonData() {
        this(1, new LinkedList<>());
    }

    // Constructor if specific values are specified*/
    public TritonData(int proofId, List<String> transactions) {
        // transactions = new LinkedList<>();
        this.proofId = proofId;
        this.transactions = transactions;
    }

    // Get transactions*/
    public List<String> getTransactions() {
        return transactions;
    }

    // Get proofId*/
    public int getProofId() {
        return proofId;
    }

    // Print the data block*/
    public String toString() {
        StringBuilder data = new StringBuilder("\nDATA Start--------------------------------\n");
        data.append("Proof of work: ");
        data.append(proofId);
        data.append("\n");
        for (String s : getTransactions()) {
            data.append("Transaction ");
            data.append(getTransactions().indexOf(s));
            data.append("\nTransaction Content: ");
            data.append(s);
            data.append("\n");
        }
        data.append("DATA End --------------------------------");
        return data.toString();
    }
}
