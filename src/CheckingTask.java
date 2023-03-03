import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class CheckingTask extends JFrame implements ActionListener  {
	private CheckImage check;
	private TaskPanel entry;
	private HashMap<String,Integer> settings;
	private DataRecorder logger;
	private SimpleDateFormat formatter;
	private Timer sessionTimer;
	private double checkValue;
	private String checkWords, valueString, participantNumber;
	private long sessionStart, trialStart, trialEnd;
	private float secondsElapsed;
	private int SCREEN_WIDTH, SCREEN_HEIGHT, ENTRY_WIDTH, ENTRY_FONT_SIZE,
	IMAGE_HEIGHT, IMAGE_WIDTH, IMAGE_HORIZONTAL_LOCATION, IMAGE_VERTICAL_LOCATION, IMAGE_ENTRY_GAP, TEXT_FONT_SIZE,
	sessionLength, correct;
	private Point IMAGE_LOCATION, ENTRY_LOCATION;


	public CheckingTask() {
		super("Task");
		loadConfigFile();
		openWindow();	
		startSequence(TEXT_FONT_SIZE);
		clickToStart();
		setupRecorder();
		setupGUI();	
		setupTimers();
		sessionStart = System.currentTimeMillis();
		correct = 0;
		sessionTimer.start();
		startTrial();
		
	}
	

	private void loadConfigFile() {
		Config config = new Config("config.csv");
		settings = config.getSettings();

		SCREEN_HEIGHT = settings.get("SCREEN_HEIGHT");
		SCREEN_WIDTH = settings.get("SCREEN_WIDTH");
		IMAGE_HEIGHT = settings.get("IMAGE_HEIGHT");
		IMAGE_WIDTH = settings.get("IMAGE_WIDTH");
		IMAGE_HORIZONTAL_LOCATION = settings.get("IMAGE_HORIZONTAL_LOCATION");
		IMAGE_VERTICAL_LOCATION = settings.get("IMAGE_VERTICAL_LOCATION");
		IMAGE_ENTRY_GAP = settings.get("IMAGE_ENTRY_GAP");
		TEXT_FONT_SIZE = settings.get("TEXT_FONT_SIZE"); 
		ENTRY_FONT_SIZE = settings.get("ENTRY_FONT_SIZE"); 
		
		ENTRY_WIDTH = SCREEN_WIDTH - IMAGE_WIDTH - IMAGE_HORIZONTAL_LOCATION*2 - IMAGE_ENTRY_GAP;
		
		IMAGE_LOCATION = new Point(IMAGE_HORIZONTAL_LOCATION, IMAGE_VERTICAL_LOCATION);
		ENTRY_LOCATION = new Point(IMAGE_HORIZONTAL_LOCATION + IMAGE_WIDTH + IMAGE_ENTRY_GAP, IMAGE_VERTICAL_LOCATION);
		
	}

	
	private void openWindow() {
		setLayout(null);
		setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);	
		
	}
	
	private void startSequence(int fontSize) {
		ExperimentParameters e = new ExperimentParameters(fontSize);
		participantNumber = e.getParticipantNumber();
		sessionLength = e.getSessionLength();	
		

	}
	
	
	private void setupRecorder() {
		formatter = new SimpleDateFormat("HH:mm:ss.SSS");
		
		try {
			logger = new DataRecorder(participantNumber + "RawData.csv");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
		
		String[] data = {"Value", "Entered", "Correct", "Latency_(ms)", "Session_Time", "Time_Stamp"};	
		logger.recordEvent(data);
		
	}
	
	
	private void setupGUI() {
		setupImage();
		setupEntry();
		
	}
	

	private void setupImage() {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		check = new CheckImage(ENTRY_FONT_SIZE);
		check.setBackground(new Color(195, 255, 192));
		check.setBorder(blackline);
		
		add(check);
		check.setBounds((int) IMAGE_LOCATION.getX(), (int) IMAGE_LOCATION.getY(), IMAGE_WIDTH, IMAGE_HEIGHT);
		
	}
	

	private void setupEntry() {
		Border blackline = BorderFactory.createLineBorder(Color.black);
		entry = new TaskPanel(this, ENTRY_FONT_SIZE);
		entry.setBackground(Color.white);
		entry.setBorder(blackline);
		add(entry);
		entry.setBounds((int) ENTRY_LOCATION.getX(), (int) ENTRY_LOCATION.getY(), ENTRY_WIDTH, IMAGE_HEIGHT);
		entry.textEntry.requestFocusInWindow();
	}
	
	
	private void setupTimers() {
        sessionTimer = new Timer(1000, new ActionListener(){      // Timer 1 seconds
            public void actionPerformed(ActionEvent e) {
            	//System.out.println(System.currentTimeMillis());
            	checkForEnd();
            }

        });
	}
	
	private void clickToStart() {
		JOptionPane.showMessageDialog(null, "Click ok when you are ready to begin.");
	}
	
	protected void checkForEnd() {
		
		secondsElapsed = (System.currentTimeMillis() - sessionStart) / 1000F;
		
		if (secondsElapsed > sessionLength) {
			endTask();
		}
			
	}


	private void startTrial() {
		trialStart = System.currentTimeMillis();
		writeCheck();
		
	}
	
	
	private void writeCheck() {
		checkValue = CheckingSetup.getRandomValue();
		checkWords = CheckingSetup.valueToString(checkValue);		
		check.updateCheck(checkValue, checkWords);
		
	}
	
	
	protected void textEntered(String entered) {
		trialEnd = System.currentTimeMillis();
		valueString = String.format("%.2f", checkValue);
		boolean result = entered.equals(valueString);
		if (result) {
			correct++;
		}
		
		secondsElapsed = (System.currentTimeMillis() - sessionStart) / 1000F;
		
		String[] data = {valueString, entered, Boolean.toString(result), Long.toString(trialEnd-trialStart), Float.toString(secondsElapsed), formatter.format(System.currentTimeMillis())};	
		logger.recordEvent(data);
		
		startTrial();
		
	}
	
	
	private void endScreen() {
		
		JPanel panel = new JPanel();
		//panel.setBackground(new Color(195, 255, 192));
		add(panel);
		panel.setBounds((int) IMAGE_LOCATION.getX(), (int) IMAGE_LOCATION.getY(), IMAGE_WIDTH + IMAGE_ENTRY_GAP + ENTRY_WIDTH, IMAGE_HEIGHT);

	      JTextField exitCodeText = new JTextField("Please Wait");
	      int exitCode = 0;

	      JPanel myPanel = new JPanel();
	      myPanel.add(new JLabel("P:"));
	      myPanel.add(exitCodeText);

	      boolean valid = false;
	      
	      while (!valid) {
	         int result = JOptionPane.showConfirmDialog(null, myPanel, 
	                  "Please wait for the experimenter", JOptionPane.OK_CANCEL_OPTION);
	         if (result == JOptionPane.OK_OPTION) {
	        	 try {
	        		 exitCode =  Integer.parseInt(exitCodeText.getText());
	     		} catch (Exception e) {
	     			exitCode = 0;
	     		}
	   
	            if (exitCode == -1) {
	            	valid = true;
	            }

	         }
	    	  
	      }

		}	
	
	
	private void endTask() {
		//logger.stopRecording();
			
		try {
			logger = new DataRecorder(participantNumber + "ProcessedData.csv");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		}	
		
		double correctPerMin = (double) correct / (double) (sessionLength/60);
    	String toPrint = String.format("Participant Number: %s%nSession_Length: %d%nTotal_Correct: %d%nCorrect_Per_Min: %f%n", participantNumber, sessionLength/60, correct, correctPerMin);
        String[] data = new String[] {toPrint};

		logger.recordEvent(data);
		//logger.stopRecording();
		endScreen();
		
		System.exit(0);
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


}
