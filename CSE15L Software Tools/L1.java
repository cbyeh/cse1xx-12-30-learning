import java.util.logging.Logger;
import java.util.logging.Level;

public class L1 {
  //initialize a logger for this class
  protected static Logger logger = Logger.getLogger("L1");

  public static void main(String[] args) {
    //Log a INFO tracing message
    logger.info("Entering main()");

    try{
      int j = 1 / 0;
    } catch (Exception ex){
      //Log the error
      logger.log(Level.SEVERE, "Problem", ex);

    }
    //Log a FINE tracing message
    logger.fine("Leaving L1.main()");
  }
}

