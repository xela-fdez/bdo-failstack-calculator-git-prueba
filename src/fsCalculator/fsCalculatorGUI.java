package fsCalculator;



import java.awt.*;
import javax.swing.*;
import java.io.*;

import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.border.LineBorder;
import javax.swing.DefaultComboBoxModel;


public class fsCalculatorGUI {

	// Pending things to implement:
	// Visual design improvements on weird borders of ComboBox to choose gear type

	private JFrame frame;

	private static int concArmor = 2500000; // Asign the variables so they have a value even if
	private static int concWeapon = 2860000; // there aren't any asignments in the API/txt config file
	private static int bsArmor = 210000;
	private static int bsWeapon = 300000;
	private static int grunilHelmet = 600000;
	private static int memoryFragment = 2250000;
	private static int fs = 150;
	private static int baseFs = 2;
	private static DecimalFormat chanceFormat;
	private static DecimalFormat bigNumberFormat;
	
	private final String backgroundColor = new String("#2C2F33");
	private final String buttonColor = new String("#99AAB5");

	/**
	 * Launch the application.
	 * 
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public static void main(String[] args) throws NumberFormatException {
		
		chanceFormat = new DecimalFormat("#0.00"); // So numbers look good
		bigNumberFormat = new DecimalFormat("#,###");

		File inputFile = new File("fsconfig.txt"); // File reader and writer
		try {									   // Creates the txt on first execution
			if(inputFile.createNewFile()) {
				FileWriter myWriter = new FileWriter("fsconfig.txt");
				myWriter.write("Concentrated Magical Blackstone Armor:"+concArmor
						+ "\r\nConcentrated Magical Blackstone Weapon:"+concWeapon
						+ "\r\nBlackstone Armor:"+bsArmor
						+ "\r\nBlackstone Weapon:"+bsWeapon
						+ "\r\nGrunil Helmet:"+grunilHelmet
						+ "\r\nMemory Fragment:"+memoryFragment
						+ "\r\nFailstack:"+fs
						+ "\r\nBase failstack chance (0-5):"+baseFs);
				myWriter.close();
			}
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		BufferedReader inputBr = null;
		try {
			inputBr = new BufferedReader(new FileReader(inputFile));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String inputFileLine = null;
		
		//Could add an if to skip reading the config file, but the file just needs to be created the first time,
		//The following times it is opened it will have to do 1 less comparison, so in the long run it looks better
		
		try {
			for(int i = 0; i < 8 && ((inputFileLine = inputBr.readLine()) != null); i++) {
							
				inputFileLine = inputFileLine.substring(inputFileLine.indexOf(":")+1);
				
				if (i == 0)
					concArmor = Integer.parseInt(inputFileLine); // fsconfig.txt file
				if (i == 1)
					concWeapon = Integer.parseInt(inputFileLine);
				if (i == 2)
					bsArmor = Integer.parseInt(inputFileLine);
				if (i == 3)
					bsWeapon = Integer.parseInt(inputFileLine);
				if (i == 4)
					grunilHelmet = Integer.parseInt(inputFileLine);
				if (i == 5)
					memoryFragment = Integer.parseInt(inputFileLine);
				if (i == 6)
					fs = Integer.parseInt(inputFileLine);
				if (i == 7)
					baseFs = Integer.parseInt(inputFileLine);
				
			}
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		try {
			inputBr.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		//Starts the window of JSwing
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fsCalculatorGUI window = new fsCalculatorGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public fsCalculatorGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Failstack calculator"); // Creates the window
		frame.setResizable(false);
		frame.setBounds(200, 0, 600, 670);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.decode(backgroundColor));
		
		//Icon of the app
		ImageIcon img = new ImageIcon("icon.png");
		frame.setIconImage(img.getImage());

		// The text for all the items price and failstack
		JLabelBasicText bsArmorLabel = new JLabelBasicText("Black Stone (Armor) price");	
		bsArmorLabel.setBounds(120, 29, 173, 20);
		frame.getContentPane().add(bsArmorLabel);	

		JLabelBasicText bsWeaponLabel = new JLabelBasicText("Black Stone (Weapon) price");
		bsWeaponLabel.setBounds(120, 59, 188, 20);
		frame.getContentPane().add(bsWeaponLabel);

		JLabelBasicText concArmorLabel = new JLabelBasicText("Concentrated Magical Black Stone (Armor) price");
		concArmorLabel.setBounds(120, 89, 324, 20);
		frame.getContentPane().add(concArmorLabel);

		JLabelBasicText concWeaponLabel = new JLabelBasicText("Concentrated Magical Black Stone (Weapon) price");
		concWeaponLabel.setBounds(120, 120, 339, 20);
		frame.getContentPane().add(concWeaponLabel);

		JLabelBasicText grunilHelmetLabel = new JLabelBasicText("Grunil Helmet price");
		grunilHelmetLabel.setBounds(120, 150, 173, 20);
		frame.getContentPane().add(grunilHelmetLabel);

		JLabelBasicText memLabel = new JLabelBasicText("Memory Fragment price");
		memLabel.setBounds(120, 180, 173, 20);
		frame.getContentPane().add(memLabel);

		JLabelBasicText failstackLabel = new JLabelBasicText("Failstack");
		failstackLabel.setBounds(120, 210, 173, 20);
		frame.getContentPane().add(failstackLabel);
		
		JLabelBasicText baseFsLabel = new JLabelBasicText("Base failstack (0-5)");
		baseFsLabel.setBounds(120, 240, 173, 20);
		frame.getContentPane().add(baseFsLabel);

		// Where the user can write the price of each		
		JTextFieldItemPrice bsArmorField = new JTextFieldItemPrice(bsArmor); 		
		bsArmorField.setBounds(30, 30, 80, 20);
		frame.getContentPane().add(bsArmorField);

		JTextFieldItemPrice bsWeaponField = new JTextFieldItemPrice(bsWeapon);
		bsWeaponField.setBounds(30, 60, 80, 20);
		frame.getContentPane().add(bsWeaponField);

		JTextFieldItemPrice concArmorField = new JTextFieldItemPrice(concArmor);
		concArmorField.setBounds(30, 90, 80, 20);
		frame.getContentPane().add(concArmorField);

		JTextFieldItemPrice concWeaponField = new JTextFieldItemPrice(concWeapon);
		concWeaponField.setBounds(30, 120, 80, 20);
		frame.getContentPane().add(concWeaponField);

		JTextFieldItemPrice grunilHelmetField = new JTextFieldItemPrice(grunilHelmet);
		grunilHelmetField.setBounds(30, 150, 80, 20);
		frame.getContentPane().add(grunilHelmetField);

		JTextFieldItemPrice memoryFragmentField = new JTextFieldItemPrice(memoryFragment);
		memoryFragmentField.setBounds(30, 180, 80, 20);
		frame.getContentPane().add(memoryFragmentField);

		JTextFieldItemPrice failstackField = new JTextFieldItemPrice(fs);
		failstackField.setBounds(30, 210, 80, 20);
		frame.getContentPane().add(failstackField);
		
		JTextFieldItemPrice baseFsField = new JTextFieldItemPrice(baseFs);
		baseFsField.setBounds(30, 240, 80, 20);
		frame.getContentPane().add(baseFsField);
		
		// The text of enhancing Accesories
		JLabelBasicText priAccChanceLabel = new JLabelBasicText("PRI Accesory Chance"); 
		priAccChanceLabel.setBounds(30, 270, 150, 20);
		frame.getContentPane().add(priAccChanceLabel);

		JLabelBasicText duoAccChanceLabel = new JLabelBasicText("DUO Accesory Chance");
		duoAccChanceLabel.setBounds(30, 300, 150, 20);
		frame.getContentPane().add(duoAccChanceLabel);

		JLabelBasicText triAccChanceLabel = new JLabelBasicText("TRI Accesory Chance");
		triAccChanceLabel.setBounds(30, 330, 150, 20);
		frame.getContentPane().add(triAccChanceLabel);

		JLabelBasicText tetAccChanceLabel = new JLabelBasicText("TET Accesory Chance");
		tetAccChanceLabel.setBounds(30, 360, 150, 20);
		frame.getContentPane().add(tetAccChanceLabel);

		JLabelBasicText penAccChanceLabel = new JLabelBasicText("PEN Accesory Chance");
		penAccChanceLabel.setBounds(30, 390, 150, 20);
		frame.getContentPane().add(penAccChanceLabel);

		// Where the actual chance of the accesories is displayed
		JTextAreaChance priAccChanceArea = new JTextAreaChance(190, 270, 59, 22); 
		frame.getContentPane().add(priAccChanceArea);

		JTextAreaChance duoAccChanceArea = new JTextAreaChance(190, 300, 59, 22);
		frame.getContentPane().add(duoAccChanceArea);

		JTextAreaChance triAccChanceArea = new JTextAreaChance(190, 330, 59, 22);
		frame.getContentPane().add(triAccChanceArea);

		JTextAreaChance tetAccChanceArea = new JTextAreaChance(190, 360, 59, 22);
		frame.getContentPane().add(tetAccChanceArea);

		JTextAreaChance penAccChanceArea = new JTextAreaChance(190, 390, 59, 22);
		frame.getContentPane().add(penAccChanceArea);

		//Text for both silver numbers
		JLabelBasicText costStackLabel = new JLabelBasicText("Failstack Cost without counting downgradres"); 
																							
		costStackLabel.setBounds(30, 450, 361, 25);
		frame.getContentPane().add(costStackLabel);

		JLabelBasicText costStackDownLabel = new JLabelBasicText("Failstack Cost counting downgradres");
		costStackDownLabel.setBounds(30, 500, 298, 25);
		frame.getContentPane().add(costStackDownLabel);

		//ComboBox declaration and modifications (to load prices from the config file or from the API)
		JComboBox<String> chooseFileComboBox = new JComboBox<String>();
		chooseFileComboBox.setToolTipText("Choose to take prices from file or from API");
		chooseFileComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "From file", "From API"}));
		((JLabel) chooseFileComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		chooseFileComboBox.setSelectedIndex(0);
		chooseFileComboBox.setForeground(new Color(204, 204, 204));
		chooseFileComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chooseFileComboBox.setFocusable(false);
		chooseFileComboBox.setBorder(new LineBorder(Color.decode("#121212")));
		chooseFileComboBox.setBackground(new Color(18, 18, 18));
		chooseFileComboBox.setBounds(368, 30, 188, 25);
		frame.getContentPane().add(chooseFileComboBox);		
		
		// To choose what the chance is between this gear types
		JComboBox<String> chooseGearComboBox = new JComboBox<String>(); 
		chooseGearComboBox.setFont(new Font("Tahoma", Font.PLAIN, 25));
		chooseGearComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Boss Gear", "Blackstar", "Fallen God" }));
		((JLabel) chooseGearComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		chooseGearComboBox.setSelectedIndex(0);
		chooseGearComboBox.setBackground(Color.decode("#121212"));
		chooseGearComboBox.setForeground(Color.decode("#CCCCCC"));
		chooseGearComboBox.setBorder(new LineBorder(Color.decode("#121212")));
		
		//I have to setup the weird white border not to display, still can't manage to do it
		
		chooseGearComboBox.setFocusable(false);
		chooseGearComboBox.setToolTipText("Choose type of gear");
		chooseGearComboBox.setBounds(368, 146, 188, 54);
		chooseGearComboBox.setFocusable(false);	
		UIManager.put("ComboBox.borderPaintsFocus", Boolean.FALSE);		
		
		frame.getContentPane().add(chooseGearComboBox);

		// Text for gear enhancing chance
		JLabelBasicText priGearChanceLabel = new JLabelBasicText("PRI Gear Chance"); 
		priGearChanceLabel.setBounds(359, 270, 128, 20);
		frame.getContentPane().add(priGearChanceLabel);

		JLabelBasicText duoGearChanceLabel = new JLabelBasicText("DUO Gear Chance");
		duoGearChanceLabel.setBounds(359, 300, 128, 20);
		frame.getContentPane().add(duoGearChanceLabel);

		JLabelBasicText triGearChanceLabel = new JLabelBasicText("TRI Gear Chance");
		triGearChanceLabel.setBounds(359, 330, 128, 20);
		frame.getContentPane().add(triGearChanceLabel);

		JLabelBasicText tetGearChanceLabel = new JLabelBasicText("TET Gear Chance");
		tetGearChanceLabel.setBounds(359, 360, 128, 20);
		frame.getContentPane().add(tetGearChanceLabel);

		JLabelBasicText penGearChanceLabel = new JLabelBasicText("PEN Gear Chance");
		penGearChanceLabel.setBounds(359,390,128,20);
		frame.getContentPane().add(penGearChanceLabel);

		// Where the chance of the gear is displayed
		JTextAreaChance priGearChanceArea = new JTextAreaChance(495, 270, 59, 22); 
		frame.getContentPane().add(priGearChanceArea);

		JTextAreaChance duoGearChanceArea = new JTextAreaChance(495, 300, 59, 22);
		frame.getContentPane().add(duoGearChanceArea);

		JTextAreaChance triGearChanceArea = new JTextAreaChance(495, 330, 59, 22);
		frame.getContentPane().add(triGearChanceArea);

		JTextAreaChance tetGearChanceArea = new JTextAreaChance(495, 360, 59, 22);
		frame.getContentPane().add(tetGearChanceArea);

		JTextAreaChance penGearChanceArea = new JTextAreaChance(495, 390, 59, 22);
		frame.getContentPane().add(penGearChanceArea);

		// Where the actual cost of the enhancement displays
		JTextAreaChance fsCostArea = new JTextAreaChance(401, 450, 155, 25); 
		fsCostArea.setText(null);
		frame.getContentPane().add(fsCostArea);

		JTextAreaChance fsCostDowngradeArea = new JTextAreaChance(338, 501, 218, 25);
		fsCostDowngradeArea.setText(null);
		frame.getContentPane().add(fsCostDowngradeArea);

		//The button that loads the prices from the API or config file
		JButton fileButton = new JButton("Choose");
		fileButton.setBackground(Color.decode(buttonColor));
		fileButton.setOpaque(true);
		fileButton.setFocusPainted(false);
		fileButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		fileButton.setBounds(369, 55, 186, 25);
		frame.getContentPane().add(fileButton);
		fileButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//API calls using the class ApiCall
				if (String.valueOf(chooseFileComboBox.getSelectedItem()).equals("From API")) {
					try {
						concArmor=ApiCall.itemPrice(16005);
						concWeapon=ApiCall.itemPrice(16004);
						bsArmor=ApiCall.itemPrice(16002);
						bsWeapon=ApiCall.itemPrice(16001);
						grunilHelmet=ApiCall.itemPrice(10933);
						memoryFragment=ApiCall.itemPrice(44195);		
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				else {
					//The same thing that opens and reads the config file on the beggining of the program
					File inputFile = new File("fsconfig.txt");
					BufferedReader inputBr = null;
					try {
						inputBr = new BufferedReader(new FileReader(inputFile));
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
					String inputFileLine = null;
					try {
						for(int i = 0; i < 8 && ((inputFileLine = inputBr.readLine()) != null); i++) {
										
							inputFileLine = inputFileLine.substring(inputFileLine.indexOf(":")+1);
							
							// fsconfig.txt file
							if (i == 0)
								concArmor = Integer.parseInt(inputFileLine); 
							if (i == 1)
								concWeapon = Integer.parseInt(inputFileLine);
							if (i == 2)
								bsArmor = Integer.parseInt(inputFileLine);
							if (i == 3)
								bsWeapon = Integer.parseInt(inputFileLine);
							if (i == 4)
								grunilHelmet = Integer.parseInt(inputFileLine);
							if (i == 5)
								memoryFragment = Integer.parseInt(inputFileLine);
							if (i == 6)
								fs = Integer.parseInt(inputFileLine);
							if (i == 7)
								baseFs = Integer.parseInt(inputFileLine);
							
						}
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					try {
						inputBr.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				concArmorField.setText(Integer.toString(concArmor));
				concWeaponField.setText(Integer.toString(concWeapon));
				bsArmorField.setText(Integer.toString(bsArmor));
				bsWeaponField.setText(Integer.toString(bsWeapon));
				grunilHelmetField.setText(Integer.toString(grunilHelmet));
				memoryFragmentField.setText(Integer.toString(memoryFragment));
			}
		});
		
		JButton saveButton = new JButton("Save");
		saveButton.setOpaque(true);
		saveButton.setFocusPainted(false);
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		saveButton.setBackground(new Color(153, 170, 181));
		saveButton.setBounds(464, 87, 91, 53);
		frame.getContentPane().add(saveButton);
		
		//Saves the prices in the config file, they can be introduced by the user, the API or the file
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File inputFile = new File("fsconfig.txt"); // File reader and writer
				
				concArmor = Integer.parseInt(concArmorField.getText()); // Need to overwrite the variables for it to work
				concWeapon = Integer.parseInt(concWeaponField.getText());
				bsArmor = Integer.parseInt(bsArmorField.getText());
				bsWeapon = Integer.parseInt(bsWeaponField.getText());
				grunilHelmet = Integer.parseInt(grunilHelmetField.getText());
				memoryFragment = Integer.parseInt(memoryFragmentField.getText());
				fs = Integer.parseInt(failstackField.getText());
				baseFs = Integer.parseInt(baseFsField.getText());
				
				//Base FS you can get is just from 0-5, so just fixed the value to the closest one
				if(baseFs<0) baseFs=0;
				if(baseFs>5) baseFs=5;
				
				try {
					inputFile.createNewFile();
						FileWriter myWriter = new FileWriter("fsconfig.txt");
						myWriter.write("Concentrated Magical Blackstone Armor:"+concArmor
								+ "\r\nConcentrated Magical Blackstone Weapon:"+concWeapon
								+ "\r\nBlackstone Armor:"+bsArmor
								+ "\r\nBlackstone Weapon:"+bsWeapon
								+ "\r\nGrunil Helmet:"+grunilHelmet
								+ "\r\nMemory Fragment:"+memoryFragment
								+ "\r\nFailstack:"+fs
								+ "\r\nBase failstack chance (0-5):"+baseFs);
						myWriter.close();
				} catch (IOException e3) {
					e3.printStackTrace();
				}
			}
		});
		
		//To tell the user when to switch Reblath, PRI Grunil, DUO grunil, TRI Grunil
		JLabelBasicText switchItemLabel = new JLabelBasicText("");
		switchItemLabel.setVerticalAlignment(SwingConstants.TOP);
		switchItemLabel.setBounds(30, 536, 524, 100);
		frame.getContentPane().add(switchItemLabel);
		
		// Here starts where calculations are made in based on the data that is inputed into the JTextField
		JButton calculateButton = new JButton("Calculate"); 
		calculateButton.setBackground(Color.decode(buttonColor));
		calculateButton.setOpaque(true);
		calculateButton.setFocusPainted(false);
		calculateButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {

				// This reads for each item what price is written
				concArmor = Integer.parseInt(concArmorField.getText()); 
				concWeapon = Integer.parseInt(concWeaponField.getText());
				bsArmor = Integer.parseInt(bsArmorField.getText());
				bsWeapon = Integer.parseInt(bsWeaponField.getText());
				grunilHelmet = Integer.parseInt(grunilHelmetField.getText());
				memoryFragment = Integer.parseInt(memoryFragmentField.getText());
				fs = Integer.parseInt(failstackField.getText());
				baseFs = Integer.parseInt(baseFsField.getText());
				if(baseFs<0) baseFs=0;
				if(baseFs>5) baseFs=5;

				// New FsCost object to calculate cost and percentages
				FsCost failstack1 = new FsCost(fs); 
				failstack1.costFs(bsArmor, concArmor, grunilHelmet, memoryFragment, baseFs);
				failstack1.costFsCostDowngrades(bsArmor, concArmor, grunilHelmet, memoryFragment, baseFs);

				//Displays the chance for the accesories
				priAccChanceArea.setText(chanceFormat.format(failstack1.getAccPerPri() * 100) + "%");
				duoAccChanceArea.setText(chanceFormat.format(failstack1.getAccPerDuo() * 100) + "%"); 
				triAccChanceArea.setText(chanceFormat.format(failstack1.getAccPerTri() * 100) + "%");
				tetAccChanceArea.setText(chanceFormat.format(failstack1.getAccPerTet() * 100) + "%");
				penAccChanceArea.setText(chanceFormat.format(failstack1.getAccPerPen() * 100) + "%");

				//Displays the chance if Blackstar Gear is chosen
				if (String.valueOf(chooseGearComboBox.getSelectedItem()).equals("Blackstar")) {
					priGearChanceArea.setText(chanceFormat.format(failstack1.getBlsPerPri() * 100) + "%");
					duoGearChanceArea.setText(chanceFormat.format(failstack1.getBlsPerDuo() * 100) + "%");
					triGearChanceArea.setText(chanceFormat.format(failstack1.getBlsPerTri() * 100) + "%");
					tetGearChanceArea.setText(chanceFormat.format(failstack1.getBlsPerTet() * 100) + "%");
					penGearChanceArea.setText(chanceFormat.format(failstack1.getBlsPerPen() * 100) + "%");
				}

				//Displays the chance if Fallen God Gear is chosen
				else if (String.valueOf(chooseGearComboBox.getSelectedItem()).equals("Fallen God")) { 
					priGearChanceArea.setText(chanceFormat.format(failstack1.getFalPerPri() * 100) + "%"); 
					duoGearChanceArea.setText(chanceFormat.format(failstack1.getFalPerDuo() * 100) + "%");
					triGearChanceArea.setText(chanceFormat.format(failstack1.getFalPerTri() * 100) + "%");
					tetGearChanceArea.setText(chanceFormat.format(failstack1.getFalPerTet() * 100) + "%");
					penGearChanceArea.setText(chanceFormat.format(failstack1.getFalPerPen() * 100) + "%");
				}

				//Displays the chance if Boss Gear is chosen
				else {
					priGearChanceArea.setText(chanceFormat.format(failstack1.getBosPerPri() * 100) + "%");
					duoGearChanceArea.setText(chanceFormat.format(failstack1.getBosPerDuo() * 100) + "%");
					triGearChanceArea.setText(chanceFormat.format(failstack1.getBosPerTri() * 100) + "%");
					tetGearChanceArea.setText(chanceFormat.format(failstack1.getBosPerTet() * 100) + "%");
					penGearChanceArea.setText(chanceFormat.format(failstack1.getBosPerPen() * 100) + "%");
				}

				//Displays both silver costs
				fsCostArea.setText(bigNumberFormat.format(failstack1.getCost()));
				fsCostDowngradeArea.setText(bigNumberFormat.format(failstack1.getCostDowngrade()));
				
				String strBrackets = new String("<html>");
				
				int brackets[] = failstack1.fsPerStage();
				if(brackets[0]>0) strBrackets+=("Use reblath until "+brackets[0]+" failstacks<br>");
				if(brackets[1]>0) strBrackets+=("Use PRI Grunil until "+brackets[1]+" failstacks<br>");
				if(brackets[2]>0) strBrackets+=("Use DUO Grunil until "+brackets[2]+" failstacks<br>");
				if(brackets[3]>0) strBrackets+=("Use TRI Grunil until "+brackets[3]+" failstacks<br>");
				
				if(brackets[0]==0) strBrackets+=("Use reblath until "+fs+" failstacks<br>");
				else if(brackets[1]==0) strBrackets+=("Use PRI Grunil until "+fs+" failstacks<br>");
				else if(brackets[2]==0) strBrackets+=("Use DUO Grunil until "+fs+" failstacks<br>");
				else if(brackets[3]==0) strBrackets+=("Use TRI Grunil until "+fs+" failstacks<br>");
				strBrackets+=("</html>");
				
				switchItemLabel.setText(strBrackets);

				System.out.println();			
			}
		});
		calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
		calculateButton.setBounds(368, 210, 188, 54);
		frame.getContentPane().add(calculateButton);	
	}
}
