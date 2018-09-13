import java.io.*;
import java.util.*;
import acm.program.*;
import acm.util.*;


public class FlightPlanner extends ConsoleProgram {
	
	public void run() {
		println("Welcome to the Flight Planner!");
		setArray();
		openFileReader();
		playProgram();
	}
	
	private void playProgram() {
		println("Here's a list of all the cities in our database");
		displayDestinations();
		println("Let's plan a round-trip route!");
		String startingCity = readLine("Enter the starting city: ");
		println("From " + startingCity + " you can fly directly to:");
		destinations.add(startingCity);
		displayArrayComp(map.get(startingCity));
		String City1 = readLine("Where do you want to go from " +  startingCity + "? " );
		checkDuplicate(startingCity, City1);
		while(true) {
			String City2 = City1;
			City1 = readLine("Where do you want to go from " + City2 + "? " );
			if(City1.equals(startingCity) == true) {
				println("The route you've chosen is:");
				printDestinations();
				break;
			} else if(checkDuplicate(City2, City1) == true) {
				City1 = City2;
			}
		}
	}
	
	private boolean checkDuplicate(String City2, String City1) {
		if(map.get(City2).contains(City1) == false) {
			println("You can't get to that city by a diret flight.");
			println("From " + City2 + " you can fly directly to:");
			displayArrayComp(map.get(City2));
			return true;
		} else {
			destinations.add(City1);
			println("From " + City1 + " you can fly directly to:");
			displayArrayComp(map.get(City1));
			return false;
		}
	}
	
	private void printDestinations() {
		print(destinations.get(0));
		for(int i = 1; i < destinations.size(); i++) {
			print(" -> " + destinations.get(i));
		}
		print(" -> " + destinations.get(0));
	}
	
	private void displayArrayComp(ArrayList array) {
		for(int i = 0; i < array.size(); i++) {
			println(array.get(i));
		}
	}
	
	private void displayDestinations() {
		for(String name : map.keySet()) {
			println(name);
		}
	}
	
	private void setArray() {
		NY = new ArrayList<String>();
		SanJose = new ArrayList<String>();
		HonLu = new ArrayList<String>();
		Anch = new ArrayList<String>();
		SanFran = new ArrayList<String>();	
		destinations = new ArrayList<String>();
		Denver = new ArrayList<String>();
	}

	private void openFileReader() {
		try {
			BufferedReader rd = new BufferedReader(
					new FileReader(FILENAME));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				if(line.equals("") == false) {
					extractCity(line);	
				}
			}
			rd.close();
		} catch(IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private void extractCity(String line) {
		int CityIndex1 = line.indexOf("-") - 1;
		int CityIndex2 = line.indexOf(">", CityIndex1) + 2;
		String City1 = line.substring(0, CityIndex1);
		String City2 = line.substring(CityIndex2);
		checkCity(City1, City2);
	}
	
	private void checkCity(String City1, String City2) {
		if(City1.equals("New York")) {
			NY.add(City2);
			map.put(City1, NY);
		} else if(City1.equals("San Jose")) {
			SanJose.add(City2);
			map.put(City1, SanJose);
		} else if(City1.equals("Anchorage")) {
			Anch.add(City2);
			map.put(City1, Anch);
		} else if(City1.equals("Honolulu")) {
			HonLu.add(City2);
			map.put(City1, HonLu);
		} else if(City1.equals("San Francisco")) {
			SanFran.add(City2);
			map.put(City1, SanFran);
		} else if(City1.equals("Denver")) {
			Denver.add(City2);
			map.put(City1, Denver);
		}
	}
	
	private static final String FILENAME = "flights.txt";
	
	private ArrayList<String> NY;
	private ArrayList<String> SanJose;
	private ArrayList<String> Anch;
	private ArrayList<String> HonLu;
	private ArrayList<String> SanFran;
	private ArrayList<String> Denver;
	private ArrayList<String> destinations;
	private HashMap<String, ArrayList> map = 
			new HashMap<String, ArrayList>();
}