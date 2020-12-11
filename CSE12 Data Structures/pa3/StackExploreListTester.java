/*import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;


public class StackExploreListTester {
    private StackExploreList empty;
    private StackExploreList one;
    private StackExploreList many;
    
    private final int N = 3;
    
    @Before
	public void setUp()
	{
		empty = new StackExploreList();
		one = new StackExploreList();
		one.add(new Square(0,0,0));
		many = new StackExploreList();
		for ( int i = 0; i < N; i++ ) {
		    many.add(new Square(i,i,0));
		}
	}
	
    @Test
	public void testSize()
	{
		assertEquals(empty.size(), 0);
		assertEquals(one.size(), 1);
		assertEquals(many.size(), N);
	}
	
   
    @Test
	public void testAdd()
	{
	    empty.add(new Square(1,2,1));
	    assertEquals(1, empty.size());
	    one.add(new Square(1,2,1));
	    assertEquals(2, one.size());
	}
	
   
    @Test
	public void getNext()
    {
        assertEquals(new Square(0,0,0), one.getNext());
        assertEquals(0, one.size());
        assertEquals(new Square(2,2,0), many.getNext());
        assertEquals(N-1, many.size());
    }
    
    @Test
	public void isEmpty()
    {
    	for ( int i = 0; i < N; i++ ) {
		    many.getNext();
		}
    	assertEquals(true, many.isEmpty());
    }
	
    
    
}
*/