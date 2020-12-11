import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 *  Title: class MyLinkedListTester
 *  Description: JUnit test class for MyLinkedList class
 *  @author Philip Papadopoulos, Christine Alvarado, Ronak Sheth
 * */
public class MyLinkedListTester {
    private MyLinkedList<Integer> empty;
    private MyLinkedList<Integer> one;
    private MyLinkedList<Integer> several;
    private MyLinkedList<Integer> tester;
    private MyLinkedList<String>  slist;
    static final int DIM = 5;
    static final int FIBMAX = 30;
    static final int SEQMAX = 10;

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and
     * a list with several entries (0,1,2)
     */
    @Before
    public void setUp() {
        empty = new MyLinkedList<Integer>();
        one = new MyLinkedList<Integer>();
        tester = new MyLinkedList<Integer>();

        one.add(0,new Integer(0));
        several = new MyLinkedList<Integer>() ;
        // List: 1,2,3,...,Dim
        for (int i = DIM; i > 0; i--)
            several.add(0,new Integer(i));

        // List: "First","Last"
        slist = new MyLinkedList<String>();
        slist.add(0,"First");
        slist.add(1,"Last");
    }

    /** Test adding null to lists */
    @Test
    public void testAddNull(){
        try{
            tester.add(null);
            fail("Should have generated a NullPointerException");
        }
        catch(NullPointerException e){ /* normal */}

        try{
            tester.add(1, null);
            fail("Should have generated a NullPointerException");
        }
        catch(NullPointerException ee){ /* normal */}

        try{
            tester.add(10, null);
            fail("Should have generated a NullPointerException");
        }
        catch(NullPointerException ee){ /* normal */}

    }

    /** Test if heads of the lists are correct */
    @Test
    public void testGetHead() {
        assertEquals("Check 0",new Integer(0),one.get(0));
        assertEquals("Check 0",new Integer(1),several.get(0));
    }

    /** Test if size of lists are correct */
    @Test
    public void testListSize() {
        assertEquals("Check Empty Size",0,empty.size()) ;
        assertEquals("Check One Size",1,one.size()) ;
        assertEquals("Check Several Size",DIM,several.size()) ;
    }

    /** Test setting a specific entry */
    @Test
    public void testSet() {
        several.set(1, new  Integer(6));
        assertEquals("setting linkedlist value", new Integer(6), several.get(1));
        assertEquals("setting linkedlist value", new Integer(6), several.get(6));
        assertEquals("setting linkedlist value", new Integer(6), several.get(11));
        assertEquals("setting linkedlist value", new Integer(6), several.get(-4));
        assertEquals("setting linkedlist value", new Integer(6), several.get(-9));
        slist.set(1,"Final");
        assertEquals("Setting specific value", "Final",slist.get(1));
        assertEquals("Setting specific value", "Final",slist.get(3));
        assertEquals("Setting specific value", "Final",slist.get(-1));
        assertEquals("Setting specific value", "Final",slist.get(-3));
    }

    /** Test isEmpty */
    @Test
    public void testEmpty() {
        assertTrue("empty is empty",empty.isEmpty()) ;
        assertTrue("one is not empty",!one.isEmpty()) ;
        assertTrue("several is not empty",!several.isEmpty()) ;
    }


    /** Test iterator on empty list and several list */
    @Test
    public void testIterator() {
        int counter = 0 ;
        ListIterator<Integer> iter;
        for (iter = empty.listIterator() ; iter.hasNext(); ) {
            fail("Iterating empty list and found element") ;
        }
        counter = 0 ;
        for (iter = several.listIterator() ; iter.hasNext(); iter.next())
            counter++;
        assertEquals("Iterator several count", counter, DIM);
    }

    @Test
    public void testGetIndexOutOfBoundsException() {
        try
        {
            empty.get(0);
            fail("Should have generated an IndexOutOfBoundsException");
        }
        catch(IndexOutOfBoundsException e)
        {
            //  normal
        }
    }

    //**test remove function
    @Test
    public void testRemoveSizeOne() {
        assertEquals("Checking remove one", new Integer(0), one.remove(0));
        assertTrue("List One is empty", one.isEmpty());
        assertEquals("List One is empty", 0, one.size());
    }

    //Testing the clear function works fine
    @Test
    public void testClear() {
        one.clear();
        several.clear();
        slist.clear();
        assertEquals(0, one.size());
        assertEquals(0, several.size());
        assertEquals(0,slist.size());
        try{
            slist.get(10);
            fail("Expected IndexOutOfBoundsException");
        }
        catch(IndexOutOfBoundsException e){ /* expected */}
    }

    /** test Iterator Fibonacci.
     * This is a more holistic test for the iterator.  You should add
     * several unit tests that do more targeted testing of the individual
     * iterator methods.  */
    @Test
    public void testIteratorFibonacci() {

        MyLinkedList<Integer> fib  = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;
        // List: 0 1 1 2 3 5 8 13 ...
        // Build the list with integers 1 .. FIBMAX
        int t, p = 0, q = 1;
        fib.add(0,p);
        fib.add(1,q);
        for (int k = 2; k <= FIBMAX; k++)
        {
            t = p+q;
            fib.add(k,t);
            p = q; q = t;
        }
        // Now iterate through the list to near the middle, read the
        // previous two entries and verify the sum.
        iter = fib.listIterator();
        int sum = 0;
        for (int j = 1; j < FIBMAX/2; j++)
            sum = iter.next();
        iter.previous();
        assertEquals(iter.previous() + iter.previous(),sum);
        // Go forward with the list iterator
        assertEquals(iter.next() + iter.next(),sum);
    }
    /* Add your own tests here */

    @Test
    public void testIteratorSequence(){

        MyLinkedList<Integer> seq = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;
        // List: 0 1 2 3 4 5 6 7 8 9 10 ...
        // Build the list with integers 1 ... SEQMAX
        for(int k = 0; k <= SEQMAX; k++){
            seq.add(k, k);
        }
        // Now iterate through the list to near the middle, read the
        // previous two entries and verify the sum.
        iter = seq.listIterator();
        int sum = 0;
        for(int j = 1; j < SEQMAX/2; j++){
            sum = iter.next();
        }
        iter.previous();
        assertEquals(iter.previous() + iter.previous(), sum);
        // Go forward with the list iterator
        assertEquals(iter.next() + iter.next(), sum);
    }

    @Test
    public void testAdd1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();

        // List: 0 1 2 3 4 5 6 7 8 9 10

        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        assertEquals(tester.get(0), new Integer(0));
        assertEquals(tester.get(1), new Integer(1));
        assertEquals(tester.get(2), new Integer(2));
        assertEquals(tester.get(3), new Integer(3));
        assertEquals(tester.get(4), new Integer(4));
        assertEquals(tester.get(5), new Integer(5));
        assertEquals(tester.get(6), new Integer(6));
        assertEquals(tester.get(7), new Integer(7));
        assertEquals(tester.get(8), new Integer(8));
        assertEquals(tester.get(9), new Integer(9));
        assertEquals(tester.get(10), new Integer(10));
        assertEquals(tester.get(11), new Integer(0));
        assertEquals(tester.get(-1), new Integer(10));
        assertEquals(tester.get(-2), new Integer(9));
    }

    @Test
    public void testAdd2(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();

        // List: 0 1 2 3 4 5 6 7 8 9 10

        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i, i);
        }

        assertEquals(tester.get(0), new Integer(0));
        assertEquals(tester.get(1), new Integer(1));
        assertEquals(tester.get(2), new Integer(2));
        assertEquals(tester.get(3), new Integer(3));
        assertEquals(tester.get(4), new Integer(4));
        assertEquals(tester.get(5), new Integer(5));
        assertEquals(tester.get(6), new Integer(6));
        assertEquals(tester.get(7), new Integer(7));
        assertEquals(tester.get(8), new Integer(8));
        assertEquals(tester.get(9), new Integer(9));
        assertEquals(tester.get(10), new Integer(10));
        assertEquals(tester.get(11), new Integer(0));
        assertEquals(tester.get(-1), new Integer(10));
        assertEquals(tester.get(-2), new Integer(9));
    }

    @Test
    public void testRemove1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();

        // List: 0 1 2 3 4 5 6 7 8 9
        // After Removal: 0 6 7 8 9

        for(int i = 0; i < SEQMAX; i++){
            tester.add(i, i);
        }

        for(int j = 0; j < SEQMAX/2; j++){
            tester.remove(1);
        }

        assertEquals(tester.get(0), new Integer(0));
        assertEquals(tester.get(1), new Integer(6));
        assertEquals(tester.get(2), new Integer(7));
        assertEquals(tester.get(3), new Integer(8));
        assertEquals(tester.get(4), new Integer(9));
        assertEquals(tester.get(5), new Integer(0));
        assertEquals(tester.get(6), new Integer(6));
        assertEquals(tester.get(7), new Integer(7));
        assertEquals(tester.get(8), new Integer(8));
        assertEquals(tester.get(9), new Integer(9));
        assertEquals(tester.get(10), new Integer(0));
        assertEquals(tester.get(11), new Integer(6));
        assertEquals(tester.get(-1), new Integer(9));
        assertEquals(tester.get(-2), new Integer(8));
    }

    /** Iterator Method Functionality Tests */

    @Test
    public void testIterAdd1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        iter.add(11);
        // List: 11 0 1 2 3 4 5 6 7 8 9 10

        assertEquals(new Integer(11), iter.previous());
        assertEquals(new Integer(11), tester.get(0));
    }

    @Test
    public void testIterAdd2(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        while(iter.hasNext()){
            iter.next();
        }
        // L = 10, R = D
        // List: 0 1 2 3 4 5 6 7 8 9 10 11
        iter.add(31);

        assertEquals(new Integer(31), iter.previous());
        assertEquals(new Integer(31), tester.get(11));

        try{
            iter.next();
            iter.next();
            fail("Should have thrown a NoSuchElementException");
        }
        catch(NoSuchElementException e){
            // normal
        }
    }

    @Test
    public void testIterAdd3(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();

        // List: D <--> D
        ListIterator<Integer> iter = tester.listIterator();

        iter.add(0);

        assertEquals(new Integer(0), iter.previous());
        assertEquals(new Integer(0), tester.get(0));
        assertEquals(new Integer(0), tester.get(-1));
        assertEquals(new Integer(0), tester.get(21));

        iter.next();
        assertEquals(0, iter.previousIndex());


    }

    @Test
    public void testIterRemove1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();

        // List: D <--> D
        ListIterator<Integer> iter = tester.listIterator();

        try{
            iter.remove();
            fail("Should have thrown an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }

        try{
            iter.next();
            fail("Should have thrown a NoSuchElementException");
        }
        catch(NoSuchElementException ex){
            // normal
        }

        iter.add(0); // List: D 0 D
                     // L = 0, R = D

        assertEquals(new Integer(0), iter.previous());
        iter.remove();

        // List: D <--> D

        try{
            tester.get(0);
            fail("Should have thrown an IndexOutofBoundsException");
        }
        catch(IndexOutOfBoundsException c){
            // normal
        }
    }

    @Test
    public void testIterRemove2(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        iter.next();// L = 0, R = 1 toRemove = 0
        iter.next(); // L = 1, R = 2 toRemove = 1

        iter.remove();
        // List: 0 2 3 4 5 6 7 8 9 10

        assertEquals(new Integer(0), tester.get(0));
        assertEquals(new Integer(2), tester.get(1));
        assertEquals(new Integer(10), tester.get(-1));
        assertEquals(new Integer(2), tester.get(-9));
        assertEquals(new Integer(0), tester.get(-10));
        assertEquals(new Integer(0), tester.get(10));
        assertEquals(new Integer(2), tester.get(11));

        assertEquals(0, iter.previousIndex());
        assertEquals(1, iter.nextIndex());
        assertEquals(new Integer(0), iter.previous());
        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
    }

    @Test
    public void testIterRemove3(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        iter.next(); // L = 0, R = 1, toRemove = 0
        iter.previous(); // L = D, R = 0, toRemove = 0

        iter.remove();
        // List: 1 2 3 4 5 6 7 8 9 10
        // L = D, R = 1;

        assertEquals(new Integer(1), tester.get(0));
        assertEquals(new Integer(2), tester.get(1));
        assertEquals(new Integer(10), tester.get(-1));
        assertEquals(new Integer(2), tester.get(-9));
        assertEquals(new Integer(1), tester.get(-10));
        assertEquals(new Integer(1), tester.get(10));
        assertEquals(new Integer(2), tester.get(11));

        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
        assertEquals(new Integer(1), iter.next());
        assertEquals(0, iter.previousIndex());
        assertEquals(1, iter.nextIndex());
    }

    @Test
    public void testIterRemove4(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        iter.next(); // L = 0, R = 1, toRemove = 0
        iter.next();// L = 1, R = 2, toRemove = 1
        iter.previous(); // L = 0, R = 1, toRemove = 1

        iter.remove();
        // List: 0 2 3 4 5 6 7 8 9 10

        assertEquals(new Integer(0), tester.get(0));
        assertEquals(new Integer(2), tester.get(1));
        assertEquals(new Integer(10), tester.get(-1));
        assertEquals(new Integer(2), tester.get(-9));
        assertEquals(new Integer(0), tester.get(-10));
        assertEquals(new Integer(0), tester.get(10));
        assertEquals(new Integer(2), tester.get(11));

        assertEquals(0, iter.previousIndex());
        assertEquals(1, iter.nextIndex());
        assertEquals(new Integer(0), iter.previous());
        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
    }

    @Test
    public void testIterRemove5(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        while(iter.hasNext()){
            iter.next();
        }
        // L = 10 R = D toRemove = 10

        iter.remove();
        // List: 0 1 2 3 4 5 6 7 8 9
        // L = 9 R = D

        assertEquals(new Integer(8), tester.get(8));
        assertEquals(new Integer(9), tester.get(9));
        assertEquals(new Integer(9), tester.get(-1));
        assertEquals(new Integer(1), tester.get(-9));
        assertEquals(new Integer(0), tester.get(-10));
        assertEquals(new Integer(8), tester.get(18));
        assertEquals(new Integer(9), tester.get(19));

        assertEquals(9, iter.previousIndex());
        assertEquals(10, iter.nextIndex());
        assertEquals(new Integer(9), iter.previous());
        assertEquals(8, iter.previousIndex());
        assertEquals(9, iter.nextIndex());


    }

    @Test
    public void testIterSet1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        iter.next(); // L = 0, R = 1, toRemove = 0
        iter.add(11); // L = 11, R = 1 toRemove = null

        // List: 0 11 1 2 3 4 5 6 7 8 9 10
        iter.previous(); // L = 0, R = 11 toRemove = 11
        iter.set(111);

        // List: 0 111 1 2 3 4 5 6 7 8 9 10
        // L = 0, R = 111, toRemove = 111
        assertEquals(new Integer(111), tester.get(1));
        assertEquals(new Integer(0), tester.get(0));
        assertEquals(new Integer(1), tester.get(2));
        assertEquals(new Integer(10), tester.get(-1));
        assertEquals(new Integer(111), tester.get(13));

        assertEquals(0, iter.previousIndex());
        assertEquals(1, iter.nextIndex());
        assertEquals(new Integer(111), iter.next());
    }

    @Test
    public void testIterSet2(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        iter.next(); // L = 0, R = 1, toRemove = 0
        iter.remove(); // L = D, R = 1 toRemove = null
        // List: 1 2 3 4 5 6 7 8 9 10

        iter.next(); // L = 1, R = 2, toRemove = 1
        iter.set(11); // L = 11, R = 2, toRemove = 11
        // List: 11 2 3 4 5 6 7 8 9 10

        assertEquals(new Integer(11), tester.get(0));
        assertEquals(new Integer(2), tester.get(1));
        assertEquals(new Integer(10), tester.get(-1));
        assertEquals(new Integer(2), tester.get(-9));
        assertEquals(new Integer(11), tester.get(-10));
        assertEquals(new Integer(11), tester.get(10));
        assertEquals(new Integer(2), tester.get(11));

        assertEquals(0, iter.previousIndex());
        assertEquals(1, iter.nextIndex());
        assertEquals(new Integer(11), iter.previous());
        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
    }

    @Test
    public void testIterSet3(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        iter.next(); // L = 0, R = 1, toRemove = 0
        iter.next(); // L = 1, R = 2, toRemove = 1

        iter.remove();
        // List: 0 2 3 4 5 6 7 8 9 10
        // L = 0 R = 2 toRemove = null

        iter.previous();
        // L = D, R = 0 toRemove = 0

        iter.set(47); // L = D, R = 47, toRemove = 47
        // List: 47 2 3 4 5 6 7 8 9 10

        assertEquals(new Integer(47), tester.get(0));
        assertEquals(new Integer(2), tester.get(1));
        assertEquals(new Integer(10), tester.get(-1));
        assertEquals(new Integer(2), tester.get(-9));
        assertEquals(new Integer(47), tester.get(-10));
        assertEquals(new Integer(47), tester.get(10));
        assertEquals(new Integer(2), tester.get(11));

        assertEquals(-1, iter.previousIndex());
        assertEquals(0, iter.nextIndex());
        assertEquals(new Integer(47), iter.next());
        assertEquals(0, iter.previousIndex());
        assertEquals(1, iter.nextIndex());
    }

    /** Iterator Exception Handling Tests */

    @Test
    public void testRemoveException1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        try{
            iter.remove();
            fail("Should have generated an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }
    }

    @Test
    public void testSetException1(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        iter.next();

        try{
            iter.set(null);
            fail("Should have generated a NullPointerException");
        }
        catch(NullPointerException e){
            // normal
        }
    }

    @Test
    public void testSetException2(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        try{
            iter.set(null);
            fail("Should have generated a NullPointerException");
        }
        catch(NullPointerException e){
            // normal
        }

        try{
            iter.set(99);
            fail("Should have generated an IllegalStateException");
        }
        catch(IllegalStateException ex){
            // normal
        }
    }

    @Test
    public void testAddException(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        try{
            iter.add(null);
            fail("Should have generated a NullPointerException");
        }
        catch(NullPointerException e){
            // normal
        }
    }

    @Test
    public void testNextExceptions(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        try {
            while (iter.hasNext()) {
                iter.next();
            }
        }
        catch(NoSuchElementException e){
            fail("Iterator loop should no go off list");
        }

        try{
            iter.next();
            fail("Should have thrown a NoSuchElementException");
        }
        catch(NoSuchElementException ex){
            // normal
        }


    }

    @Test
    public void testPreviousExceptions(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        try{
            iter.previous();
            fail("Should have thrown a NoSuchElementException");
        }
        catch(NoSuchElementException e){
            // normal
        }
    }

    @Test
    public void testRemoveException2(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        iter.next(); // left pointing at 0, right at 1
        iter.remove(); // removes 0

        try{
            iter.remove();
            fail("Should have thrown an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }
    }

    @Test
    public void testSetException3(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        iter.next(); // left pointing at 0, right at 1
        iter.add(12);
        // newList: 0 12 1 2 3 4 5 6 7 8 9 10

        try{
            iter.set(13);
            fail("Supposed to throw an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }
    }

    @Test
    public void testSetException4(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();
        iter.next();
        iter.next();
        iter.previous(); // left = 0, right = 1
        iter.add(21);
        // List: 0 21 1 2 3 4 5 6 7 8 9 10
        try{
            iter.set(22);
            fail("Supposed to throw an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }
    }

    @Test
    public void testSetException5(){
        MyLinkedList<Integer> tester = new MyLinkedList<Integer>();
        ListIterator<Integer> iter;

        // List: 0 1 2 3 4 5 6 7 8 9 10
        for(int i = 0; i <= SEQMAX; i++){
            tester.add(i);
        }

        iter = tester.listIterator();

        iter.next();
        iter.next();
        iter.remove();

        try{
            iter.set(22);
            fail("Supposed to throw an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }

        iter.next();
        iter.previous();
        iter.remove();

        try{
            iter.set(22);
            fail("Supposed to throw an IllegalStateException");
        }
        catch(IllegalStateException e){
            // normal
        }
    }




}
