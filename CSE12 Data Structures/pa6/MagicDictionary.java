import java.util.*;

public class MagicDictionary {

    String[] magicWords;

    public static void main(String[] args) {
        String[] arr = new String[]{"a", "b", "c", "d", "1e", "11e", "f", "z", "a1", "a", "a123", "bb2", "2b", "A", "C", "AA", "5F", "ilovecse12", "2b", "aaaa", "bbbb", "BB", "BBB", "BBBBBB"};
        MagicDictionary a = new MagicDictionary(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(a.sort3()));
    }


    public MagicDictionary(String[] words) {
        magicWords = words;
    }

    public String[] copy() {
        int len = magicWords.length;
        String[] x = new String[len];
        for (int i = 0; i < len; i++) {
            x[i] = magicWords[i];
        }
        return x;
    }

    // Natural ordering
    public String[] sort1() {
        String[] tmp = copy();
        Arrays.sort(tmp, new Comparator<String>() {
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });
        return tmp;
    }

    // Sort by length, then by natural ordering
    public String[] sort2() {
        String[] tmp = copy();
        Arrays.sort(tmp, new Comparator<String>() {
            public int compare(String a, String b) {
                if (a.length() != b.length())
                    return b.length() - a.length();
                else return a.compareTo(b);
            }
        });
        return tmp;
    }

    // Sort by word frequency, then by natural ordering
    public String[] sort3() {
        String[] tmp = copy();
        Arrays.sort(tmp, new Comparator<String>() {
            public int compare(String a, String b) {
                int aCount = 0;
                int bCount = 0;
                for (int i = 0; i < tmp.length; i++) {
                    if (tmp[i] == a)
                        aCount++;
                    if (tmp[i] == b)
                        bCount++;
                }
                if (aCount < bCount)
                    return 1;
                else if (aCount > bCount)
                    return -1;
                else return a.compareTo(b);
            }
        });
        return tmp;
    }

    // Sort by Strings that contains the most number of capital letters, then by natural ordering.
    public String[] sort4() {
        String[] tmp = copy();
        Arrays.sort(tmp, new Comparator<String>() {
            public int compare(String a, String b) {
                char[] ac = a.toCharArray();
                char[] bc = b.toCharArray();
                int aCount = 0;
                int bCount = 0;
                for (int i = 0; i < ac.length; i++)
                    if (Character.isUpperCase(ac[i]))
                        aCount++;
                for (int i = 0; i < bc.length; i++)
                    if (Character.isUpperCase(bc[i]))
                        bCount++;
                if (aCount != bCount)
                    return bCount - aCount;
                else return a.compareTo(b);
            }
        });
        return tmp;
    }

    /**
     * Palindrome should appear before non-palindrome words.
     * Longer palindrome should appear before shorter palindrome.
     * If both are palindromes and has the same length, order by natural
     * ordering.
     * If both are non-palindrome, order by natural ordering
     */
    public String[] sort5() {
        String[] tmp = copy();
        Arrays.sort(tmp, new Comparator<String>() {
            public int compare(String a, String b) {
                if (isPalindrome(a) && !isPalindrome(b)) {
                    return -1;
                } else if (!isPalindrome(a) && isPalindrome(b))
                    return 1;
                else if (isPalindrome(a) && isPalindrome(b)) {
                    if (a.length() == b.length())
                        return a.compareTo(b);
                    else
                        return b.length() - a.length();
                } else return a.compareTo(b);
            }

            public boolean isPalindrome(String s) {
                int n = s.length();
                for (int i = 0; i < (n / 2); i++)
                    if (Character.toLowerCase(s.charAt(i))
                            != Character.toLowerCase(s.charAt(n - i - 1)))
                        return false;
                return true;
            }
        });
        return tmp;
    }
}