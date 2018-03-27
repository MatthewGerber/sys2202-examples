package sys2202.interchange.parsing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Homework4Template {

	public static void main(String[] args) throws FileNotFoundException {
		
		ArrayList<String> paths = new ArrayList<String>();

		// get JSON file paths as in Homework 3
		// ... 
		// ...
		// ...
		
		// open a connection to the PostgreSQL server
		// ...
		// ...
		// ...

		// iterate over paths
		for (String path : paths) {

			// build a string containing the SQL insert values (i.e., everything following the VALUES keyword of the INSERT
			// statement) for readings in the current file.
			StringBuilder sqlValues = new StringBuilder();
			
			// extract readings from the JSON file and append them to the SQL values string
			FileReader jsonFile = new FileReader(path);
			JsonParser jsonParser = new JsonParser(); 
			JsonArray readingArray = jsonParser.parse(jsonFile).getAsJsonArray();
			for (int i = 0; i < readingArray.size(); ++i) {
				
				// extract the reading and append it to the sqlValues variable. you will need to account for the fact that
				// readings have different JSON elements depending on their types (e.g., location, battery, etc.).
				// ...
				// ...
				// ...

			}
			
			// execute the SQL statement that inserts the readings from the current file into the database. be sure
			// to close the statement after executing it, as shown in the example code in ConnectionExample.java. this
			// code will build an INSERT statement for the appropriate table and will use sqlValues, which was built above.
			// ...
			// ...
			// ...
		}
		
		// close the connection to the PostgreSQL server
		// ...
		// ...
		// ...
	}
}
