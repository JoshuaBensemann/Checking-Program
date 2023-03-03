import java.awt.BasicStroke;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class CheckImage extends JPanel {
	private String value, words, name;
	private Font basicFont, fieldFont;
	private JTextField checkValue;
	private RandomName names;
	
	private final int LINES_START_X = 200;
	private final int LINE_ONE_END = 800;
	private final int LINE_TWO_END = 1000;
	private final int LINE_ONE_Y = 200;
	private final int LINE_TWO_Y = 300;
	private final int LINE_WORD_GAP = 5;
	
	private final int INDENT = 20;
	private final int LINE_INDENT = 10;
	
	private final int TEXTFIELD_GAP = 50;
	private final int TEXTFIELD_WIDTH = 150;
	private final int TEXTFIELD_HEIGHT = 30;
	
	
	public CheckImage(int font_size) {
		setLayout(null);
		fieldFont = new Font("Arial", Font.PLAIN, font_size);
		basicFont = new Font("Arial", Font.PLAIN, (int) (font_size/1.5));
		names = new RandomName();
		
		checkValue = new JTextField(6);
		checkValue.setHorizontalAlignment(JTextField.CENTER);
		checkValue.setFont(fieldFont);
		add(checkValue);
		checkValue.setBounds(LINE_ONE_END+TEXTFIELD_GAP, LINE_ONE_Y-TEXTFIELD_HEIGHT, TEXTFIELD_WIDTH, TEXTFIELD_HEIGHT);
	}
	
	
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        checkValue.setText(value);
        name = names.getName();
    
        Graphics2D g2 = (Graphics2D) g;        
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(LINES_START_X, LINE_ONE_Y, LINE_ONE_END, LINE_ONE_Y);
        g2.drawLine(LINES_START_X, LINE_TWO_Y, LINE_TWO_END, LINE_TWO_Y);
        
        g2.setFont(basicFont);
        g2.drawString("Pay to the Order of", INDENT, LINE_ONE_Y);
        g2.drawString("In the Amount of", INDENT, LINE_TWO_Y);
        g2.drawString("Dollars", LINE_TWO_END + INDENT, LINE_TWO_Y);

        g2.setFont(fieldFont);
        g2.drawString("$", LINE_ONE_END + TEXTFIELD_GAP - 20, LINE_ONE_Y - LINE_WORD_GAP);
        g2.drawString(name, LINES_START_X + LINE_INDENT, LINE_ONE_Y - LINE_WORD_GAP);
        g2.drawString(words, LINES_START_X + LINE_INDENT, LINE_TWO_Y - LINE_WORD_GAP);
          
    }
	
	
    public void updateCheck(double value, String words) {
    	this.value = String.format("%.2f", value);
    	this.words = words;
    	repaint();
    }

}