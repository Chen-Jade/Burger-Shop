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
import javax.swing.JTextArea;
import javax.swing.JTextField;


import javax.swing.JLabel;

public class MyFrameHashtable {

	private JFrame frmBurgerShop;
	private JTextArea hashArea;
	private JScrollPane scrollpane;//
	private JPanel panel;//
	
	private HashTable orderTable = new HashTable();
	
	public void printNode(HashTable.HashNode<String, Drink> tempNode){
		 hashArea.append("["+tempNode.key+":"+tempNode.order.cost()+"€] -->  ");
	}
	public void printHashMap(HashTable orderMap) {
		if(orderMap.isEmpty()) {
			hashArea.append("Hash Map is empty!");
		}else {
			for(int i=0;i<orderMap.numBuckets;i++) {
				hashArea.append("\n");
				hashArea.append("[ "+ i +"] -> ");
				HashTable.HashNode<String, Drink> tempNode = orderMap.bucketArray.get(i);
				while(tempNode != null) {
					printNode(tempNode);
					tempNode = tempNode.next; 
				}
				hashArea.append("NULL");
			}
		}
	}


	/**
	 * Create the application.
	 */
	public MyFrameHashtable(HashTable orderTable) {
		initialize(orderTable);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(HashTable orderTable) {
		
		frmBurgerShop = new JFrame("Burger Shop - Order");
		frmBurgerShop.setTitle("Burger Shop - Hash Map");
		frmBurgerShop.setBounds(100, 100, 698, 378);
		frmBurgerShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		scrollpane = new JScrollPane();
		scrollpane.setBounds(43, 46, 589, 266);
		frmBurgerShop.getContentPane().add(scrollpane);  //add scrollpane into panel
		
		hashArea = new JTextArea();
		hashArea.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel = new JPanel();
		scrollpane.setViewportView(panel);
		panel.setBackground(Color.WHITE);
		panel.add(hashArea);   //add textarea into panel
		
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
				//new MyFrameSearch().main(null);
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
		frmBurgerShop.getContentPane().setLayout(null);
		
		JLabel nameLabel = new JLabel("Hash Map");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		nameLabel.setBounds(286, 10, 92, 26);
		frmBurgerShop.getContentPane().add(nameLabel);
		
		this.printHashMap(orderTable);
		
		//frmBurgerShop.setSize(650,400);    
		frmBurgerShop.setVisible(true);
	}
	
}

