import java.util.*;

public class TritonBlockChain {

	private static final String MINE_REWARD = "1";
    /*Blockchain clas variable*/
    private List<TritonBlock> blockchain;

    /*Constructor, takes in genesis block data to start the blockchain*/
    public TritonBlockChain(int index, long timestamp, TritonData data, String prev_hash) {
        //TODO
    }

    /*Makes the next block after the proof of work from mining is finished*/
    public TritonBlock makeNewBlock(TritonBlock lastBlock, TritonData newData) {
        //TODO
    }

    /*Mines the transaction and creates the block to add to the blockchain*/
    public boolean beginMine(List<String> curTransactions) {
        //TODO
    }

    /*Simple proof of work algorithm to prove cpu usage was used to mine block*/
    public int proofOfWork() {
        //TODO
    }

    /*Prints current blockchain*/
    public String toString() {
        //TODO
    }

    /*Validates each block in the chain looking for any hash pointer descrepancies, which can point to a tampering problem*/
    public boolean validateChain() {
        //TODO
    }
    /*Get blockchain*/
    public List<TritonBlock> getBlockchain() {
        //TODO
    }
}
