import acm.program.*;
import java.util.*;


public class countNames extends ConsoleProgram {
	
	public void run() {
		enterNames();
		printNames();
	}
	
	private void enterNames() {
		String name = "";
		int times = 0;
		while(true) {
			name = readLine("Enter name: ");
			if(name.equals("")) break;
			if(map.get(name) != null) {
				times = map.get(name) + 1;
				map.put(name, times);
			} else {
				map.put(name, 1);
			}
		}
	}
	
	private void printNames() {
		for(String name : map.keySet()) {
			println("Entry ["+ name +"] has count " + map.get(name));
		}
	}
	
	
	private Map<String, Integer> map = new HashMap<String, Integer>();
}