import edu.rit.pj2.Vbl;

/**
 * @file FourNumbers.java
 * 
 *       Class FourNumbers is implements the Vbl class in the Parallel Java 2
 *       library and reduces the set of four numbers that constitutes the lexicographically
 *       largest expression.
 * 
 *       This class uses the Parallel Java 2 library authored by Prof. Alan
 *       Kaminsky.
 * 
 * 
 * @author Sapna Ganesh sg1368
 */
public class FourNumbers implements Vbl {
	// The four numbers that constitute the expression
	int a;
	int b;
	int c;
	int d;

	/**
	 * Constructor to set the numbers to the instance of the class.
	 * 
	 * @param Integers
	 *            a, b, c, d
	 */
	FourNumbers(int a, int b, int c, int d) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.d = d;

	}

	/**
	 * This method reduces the set of four numbers to obtain the
	 * lexicographically largest expression
	 * 
	 * @param Object
	 *            of class Vbl
	 */
	@Override
	public void reduce(Vbl vbl) {
		// The expression with the larger a value is set as the
		// lexicographically largest expression. If both
		// expressions' a values are the same, then b is compared. If both
		// expressions' a and b values are the same, then c is compared. If both
		// expressions' a, b, and c values are the same, then d is compared.

		if (this.a < ((FourNumbers) vbl).a) {
			this.set(vbl);
		} else if ((this.a == ((FourNumbers) vbl).a)
				&& (this.b < ((FourNumbers) vbl).b)) {
			this.set(vbl);
		} else if ((this.a == ((FourNumbers) vbl).a)
				&& (this.b == ((FourNumbers) vbl).b)
				&& (this.c < ((FourNumbers) vbl).c)) {
			this.set(vbl);
			// It is not necessary to compare d because the number is sum of
			// squares and it is not possible for only d to vary
		} else {
			// do nothing
		}

	}

	/**
	 * This method sets the current instance to the parameter that is passed
	 * 
	 * @param Object
	 *            of type Vbl
	 */
	@Override
	public void set(Vbl vbl) {
		this.a = ((FourNumbers) vbl).a;
		this.b = ((FourNumbers) vbl).b;
		this.c = ((FourNumbers) vbl).c;
		this.d = ((FourNumbers) vbl).d;
	}

	/**
	 * This method returns a copy of the current instance
	 * 
	 * @return Object of type FourNumbers
	 */
	public FourNumbers clone() {
		return new FourNumbers(a, b, c, d);
	}

}
