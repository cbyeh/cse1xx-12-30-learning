import java.util.*;

public class TritonBlockChain {

    private static final String MINE_REWARD = "1";
    // Blockchain class variable
    private List<TritonBlock> blockchain;

    // Constructor, takes in genesis block data to start the blockchain*/
    public TritonBlockChain(int index, long timestamp, TritonData data, String prev_hash) {
        blockchain = new LinkedList<>();
        TritonBlock genesis = new TritonBlock(index, timestamp, data, prev_hash);
        blockchain.add(genesis);
    }

    // Makes the next block after the proof of work from mining is finished*/
    public TritonBlock makeNewBlock(TritonBlock lastBlock, TritonData newData) {
        int index = lastBlock.getIndex() + 1;
        long timestamp = System.currentTimeMillis();
        String prevHash = lastBlock.getSelf_hash();
        TritonBlock newBlock = new TritonBlock(index, timestamp, newData, prevHash);
        blockchain.add(newBlock);
        return newBlock;
    }

    // Mines the transaction and creates the block to add to the blockchain*/
    public boolean beginMine(List<String> curTransactions) {
        if (curTransactions.size() == 0)
            return false;
        int proofId = proofOfWork();
        curTransactions.add("Triton coined earned: " + MINE_REWARD);
        TritonData newData = new TritonData(proofId, curTransactions);
        makeNewBlock(blockchain.get(blockchain.size() - 1), newData);
        return true;
    }

    /**
     * Calculates least common multiple of previous proofOfWork + 1 and 13
     */

    public int proofOfWork() {
        int prev = blockchain.get(blockchain.size() - 1).getData().getProofId() + 1;
        int proof = Math.max(13, prev);
        while (proof % prev != 0 || proof % 13 != 0)
            proof++;
        return proof;
    }

    // Prints current blockchain*/
    public String toString() {
        StringBuilder chain = new StringBuilder("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" +
                "\n" +
                "T R I T O N B L O C K C H A I N" +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        for (TritonBlock t : blockchain)
            chain.append(t.toString());
        return chain.toString();
    }

    // Validates each block in the chain looking for any hash pointer discrepancies, which can point to a tampering problem*/
    public boolean validateChain() {
        boolean flag = true;
        for (int i = 1; i < blockchain.size(); i++)
            if (!blockchain.get(i).getPrev_hash().equals(blockchain.get(i - 1).getSelf_hash()))
                flag = false;
        return flag;
    }

    // Get blockchain*/
    public List<TritonBlock> getBlockchain() {
        return blockchain;
    }
}
