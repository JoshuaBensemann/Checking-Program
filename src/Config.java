/**
 * 
 */

/**
 * @author Joshua Bensemann
 *
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Config {
	private HashMap<String,Integer> settings;
	
	public Config(String filepath) {
		settings = new HashMap<String,Integer>();
		
		readFile(filepath);
	}

	private void readFile(String filepath) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(
					filepath));
			String line = reader.readLine();
			while (line != null) {
				String[] stringParts = line.split(",");
				String key = stringParts[0];
				int value = Integer.parseInt(stringParts[1]);
				settings.put(key, value);
				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public HashMap<String,Integer> getSettings(){
		return settings;
		
	}

}
