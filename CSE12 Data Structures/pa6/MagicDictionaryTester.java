import static org.junit.Assert.*;
import org.junit.*;

public class MagicDictionaryTester
{
    String[] arr;
    MagicDictionary magicDict;

    @Before
    public void setUp() throws Exception
    {
      arr = new String[]{"a", "b", "c", "d", "1e", "11e", "f", "z", "a1", "a", "a123", "bb2", "2b", "A", "C", "AA", "5F", "ilovecse12", "2b", "aaaa", "bbbb", "BB", "BBB", "BBBBBB"};
      magicDict = new MagicDictionary(arr);
    }
    /** Test if heads of the lists are correct */

    @Test
    public void testSort1()
    {
      System.out.println("testSort1");
      //sort by natural ordering
      String[] expected = {"11e", "1e", "2b", "2b", "5F", "A", "AA", "BB", "BBB", "BBBBBB", "C", "a", "a", "a1", "a123", "aaaa", "b", "bb2", "bbbb", "c", "d", "f", "ilovecse12", "z"};
      String[] result = magicDict.sort1();
      assertArrayEquals(expected, result);
    }

    @Test
    public void testSort2()
    {
      System.out.println("testSort2");
      //sort by length
      String[] expected = {"ilovecse12","BBBBBB","a123","aaaa","bbbb","11e","BBB","bb2","1e","2b","2b","5F","AA","BB","a1","A","C","a","a","b","c","d","f","z"};
      String[] result = magicDict.sort2();
      assertArrayEquals(expected, result);
    }

    @Test
    public void testSort3()
    {
      System.out.println("testSort3");
      //sort by word frequency
      String[] expected = {"2b","2b","a","a","11e","1e","5F","A","AA","BB","BBB","BBBBBB","C","a1","a123","aaaa","b","bb2","bbbb","c","d","f","ilovecse12","z"};
      String[] result = magicDict.sort3();
      assertArrayEquals(expected, result);
    }

    @Test
    public void testSort4()
    {
      System.out.println("testSort4");
      //sort by captical letter number
      String[] expected = {"BBBBBB","BBB","AA","BB","5F","A","C","11e","1e","2b","2b","a","a","a1","a123","aaaa","b","bb2","bbbb","c","d","f","ilovecse12","z"};
      String[] result = magicDict.sort4();
      assertArrayEquals(expected, result);
    }

    @Test
    public void testSort5()
    {
      System.out.println("testSort5");
      //sort by palindrome first, longest first
      String[] expected = {"BBBBBB","aaaa","bbbb","BBB","AA","BB","A","C","a","a","b","c","d","f","z","11e","1e","2b","2b","5F","a1","a123","bb2","ilovecse12"};
      String[] result = magicDict.sort5();
      assertArrayEquals(expected, result);
    }
}


// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4