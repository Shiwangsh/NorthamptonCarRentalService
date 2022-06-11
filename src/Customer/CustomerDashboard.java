package Customer;

import Vehicles.Car;
import Vehicles.Lorry;
import Vehicles.MiniBus;

import java.awt.Color;

import javax.swing.*;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.awt.CardLayout;

public class CustomerDashboard {

	JLayeredPane layeredPane = new JLayeredPane();
	JPanel main_panel = new JPanel();
	private JPanel main_panel_1;
	JPanel vehiclePanel = new JPanel();
	private int id;
	private JFrame frame;
	private JLabel customerID;
	private JLabel customerUsername;
	private JLabel customerPassword;
	private JLabel customerAddress;
	private JLabel customerEmail;
	private JLabel customerNumber;
	private JLabel customerCorporation;
	private JLabel customerVehicleOnLoan_lbl;
	DefaultTableModel vehicleModel;
	//Array list to store and retrieve data of different  types from binary file
	ArrayList<Customer> customerArrayList = new ArrayList();
	ArrayList<Car> carArrayList = new ArrayList<>();
	ArrayList<Lorry> lorryArrayList = new ArrayList<>();
	ArrayList<MiniBus> miniBusArrayList = new ArrayList<>();
	private JTable table;

	public CustomerDashboard(int id) {
		int customerID = id;
		initialize();
		loadCustomerData(customerID);
		getHiredVehicles(customerID);
		showAvailableVehicles();
	}
//Function retrieves vehicles that are hired out to specific customer
	public void getHiredVehicles(int customerID) {
		carArrayList = readFromFile(carArrayList,"files/car.dat");
		for (int i = 0; i < carArrayList.size(); i++) {
			Car car1 = new Car();
			car1 = carArrayList.get(i);
			//id check
			if (car1.getHiredTo() == customerID) {
				int vehicleID = car1.getRegNum();
				customerVehicleOnLoan_lbl.setText(String.valueOf(vehicleID));
				break;
			}}

		miniBusArrayList = readFromFile(miniBusArrayList, "files/minibus.dat");
		for (int i = 0; i < miniBusArrayList.size(); i++) {
			MiniBus minibus = new MiniBus();
			minibus = miniBusArrayList.get(i);
			if(minibus.getHiredTo() == customerID ) {
				int vehicleID =  minibus.getRegNum();
				customerVehicleOnLoan_lbl.setText(customerVehicleOnLoan_lbl.getText()+","+vehicleID);
				break;
			}}

		lorryArrayList = readFromFile(lorryArrayList,"files/lorry.dat");
		for (int i = 0; i < lorryArrayList.size(); i++) {
			Lorry lorry = new Lorry();
			lorry = lorryArrayList.get(i);
			if (lorry.getHiredTo() == customerID) {
				int vehicleID =  lorry.getRegNum();
				customerVehicleOnLoan_lbl.setText(customerVehicleOnLoan_lbl.getText()+","+vehicleID);
				break;
			}}
	}
//Function to switch between 2 panels
	public void switchPanels(JPanel panel){
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();

	}
//Function displays data of the user
	public void loadCustomerData(int id) {
			customerArrayList = readFromFile(customerArrayList,"files/customer.dat");
			for (int i = 0; i < customerArrayList.size(); i++) {
				Customer customer1 = new Customer();
				customer1 = customerArrayList.get(i);
				if(id == customer1.getId()){
					customerID.setText(String.valueOf(customer1.getId()));
					customerUsername.setText(String.valueOf(customer1.getUsername()));
					customerPassword.setText(String.valueOf(customer1.getPassword()));
					customerAddress.setText(String.valueOf(customer1.getAddress()));
					customerEmail.setText(String.valueOf(customer1.getEmail()));
					customerNumber.setText(String.valueOf(customer1.getNumber()));
					customerCorporation.setText(String.valueOf(customer1.getCorporationName()));
				}
			}
	}
	//Function displays vehicles which are currently available for hire
	public void showAvailableVehicles() {
		carArrayList = readFromFile(carArrayList,"files/car.dat");
		for (int i = 0; i < carArrayList.size(); i++) {
			Car car1 = new Car();
			car1 = carArrayList.get(i);
			if (car1.getHiredTo() == 0) {
				vehicleModel.addRow(new Object[]{
						car1.getRegNum(),
						car1.getMake(),
						car1.getModel(),
						car1.getVehicleType(),
						car1.getTopSpeed(),
						car1.getFuelType(),
						car1.getHireRate(),
						car1.getNumOfDoors(),
						"--",
						"--"
				});
			}
		}
		miniBusArrayList = readFromFile(miniBusArrayList, "files/minibus.dat");
		for (int i = 0; i < miniBusArrayList.size(); i++) {
			MiniBus minibus = new MiniBus();
			minibus = miniBusArrayList.get(i);
			if(minibus.getHiredTo() == 0) {
				vehicleModel.addRow(new Object[]{
						minibus.getRegNum(),
						minibus.getMake(),
						minibus.getModel(),
						minibus.getVehicleType(),
						minibus.getTopSpeed(),
						minibus.getFuelType(),
						minibus.getHireRate(),
						minibus.getNumOfDoors(),
						minibus.getSeatingCapacity(),
						"--"

				});
			}
		}
		lorryArrayList = readFromFile(lorryArrayList,"files/lorry.dat");
		for (int i = 0; i < lorryArrayList.size(); i++) {
			Lorry lorry = new Lorry();
			lorry = lorryArrayList.get(i);
			if (lorry.getHiredTo() == 0) {
				vehicleModel.addRow(new Object[]{
						lorry.getRegNum(),
						lorry.getMake(),
						lorry.getModel(),
						lorry.getVehicleType(),
						lorry.getTopSpeed(),
						lorry.getFuelType(),
						lorry.getHireRate(),
						lorry.getNumOfDoors(),
						"--",
						lorry.getLoadCapacity()
				});
			}
		}
	}



	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Customer Dashboard");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);

		JPanel sidebar_panel = new JPanel();
		sidebar_panel.setBackground(Color.LIGHT_GRAY);
		sidebar_panel.setBounds(10, 39, 161, 451);
		frame.getContentPane().add(sidebar_panel);
		sidebar_panel.setLayout(null);

		JButton home_btn = new JButton("Home");
		home_btn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		home_btn.setBounds(22, 106, 110, 50);
		sidebar_panel.add(home_btn);
		home_btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switchPanels(main_panel_1);
			}
		});

		JButton vehicle_btn = new JButton("Vehicles");
		vehicle_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanels(vehiclePanel);
				
			}
		});

		vehicle_btn.setFont(new Font("Times New Roman", Font.BOLD, 14));
		vehicle_btn.setBounds(22, 238, 110, 50);
		sidebar_panel.add(vehicle_btn);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(181, 39, 605, 451);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		main_panel_1 = new JPanel();
		main_panel_1.setBackground(Color.PINK);
		layeredPane.add(main_panel_1, "name_87962283516600");
		main_panel_1.setLayout(null);
		
		JLabel id_lbl = new JLabel("ID Number :");
		id_lbl.setBounds(10, 27, 122, 38);
		id_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(id_lbl);
		
		JLabel username_lbl = new JLabel(" Username :");
		username_lbl.setBounds(10, 75, 122, 38);
		username_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(username_lbl);
		
		JLabel password_lbl = new JLabel(" Password :");
		password_lbl.setBounds(10, 123, 122, 38);
		password_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(password_lbl);
		
		JLabel email_lbl = new JLabel(" Email :");
		email_lbl.setBounds(10, 171, 122, 38);
		email_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(email_lbl);
		
		JLabel phoneNumber_lbl = new JLabel("Phone Number :");
		phoneNumber_lbl.setBounds(10, 219, 122, 38);
		phoneNumber_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(phoneNumber_lbl);
		
		JLabel address_lbl = new JLabel("Address :");
		address_lbl.setBounds(10, 280, 122, 38);
		address_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(address_lbl);
		
		JLabel corporation_lbl = new JLabel("Name of Corporation :");
		corporation_lbl.setBounds(10, 348, 158, 38);
		corporation_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(corporation_lbl);

		JLabel vehicleOnLoan_lbl = new JLabel("Vehicles on Loan :");
		vehicleOnLoan_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		vehicleOnLoan_lbl.setBounds(10, 403, 158, 38);
		main_panel_1.add(vehicleOnLoan_lbl);
		
		customerUsername = new JLabel(" Username :");
		customerUsername.setBounds(178, 75, 177, 38);
		customerUsername.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerUsername);
		
		customerID = new JLabel("ID Number :");
		customerID.setBounds(178, 27, 158, 38);
		customerID.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerID);
		
		customerEmail = new JLabel(" Email :");
		customerEmail.setBounds(178, 171, 177, 38);
		customerEmail.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerEmail);
		
		customerNumber = new JLabel("Phone Number :");
		customerNumber.setBounds(178, 219, 158, 38);
		customerNumber.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerNumber);

		customerAddress = new JLabel("Address :");
		customerAddress.setBounds(178, 280, 177, 38);
		customerAddress.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerAddress);
		
		customerCorporation = new JLabel("Name of Corporation :");
		customerCorporation.setBounds(177, 348, 228, 38);
		customerCorporation.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerCorporation);
		
		customerPassword = new JLabel(" Password :");
		customerPassword.setBounds(178, 123, 177, 38);
		customerPassword.setFont(new Font("Times New Roman", Font.BOLD, 15));
		main_panel_1.add(customerPassword);

		customerVehicleOnLoan_lbl = new JLabel(" ");
		customerVehicleOnLoan_lbl.setFont(new Font("Times New Roman", Font.BOLD, 15));
		customerVehicleOnLoan_lbl.setBounds(177, 403, 228, 38);
		main_panel_1.add(customerVehicleOnLoan_lbl);

		vehiclePanel = new JPanel();
		vehiclePanel.setBackground(Color.PINK);
		layeredPane.add(vehiclePanel, "name_88028247742800");
		vehiclePanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 585, 276);
		vehiclePanel.add(scrollPane);

		vehicleModel = new DefaultTableModel();
		vehicleModel.setDataVector(new Object[][]{
						,
				},
				new String[]{
						"Reg number", "Make", "Model", "Vehicle type", "Top speed", "Fuel Type", "Hire Rate", "Number of doors", "Seating Capacity", "Load Capacity"
				}
		);

		table = new JTable(vehicleModel);
		table.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table);

		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	//Function reads the contents of a binary file
	public ArrayList readFromFile(ArrayList arrayList, String filePath) {
		try {
			FileInputStream fi = new FileInputStream(filePath);
			ObjectInputStream oi = new ObjectInputStream(fi);
			arrayList = (ArrayList)oi.readObject();

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
