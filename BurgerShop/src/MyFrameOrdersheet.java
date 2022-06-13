/*import java.awt.EventQueue;

import javax.swing.JFrame;

public class MyFrameOrdersheet {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyFrameOrdersheet window = new MyFrameOrdersheet();
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
	/*public MyFrameOrdersheet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	/*private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}*/
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

public class MyFrameOrdersheet {

	private JFrame frmBurgerShop;
	private JTextField cartField;
	private JTextField orderIDField;
	private JTextArea resultArea;
	private JScrollPane scrollpane;//
	private JPanel panel;//
	
	//private HashMap orderMap = new HashMap();
	

	/**
	 * Create the application.
	 */
	public MyFrameOrdersheet(HashTable orderTable) {
		//if(tempMap != null) orderMap = tempMap;
		initialize(orderTable);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(HashTable orderTable) {
		
		frmBurgerShop = new JFrame("Burger Shop - Order");
		frmBurgerShop.setTitle("Burger Shop - Order Sheet");
		frmBurgerShop.setBounds(100, 100, 698, 378);
		frmBurgerShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBurgerShop.getContentPane().setLayout(null);
		
		JLabel orderIDLabel = new JLabel("Order ID:");
		orderIDLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		orderIDLabel.setBounds(97, 80, 75, 20);
		frmBurgerShop.getContentPane().add(orderIDLabel);
		
		JLabel DepartmentLabel = new JLabel("Welcome to our Burger Shop!");
		DepartmentLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		DepartmentLabel.setBounds(209, 10, 287, 26);
		frmBurgerShop.getContentPane().add(DepartmentLabel);
		
		JLabel resultLabel = new JLabel("Search Result");
		resultLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 16));
		resultLabel.setBounds(448, 52, 111, 20);
		frmBurgerShop.getContentPane().add(resultLabel);
		
		/*cartField = new JTextField();
		cartField.setFont(new Font("Dialog", Font.PLAIN, 16));
		cartField.setBounds(388, 80, 232, 80);
		frmBurgerShop.getContentPane().add(cartField);
		cartField.setColumns(10);*/
		scrollpane = new JScrollPane();
		scrollpane.setBounds(388, 80, 236, 80);
		frmBurgerShop.getContentPane().add(scrollpane);  //add scrollpane into panel
		
		resultArea = new JTextArea();
		resultArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel = new JPanel();
		scrollpane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		panel.add(resultArea);   //add textarea into panel
		
		orderIDField = new JTextField();
		orderIDField.setFont(new Font("Dialog", Font.PLAIN, 16));
		orderIDField.setBounds(182, 78, 111, 24);
		frmBurgerShop.getContentPane().add(orderIDField);
		orderIDField.setColumns(10);
		
		JButton deleteButton = new JButton("delete");
		deleteButton.setFont(new Font("Dialog", Font.BOLD, 14));
		deleteButton.setBounds(182, 145, 84, 23);
		frmBurgerShop.getContentPane().add(deleteButton);
		
		JButton clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Dialog", Font.BOLD, 14));
		clearButton.setBounds(277, 145, 84, 23);
		frmBurgerShop.getContentPane().add(clearButton);
		
		String[] burgerTypeList = new String[]{"(none)", "Fillet Burger 5.5€", "Zinger Burger 5.5€", "Mini Burger 2.5€", "Zinger Stacker 7.1€"};
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Dialog", Font.BOLD, 14));
		searchButton.setBounds(85, 145, 84, 23);
		frmBurgerShop.getContentPane().add(searchButton);
		
		JLabel TotalOrdersLabel = new JLabel("Total Orders:");
		TotalOrdersLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		TotalOrdersLabel.setBounds(97, 215, 111, 20);
		frmBurgerShop.getContentPane().add(TotalOrdersLabel);
		
		JLabel TotalNumLabel = new JLabel("");
		TotalNumLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		TotalNumLabel.setBounds(218, 215, 111, 20);
		frmBurgerShop.getContentPane().add(TotalNumLabel);
		

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
		
		TotalNumLabel.setText(Integer.toString(orderTable.getsize()));
		
		frmBurgerShop.setVisible(true);
		
		
		//search
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = orderIDField.getText();
				Drink resultOrder = orderTable.search(key);
				resultArea.setText(null);
				if(resultOrder == null) {
					resultArea.append("This order does not exist!");
				}else {
					resultArea.append(resultOrder.readDescription());
				}
				
			}
		});
		
		//delete
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key = orderIDField.getText();
				Drink resultOrder = orderTable.remove(key);
				resultArea.setText(null);
				if(resultOrder == null) {
					resultArea.append("This order does not exist!");
				}else {
					resultArea.append("Delete sucessful!");
					TotalNumLabel.setText(Integer.toString(orderTable.getsize()));
				}
			
			}
		});
		
		//Clear
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resultArea.setText(null);
				orderIDField.setText(null);
			}
				
		});
		
		
	}
}
