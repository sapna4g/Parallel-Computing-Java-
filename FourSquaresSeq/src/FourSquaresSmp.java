import edu.rit.pj2.IntVbl;
import edu.rit.pj2.Loop;
import edu.rit.pj2.Task;

/**
 * @file FourSquaresSmp.java
 * 
 *       Class FourSquaresSmp is a parallel program that implements the theorem
 *       by Joseph Louis Lagrange that any natural number is the sum of four
 *       squares.
 *       
 *       Here the calculation of the combination of four such numbers occur in
 *       parallel, and then reduced to the lexicographically largest expression
 *       using the FourNumbers.java class
 * 
 *       This class displays the lexicographically largest expression and the
 *       count of such combinations of four numbers for a given integer n. This
 *       class uses the Parallel Java 2 library authored by Prof. Alan Kaminsky
 * 
 *       Usage:
 *       <TT>java threads=<I>number</I> FourSquaresSmp <I>number</I> </TT>
 * 
 * @author Sapna Ganesh sg1368
 */
public class FourSquaresSmp extends Task {

	IntVbl counter; // count of combinations of the numbers, using the parallel
					// mechanism
	FourNumbers globalHigh = new FourNumbers(0, 0, 0, 0);;// Lexicographically largest expression among all
							// threads.

	/**
	 * Empty constructor
	 */
	FourSquaresSmp() {

	}

	/**
	 * Throws an exception error if arguments are not sufficient.
	 */
	public void error() {
		System.err.println("Arguments are invalid.");
		throw new IllegalArgumentException();
	}

	/**
	 * The main function calculates the four integers and the count whose sum of
	 * squares equals the given integer. These four integers constitute the
	 * lexicographically largest expression. This method displays this
	 * expression with the count of such combinations.
	 * 
	 * @param args
	 */
	public void main(String args[]) {

		if (args.length < 1) {
			error();
		} else {
			final int n = Integer.parseInt(args[0]);
			// Initialize the counter
			counter = new IntVbl.Sum(0);
			// The following loops count up to the square root of n because
			// n is a sum of squares of numbers and the square of these
			// individual numbers cannot exceed more than the number itself.
			// This cuts down the loop making the program run faster.
			final int m = (int) Math.sqrt(n);

			parallelFor(0, m).schedule(dynamic).exec(new Loop() {
				// the local count of the expressions
				IntVbl thrCount;

				public void start() {
					// initialize the largest expression and the count
						thrCount = threadLocal(counter);
				}

				public void run(int i) {
					// the initialization of j, k and l satisfies the condition:
					// a > b > c > d
					for (int j = i; j <= m; j++) {
						for (int k = j; k <= m; k++) {
							for (int l = k; l <= m; l++) {
								if (i * i + j * j + k * k + l * l == n) {
									// set the FourNumbers object to the current
									// numbers
									FourNumbers localHigh = new FourNumbers(i,
											j, k, l);
									// compare this local expression with the
									// largest expression calculated up to now
									// to set or ignore globalHigh to the
									// current largest numbers
									globalHigh.reduce(localHigh);
									// increase the local count
									thrCount.item++;
								}
							}
						}
					}
				}
			});
			System.out.println(n + " = " + globalHigh.a + "^2 + "
					+ globalHigh.b + "^2 + " + globalHigh.c + "^2 + "
					+ globalHigh.d + "^2\n" + counter);

		}
	}
}