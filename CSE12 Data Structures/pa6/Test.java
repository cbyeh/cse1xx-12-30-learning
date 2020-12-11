public class Test {

    public static void main(String[] args) {
        System.out.println('0' + 5);
    }

    public static int roundTo(int n, int toRoundTo) {
        while (n % toRoundTo != 0)
            n++;
        return n;
    }

    public static String concatThree(String one, String two, String three) {
        // String newString = one + two;
        return one + two + three;
    }
}
