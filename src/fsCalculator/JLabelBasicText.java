package fsCalculator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class JLabelBasicText extends JLabel {
	private Color fontColor = Color.decode("#FFFFFF");
	
	public JLabelBasicText(String label) {
		super(label);
		setHorizontalAlignment(SwingConstants.LEFT);
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setForeground(fontColor);
	}
}