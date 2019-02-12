package sys2202.postgresql;

import java.sql.*;
import java.util.Properties;

public class ConnectionExample {

	public static void main(String[] args) throws Exception {
		
		// Load the PostgreSQL driver into your Java program.
		Class.forName("org.postgresql.Driver");
		
		// Host:  The host is "localhost" if you installed PostgreSQL on your machine.
		String host = "localhost";
		
		// Port:  Obtain the port from within PgAdmin (right-click the server and select the Connection tab). Yours may differ from what is used below.
		int port = 5434; 
		
		// Database:  Enter the database you wish to work with (also shown within PgAdmin).
		String database = "postgres";
		
		// Form the connection URL.
		String url = "jdbc:postgresql://" + host + ":" + port  + "/" + database;
		
		// Open the connection to the PostgreSQL server. Be sure to replace "test" with your password below.
		Properties connectionProperties = new Properties();
		connectionProperties.setProperty("user","postgres");
		connectionProperties.setProperty("password","test");
		connectionProperties.setProperty("ssl","false");
		Connection connection = DriverManager.getConnection(url, connectionProperties);
		
		// Create a schema and table
		Statement createSchemaAndTable = connection.createStatement();
		createSchemaAndTable.execute(
                                     "CREATE SCHEMA my_test_schema;" + 
                                     "CREATE TABLE my_test_schema.fruit" + 
                                     "(" + 
                                     "  id SERIAL PRIMARY KEY," + 
                                     "  name CHARACTER VARYING NOT NULL," + 
                                     "  color CHARACTER VARYING CHECK (color = 'Red' OR color = 'Yellow')" + 
                                     ");"
                                     );
		createSchemaAndTable.close();
		
		// Execute an SQL INSERT statement on the PostgreSQL server. This statement inserts two rows into the fruit table.
		Statement insert = connection.createStatement();
		insert.execute(
				       "INSERT INTO my_test_schema.fruit" + 
		               "(name, color)" + 
				       "VALUES" + 
		               "('Apple','Red')," + 
				       "('Banana','Yellow');"
		               );
		insert.close();
		
		// Execute an SQL SELECT statement on the PostgreSQL server. This statement selects all yellow fruit.
		Statement select = connection.createStatement();
		ResultSet results = select.executeQuery(
				                                "SELECT id,name,color " + 
		                                        "FROM my_test_schema.fruit " + 
				                                "WHERE color = 'Yellow';"
		                                       );
		
		// Iterate over the results.
		while(results.next()) {
			
			System.out.println("Fruit name:  " + results.getString(2));  // The number of the first column is 1, not 0.
			
		}	
		
		select.close();
		
		// Execute an invalid SQL INSERT statement on the PostgreSQL server. Why does the following code throw an exception?
		try
		{
			Statement badInsert = connection.createStatement();
			badInsert.execute(
					          "INSERT INTO my_test_schema.fruit" + 
			                  "(name, color)" + 
					          "VALUES" + 
			                  "('Apple','Green');"
					         );
			badInsert.close();
		}
		catch(Exception ex)
		{
			System.err.println("Something bad happened:  " + ex);
		}
	}
}