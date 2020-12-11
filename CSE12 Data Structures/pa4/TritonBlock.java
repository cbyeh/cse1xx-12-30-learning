public class TritonBlock {
	/*Class variables, all the attributes of the block*/
    private int index;
    private long timestamp;
    private TritonData data;
    private String prev_hash;
    private String self_hash;

    /*Constructor, builds a block with passed in variables, then creates a hash for curr block*/
    public TritonBlock(int index, long timestamp, TritonData data, String prev_hash){
        //TODO
    }

    private String hashBlock(){
        //TODO
    }

    /*Get index*/
    public int getIndex(){
        //TODO
    }

    /*Get timestamp*/
    public long getTimestamp(){
        //TODO
    }

    /*Get data block*/
    public TritonData getData(){
        //TODO
    }

    /*Get previous hash*/
    public String getPrev_hash(){
        //TODO
    }

    /*Get current hash*/
    public String getSelf_hash(){
        //TODO
    }

    /*Print the block*/
    public String toString(){
        //TODO
    }
}
