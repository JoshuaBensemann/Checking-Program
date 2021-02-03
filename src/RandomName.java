import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Loads 1000 random names generated from https://www.name-generator.org.uk/quick/
 */

/**
 * @author Joshua Bensemann
 *
 */
public class RandomName {
	String[] names;
	
	public RandomName() {
		String row;
		int idx = 0;
		
		names = new String[1000];

		try {
			BufferedReader csvReader = new BufferedReader(new FileReader("names.csv"));
			while ((row = csvReader.readLine()) != null) {
			    names[idx] = row.split(",")[0];
			    idx++;
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getName() {
		int idx = ThreadLocalRandom.current().nextInt(0, 999 + 1);
		
		return names[idx];
	}

}
