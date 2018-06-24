import acm.program.*;

public class removeOccurrences extends ConsoleProgram {
	
	public void run() {
		String str = "--------0----------";
		char ch = '-';
		println(removeAllOccurrences(str, ch));
	}
	
	private String removeAllOccurrences (String str, char ch) {
		String result = "";
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) != ch) {
				result += str.charAt(i);
			}
		}
		return result;
	}

}
