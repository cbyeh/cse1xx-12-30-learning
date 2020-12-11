public class Improve {

	int counter;

	public Improve() {
		counter = 0;
	}
    /**
     * NOTE: THIS FUNCTION IS DEFINED ONLY FOR POSITIVE INTEGERS
     */
    public int not_really_a_mystery(int n) {
		counter++;
        if (n < 2) return n;
        else return not_really_a_mystery(n-1) + not_really_a_mystery(n-2);
    }
	
	public static void main(String[] args) {
		Improve imp = new Improve();
		System.out.println(imp.not_really_a_mystery(8));
		System.out.println(imp.counter);
	}

}
