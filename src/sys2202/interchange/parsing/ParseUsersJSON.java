package sys2202.interchange.parsing;

import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import sys2202.interchange.User;

public class ParseUsersJSON {

	public static void main(String[] args) throws Exception {

		// parse JSON file
		FileReader jsonFile = new FileReader("data/users.json");
		JsonParser jsonParser = new JsonParser(); 
		JsonArray usersJsonArray = jsonParser.parse(jsonFile).getAsJsonArray();

		// store all users in a list
		ArrayList<User> users = new ArrayList<User>();

		for (int i = 0; i < usersJsonArray.size(); ++i) {

			JsonObject userJsonObject = usersJsonArray.get(i)
					                                  .getAsJsonObject();

			// get id
			JsonElement idJsonElement = userJsonObject.get("id");
			int id = idJsonElement.getAsInt();

			// get first name
			JsonObject nameJsonObject = userJsonObject.get("name")
					                                  .getAsJsonObject();
			
			JsonElement firstNameJsonElement = nameJsonObject.get("first");
			String firstName = firstNameJsonElement.getAsString();

			// get last name
			JsonElement lastNameJsonElement = nameJsonObject.get("last");
			String lastName = lastNameJsonElement.getAsString();

			// get date of birth
			JsonElement dateOfBirthJsonElement = userJsonObject.get("date_of_birth");
			String dateOfBirthString = dateOfBirthJsonElement.getAsString();
			LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);

			// get addresses
			JsonArray addressesJsonArray = userJsonObject.getAsJsonArray("addresses");
			ArrayList<String> addresses = new ArrayList<String>();
			for (int j = 0; j < addressesJsonArray.size(); ++j) {
				
				JsonElement addressJsonElement = addressesJsonArray.get(j);
				String address = addressJsonElement.getAsString();
				addresses.add(address);
				
			}

			// instantiate user
			User user = new User(id, firstName, lastName, dateOfBirth, addresses);
			users.add(user);

			System.out.println("Read user:  " + user);
		}

		System.out.println("Read " + users.size() + " users.");
	}
}
