package ParenthesesChecker;

import java.util.ArrayDeque;
import java.util.Scanner;

public class ParenthesesChecker {
	public static void main(String[] args) {
		
		String code;
		
		if (args.length > 0) {
			// User input from command line
			var sb = new StringBuilder();
			
			for (String str: args) {
				sb.append(str).append(" ");
			}
			
			code = sb.toString();
		} else {
			// Getting user input
			var scan = new Scanner(System.in);
			
			System.out.print("Enter LISP code to have parentheses checked: ");
			code = scan.nextLine();
			scan.close();
		}
		
		System.out.println("You entered: " + code);

		System.out.println(validateParentheses(code) ? "The parentheses are balanced!" : "The parentheses are not balanced.");
	}
	
	private static boolean validateParentheses(String code) {
		// Checking to see if parentheses are valid
		var stack = new ArrayDeque<Character>();
		
		for (char ch: code.toCharArray()) {
			
			if (ch == '(') {
				stack.push(ch);
			} else if (ch == ')') {		
				if (stack.isEmpty())
					return false;
			
				stack.pop();
			} else {
				continue;
			}
		}
		
		return stack.isEmpty();
	}
}
