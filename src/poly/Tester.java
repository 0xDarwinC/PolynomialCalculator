package poly;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Tester {

	static Scanner sc1, sc2;
	
	public static void main(String[] args) throws IOException {
		
		//answers
		double e1 = 5;
		double e2 = 342391.0;
		double e3 = 1.0;
		
		String a1 = "0";
		String a2 = "24.0x^8 + 4.0x^6 + 15.0x^5 + 110.0x^4 + 5.0x^3 + -6.0x + 5.0";
		String a3 = "3.0x^48976 + 2.0x^1090";
		String a4 = "0";
		String a5 = "1.0x^6 + 2.0x^5 + 3.0x^4 + 4.0x^3 + 5.0x^2 + 6.0x + 7.0";
		
		String m1 = "-1.0x^12 + -4.0x^11 + -10.0x^10 + -20.0x^9 + -35.0x^8 + -56.0x^7 + -84.0x^6 + -104.0x^5 + -115.0x^4 + -116.0x^3 + -106.0x^2 + -84.0x + -49.0";
		String m2 = "96.0x^14 + 180.0x^11 + 440.0x^10 + -264.0x^9 + 195.0x^8 + 570.0x^7 + -165.0x^6 + -1135.0x^5 + 575.0x^4 + -55.0x^2 + 25.0x";
		String m3 = "3.0x^48982 + 6.0x^48981 + 9.0x^48980 + 2.0x^1096 + 4.0x^1095 + 6.0x^1094 + -1.0x^12 + -4.0x^11 + -10.0x^10 + -12.0x^9 + -9.0x^8";
		String m4 = "0";
		String m5 = "0";
		
		//evaluate
		System.out.println("Evaluate #1: " + (e1 == evaluate(new File("ptest3.txt"),0)));
		System.out.println("Evaluate #2: " + (e2 == evaluate(new File("ptest4.txt"),8)));
		System.out.println("Evaluate #3: " + (e3 == evaluate(new File("ptest5.txt"),-1)));
		
		//add
		System.out.println("Add #1: " + (a1.equals(add(new File("ptest4.txt"), new File("ptest4opp.txt")))));
		System.out.println("Add #2: " + (a2.equals(add(new File("ptest3.txt"), new File("ptest6.txt")))));
		System.out.println("Add #3: " + (a3.equals(add(new File("ptest7.txt"), new File("ptest8.txt")))));
		System.out.println("Add #4: " + (a4.equals(add(new File("empty.txt"), new File("empty.txt")))));
		System.out.println("Add #5: " + (a5.equals(add(new File("ptest4.txt"), new File("empty.txt")))));
		
		//multiply
		System.out.println("Multiply #1: " + (m1.equals(multiply(new File("ptest4.txt"), new File("ptest4opp.txt")))));
		System.out.println("Multiply #2: " + (m2.equals(multiply(new File("ptest3.txt"), new File("ptest6.txt")))));
		System.out.println("Multiply #3: " + (m3.equals(multiply(new File("ptest7.txt"), new File("ptest8.txt")))));
		System.out.println("Multiply #4: " + (m4.equals(multiply(new File("empty.txt"), new File("empty.txt")))));
		System.out.println("Multiply #5: " + (m5.equals(multiply(new File("ptest4.txt"), new File("empty.txt")))));
		
		
	}
	
	public static double evaluate(File testCase, float x) throws IOException {
		sc1 = new Scanner(testCase);
		Node poly = Polynomial.read(sc1);
		return Polynomial.evaluate(poly, x);
	}
	
	public static String add(File testCase1, File testCase2) throws IOException{
		sc1 = new Scanner(testCase1);
		sc2 = new Scanner(testCase2);
		Node poly1 = Polynomial.read(sc1);
		Node poly2 = Polynomial.read(sc2);
		Node add = Polynomial.add(poly1, poly2);
		return Polynomial.toString(add);
	}
	
	public static String multiply(File testCase1, File testCase2) throws IOException{
		sc1 = new Scanner(testCase1);
		sc2 = new Scanner(testCase2);
		Node poly1 = Polynomial.read(sc1);
		Node poly2 = Polynomial.read(sc2);
		Node multiply = Polynomial.multiply(poly1, poly2);
		return Polynomial.toString(multiply);
	}

}
