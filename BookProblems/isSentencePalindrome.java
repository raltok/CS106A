import acm.program.*;
import java.util.*;

public class isSentencePalindrome extends ConsoleProgram {
	
	private static final String DELIMITERS = "!,';.:-&%$£)(=?^<>_|/ ";
	
	public void run() {
		println("This program checks for sentence palindromes.");
		println("Indicate the end of the input with a blank line.");
		String str = readLine("Enter a sentence: ");
		if(isPalindrome(str) == true) {
			println("Palindrome!");
		} else println("Not Palindrome");
	}
	
	private boolean isPalindrome(String str) {
			String cleanSentence = ignoreSpaces(str);
			cleanSentence = cleanSentence.toLowerCase();
			for(int i = 0; i < cleanSentence.length() / 2; i++) {
				if (cleanSentence.charAt(i) != cleanSentence.charAt(cleanSentence.length() - (i + 1))) {
					return false;
				}
			}
		return true;
	}
	
	private String ignoreSpaces(String str) {
		StringTokenizer tokenizer = new StringTokenizer(str, DELIMITERS, false); //we need to use tokens
		String result = "";
		while(tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken();
			if (isWord(token)) {
				result += token;
			}
		}
		return result;
	}
	
	private boolean isWord(String token) {
		for (int i = 0; i < token.length(); i++) {
			char ch = token.charAt(i);
			if(!Character.isLetter(ch)) return false;
		}
		return true;
	}
}