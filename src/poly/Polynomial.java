package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @Name Darwin Christopher
 * @Date 1/19/2023
 * @Period 3
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextDouble(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}

	/** METHOD 1
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static double evaluate(Node poly, double x) {
		/** Your code goes here **/
		if(poly == null) return 0.0; //trivial case
		
		Node pointer = poly;
		double answer = 0.0;
		
		while(pointer != null) {
			answer += pointer.term.coeff*(Math.pow(x, pointer.term.degree));
			pointer = pointer.next;
		}
		
		return answer; 
	}
	
	/** METHOD 2
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** Your code goes here **/
		Node pointer1 = poly1;
		Node pointer2 = poly2;
		Node answer = new Node(0.0, 0, null);
		Node pointer3 = answer;
		
		while(pointer1!=null) { //add all poly1 terms
			pointer3.next = new Node(pointer1.term.coeff, pointer1.term.degree, null);
			pointer3 = pointer3.next;
			pointer1 = pointer1.next;
		}
		while(pointer2!=null) { //add all poly2 terms
			pointer3.next = new Node(pointer2.term.coeff, pointer2.term.degree, null);
			pointer3 = pointer3.next;
			pointer2 = pointer2.next;
		}
		
		answer = answer.next; //remove trivial node
		return simplify(answer); //sort and return
	}
	
	/** METHOD 3
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** Your code goes here **/
		return simplify(multi(poly1, poly2)); //uses private methods 
	}
		
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
	/**
	 * Multiplies polynomials without simplification.
	 */
	private static Node multi(Node one, Node two) {
		if(one == null || two == null)return null; //trivial case
		
		Node pointer1 = one;
		Node pointer2 = two;
		Node answer = new Node(0.0, 0, null);
		Node pointer3 = answer;
		
		while(pointer1 != null) { //multiply each node together
			while(pointer2 != null) {
				pointer3.next = new Node(pointer1.term.coeff*pointer2.term.coeff, pointer1.term.degree+pointer2.term.degree, null);
				pointer3 = pointer3.next;
				pointer2 = pointer2.next;	
			}
			pointer1 = pointer1.next;
			pointer2 = two;
		}
		answer = answer.next; //remove trivial node
		return answer;
	}
	/**
	 * Simplifies polynomial. Doubles as a sorting algorithm for unsorted polynomials.
	 */
	private static Node simplify(Node one) {
		if(one == null)return null;	//trivial case
		
		Node pointer1 = one;
		int min = pointer1.term.degree;
		int max = highestDegree(one);
		double holder = 0.0;
		Node answer = new Node(0.0, 0, null);
		Node pointer2 = answer;
		
		while (min <= max) { //increments through every degree until it reaches max
			while(pointer1 != null) {
				if(pointer1.term.degree == min) {
					holder += pointer1.term.coeff;
				}
				pointer1 = pointer1.next;
			}
			if(holder!=0.0) { //does not add 0 nodes
				pointer2.next = new Node(holder, min, null);
				pointer2 = pointer2.next;
			}
			pointer1 = one;
			holder = 0;
			min++;
		}
		
		answer = answer.next; //removes trivial node
		return answer;
	}
	/**
	 * Finds the highest degree of the polynomial.
	 */
	private static int highestDegree(Node one) {
		if(one == null) return 0; //trivial case
		
		Node answer = one;
		int holder = 0;
		while(answer != null) {
			holder = answer.term.degree;
			answer = answer.next;
		}
		return holder;
	}
} //end of class
