import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class TaskPanel extends JPanel implements ActionListener {
	private JLabel instructions;
	protected JTextField textEntry;
	private JButton submitButton;
	private Font font;
	private CheckingTask owner;
	private Point instructionXY, textXY, buttonXY;
	
	private final int INSTRUCTION_WIDTH = 680;
	private final int INSTRUCTION_HEIGHT = 150;
	private final int INSTRUCTION_INDENT = 40;
	private final int INSTRUCTION_Y = 20;
	
	private final int TEXTFIELD_X = 280;
	private final int TEXTFIELD_WIDTH = 160;
	private final int TEXTFIELD_HEIGHT = 40;
	
	private final int VERTICAL_GAP = 50;
		
	public TaskPanel(CheckingTask owner, int font_size) {
		setLayout(null);
		this.owner = owner;
		instructionXY = new Point(INSTRUCTION_INDENT, INSTRUCTION_Y);
		textXY = new Point(TEXTFIELD_X, INSTRUCTION_Y + INSTRUCTION_HEIGHT + VERTICAL_GAP);
		buttonXY = new Point(TEXTFIELD_X, INSTRUCTION_Y + INSTRUCTION_HEIGHT + VERTICAL_GAP*2);
		font = new Font("Arial", Font.PLAIN, font_size);	
		createPanel();
		
	}

	private void createPanel() {
		String instructionText = "Enter the amount displayed on the check in the "
	              + "box below. Press ENTER or click the Submit "
	              + "button to submit the amount and move "
	              + "to the next check.";
		
		instructions = new JLabel("<html><div style='text-align: center;'>" + instructionText + "</div></html>");
		instructions.setFont(font);
		
		textEntry = new JTextField(16);
		textEntry.setFont(font);
		textEntry.addActionListener(this);
		
		submitButton = new JButton("Submit");
		submitButton.setFont(font);
		submitButton.addActionListener(this);
		
		add(instructions);
		add(textEntry);
		add(submitButton);
		
		instructions.setBounds((int) instructionXY.getX(), (int) instructionXY.getY(), INSTRUCTION_WIDTH-INSTRUCTION_INDENT*2, INSTRUCTION_HEIGHT);
		textEntry.setBounds((int) textXY.getX(), (int) textXY.getY(), TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
		submitButton.setBounds((int) buttonXY.getX(), (int) buttonXY.getY(), TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String entrytext = textEntry.getText();
		
		if (!entrytext.equals("")) {
			owner.textEntered(entrytext);
			textEntry.setText("");
		}
		
	}

}
