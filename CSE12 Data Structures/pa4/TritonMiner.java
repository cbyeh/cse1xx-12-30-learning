import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.util.Random;
import java.util.StringTokenizer;

/*Sets up server, manages blockchains, takes in and acts on user input*/
public class TritonMiner {

    private static final int BLOCK_SIZE = 2;

    public static void main(String[] args) throws IOException {
        /*Initialize the local blockchain*/
        long timestamp = System.currentTimeMillis();
        /*Created local blockchain and added genesis block*/
        TritonBlockChain blockchain = new TritonBlockChain(0, timestamp, new TritonData(), "0");
        /*Represents the queue for all transactions*/
        //read in trascatipn and put in transcation queue
        List<String> transaction_queue = new ArrayList<String>();
        loadTranscation(transaction_queue, "transaction/transaction_1.txt");

        List<String> currentTransaction = new ArrayList<String>();
        for (int i = 0; i < transaction_queue.size(); i++) {
            currentTransaction.add(transaction_queue.get(i));
            if (currentTransaction.size() == BLOCK_SIZE) {
                //mine
                blockchain.beginMine(new ArrayList<>(currentTransaction));
                //reintilize
                currentTransaction.clear();
            }
        }
        /*Print blockchain*/
        System.out.println(blockchain.toString());
        /*Validate the blockchain Mine*/
        System.out.println("This block chain is: "+blockchain.validateChain());
    }

    public static boolean loadTranscation(List<String> transaction_queue, String fname) {
        String line;
        BufferedReader inputStrem;
        StringTokenizer st;
        try {
            inputStrem = new BufferedReader(new FileReader(fname));
            while ((line = inputStrem.readLine()) != null) {
                transaction_queue.add(line);
            }
        } catch (IOException e) {
            System.out.println (e.toString());
            System.out.println("Could not find file " + fname);
        } 
        return true;
    } 

}


