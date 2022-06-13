import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;

public class MyFrameOrder {

	private JFrame frmBurgerShop;
	private JTextField cartField;
	private JTextField costField;
	private JTextArea cartArea;
	private JScrollPane scrollpane;//
	private JPanel panel;//
	private JTextField tipField;
	
	private HashTable orderTable = new HashTable();
	
	private void writeObject(Drink object) {
		//write the object
		File file = new File("C:\\Users\\chenj\\eclipse-workspace\\BurgerShop\\src\\Data");
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(object);
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}catch (IOException e1) {
			e1.printStackTrace();
		}finally {
			try {
				oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private Drink readObject() {
		//read the object
		File file = new File("C:\\Users\\chenj\\eclipse-workspace\\BurgerShop\\src\\Data");
		ObjectInputStream ois=null;
		try {
			ois = new ObjectInputStream(new FileInputStream(file));
			Drink tempOrder = (Drink)ois.readObject();
			return tempOrder;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}finally {
			try {
				ois.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	


	/**
	 * Create the application.
	 */
	public MyFrameOrder(HashTable tempTable) {
		if(tempTable != null) orderTable = tempTable;
		initialize(orderTable);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(HashTable orderTable) {
		
		frmBurgerShop = new JFrame("Burger Shop - Order");
		frmBurgerShop.setBounds(100, 100, 698, 378);
		frmBurgerShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBurgerShop.getContentPane().setLayout(null);
		
		JLabel burgerLabel = new JLabel("Burger:");
		burgerLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		burgerLabel.setBounds(70, 80, 64, 20);
		frmBurgerShop.getContentPane().add(burgerLabel);
		
		JLabel drinkLabel = new JLabel("Drink:");
		drinkLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		drinkLabel.setBounds(70, 127, 64, 15);
		frmBurgerShop.getContentPane().add(drinkLabel);
		
		JLabel DepartmentLabel = new JLabel("Welcome to our Burger Shop!");
		DepartmentLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		DepartmentLabel.setBounds(209, 10, 287, 26);
		frmBurgerShop.getContentPane().add(DepartmentLabel);
		
		JLabel typeLabel = new JLabel("Type");
		typeLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		typeLabel.setBounds(183, 52, 75, 20);
		frmBurgerShop.getContentPane().add(typeLabel);
		
		JLabel numLabel = new JLabel("Num");
		numLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		numLabel.setBounds(303, 55, 64, 15);
		frmBurgerShop.getContentPane().add(numLabel);
		
		JLabel costLabel = new JLabel("Cost:");
		costLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		costLabel.setBounds(449, 175, 54, 15);
		frmBurgerShop.getContentPane().add(costLabel);
		
		JLabel cartLabel = new JLabel("Cart");
		cartLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		cartLabel.setBounds(477, 54, 46, 20);
		frmBurgerShop.getContentPane().add(cartLabel);
		
		/*cartField = new JTextField();
		cartField.setFont(new Font("Dialog", Font.PLAIN, 16));
		cartField.setBounds(388, 80, 232, 80);
		frmBurgerShop.getContentPane().add(cartField);
		cartField.setColumns(10);*/
		scrollpane = new JScrollPane();
		scrollpane.setBounds(388, 80, 236, 80);
		frmBurgerShop.getContentPane().add(scrollpane);  //add scrollpane into panel
		
		cartArea = new JTextArea();
		cartArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel = new JPanel();
		scrollpane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		panel.add(cartArea);   //add textarea into panel
		
		costField = new JTextField();
		costField.setFont(new Font("Dialog", Font.PLAIN, 16));
		costField.setBounds(513, 170, 111, 24);
		frmBurgerShop.getContentPane().add(costField);
		costField.setColumns(10);
		
		JButton orderButton = new JButton("Order");
		orderButton.setFont(new Font("Dialog", Font.BOLD, 14));
		orderButton.setBounds(388, 225, 93, 23);
		frmBurgerShop.getContentPane().add(orderButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Dialog", Font.BOLD, 14));
		clearButton.setBounds(531, 225, 93, 23);
		frmBurgerShop.getContentPane().add(clearButton);
		
		String[] burgerTypeList = new String[]{"(none)", "Fillet Burger 5.5€", "Zinger Burger 5.5€", "Mini Burger 2.5€", "Zinger Stacker 7.1€"};
		JComboBox burgerTypeBox = new JComboBox(burgerTypeList);
		burgerTypeBox.setFont(new Font("Dialog", Font.PLAIN, 14));
		burgerTypeBox.setBounds(152, 81, 127, 29);
		frmBurgerShop.getContentPane().add(burgerTypeBox);
		
		JSpinner burgerNumSpinner = new JSpinner();
		burgerNumSpinner.setFont(new Font("Dialog", Font.PLAIN, 14));
		burgerNumSpinner.setBounds(303, 82, 39, 29);
		frmBurgerShop.getContentPane().add(burgerNumSpinner);
		
		JLabel cokeLabel = new JLabel("Coke");
		cokeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		cokeLabel.setBounds(152, 127, 39, 15);
		frmBurgerShop.getContentPane().add(cokeLabel);
		
		JLabel orangeLabel = new JLabel("Orange");
		orangeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		orangeLabel.setBounds(152, 158, 59, 20);
		frmBurgerShop.getContentPane().add(orangeLabel);
		
		JLabel waterLabel = new JLabel("Water");
		waterLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		waterLabel.setBounds(152, 195, 49, 15);
		frmBurgerShop.getContentPane().add(waterLabel);
		
		JSpinner cokeNumSpinner = new JSpinner();
		cokeNumSpinner.setFont(new Font("Dialog", Font.PLAIN, 14));
		cokeNumSpinner.setBounds(303, 123, 39, 24);
		frmBurgerShop.getContentPane().add(cokeNumSpinner);
		
		JSpinner orangeNumSpinner = new JSpinner();
		orangeNumSpinner.setFont(new Font("Dialog", Font.PLAIN, 14));
		orangeNumSpinner.setBounds(303, 157, 39, 24);
		frmBurgerShop.getContentPane().add(orangeNumSpinner);
		
		JSpinner waterNumSpinner = new JSpinner();
		waterNumSpinner.setFont(new Font("Dialog", Font.PLAIN, 14));
		waterNumSpinner.setBounds(303, 191, 39, 24);
		frmBurgerShop.getContentPane().add(waterNumSpinner);
		
		JButton addButton = new JButton("Add to Cart");
		addButton.setFont(new Font("Dialog", Font.BOLD, 14));
		addButton.setBounds(152, 225, 127, 23);
		frmBurgerShop.getContentPane().add(addButton);
		
		tipField = new JTextField();
		tipField.setText(" Tips: Only one choice of burger is allowed per package! No a la carte drinks are allowed!");
		tipField.setFont(new Font("Dialog", Font.BOLD, 14));
		tipField.setBounds(0, 284, 684, 29);
		frmBurgerShop.getContentPane().add(tipField);
		tipField.setColumns(10);
		
		JLabel cokePriceLabel = new JLabel("2.3€");
		cokePriceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		cokePriceLabel.setBounds(224, 127, 39, 15);
		frmBurgerShop.getContentPane().add(cokePriceLabel);
		
		JLabel orangePriceLabel = new JLabel("2.3€");
		orangePriceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		orangePriceLabel.setBounds(224, 158, 59, 20);
		frmBurgerShop.getContentPane().add(orangePriceLabel);
		
		JLabel waterPriceLabel = new JLabel("1.7€");
		waterPriceLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		waterPriceLabel.setBounds(227, 197, 49, 15);
		frmBurgerShop.getContentPane().add(waterPriceLabel);
		

		JMenuBar menuBar = new JMenuBar();
		frmBurgerShop.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		mnMenu.setFont(new Font("Dialog", Font.BOLD, 15));
		menuBar.add(mnMenu);
		
		JMenuItem orderMenuItem = new JMenuItem("Order");
		orderMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBurgerShop.dispose();
				new MyFrameOrder(orderTable);
			}
		});
		orderMenuItem.setFont(new Font("Dialog", Font.PLAIN, 15));
		mnMenu.add(orderMenuItem);
		
		JMenuItem orderSheetMenuItem = new JMenuItem("Order Sheet");
		orderSheetMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBurgerShop.dispose();
				new MyFrameOrdersheet(orderTable);
			}
		});
		orderSheetMenuItem.setFont(new Font("Dialog", Font.PLAIN, 15));
		mnMenu.add(orderSheetMenuItem);
		
		JMenuItem hashMapMenuItem = new JMenuItem("Hash Map");
		hashMapMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBurgerShop.dispose();
				new MyFrameHashtable(orderTable);
			}
		});
		hashMapMenuItem.setFont(new Font("Dialog", Font.PLAIN, 15));
		mnMenu.add(hashMapMenuItem);
		
		JMenuItem LogoutMenuItem = new JMenuItem("Log out");
		LogoutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmBurgerShop.dispose();
			}
		});
		LogoutMenuItem.setFont(new Font("Dialog", Font.PLAIN, 15));
		mnMenu.add(LogoutMenuItem);
		
		frmBurgerShop.setVisible(true);
		
		
		//Add to Cart
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cartArea.getLineCount() >= 1) cartArea.setText(null);
				
				if((burgerTypeBox.getSelectedIndex() != 0) && (Integer.parseInt(burgerNumSpinner.getValue().toString()) > 0)) {

					int burger = burgerTypeBox.getSelectedIndex();
					int cokeNum = Integer.parseInt(cokeNumSpinner.getValue().toString());
					int orangeNum = Integer.parseInt(orangeNumSpinner.getValue().toString());
					int waterNum = Integer.parseInt(waterNumSpinner.getValue().toString());
					if(burger == 1) {
						Drink tempOrder = new FilletBurger(Integer.parseInt(burgerNumSpinner.getValue().toString()));
						if(cokeNum > 0) tempOrder = new Coke(tempOrder, Integer.parseInt(cokeNumSpinner.getValue().toString()));
						if(orangeNum > 0) tempOrder = new Orange(tempOrder, Integer.parseInt(orangeNumSpinner.getValue().toString()));
						if(waterNum > 0) tempOrder = new Water(tempOrder, Integer.parseInt(waterNumSpinner.getValue().toString()));
						cartArea.append(tempOrder.readDescription());
						costField.setText(Float.toString(tempOrder.cost()));
						//write the object
						writeObject(tempOrder);
		                
					}
					else if(burger == 2) {
						Drink tempOrder = new ZingerBurger(Integer.parseInt(burgerNumSpinner.getValue().toString()));
						if(cokeNum > 0) tempOrder = new Coke(tempOrder, Integer.parseInt(cokeNumSpinner.getValue().toString()));
						if(orangeNum > 0) tempOrder = new Orange(tempOrder, Integer.parseInt(orangeNumSpinner.getValue().toString()));
						if(waterNum > 0) tempOrder = new Water(tempOrder, Integer.parseInt(waterNumSpinner.getValue().toString()));
						cartArea.append(tempOrder.readDescription());
						costField.setText(Float.toString(tempOrder.cost()));
						//write the object
						writeObject(tempOrder);
					}
					else if(burger == 3) {
						Drink tempOrder = new MiniBurger(Integer.parseInt(burgerNumSpinner.getValue().toString()));
						if(cokeNum > 0) tempOrder = new Coke(tempOrder, Integer.parseInt(cokeNumSpinner.getValue().toString()));
						if(orangeNum > 0) tempOrder = new Orange(tempOrder, Integer.parseInt(orangeNumSpinner.getValue().toString()));
						if(waterNum > 0) tempOrder = new Water(tempOrder, Integer.parseInt(waterNumSpinner.getValue().toString()));
						cartArea.append(tempOrder.readDescription());
						costField.setText(Float.toString(tempOrder.cost()));
						//write the object
						writeObject(tempOrder);
					}
					else if(burger == 4) {
						Drink tempOrder = new ZingerStacker(Integer.parseInt(burgerNumSpinner.getValue().toString()));
						if(cokeNum > 0) tempOrder = new Coke(tempOrder, Integer.parseInt(cokeNumSpinner.getValue().toString()));
						if(orangeNum > 0) tempOrder = new Orange(tempOrder, Integer.parseInt(orangeNumSpinner.getValue().toString()));
						if(waterNum > 0) tempOrder = new Water(tempOrder, Integer.parseInt(waterNumSpinner.getValue().toString()));
						cartArea.append(tempOrder.readDescription());
						costField.setText(Float.toString(tempOrder.cost()));
						//write the object
						writeObject(tempOrder);
					}
				}
				
			}
		});
		
		//Order
		orderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random r = new Random(); 
		        int tag[] = {0,0,0,0,0,0,0,0,0,0};
		        String OrderID = "";
		        int temp = 0;
		        while(OrderID.length() != 4){
		        	temp = r.nextInt(10);
		            if(tag[temp] == 0){
		            	OrderID += temp;
		                tag[temp] = 1;
		            }
		        }
		        tipField.setText(" Tips: Order successful! Your Order ID is " + OrderID);
		        // read the object
		        Drink tempOrder = readObject();
		        orderTable.add(OrderID, tempOrder);
			}
		
		});
		
		//Clear
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cartArea.setText(null);
				costField.setText(null);
			}
				
		});
		
		
	}
}
