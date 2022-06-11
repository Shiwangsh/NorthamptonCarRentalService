package Admin;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.io.*;
import java.util.ArrayList;
import Customer.*;

public class AddCustomer {

	private JFrame frame;
	private JTextField customerID_tf;
	private JTextField phoneNummber_tf;
	private JTextField address_tf;
	private JTextField userName_tf;
	private JTextField password_tf;
	private JTextField email_tf;
	private JTextField corporation_tf;
	private JTable table_1;

	DefaultTableModel customerTableModel;
	//Array list to store and retrieve data of customer type
	ArrayList<Customer> customerArrayList = new ArrayList();



	public AddCustomer() {
		initialize();
		 loadCustomers();
	}

/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Add Customers");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);

		JPanel vehicePanel = new JPanel();
		vehicePanel.setBackground(Color.LIGHT_GRAY);
		vehicePanel.setLayout(null);
		vehicePanel.setBounds(127, 10, 659, 358);
		frame.getContentPane().add(vehicePanel);

		JLabel customerID = new JLabel("Customer ID");
		customerID.setFont(new Font("Tahoma", Font.PLAIN, 16));
		customerID.setBounds(10, 63, 128, 22);
		vehicePanel.add(customerID);

		customerID_tf = new JTextField();
		customerID_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		customerID_tf.setColumns(10);
		customerID_tf.setBounds(10, 95, 135, 31);
		vehicePanel.add(customerID_tf);

		JLabel phoneNumber_lbl = new JLabel("Phone Number");
		phoneNumber_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneNumber_lbl.setBounds(473, 63, 108, 22);
		vehicePanel.add(phoneNumber_lbl);

		JLabel email_lbl = new JLabel("Email");
		email_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		email_lbl.setBounds(224, 136, 85, 22);
		vehicePanel.add(email_lbl);

		JLabel corporation_lbl = new JLabel("Name of Corporation");
		corporation_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		corporation_lbl.setBounds(448, 136, 178, 22);
		vehicePanel.add(corporation_lbl);

		JButton submitBtn = new JButton("Submit");
		submitBtn.setBackground(Color.GRAY);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enterCustomer();
			}
		});
		submitBtn.setBounds(257, 248, 100, 42);
		vehicePanel.add(submitBtn);

		JLabel username_lbl = new JLabel("User Name");
		username_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		username_lbl.setBounds(152, 63, 128, 22);
		vehicePanel.add(username_lbl);

		userName_tf = new JTextField();
		userName_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		userName_tf.setColumns(10);
		userName_tf.setBounds(152, 95, 157, 31);
		vehicePanel.add(userName_tf);

		JLabel password_lbl = new JLabel("Password");
		password_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		password_lbl.setBounds(335, 63, 128, 22);
		vehicePanel.add(password_lbl);

		password_tf = new JTextField();
		password_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		password_tf.setColumns(10);
		password_tf.setBounds(320, 95, 141, 31);
		vehicePanel.add(password_tf);


		phoneNummber_tf = new JTextField();
		phoneNummber_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		phoneNummber_tf.setColumns(10);
		phoneNummber_tf.setBounds(473, 95, 153, 31);
		vehicePanel.add(phoneNummber_tf);

		JLabel address_lbl = new JLabel("Address");
		address_lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		address_lbl.setBounds(10, 136, 128, 22);
		vehicePanel.add(address_lbl);

		address_tf = new JTextField();
		address_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		address_tf.setColumns(10);
		address_tf.setBounds(10, 168, 204, 31);
		vehicePanel.add(address_tf);

		JButton resetBtn = new JButton("Reset");
		resetBtn.setBackground(Color.GRAY);
		resetBtn.setBounds(147, 248, 100, 42);
		vehicePanel.add(resetBtn);
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				customerID_tf.setText(null);
						phoneNummber_tf.setText(null);
						address_tf.setText(null);
						userName_tf.setText(null);
						password_tf.setText(null);
						email_tf.setText(null);
						corporation_tf.setText(null);
			}
		});

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(Color.GRAY);
		deleteBtn.setBounds(380, 248, 100, 42);
		vehicePanel.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rowIndex = table_1.getSelectedRow();
					String selectedID = table_1.getModel().getValueAt(rowIndex, 0).toString();
					customerArrayList = readFromFile(customerArrayList, "files/customer.dat");
					for (int i = 0; i < customerArrayList.size(); i++) {
						Customer customer = new Customer();
						customer = customerArrayList.get(i);
						if (Integer.parseInt(selectedID) == customer.getId()) {
							customerArrayList.remove(customer);
							customerTableModel.removeRow(rowIndex);
						}
						writeToFile(customerArrayList, "files/customer.dat");
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(frame, "Please select a record from the table below to delete", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		email_tf = new JTextField();
		email_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		email_tf.setColumns(10);
		email_tf.setBounds(224, 168, 204, 31);
		vehicePanel.add(email_tf);

		corporation_tf = new JTextField();
		corporation_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		corporation_tf.setColumns(10);
		corporation_tf.setBounds(438, 168, 188, 31);
		vehicePanel.add(corporation_tf);

		JLabel addCustomer_lbl = new JLabel("Add customer");
		addCustomer_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
		addCustomer_lbl.setBounds(243, 10, 153, 31);
		vehicePanel.add(addCustomer_lbl);

		JPanel tabelPanel = new JPanel();
		tabelPanel.setBackground(Color.GRAY);
		tabelPanel.setBounds(10, 378, 776, 184);
		frame.getContentPane().add(tabelPanel);
		tabelPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		tabelPanel.add(scrollPane);


		customerTableModel = new DefaultTableModel();
		customerTableModel.setDataVector(
					new Object[][] {},
					new String[] {
						"ID", "Username", "Password", "Address", "Number", "Email", "Name of Corporation"
					}
				);

		table_1 = new JTable(customerTableModel);
		table_1.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table_1);

		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(Color.LIGHT_GRAY);
		sidebarPanel.setBounds(10, 10, 109, 358);
		frame.getContentPane().add(sidebarPanel);
		sidebarPanel.setLayout(null);

		JButton btnNewButton = new JButton("Add Vehicle");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton.setBounds(10, 43, 89, 40);
		sidebarPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminDashboard adminDashboard = new AdminDashboard();
				frame.dispose();
			}
		});

		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.setBackground(Color.GRAY);
		btnAddCustomer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		btnAddCustomer.setBounds(10, 120, 89, 40);
		sidebarPanel.add(btnAddCustomer);

		JButton btnRent = new JButton("Rent");
		btnRent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HireMenu hMenu = new HireMenu();
				frame.dispose();
			}
		});
		btnRent.setBackground(Color.GRAY);
		btnRent.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnRent.setBounds(10, 188, 89, 40);
		sidebarPanel.add(btnRent);

		JButton btnQuery = new JButton("Query");
		btnQuery.setBackground(Color.GRAY);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryMenu qMenu = new QueryMenu();
				frame.dispose();
			}
		});
		btnQuery.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnQuery.setBounds(10, 256, 89, 40);
		sidebarPanel.add(btnQuery);

		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//Function adds customer into the binary file
		private void enterCustomer() {
		try{
			Customer customer = new Customer();
			customer.setId(Integer.parseInt(customerID_tf.getText()));
			customer.setCorporationName(corporation_tf.getText());
			customer.setUsername(userName_tf.getText());
			customer.setPassword(password_tf.getText());
			customer.setAddress(address_tf.getText());
			customer.setNumber(Integer. parseInt(phoneNummber_tf.getText()));
			customer.setEmail(email_tf.getText());
			//Add the contents into customer arraylist
			customerArrayList.add(customer);
			//Add customer arraylist to customer file
			writeToFile(customerArrayList,"files/customer.dat");
			DefaultTableModel model = (DefaultTableModel)this.table_1.getModel();
			model.addRow(new Object[]{customer.getId(),customer.getUsername(),customer.getPassword(),customer.getAddress(),customer.getNumber(),customer.getEmail(),customer.getCorporationName()});
	}catch (Exception e) {
		JOptionPane.showMessageDialog(frame,"Please fill in all the fields","Error", JOptionPane.INFORMATION_MESSAGE);
	}}
	//Function to display customers from binary file
	private void loadCustomers() {
			customerArrayList = readFromFile(customerArrayList,"files/customer.dat");
			for (int i = 0; i < customerArrayList.size(); i++) {
				Customer customer1 = new Customer();
				customer1 = customerArrayList.get(i);
				customerTableModel.addRow(new Object[]{
						customer1.getId(),
						customer1.getUsername(),
						customer1.getPassword(),
						customer1.getAddress(),
						customer1.getNumber(),
						customer1.getEmail(),
						customer1.getCorporationName(),

				});
			}
	}
	//Function reads the contents of a binary file
	public ArrayList readFromFile(ArrayList arrayList, String filePath) {
		try {
			FileInputStream fi = new FileInputStream(filePath);
			ObjectInputStream oi = new ObjectInputStream(fi);
			arrayList = (ArrayList)oi.readObject();
			oi.close();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		} catch (IOException e) {
			System.out.println("File is empty");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
	//Function writes the contents onto a binary file
	public void writeToFile(ArrayList arrayList, String filepath){
		try{
			FileOutputStream fos = new FileOutputStream(filepath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arrayList);
			oos.close();

		} catch (FileNotFoundException e) {
			System.out.println("File does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
