import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author Joshua Bensemann
 *
 */
public class DataRecorder {
	private String filePath;
	private FileWriter fileWriter;
	private PrintWriter recorder;
	
	public DataRecorder(String filepath) throws IOException {
		this.filePath = filepath;
	}
	
	public void recordEvent(String[] data) {
		try {
			fileWriter = new FileWriter(filePath, true);
			recorder = new PrintWriter(fileWriter);	
			recorder.println(String.join(",", data));
			recorder.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}