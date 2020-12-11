/*import static org.junit.Assert.*;

import org.junit.Test;


public class ExplorerTester {

	@Test
	public void test_bfs_sea1() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-1.txt");
        Explorer queue_solver = new Explorer(sea, new QueueExploreList());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Found Nemo!" +
        		"\nPath from start to finish: [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [0,6] ");
        assertEquals( ("SxxxxxN\n"),queue_solver.getSea().toString());
        assertEquals( 7,queue_solver.getTotalVisited());

	}
	@Test
    public void test_dfs_sea1() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-1.txt");
        Explorer stack_solver = new Explorer(sea, new StackExploreList());
        stack_solver.solve();
        assertEquals("Found Nemo!\n" +
                "Path from start to finish: [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [0,6] ", stack_solver.getPath());
        assertEquals(("SxxxxxN\n"), stack_solver.getSea().toString());
        assertEquals(7, stack_solver.getTotalVisited());
    }
	@Test
	public void test_bfs_sea2() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-2.txt");
        Explorer queue_solver = new Explorer(sea, new QueueExploreList());
        queue_solver.solve();
        assertEquals("Found Nemo!" +
        		"\nPath from start to finish: [0,0] [0,1] [0,2] [1,2] [2,2] ", queue_solver.getPath());
        assertEquals( ("Sxx\n" + "..x\n" + "..N\n"),queue_solver.getSea().toString());
        assertEquals( 9,queue_solver.getTotalVisited());

	}
	
	@Test
    public void test_dfs_sea2() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-2.txt");
        Explorer stack_solver = new Explorer(sea, new StackExploreList());
        stack_solver.solve();
        assertEquals("Found Nemo!" +
                "\nPath from start to finish: [0,0] [1,0] [2,0] [2,1] [2,2] ", stack_solver.getPath());
        assertEquals( ("S__\n" + "x__\n" + "xxN\n"),stack_solver.getSea().toString());
        assertEquals( 5,stack_solver.getTotalVisited());

    }
	@Test
    public void test_bfs_sea3() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-3.txt");
        Explorer queue_solver = new Explorer(sea, new QueueExploreList());
        queue_solver.solve();
        assertEquals(queue_solver.getPath(), "Found Nemo!" +
                "\nPath from start to finish: [0,1] [0,0] [1,0] [2,0] [3,0] [4,0] [5,0] [6,0] [7,0] [8,0] [9,0] [9,1] [9,2] [9,3] [9,4] [9,5] [9,6] [9,7] [9,8] [8,8] [7,8] [6,8] [5,8] [4,8] [3,8] [2,8] [2,7] [2,6] [2,5] [2,4] [2,3] [2,2] [3,2] [4,2] [5,2] [6,2] [7,2] [7,3] [7,4] [7,5] [7,6] [6,6] [5,6] [4,6] [4,5] [4,4] [5,4] ");
        assertEquals(queue_solver.getSea().toString(), (
                "xS........\n"
                +"x#########\n"
                +"x#xxxxxxx#\n"
                +"x#x#####x#\n"
                +"x#x#xxx#x#\n"
                +"x#x#N#x#x#\n"
                +"x#x###x#x#\n"
                +"x#xxxxx#x#\n"
                +"x#######x#\n"
                +"xxxxxxxxx#\n"
                ));
        assertEquals( 55,queue_solver.getTotalVisited());
    }
    
    @Test
    public void test_dfs_sea3() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-3.txt");
        Explorer stack_solver = new Explorer(sea, new StackExploreList());
        stack_solver.solve();
        assertEquals(stack_solver.getPath(), "Found Nemo!" +
                "\nPath from start to finish: [0,1] [0,0] [1,0] [2,0] [3,0] [4,0] [5,0] [6,0] [7,0] [8,0] [9,0] [9,1] [9,2] [9,3] [9,4] [9,5] [9,6] [9,7] [9,8] [8,8] [7,8] [6,8] [5,8] [4,8] [3,8] [2,8] [2,7] [2,6] [2,5] [2,4] [2,3] [2,2] [3,2] [4,2] [5,2] [6,2] [7,2] [7,3] [7,4] [7,5] [7,6] [6,6] [5,6] [4,6] [4,5] [4,4] [5,4] ");
        assertEquals(stack_solver.getSea().toString(), (
                "xS________\n"
                +"x#########\n"
                +"x#xxxxxxx#\n"
                +"x#x#####x#\n"
                +"x#x#xxx#x#\n"
                +"x#x#N#x#x#\n"
                +"x#x###x#x#\n"
                +"x#xxxxx#x#\n"
                +"x#######x#\n"
                +"xxxxxxxxx#\n"
                ));
        assertEquals( 47,stack_solver.getTotalVisited());

    }
    
    @Test
    public void test_bfs_sea4() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-4.txt");
        Explorer queue_solver = new Explorer(sea, new QueueExploreList());
        queue_solver.solve();
        assertEquals("Found Nemo!" +
                "\nPath from start to finish: [0,0] [0,1] [0,2] [0,3] [0,4] [0,5] [1,5] [2,5] [3,5] [4,5] [5,5] ", queue_solver.getPath());
        assertEquals((
                "Sxxxxx....\n"+
                ".....x....\n"+
                ".....x..._\n"+
                ".....x..__\n"+
                ".....x.___\n"+
                ".....N____\n"+
                "....______\n"+
                "..._______\n"+
                "..________\n"+
                "._________\n"
                ), queue_solver.getSea().toString());
        assertEquals( 60,queue_solver.getTotalVisited());

    }
    
    @Test
    public void test_dfs_sea4() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-4.txt");
        Explorer stack_solver = new Explorer(sea, new StackExploreList());
        stack_solver.solve();
        assertEquals("Found Nemo!" +
                "\nPath from start to finish: [0,0] [1,0] [2,0] [3,0] [4,0] [5,0] [6,0] [7,0] [8,0] [9,0] [9,1] [9,2] [9,3] [9,4] [9,5] [9,6] [9,7] [9,8] [9,9] [8,9] [8,8] [8,7] [8,6] [8,5] [8,4] [8,3] [8,2] [8,1] [7,1] [7,2] [7,3] [7,4] [7,5] [7,6] [7,7] [7,8] [7,9] [6,9] [6,8] [6,7] [6,6] [6,5] [6,4] [6,3] [6,2] [6,1] [5,1] [5,2] [5,3] [5,4] [5,5] ", stack_solver.getPath());
        assertEquals((
                "S_________\n"+
                "x_________\n"+
                "x_________\n"+
                "x_________\n"+
                "x_________\n"+
                "xxxxxN____\n"+
                "xxxxxxxxxx\n"+
                "xxxxxxxxxx\n"+
                "xxxxxxxxxx\n"+
                "xxxxxxxxxx\n"
                ), stack_solver.getSea().toString());
        assertEquals( 51,stack_solver.getTotalVisited());

    }

    @Test
    public void test_bfs_sea5() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-5.txt");
        Explorer queue_solver = new Explorer(sea, new QueueExploreList());
        queue_solver.solve();
        assertEquals( "Uh Oh!! Could not find Nemo!!",queue_solver.getPath());
        assertEquals( ("S...\n" + "..##\n" + "..#_\n"+"..#N\n"),queue_solver.getSea().toString());
        assertEquals( 10,queue_solver.getTotalVisited());

    }
    
    @Test
    public void test_dfs_sea5() {
        Sea sea = new Sea();
        sea.loadSea("seas/sea-5.txt");
        Explorer stack_solver = new Explorer(sea, new StackExploreList());
        stack_solver.solve();
        assertEquals( "Uh Oh!! Could not find Nemo!!",stack_solver.getPath());
        assertEquals( ("S...\n" + "..##\n" + "..#_\n"+"..#N\n"),stack_solver.getSea().toString());
        assertEquals( 0,stack_solver.getExploreListSize());

    }
	
}
*/