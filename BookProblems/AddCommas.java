import acm.program.*;

public class AddCommas extends ConsoleProgram {
	
	public void run() {
		while(true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) break;
			println(addCommasToNumericString(digits));
		}
	}
	
	private String addCommasToNumericString(String digits) {
		String result = "";
		int i = 0;
		if(digits.length() > 3)  {
			for(i = digits.length(); i > 3; i -=3) {
					String rightDigits = "," + digits.substring(i - 3, i);
					result += rightDigits;
				}
			} else return digits;
		return digits.substring(0, i) + result;
	}
}