import java.awt.BorderLayout;
import java.awt.Font;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

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
	private String participantNumber; //age, disability, ethnicity, gender, 
	private int sessionLength;	
	private javax.swing.plaf.FontUIResource font;
	
	public ExperimentParameters(int fontSize) {
		frame = new JFrame();
		font = new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, fontSize);

		setUIFont(font);
		setParticipantNumber();
		setLength();
		//getParticipantDetails();
		//displayBriefing();
		recordParameters();

	}
	
	private static void setUIFont (javax.swing.plaf.FontUIResource f){
	    Enumeration<Object> keys = UIManager.getDefaults().keys();
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
		String length = JOptionPane.showInputDialog(null, "Enter Session Length (mins).", "40");
		sessionLength = Integer. parseInt(length);
		sessionLength = sessionLength * 60;
		
	}

/*
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
		JComboBox<?> jc = new JComboBox<Object>(yesNo);
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
        
    	//String toPrint = String.format("Participant Number: %s%nSession_Length: %d%nDate %s%nAge: %s%nGender: %s%nEthnicity: %s%nDisability: %s%n", participantNumber, sessionLength/60, recorded_date, age, gender, ethnicity, disability);
    	String toPrint = String.format("Participant Number: %s%nSession_Length: %d%nDate %s%n", participantNumber, sessionLength/60, recorded_date);
        String[] data = new String[] {toPrint};
        
        parameterFile.recordEvent(data);
        //parameterFile.stopRecording();
		
	}
	
	public String getParticipantNumber() {
		return participantNumber;
		
	}
	
	public int getSessionLength() {
		return sessionLength;
		
	}

}