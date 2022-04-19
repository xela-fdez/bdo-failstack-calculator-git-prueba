package fsCalculator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class JTextAreaChance extends JTextArea {
	private Color fontColor = Color.decode("#CCCCCC");
	private Color bgColor = Color.decode("#121212");
	private LineBorder edgeColor = new LineBorder(bgColor);
	public JTextAreaChance(int x, int y, int length, int height){
		super();
		setEditable(false);
		setFont(new Font("Monospaced", Font.PLAIN, 15));
		setText("00,00%");
		setBounds(x,y,length,height);
		setForeground(fontColor);
		setBackground(bgColor);
		setBorder(edgeColor);
	}
}
