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
    		
    }

    public static String produceAnswer(String input) { 
        
    	String[] splitEq = input.split(" ");
    	
    	String operator = splitEq[1];
    	int[] firstOp = splitFrac(splitEq[0]);
    	int[] secondOp = splitFrac(splitEq[2]);
    	
    	return math(firstOp, operator, secondOp);
    	
    }
    
    public static String math(int[] firstOp, String operator, int[] secondOp) {
    	
    	int num1 = firstOp[0];
    	int den1 = firstOp[1];
    	int num2 = secondOp[0];
    	int den2 = secondOp[1]; 
    	
    	int[] answer = {0, 0};
    	
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
    	
    	int numerator = answer[0];
    	int denominator = answer[1];
    	
    	//return numerator + "/" + denominator;
    	
    	int[] mixedFrac = toMixedNum(numerator, denominator);
    	int wholeMixed = mixedFrac[0];
    	int numeratorMixed = mixedFrac[1];
    	int denominatorMixed = mixedFrac[2];
    	
    	return wholeMixed + "_" + numeratorMixed + "/" + denominatorMixed;
    	
    }
    
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
    	
    }
    
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
    	
	} 
   
    public static int[] toMixedNum(int numerator, int denominator) {
		
    	int[] fraction = {0,0,1};

    	int gcf = gcf(numerator, denominator);

    	int simpNum = numerator / gcf;
    	int simpDen = denominator / gcf;
    	 
    	fraction[0] = simpNum / simpDen;
    	fraction[1] = abs
    	
    	/*if (simpNum < 0) {
    		
    		int[] simpNeg = new int[3];
    		
    		simpNum = simpNum * -1;
    		simpNeg[0] = (simpNum - simpDen);
    		simpNeg[1] = (simpNum % simpDen);
    		simpNeg[2] = (simpDen);
    		
    		fraction[0] = simpNeg[0] * -1;
    		fraction[1] = simpNeg[1];
    		fraction[2] = simpNeg[2];
    		
    	} else if (simpNum > 0) {
    		
    		int[] simpNeg = new int[3];

    		simpNeg[0] = (simpNum - simpDen);
    		simpNeg[1] = (simpNum % simpDen);
    		simpNeg[2] = (simpDen);
    		
    		fraction[0] = simpNeg[0];
    		fraction[1] = simpNeg[1];
    		fraction[2] = simpNeg[2];
    		
    	} else {
    		
    		fraction[0] = numerator;
    		
    	}*/
    	
    	return fraction;
    	
    }
    
    public static int max(int number1, int number2) {
		
    	if (number1 > number2) {
			
    		return (number1);
    		
		} else {
			
			return (number2);
			
		}
		
	} 
    
    public static int min(int number1, int number2) {
		
    	if (number1 > number2) {
    		
			return (number2);
			
		} else if (number1 < number2) {
			
			return (number1);
			
		} else {
			
			return (number1); 
			
		}
    	
	} 

    public static boolean isDivisibleBy(double dividend, double divisor) {
		
    	if (divisor == 0) {
			
    		throw new IllegalArgumentException("Number cannot be divided by zero.");
    		
		} else if (dividend % divisor == 0) { 
			
			return true;
			
		} else {
			
			return false;
			
		}
    	
    }
    
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
	
    }

}