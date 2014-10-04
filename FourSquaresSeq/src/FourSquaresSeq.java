import edu.rit.pj2.Task;

/**
 * @file FourSquaresSeq.java
 * 
 *       Class FourSquaresSeq is a sequential program that implements the
 *       theorem by Joseph Louis Lagrange that any natural number is the
 *       sum of four squares. 
 * 
 *       This class displays the lexicographically largest expression and the
 *       count of such combinations of four numbers for a given integer n. This
 *       class uses the Parallel Java 2 library authored by Prof. Alan Kaminsky
 * 
 *       Usage: <TT>java FourSquaresSeq <I>number</I> </TT>
 * 
 * @author Sapna Ganesh sg1368
 */
public class FourSquaresSeq extends Task {

	// the four numbers that constitute the expression, and the count
	int n, a, b, c, d, counter;

	/**
	 * Empty constructor
	 */
	FourSquaresSeq() {

	}

	/**
	 * Constructor to set the given integer value to the instance of the class.
	 * 
	 * @param Integer
	 *            n
	 */
	FourSquaresSeq(int n) {
		this.n = n;
		a = b = c = d = counter = 0;
	}

	/**
	 * Calculates the four integers and the count whose sum of squares equals
	 * the given integer. These four integers constitute the lexicographically
	 * largest expression.
	 * 
	 * @param object
	 *            of FourSquaresSeq class
	 * @return computed object of FourSquaresSeq class
	 */
	public FourSquaresSeq calculate(FourSquaresSeq fs) {
		// The following loops count up to the square root of n because
		// n is a sum of squares of numbers and the square of these individual
		// numbers cannot exceed more than the number itself. This cuts down the
		// loop making the program run faster.
		int m = (int) Math.sqrt(n);

		// the initialization of j, k and l satisfies the condition: a > b > c >
		// d
		for (int i = 0; i <= m; i++) {
			for (int j = i; j <= m; j++) {
				for (int k = j; k <= m; k++) {
					for (int l = k; l <= m; l++) {
						if (i * i + j * j + k * k + l * l == n) {
							fs.counter++;
							fs.a = i;
							fs.b = j;
							fs.c = k;
							fs.d = l;
						}
					}
				}
			}
		}
		return fs;
	}

	/**
	 * overrides the toString function to display the expression and the count.
	 * 
	 * @param void
	 * @return string to display
	 * */
	public String toString() {
		return (this.n + " = " + this.a + "^2 + " + this.b + "^2 + " + this.c
				+ "^2 + " + this.d + "^2\n" + this.counter);
	}

	/**
	 * Throws an exception error if arguments are not sufficient.
	 */
	public void error() {
		System.err.println("Arguments are invalid.");
		throw new IllegalArgumentException();
	}

	/**
	 * main function to input integer n and display the lexicographically
	 * largest expression.
	 * 
	 * @param args
	 */
	public void main(String args[]) {
		if (args.length < 1) {
			error();
		} else {
			int n = Integer.parseInt(args[0]);
			FourSquaresSeq fs = new FourSquaresSeq(n);
			fs = fs.calculate(fs);
			System.out.println(fs);

		}
	}
}
