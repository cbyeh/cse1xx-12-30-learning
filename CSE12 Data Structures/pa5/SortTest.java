import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SortTest {
    private static Sort s;
    private static int[] ns = {10, 20, 30, 40, 50};
    private static int[] works = {1, 2, 5};
    private static int[] offsets = {0, 200, 400, 800, 1000, 1200, 1400, 1600, 1800, 2000};
    private static FileWriter fileWriter;

    public static void append(String s) throws IOException {
        fileWriter.write(s);
    }

    @AfterClass
    public static void tearDown() throws IOException {
        fileWriter.close();
    }

    @BeforeClass
    public static void init() throws IOException {
        s = new Sort(100, 100, 0);
        fileWriter = new FileWriter("sort.csv");
    }

    @Test
    public void test_strategy_1() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bba", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.sort_strategy_1();
        a = s.get_elements();
        assertEquals("acc", a.get(0).key);
        assertEquals("bba", a.get(1).key);
        assertEquals("bbc", a.get(2).key);
    }

    @Test
    public void test_strategy_2() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bba", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.sort_strategy_2();
        a = s.get_elements();
        assertEquals("acc", a.get(0).key);
        assertEquals("bba", a.get(1).key);
        assertEquals("bbc", a.get(2).key);
    }

    @Test
    public void test_strategy_3() {
        ArrayList<Entry> a = new ArrayList<Entry>();
        a.add(new Entry("bba", 2));
        a.add(new Entry("bbc", 3));
        a.add(new Entry("acc", 4));
        s.set_elements(a);
        s.sort_strategy_3();
        a = s.get_elements();
        assertEquals("acc", a.get(0).key);
        assertEquals("bba", a.get(1).key);
        assertEquals("bbc", a.get(2).key);
    }

    /*@Test
    public void demo() throws IOException {
        long t1, t2;
        s = new Sort(100, 100, 0);
        t1 = System.currentTimeMillis();
        s.sort_strategy_3();
        t2 = System.currentTimeMillis();
        append("sample_strategy_1_w1,100,"+(t2-t1));
        System.out.println("Time: " + (t2-t1) + "\t Operations: "+s.get_work_counter());
    }*/
    @Test
    public void run1() throws IOException {
        long t1, t2;
        for (int n : ns) {
            append("\n");
            for (int w : works) {
                append("\n");
                for (int h : offsets) {
                    s = new Sort(w, n, h);
                    if (n == 10) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_1();
                        t2 = System.currentTimeMillis();
                        append("sort1_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 20) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_1();
                        t2 = System.currentTimeMillis();
                        append("sort1_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 30) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_1();
                        t2 = System.currentTimeMillis();
                        append("sort1_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 40) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_1();
                        t2 = System.currentTimeMillis();
                        append("sort1_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 50) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_1();
                        t2 = System.currentTimeMillis();
                        append("sort1_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    }
                }
            }
        }
    }

    @Test
    public void run2() throws IOException {
        long t1, t2;
        for (int n : ns) {
            append("\n");
            for (int w : works) {
                append("\n");
                for (int h : offsets) {
                    s = new Sort(w, n, h);
                    if (n == 10) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_2();
                        t2 = System.currentTimeMillis();
                        append("sort2_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 20) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_2();
                        t2 = System.currentTimeMillis();
                        append("sort2_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 30) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_2();
                        t2 = System.currentTimeMillis();
                        append("sort2_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 40) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_2();
                        t2 = System.currentTimeMillis();
                        append("sort2_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 50) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_2();
                        t2 = System.currentTimeMillis();
                        append("sort2_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    }
                }
            }
        }
    }

    @Test
    public void run3() throws IOException {
        long t1, t2;
        for (int n : ns) {
            append("\n");
            for (int w : works) {
                append("\n");
                for (int h : offsets) {
                    s = new Sort(w, n, h);
                    if (n == 10) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_3();
                        t2 = System.currentTimeMillis();
                        append("sort3_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 20) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_3();
                        t2 = System.currentTimeMillis();
                        append("sort3_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 30) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_3();
                        t2 = System.currentTimeMillis();
                        append("sort3_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 40) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_3();
                        t2 = System.currentTimeMillis();
                        append("sort3_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    } else if (n == 50) {
                        t1 = System.currentTimeMillis();
                        s.sort_strategy_3();
                        t2 = System.currentTimeMillis();
                        append("sort3_ w: " + w + " o: " +
                                h + " n: " + n + " , " + (t2 - t1) + "\n");
                        System.out.println("Time: " + (t2 - t1) +
                                "\t Operations: " + s.get_work_counter());
                    }
                }
            }
        }
    }
}
