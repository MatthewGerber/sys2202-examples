package sys2202.interchange.parsing;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import sys2202.interchange.User;

public class ParseUsersXML {

	public static void main(String[] args) throws Exception {

		// parse XML document
		File xmlFile = new File("data/users.xml");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		documentBuilderFactory.setValidating(true);
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();	
		Document parsedXml = documentBuilder.parse(xmlFile);
		
		// store all users in a list
		ArrayList<User> users = new ArrayList<User>();
		
		// read each user element into a User object and store in our list
		NodeList userNodes = parsedXml.getElementsByTagName("user");
		for(int i = 0; i < userNodes.getLength(); ++i) {

			Element userNode = (Element) userNodes.item(i);
			
			// get id
			String idString = userNode.getAttribute("id");
			int id = Integer.parseInt(idString);
			
			// get first name
			Element nameNode = (Element) userNode.getElementsByTagName("name").item(0);
			String firstName = nameNode.getElementsByTagName("first")
					                   .item(0)
					                   .getTextContent();			
			
			// get last name
			String lastName = nameNode.getElementsByTagName("last")
					                  .item(0)
					                  .getTextContent();
			
			// get date of birth
			String dateOfBirthString = userNode.getElementsByTagName("date_of_birth")
					                           .item(0)
					                           .getTextContent();
			
			LocalDate dateOfBirth = LocalDate.parse(dateOfBirthString);
			
			// get address nodes			
			Element addressesNode = (Element) userNode.getElementsByTagName("addresses")
					                                  .item(0);		
			
			NodeList addressNodes = addressesNode.getElementsByTagName("address");
			
			// loop over address nodes and gather address strings into a list
			ArrayList<String> addresses = new ArrayList<String>();
			for(int j = 0; j < addressNodes.getLength(); ++j) {
				
				Element addressNode = (Element) addressNodes.item(j);
				String addressString = addressNode.getTextContent();
				addresses.add(addressString);
				
			}
			
			// instantiate user and add to list
			User user = new User(id, firstName, lastName, dateOfBirth, addresses);
			users.add(user);
			
			System.out.println("Read user:  " + user);
		}
		
		System.out.println("Read " + users.size() + " users.");
	}
}