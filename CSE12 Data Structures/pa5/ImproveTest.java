import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImproveTest {
    @Test
    public void test_strategy_2() {
        assertEquals(2, Improve.not_really_a_mystery(3));
        assertEquals(8, Improve.not_really_a_mystery(6));
        assertEquals(13, Improve.not_really_a_mystery(7));
        assertEquals(21, Improve.not_really_a_mystery(8));
    }
}
