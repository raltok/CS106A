import acm.program.*;
import java.util.*;

public class UniqueNames extends ConsoleProgram {
	
	public void run() {
		enterNames();
	}
	
	private void enterNames() {
		while(true) {
			String name = readLine("Enter name: ");
			if(name.equals("")) break;
			if(nameList.contains(name) == false) {
				nameList.add(name);
			}
		}
		listSingleNames();
	}
	
	private void listSingleNames() {
		println("Unique name list contains: ");
		for(int i = 0; i < nameList.size(); i++) {
			println(nameList.get(i));
		}
	}
	ArrayList<String> nameList = new ArrayList<String>();
}