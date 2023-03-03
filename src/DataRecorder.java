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
		fileWriter = new FileWriter(filePath, true);
		recorder = new PrintWriter(fileWriter);	
		
	}
	
	public void recordEvent(String[] data) {
		recorder.println(String.join(",", data));

	}
	
	public void stopRecording() {
		recorder.close();
		
	}
	
}
