package sys2202.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileSystem {

	public static void main(String[] args) throws IOException {

		// get all file paths under a particular directory
		ArrayList<String> paths = new ArrayList<String>();
		Files.walk(Paths.get("/Users/matthewgerber/Desktop/sp2017sys2202_mobile_data")).filter(Files::isRegularFile)
		                                                                               .map(path -> path.toString())
		                                                                               .filter(path -> path.endsWith(".json"))
				                                                                       .forEach(paths::add);
		
		// print the paths
		for(String path : paths) {
			System.out.println("\t" + path);
		}
		
		System.out.println("There were " + paths.size() + " path(s):");
	}
}