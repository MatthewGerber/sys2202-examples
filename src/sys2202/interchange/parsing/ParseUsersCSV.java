package sys2202.interchange.parsing;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import sys2202.interchange.User;

public class ParseUsersCSV {

	public static void main(String[] args) throws Exception {

		String path = "data/users.csv";
		FileReader file = new FileReader(path);
		CSVReader csv = new CSVReader(file, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, 1);

		ArrayList<User> users = new ArrayList<User>();

		// read first record
		String[] record = csv.readNext();
		
		// while we have a record...
		while (record != null) {

			// get fields
			int id = Integer.parseInt(record[0]);
			String firstName = record[1];
			String lastName = record[2];
			LocalDate dateOfBirth = LocalDate.parse(record[3]);
			String addressString = record[4];

			// break apart addresses and add to list
			ArrayList<String> addresses = new ArrayList<String>();
			for (String address : addressString.split(Pattern.quote("|"))) {
				addresses.add(address);
			}

			// create user and add to list
			User user = new User(id, firstName, lastName, dateOfBirth, addresses);
			users.add(user);
			
			// get next record
			record = csv.readNext();
		}
		
		csv.close();

		// print users
		for (User user : users) {
			System.out.println(user.toString());
		}
	}
}