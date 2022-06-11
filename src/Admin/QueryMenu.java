package Admin;
import Customer.Customer;
import Vehicles.Car;
import Vehicles.Lorry;
import Vehicles.MiniBus;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class QueryMenu {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	private JTextField customerID_tf;
	private JTextField vehicleID_tf;
	//Array list to store and retrieve data of different  types from binary file
	ArrayList<Customer> customerArrayList = new ArrayList();
	ArrayList<Car> cars = new ArrayList();
	ArrayList<MiniBus> miniBuses = new ArrayList();
	ArrayList<Lorry> lorries = new ArrayList();
	public QueryMenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Query Menu");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);
		
		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setLayout(null);
		sidebarPanel.setBackground(Color.LIGHT_GRAY);
		sidebarPanel.setBounds(10, 10, 107, 341);
		frame.getContentPane().add(sidebarPanel);
		
		JButton btnNewButton = new JButton("Add Vehicle");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		btnNewButton.setBackground(Color.GRAY);
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
		btnAddCustomer.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnAddCustomer.setBackground(Color.GRAY);
		btnAddCustomer.setBounds(10, 120, 89, 40);
		sidebarPanel.add(btnAddCustomer);
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCustomer addCustomer = new AddCustomer();
				frame.dispose();
			}
		});
		
		JButton btnRent = new JButton("Rent");
		btnRent.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnRent.setBackground(Color.GRAY);
		btnRent.setBounds(10, 188, 89, 40);
		sidebarPanel.add(btnRent);
		btnRent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HireMenu hireMenu = new HireMenu();
				frame.dispose();
			}
		});
		
		JButton btnQuery = new JButton("Query");
		btnQuery.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnQuery.setBackground(Color.GRAY);
		btnQuery.setBounds(10, 267, 89, 40);
		sidebarPanel.add(btnQuery);
		
		JPanel panel = new JPanel();
		panel.setBounds(127, 10, 648, 160);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel customerID_lbl = new JLabel("Customer ID: ");
		customerID_lbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		customerID_lbl.setBounds(29, 26, 104, 34);
		panel.add(customerID_lbl);
		
		customerID_tf = new JTextField();
		customerID_tf.setBounds(151, 28, 128, 34);
		panel.add(customerID_tf);
		customerID_tf.setColumns(10);
		
		vehicleID_tf = new JTextField();
		vehicleID_tf.setColumns(10);
		vehicleID_tf.setBounds(463, 61, 154, 34);
		panel.add(vehicleID_tf);
		
		JButton customerSearch_btn = new JButton("Search Customer");
		customerSearch_btn.setBounds(35, 116, 120, 34);
		panel.add(customerSearch_btn);
		
		JComboBox vehicleID_cb = new JComboBox();
		vehicleID_cb.setModel(new DefaultComboBoxModel(new String[] {"Car", "Minibus", "Lorry"}));
		vehicleID_cb.setBounds(463, 10, 154, 39);
		panel.add(vehicleID_cb);
		
		JLabel vehicleType_lbl = new JLabel("Vehicle Type :");
		vehicleType_lbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		vehicleType_lbl.setBounds(344, 15, 104, 34);
		panel.add(vehicleType_lbl);
		
		JLabel vehicleID_lbl = new JLabel("Vehicle ID :");
		vehicleID_lbl.setFont(new Font("Times New Roman", Font.BOLD, 16));
		vehicleID_lbl.setBounds(344, 61, 104, 34);
		panel.add(vehicleID_lbl);
		
		JButton vehicleSearch_btn = new JButton("Search Vehicle");
		vehicleSearch_btn.setBounds(484, 116, 120, 34);
		panel.add(vehicleSearch_btn);

		customerSearch_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (customerID_tf.getText().isEmpty() == false){
					queryCustomer();
				}
			else {
				JOptionPane.showMessageDialog(frame,"Enter id first");
			}
			}
		});

		vehicleSearch_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(vehicleID_cb.getSelectedIndex()==0 && vehicleID_tf.getText().isEmpty()==false){
					queryCar();
				}
				else if(vehicleID_cb.getSelectedIndex()==1&& vehicleID_tf.getText().isEmpty()==false){
					queryMiniBus();
				}
				else if(vehicleID_cb.getSelectedIndex() == 2 && vehicleID_tf.getText().isEmpty()==false){
					queryLorry();
				}
				else{
					JOptionPane.showMessageDialog(frame,"Enter id first");

				}
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(127, 180, 648, 171);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 628, 135);
		panel_1.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
					"ID", "Username", "Password", "Address", "Number", "Email", "Name of Corporation"
			}
		));
		
		JLabel lblCustomer = new JLabel("Customer");
		lblCustomer.setBounds(256, 0, 115, 29);
		panel_1.add(lblCustomer);
		lblCustomer.setFont(new Font("Times New Roman", Font.BOLD, 25));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 361, 765, 201);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 745, 152);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {

			},
			new String[] {
					"Reg number", "Make", "Model", "Vehicle type", "Top speed", "Fuel Type", "Hire Rate", "Number of doors", "Seating Capacity", "Load Capacity","Hired to"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel vehicle_lbl = new JLabel("Vehicle");
		vehicle_lbl.setBounds(388, 0, 95, 30);
		panel_2.add(vehicle_lbl);
		vehicle_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));

		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	//Function to query customer
	public void queryCustomer() {
		//read from file
			customerArrayList = readFromFile(customerArrayList,"files/customer.dat");
			for (int i = 0; i < customerArrayList.size(); i++) {
				Customer customer1 = new Customer();
				customer1 = customerArrayList.get(i);
				int id = (Integer.parseInt(customerID_tf.getText()));
				//check id
				if(customer1.getId() == id){
					DefaultTableModel model = (DefaultTableModel) table_1.getModel();
					model.addRow(new Object[]{customer1.getId(),
							customer1.getUsername(),
							customer1.getPassword(),
							customer1.getAddress(),
							customer1.getNumber(),
							customer1.getEmail(),
							customer1.getCorporationName(),
					});
					break;
				}
				else if(i == customerArrayList.size() - 1 && customer1.getId() != id){
					JOptionPane.showMessageDialog(frame,"Invalid User ID","Error",JOptionPane.WARNING_MESSAGE);
				}
			}
	}
	//Function to query car
	public void queryCar(){
	//read from file
			cars = readFromFile(cars,"files/car.dat");
			int id = (Integer.parseInt(vehicleID_tf.getText()));
			Car car = new Car();
			for (int i = 0; i < cars.size(); i++) {
				car = cars.get(i);
				//check id
				if(car.getRegNum() == id) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{car.getRegNum(), car.getMake(), car.getModel(), car.getVehicleType(),car.getTopSpeed(), car.getFuelType(), car.getHireRate(), car.getNumOfDoors(),"--","--",car.getHiredTo()});
					break;
				}
				else if(i == cars.size() - 1 && car.getRegNum() != id){
					JOptionPane.showMessageDialog(frame,"Invalid Car ID","Error",JOptionPane.WARNING_MESSAGE);
				}
			}

	}
	//Function to query minibus
	public void queryMiniBus(){
		//read from file
			miniBuses = readFromFile(miniBuses,"files/minibus.dat");
			for (int i = 0; i < miniBuses.size(); i++) {
				MiniBus minibus = new MiniBus();
				minibus = miniBuses.get(i);
				int id = (Integer.parseInt(vehicleID_tf.getText()));
				//check id
				if(minibus.getRegNum() == id) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{minibus.getRegNum(), minibus.getMake(), minibus.getModel(),minibus.getVehicleType(), minibus.getTopSpeed(), minibus.getFuelType(), minibus.getHireRate(), minibus.getNumOfDoors(),minibus.getSeatingCapacity(),"--",minibus.getHiredTo()});
					break;
				}
				else if (i == miniBuses.size()-1&& minibus.getRegNum() != id){
					JOptionPane.showMessageDialog(frame,"Invalid MiniBus ID","Error",JOptionPane.WARNING_MESSAGE);
				}
			}
	}
	//Function to query lorry
	public void queryLorry(){
		//read from file
			lorries = readFromFile(lorries, "files/lorry.dat");
			for (int i = 0; i < lorries.size(); i++) {
				Lorry lorry = new Lorry();
				lorry = lorries.get(i);
				int id = (Integer.parseInt(vehicleID_tf.getText()));
				//check id
				if (lorry.getRegNum() == id) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{lorry.getRegNum(), lorry.getMake(), lorry.getModel(), lorry.getVehicleType(), lorry.getTopSpeed(), lorry.getFuelType(), lorry.getHireRate(), lorry.getNumOfDoors(), "--", lorry.getLoadCapacity(), lorry.getHiredTo()});
					break;

				} else if (i == lorries.size() - 1 && lorry.getRegNum() != id) {
					JOptionPane.showMessageDialog(frame, "Invalid Lorry ID", "Error", JOptionPane.WARNING_MESSAGE);
				}
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
	}
