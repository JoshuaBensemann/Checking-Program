import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * 
 */

/**
 * @author Joshua Bensemann
 *
 */
public class ExperimentParameters {
	JFrame frame;
	private String age, disability, ethnicity, gender, language, participantNumber, selected;
	private int sessionLength;	
	private javax.swing.plaf.FontUIResource font;
	
	public ExperimentParameters(int fontSize) {
		frame = new JFrame();
		font = new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, fontSize);

		setUIFont(font);
		setParticipantNumber();
		setLength();
		getParticipantDetails();
		//displayBriefing();
		recordParameters();

	}
	
	private static void setUIFont (javax.swing.plaf.FontUIResource f){
	    java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	      }
	    } 	

	/**
	 * 
	 */
	private void setParticipantNumber() {
		participantNumber = JOptionPane.showInputDialog("Enter Participant Number.");

	}
	
	/**
	 * 
	 */
	private void setLength() {
		String length = JOptionPane.showInputDialog(null, "Enter Session Length (mins).", "45");
		sessionLength = Integer. parseInt(length);
		sessionLength = sessionLength * 60;
		
	}
	
	private void getParticipantDetails() {
    	JOptionPane.showMessageDialog(null, "Click ok to enter participant information.");
    	
    	age = null;
    	
    	while (age == null || age == "") {
    		age = JOptionPane.showInputDialog("Please enter your age.");
    		
    	}
    	
    	gender = null;
    	
    	while (gender == null || gender == "") {
    		gender = JOptionPane.showInputDialog("Please enter your gender.");
    		
    	}
    	
    	ethnicity = null;
    	
    	while (ethnicity == null || ethnicity == "") {
    		ethnicity = JOptionPane.showInputDialog("Please enter your ethnicity.");
    		
    	}
    	
		Object[] yesNo = {"Please select", "Yes", "No"};
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JLabel jl = new JLabel(
		        "<html>Do you have a documented disability?:"
		        + "</b><br><br></html>");  

		jl.setFont(font);
		jp.add(jl, BorderLayout.NORTH);
		JComboBox jc = new JComboBox(yesNo);
		jc.setSelectedIndex(0);

		jc.setFont(font);
		jp.add(jc, BorderLayout.SOUTH);
    	
		disability = null;
		
		while (disability == null || disability == "Please select") {
			if (JOptionPane.showConfirmDialog(frame, jp, "Disability", 
			        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)  == 0) {
				disability = jc.getSelectedItem().toString();
			}
			else {
				disability = null;
			}
		}
		/*
		jp = new JPanel();
		jp.setLayout(new BorderLayout());
		jl = new JLabel(
		        "<html>Do you have prior exposure to character-based languages?:"
		        + "</b><br><br></html>");  

		jl.setFont(font);
		jp.add(jl, BorderLayout.NORTH);
		jc = new JComboBox(yesNo);
		jc.setSelectedIndex(0);

		jc.setFont(font);
		jp.add(jc, BorderLayout.SOUTH);		
		
		
		language = null;
		
		while (language == null || language == "Please select") {
			if (JOptionPane.showConfirmDialog(frame, jp, "Language", 
			        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)  == 0) {
				language = jc.getSelectedItem().toString();
			}
			else {
				language = null;
			}
			
		}
		*/
		
	}
	
	/*
	private void displayBriefing() {
		
		boolean correct = false;
		
		String briefingText = "<html>Thank you for participating in this study. This study involves learning the names of shapes.<br>"
				    + "Once the study begins, a shape will appear on screen and next to it will be five names. Choose <br>"
				    + "the name that you think belongs to the shape. To select the name of the shape, use the computer <br>"
				    + "mouse and click once on the name you think belongs to the shape; you can only make one selection <br>"
				    + "per trial. Use only the computer mouse.  Do not use the touch screen option. Following every <br>"
				    + "trial, you will receive onscreen feedback for a short period of time whether you selected the <br>"
				    + "correct name for the shape. The feedback will appear directly beneath the shape. Following the <br>"
				    + "feedback, there will be a short pause before the start of the next trial. Once you have completed<br>"
				    + "all of the trials, further instructions will be provided onscreen. The study should take <br>"
				    + "approximately 60 minutes to complete. If you have any questions, raise your hand and wait in your <br>"
				    + "seat until the researcher comes over and answers your question. Once you have finished the experiment,<br>"
				    + "raise your hand and the researcher will provide you with further instructions.<br><br>"
				    + "You may begin when you are ready.<br><br>"
				    + "How often will you receive feedback?</html>";
		
		Object[] quiz = {"Please select", "Following every 3rd trial", "Following every trial", "Following every 10th trial", "Never"};
		
		JPanel jp = new JPanel();
		jp.setLayout(new BorderLayout());
		JLabel jl = new JLabel(briefingText);  

		jl.setFont(font);
		jp.add(jl, BorderLayout.NORTH);
		JComboBox jc = new JComboBox(quiz);
		jc.setSelectedIndex(0);

		jc.setFont(font);
		jp.add(jc, BorderLayout.SOUTH);
		
		while (!correct) {
			String answer = null;
			if (JOptionPane.showConfirmDialog(frame, jp, "Briefing", 
			        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE)  == 0) {
				answer = jc.getSelectedItem().toString();
			}
			
			correct = answer == "Following every trial";	
			
		}
		
		JOptionPane.showMessageDialog(null, "Click ok when you are ready to begin.");
	
	}
	*/
	
	private void recordParameters() {	
		DataRecorder parameterFile = null;
		
        try {
        	parameterFile = new DataRecorder(participantNumber+"Info.txt");       	
        } catch (IOException e) {
        	System.err.println("File Error");
        }
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	String recorded_date = dateFormat.format(date);
        
    	String toPrint = String.format("Participant Number: %s%nSession_Length: %d%nDate %s%nAge: %s%nGender: %s%nEthnicity: %s%nDisability: %s%n", participantNumber, sessionLength/60, recorded_date, age, gender, ethnicity, disability);
        String[] data = new String[] {toPrint};
        
        parameterFile.recordEvent(data);
        parameterFile.stopRecording();
		
	}
	
	public String getParticipantNumber() {
		return participantNumber;
		
	}
	
	public int getSessionLength() {
		return sessionLength;
		
	}

}