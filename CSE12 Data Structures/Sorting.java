import java.util.Arrays;

public class Sorting {

    public static void main(String[] args) {
        // int arr[] = {3, 2, 5, 6, 4, 1};
        // sortB(arr);
        System.out.println("Hello" == "Hello");

    }


    public static void sortA(int list[]) {
        System.out.println("init list: " + Arrays.toString(list));
        System.out.println();
        int comparisons = 0;
        for (int j = 1; j < list.length; j++) {
            int i = 0;
            System.out.println("comparing J=" + list[j] + " I=" + list[i]);
            // comparisons++;
            while (list[j] > list[i]) {
                comparisons++;
                System.out.println("comparing J=" + list[j] + " I=" + list[i]);
                i += 1;
            }
            int m = list[j];
            for (int k = 0; k <= j - i - 1; k++)
                list[j - k] = list[j - k - 1];
            list[i] = m;
            System.out.println(Arrays.toString(list));
            System.out.println("Comparisons so far: " + comparisons);
            System.out.println("");
        }
    }

    public static void sortB(int list[]) {
        System.out.println("init list: " + Arrays.toString(list));
        System.out.println();
        int comparisons = 0;
        for (int j = 1; j < list.length; j++) {
            int i = j;
            System.out.println("comparing J=" + list[j] + " I=" + list[i-1]);
            // comparisons++;
            while (i > 0 && list[j] < list[i - 1]) {
                comparisons++;
                System.out.println("comparing J=" + list[j] + " I=" + list[i-1]);
                i -= 1;
            }
            int m = list[j];
            for (int k = 0; k <= j - i - 1; k++)
                list[j - k] = list[j - k - 1];
            list[i] = m;
            System.out.println(Arrays.toString(list));
            System.out.println("Comparisons so far: " + comparisons);
            System.out.println("");
        }
    }
}