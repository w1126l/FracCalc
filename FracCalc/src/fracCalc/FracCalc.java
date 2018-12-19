/* This class contains methods that make up a fractional calculator. The calculator can accept fractional inputs and will return the answer as a mixed number.
 * @author Winnie Liang
 * @version 12/18/2018
 */

package fracCalc;

import java.util.*;

public class FracCalc {

    public static void main(String[] args) {
    	
    	Scanner userInput = new Scanner(System.in);
    	
    	String quit = "quit";
    	String problem = null;
    	
    	while (problem != quit) {
    		problem = userInput.nextLine();
    		System.out.println(produceAnswer(problem));
    	}		
    	userInput.close();
    		
    } // This method accepts the question and quits the program once the user says so.

    public static String produceAnswer(String input) { 
        
    	String[] splitEq = input.split(" ");
    	
    	String operator = splitEq[1];
    	int[] firstOp = splitFrac(splitEq[0]);
    	int[] secondOp = splitFrac(splitEq[2]);
    	
    	return math(firstOp, operator, secondOp);
    	
    } // This method produces the answer to the problem given from the main method.
    
    public static int[] splitFrac(String input) {
    	
    	int[] all = {0, 0, 1};
    	
    	if (input.contains("_")) {
    		String[] sepWhole = input.split("_");
    		all[0] = Integer.parseInt(sepWhole[0]);
    		input = sepWhole[1];
    	}
    	
    	if (input.contains("/")) {
    		String[] sepFrac = input.split("/");
    		all[1] = Integer.parseInt(sepFrac[0]);
    		all[2] = Integer.parseInt(sepFrac[1]);
    	} else {
    		all[0] = Integer.parseInt(input);
    	}
    	
    	return toImproperFrac(all);
    	
    } // This method splits the fraction given from produceAnswer into an array.
    
    public static int[] toImproperFrac(int[] operand) {

    	int whole = operand[0];
    	int num = operand[1];
    	int den = operand[2];
    	
    	int[] frac = {0, 0};
    	
    	if (whole < 0) {
    		whole = whole * -1;
    		frac[0] = -1 * ((whole * den) + num);
    	} else {
    		frac[0] = ((whole * den) + num);
    	}
    	
    	frac[1] = den;
    	
    	return frac;
    	
	} // This method returns the mixed number given in splitFrac into an improper fraction.
    
    public static String math(int[] firstOp, String operator, int[] secondOp) {
    	
    	// Separates the array inputs into clearer names.
    	int num1 = firstOp[0];
    	int den1 = firstOp[1];
    	int num2 = secondOp[0];
    	int den2 = secondOp[1]; 
    	
    	// Initializes an array to be used later to hold the numerator and denominator after it goes through their given math functions.
    	int[] answer = {0, 0};
    	
    	// Determines what operation user wants and does it.
    	if (operator.equals("+")) {
       		
    		answer[0] = (num1 * den2) + (num2 * den1);
        	answer[1] = (den1 * den2);
        	
    	} else if (operator.equals("-")) {
    	
    		answer[0] = ((num1 * den2) - (num2 * den1));
        	answer[1] = ((den1 * den2));
        	
    	} else if (operator.equals("*")) {
    		
    		answer[0] = (num1 * num2);
        	answer[1] = (den1 * den2);    		
        	
    	} else {
    		
    		answer[0] = (num1 * den2);
        	answer[1] = (num2 * den1);   	
        	
    	}
    	
    	// Declares the numerator and denominator in to the array above.
    	int numerator = answer[0];
    	int denominator = answer[1];
    	
    	// Makes the answer given into a mixed number.
    	int[] mixedFrac = toMixedNum(numerator, denominator);
    	int wholeMixed = mixedFrac[0];
    	int numeratorMixed = mixedFrac[1];
    	int denominatorMixed = mixedFrac[2];
    	
    	// Initializes a variable to return as the answer.
    	String finalAnswer = null;
    	
    	// Determines how to release the answer if there is no whole number or just a fraction.
    	if (numeratorMixed != 0) {
    		
    		if (wholeMixed == 0) {
    			finalAnswer = numeratorMixed + "/" + denominatorMixed;
    		} else {
    			finalAnswer = wholeMixed + "_" + numeratorMixed + "/" + denominatorMixed;
    		}
    		
    	} else {
    		finalAnswer = wholeMixed + "";
    	}
    	
    	return finalAnswer;
    	
    } // This method returns the answer to the problem given from produceAnswer.
   
    public static int[] toMixedNum(int numerator, int denominator) {
		
    	int[] fraction = {0,0,1};

    	int gcf = gcf(numerator, denominator);

    	int simpNum = numerator / gcf;
    	int simpDen = denominator / gcf;
    	
    	fraction[0] = simpNum / simpDen;
    	fraction[1] = absValue(simpNum) % simpDen;
    	fraction[2] = absValue(simpDen);
    	
    	return fraction;
    	
    } // This method takes an improper fraction from math and makes it into a mixed number.
    
    public static int max(int number1, int number2) {
		
    	if (number1 > number2) {
    		return (number1);
		} else {
			return (number2);
		}
		
	} // Returns maximum of two values.
    
    public static int min(int number1, int number2) {
		
    	if (number1 > number2) {
			return (number2);
		} else if (number1 < number2) {
			return (number1);
		} else {
			return (number1); 
		}
    	
	} // Returns minimum of two values

    public static boolean isDivisibleBy(double dividend, double divisor) {
		
    	if (divisor == 0) {	
    		throw new IllegalArgumentException("Number cannot be divided by zero.");
		} else if (dividend % divisor == 0) { 
			return true;
		} else {
			return false;
		}
    	
    } // Tests to see if one number is divisible by another
    
    public static int gcf(int number1, int number2) {
		
    	int sm = min(number1, number2);
		int lg = max(number1, number2);
		int i = lg;
		
		while (i > 1) {
			if (isDivisibleBy(sm, i) == true && isDivisibleBy(lg, i) == true) {
				return i;
			}
			i--;
		}
		return 1;
	
    } // Finds the greatest common factor.
    
    public static int absValue(int number) {
		
    	if (number >= 0) {
			return (number);
		} else {
			return (number * -1);
		}
    	
    } // Finds the absolute value.
    
}