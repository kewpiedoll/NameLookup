import java.util.*; // for Scanner
import java.io.*; 	// for File

public class NameLookup {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner console = new Scanner(System.in);
		String prompt = "Please enter a name: ";
		String name = getName(console, prompt);

		// format name into first letter capital, remaning letters lowercase
		prompt = "Please enter a gender (M/F or m/f): ";
		String gender = getName(console, prompt).toUpperCase();
		while (!(gender.equals("M") || gender.equals("F"))) {
			gender = getName(console, prompt).toUpperCase();
		}



		System.out.print("Enter the starting year (1880-2018): ");
		int startYear = getInt(console);
		System.out.print("Enter the ending year (1880-2018): ");
		int endYear = getInt(console);

		for (int i = startYear; i <= endYear; i++) {
			String startYearFileName = "names/yob" + i + ".txt";
			Scanner file = new Scanner(new File(startYearFileName));
			String nameLine[] = getNameLine(file, name, gender);
			System.out.println(i + ": " + nameLine[0] + " = " + nameLine[2]);
		}
		
	}

	// gets name from user and reject blank entry
	public static String getName(Scanner console, String prompt) {
		System.out.print(prompt);
		String name = console.nextLine();
		if (name.equals("")) {
			getName(console, prompt);
		}
		// enfore the proper case before returning the name
		return enforceNameCase(name);
	}

	// method to enfore the proper case, for example: Christina and not christina or cHRIstINa
	public static String enforceNameCase(String name) {
		String returnName = "" + Character.toUpperCase(name.charAt(0));
		if (name.length() > 1) {
			for (int i = 1; i < name.length(); i++) {
				returnName += Character.toLowerCase(name.charAt(i));
			}
		}

		return returnName;
	}


	// search for name in file and return line as a string
	public static String[] getNameLine(Scanner file, String name, String gender) {
		while (file.hasNextLine()) {
			String[] nextLine = new String[3]; 
			nextLine = file.nextLine().split(",");
			if (nextLine[0].equals(name) && nextLine[1].equals(gender)) {
				return nextLine;
			}
		}
		String[] nullArray = new String[] {"0","0","0"};
		return nullArray;
	}

	public static int getInt(Scanner console) {
		while(!console.hasNextInt()) {
			console.next(); // clear the inuput
			System.out.print("Please enter a year (for example, 2014): ");
		}
		return console.nextInt();
	}

	


}