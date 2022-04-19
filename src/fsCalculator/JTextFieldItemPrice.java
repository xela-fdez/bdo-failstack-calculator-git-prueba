package fsCalculator;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class JTextFieldItemPrice extends JTextField {
	private Color fontColor = Color.decode("#CCCCCC");
	private Color bgColor = Color.decode("#121212");
	private LineBorder edgeColor = new LineBorder(bgColor);
	
	public JTextFieldItemPrice(int number) {
		super();
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setText(String.valueOf(number));
		setColumns(10);
		setForeground(fontColor);
		setBackground(bgColor);
		setBorder(edgeColor);
		
	}
	public JTextFieldItemPrice(String number) {
		super();
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setText(String.valueOf(number));
		setColumns(10);
		setForeground(fontColor);
		setBackground(bgColor);
		setBorder(edgeColor);
	}
	public JTextFieldItemPrice(double number) {
		super();
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setText(String.valueOf(number));
		setColumns(10);
		setForeground(fontColor);
		setBackground(bgColor);
		setBorder(edgeColor);
	}
	public JTextFieldItemPrice() {
		super();
		setHorizontalAlignment(SwingConstants.CENTER);
		setFont(new Font("Tahoma", Font.PLAIN, 15));
		setColumns(10);
		setForeground(fontColor);
		setBackground(bgColor);
		setBorder(edgeColor);
	}
	
}
