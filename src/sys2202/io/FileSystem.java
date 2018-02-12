package sys2202.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileSystem {

	public static void main(String[] args) throws IOException {

		// get all file paths at or below a particular directory that have a .json file extension
		ArrayList<String> paths = new ArrayList<String>();
		Files.walk(Paths.get("/Users/matthewgerber/Desktop/sys2202_mobile_data")).filter(Files::isRegularFile)
		                                                                         .map(path -> path.toString())
		                                                                         .filter(path -> path.endsWith(".json"))
				                                                                 .forEach(paths::add);
		
		// do something with the paths. the example below simply prints each path to the console.
		for(String path : paths) {
			
			System.out.println("\t" + path);
			
		}
		
		System.out.println("There were " + paths.size() + " path(s).");
	}
}