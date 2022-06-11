package Admin;
import Customer.Customer;
import Vehicles.Car;
import Vehicles.HireVehicle;
import Vehicles.MiniBus;
import Vehicles.Lorry;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class HireMenu{

	private JFrame frame;
	private JButton submitBtn;
	private JTable table;
	private JTextField customerID_tf;
	private JTextField vehicleID_tf;
	private JComboBox vehicleType_cb;
	private JLabel seatingCapacity_lbl;
	private JTextField seatingCapacity_tf;
	private JLabel loadCapacity_lbl;
	private JTextField loadCapacity_tf;
	private JTable table_1;
	DefaultTableModel onHireModel;
	DefaultTableModel vehicleModel;

	//Array list to store and retrieve data of different  types from binary file
	ArrayList<Car> carArrayList = new ArrayList<>();
	ArrayList<Lorry> lorryArrayList = new ArrayList<>();
	ArrayList<MiniBus> miniBusArrayList = new ArrayList<>();
	ArrayList<Customer> customerArrayList = new ArrayList();
	ArrayList<HireVehicle> onhireArrayList = new ArrayList<>();

	public HireMenu() {
		initialize();
		showOnHire();
		showAvailableVehicles();
	}
//Function to display vehicles that are available for hire
		public void showAvailableVehicles() {
		//read from binary file
		carArrayList = readFromFile(carArrayList,"files/car.dat");
		for (int i = 0; i < carArrayList.size(); i++) {
			Car car1 = new Car();
			car1 = carArrayList.get(i);
			//check hired to
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
		try {
			lorryArrayList = readFromFile(lorryArrayList, "files/lorry.dat");
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
		catch (ClassCastException c){
			System.out.println("No lorries currently in storage");
		}
	}

	//Function displays vehicles currently on hire
	public void showOnHire() {
		onhireArrayList = readFromFile(onhireArrayList, "files/onhire.dat");
		for (int i = 0; i < onhireArrayList.size(); i++) {
			HireVehicle hv = new HireVehicle();
			hv = onhireArrayList.get(i);
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.addRow(new Object[]{
					hv.getCustomerID(), hv.getVehicleID(), hv.getVehicleType()
			});
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Hire Menu");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);


		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(Color.LIGHT_GRAY);
		sidebarPanel.setBounds(10, 10, 115, 305);
		frame.getContentPane().add(sidebarPanel);
		sidebarPanel.setLayout(null);

		JButton btnNewButton = new JButton("Add Vehicle");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnNewButton.setBounds(10, 10, 89, 40);
		sidebarPanel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminDashboard adminDashboard = new AdminDashboard();
				frame.dispose();
			}
		});

		JButton btnAddCustomer = new JButton("Add Customers");
		btnAddCustomer.setBackground(Color.GRAY);
		btnAddCustomer.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnAddCustomer.setBounds(10, 68, 89, 40);
		sidebarPanel.add(btnAddCustomer);
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCustomer addCustomer = new AddCustomer();
				frame.dispose();
			}
		});


		JButton btnRent = new JButton("Rent");
		btnRent.setBackground(Color.GRAY);
		btnRent.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		btnRent.setBounds(10, 132, 89, 40);
		sidebarPanel.add(btnRent);

		JButton btnQuery = new JButton("Query");
		btnQuery.setBackground(Color.GRAY);
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryMenu queryMenu = new QueryMenu();
				frame.dispose();
			}
		});
		btnQuery.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnQuery.setBounds(10, 202, 89, 40);
		sidebarPanel.add(btnQuery);

		JPanel Hire_panel = new JPanel();
		Hire_panel.setBackground(Color.LIGHT_GRAY);
		Hire_panel.setBounds(135, 10, 279, 305);
		frame.getContentPane().add(Hire_panel);
		Hire_panel.setLayout(null);

		JLabel customerID_lbl = new JLabel("Customer ID :");
		customerID_lbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		customerID_lbl.setBounds(10, 50, 100, 28);
		Hire_panel.add(customerID_lbl);

		JLabel hireVehicle_lbl = new JLabel("Vehicle ID :");
		hireVehicle_lbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		hireVehicle_lbl.setBounds(10, 110, 100, 28);
		Hire_panel.add(hireVehicle_lbl);

		submitBtn = new JButton("Hire");
		submitBtn.setBackground(Color.GRAY);
		submitBtn.setBounds(100, 253, 109, 42);
		Hire_panel.add(submitBtn);
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					if(vehicleType_cb.getSelectedIndex()==0 && vehicleID_tf.getText().isEmpty()==false && customerID_lbl.getText().isEmpty()== false){
						int customerID = getCustomerID();
						int vehicleID = getCarID();
						String vehicleType = (String) vehicleType_cb.getSelectedItem();
						//when selected vehicle is a car
						hireVehicle(customerID,vehicleID, vehicleType);
					}
					else if(vehicleType_cb.getSelectedIndex()==1&& vehicleID_tf.getText().isEmpty()==false && customerID_lbl.getText().isEmpty()==false){
						seatingCapacity_tf.setVisible(true);
						int customerID = getCustomerID();
						int vehicleID = getMiniBusID();
						String vehicleType = (String) vehicleType_cb.getSelectedItem();
						//when selected vehicle is a minibus
						hireVehicle(customerID,vehicleID,vehicleType);
					}
					else if(vehicleType_cb.getSelectedIndex() == 2 && vehicleID_tf.getText().isEmpty()==false && customerID_lbl.getText().isEmpty()==false){
						int customerID = getCustomerID();
						int vehicleID = getLorryID();
						String vehicleType = (String) vehicleType_cb.getSelectedItem();
						//when selected vehicle is a lorry
						hireVehicle(customerID,vehicleID,vehicleType);
					}
					else {
					JOptionPane.showMessageDialog(frame, "please enter all fields");

				}

			}

		});
		JLabel lbl1 = new JLabel("Hire a Vehicle");
		lbl1.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lbl1.setBounds(100, 10, 157, 30);
		Hire_panel.add(lbl1);

		customerID_tf = new JTextField();
		customerID_tf.setBounds(148, 51, 109, 30);
		Hire_panel.add(customerID_tf);
		customerID_tf.setColumns(10);

		vehicleID_tf = new JTextField();
		vehicleID_tf.setColumns(10);
		vehicleID_tf.setBounds(148, 111, 109, 30);
		Hire_panel.add(vehicleID_tf);

		vehicleType_cb = new JComboBox();
		vehicleType_cb.setModel(new DefaultComboBoxModel(new String[]{"Car", "Mini Bus", "Lorry"}));
		vehicleType_cb.setBounds(148, 161, 109, 28);
		Hire_panel.add(vehicleType_cb);
		vehicleType_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(vehicleType_cb.getSelectedIndex() == 1){
					seatingCapacity_tf.setVisible(true);
					seatingCapacity_lbl.setVisible(true);
					loadCapacity_lbl.setVisible(false);
					loadCapacity_tf.setVisible(false);
				}
				else if(vehicleType_cb.getSelectedIndex()==2){
					loadCapacity_lbl.setVisible(true);
					loadCapacity_tf.setVisible(true);
					seatingCapacity_tf.setVisible(false);
					seatingCapacity_lbl.setVisible(false);
				}
				else{
					seatingCapacity_tf.setVisible(false);
					seatingCapacity_lbl.setVisible(false);
					loadCapacity_lbl.setVisible(false);
					loadCapacity_tf.setVisible(false);
				}
			}
		});


		JLabel vehicleType_lbl = new JLabel("Vehicle Type :");
		vehicleType_lbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		vehicleType_lbl.setBounds(10, 161, 118, 26);
		Hire_panel.add(vehicleType_lbl);
		
		seatingCapacity_lbl = new JLabel("Seating Capacity");
		seatingCapacity_lbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		seatingCapacity_lbl.setBounds(10, 215, 118, 28);
		seatingCapacity_lbl.setVisible(false);
		Hire_panel.add(seatingCapacity_lbl);

		
		seatingCapacity_tf = new JTextField();
		seatingCapacity_tf.setColumns(10);
		seatingCapacity_tf.setBounds(148, 213, 109, 30);
		seatingCapacity_tf.setVisible(false);
		Hire_panel.add(seatingCapacity_tf);

		
		loadCapacity_lbl = new JLabel("Load Capacity");
		loadCapacity_lbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		loadCapacity_lbl.setBounds(10, 215, 118, 28);
		loadCapacity_lbl.setVisible(false);
		Hire_panel.add(loadCapacity_lbl);

		
		loadCapacity_tf = new JTextField();
		loadCapacity_tf.setColumns(10);
		loadCapacity_tf.setBounds(148, 213, 109, 30);
		loadCapacity_tf.setVisible(false);
		Hire_panel.add(loadCapacity_tf);


		JPanel tabel_panel = new JPanel();
		tabel_panel.setBackground(Color.GRAY);
		tabel_panel.setBounds(10, 325, 766, 228);
		frame.getContentPane().add(tabel_panel);
		tabel_panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 746, 179);
		tabel_panel.add(scrollPane);

		vehicleModel = new DefaultTableModel();
		vehicleModel.setDataVector(new Object[][]{
						,
				},
				new String[]{
						"Reg number", "Make", "Model", "Vehicle type", "Top speed", "Fuel Type", "Hire Rate", "Number of doors", "Seating Capacity", "Load Capacity"
				}
		);
		table = new JTable(vehicleModel);
		scrollPane.setViewportView(table);
		table.setBackground(Color.LIGHT_GRAY);

		JLabel lblVehicleAvailabelFor = new JLabel("Vehicles Availabel for Hire");
		lblVehicleAvailabelFor.setFont(new Font("Times New Roman", Font.BOLD, 25));
		lblVehicleAvailabelFor.setBounds(219, 0, 290, 40);
		tabel_panel.add(lblVehicleAvailabelFor);

		onHireModel = new DefaultTableModel();
		onHireModel.setDataVector(
				new Object[][]{
				},
				new String[]{
						"Customer ID", "Vehicle ID", "Vehicle Type"
				}
		);

		JPanel unHire_panel = new JPanel();
		unHire_panel.setBackground(Color.LIGHT_GRAY);
		unHire_panel.setBounds(398, 10, 378, 305);
		frame.getContentPane().add(unHire_panel);
		unHire_panel.setLayout(null);

		JButton submitBtn_1 = new JButton("Unhire");
		submitBtn_1.setBackground(Color.GRAY);
		submitBtn_1.setBounds(162, 253, 109, 42);
		unHire_panel.add(submitBtn_1);
		submitBtn_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rowIndex = table_1.getSelectedRow();
					String customerID = table_1.getModel().getValueAt(rowIndex, 0).toString();
					String vehicleID = table_1.getModel().getValueAt(rowIndex, 1).toString();
					String vehicleType = table_1.getModel().getValueAt(rowIndex, 2).toString();
					unhireVehicle(Integer.parseInt(customerID), Integer.parseInt(vehicleID));
					deleteRecord(Integer.parseInt(vehicleID), vehicleType);
				}catch(Exception ex){
					JOptionPane.showMessageDialog(frame, "Please select a vehicle to un-hire", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			}
		);
		
				JLabel lblVehiclesOnHire = new JLabel("Vehicles on Hire");
				lblVehiclesOnHire.setBounds(90, 0, 222, 40);
				unHire_panel.add(lblVehiclesOnHire);
				lblVehiclesOnHire.setFont(new Font("Times New Roman", Font.BOLD, 25));
				
						JScrollPane scrollPane_1 = new JScrollPane();
						scrollPane_1.setBounds(32, 50, 323, 179);
						unHire_panel.add(scrollPane_1);
						
								table_1 = new JTable(onHireModel);
								table_1.setBackground(Color.LIGHT_GRAY);
								scrollPane_1.setViewportView(table_1);

		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
//Function un-hires a vehicle which is currently hired out
	private void unhireVehicle(int customerID, int vehicleID) {
		onhireArrayList = readFromFile(onhireArrayList, "files/onhire.dat");

		for (int i = 0; i < onhireArrayList.size(); i++) {
			HireVehicle hv = new HireVehicle();
			hv = onhireArrayList.get(i);
			if (customerID == hv.getCustomerID() && vehicleID == hv.getVehicleID()) {
				onhireArrayList.remove(hv);
				writeToFile(onhireArrayList, "files/onhire.dat");
				onHireModel.removeRow(table_1.getSelectedRow());
			}
		}
	}

//Function hires out selcted vehicle
	private void hireVehicle(int customerID, int vehicleID, String vehicleType) {
		onhireArrayList = readFromFile(onhireArrayList, "files/onhire.dat");
		HireVehicle hv = new HireVehicle();
		if (customerID !=0 && vehicleID != 0) {
			hv.setCustomerID(customerID);
			hv.setVehicleID(vehicleID);
			hv.setVehicleType(vehicleType);
			onhireArrayList.add(hv);
			writeToFile(onhireArrayList, "files/onhire.dat");
			onHireModel.addRow(new Object[]{
							customerID,vehicleID,vehicleType
					});
			JOptionPane.showMessageDialog(frame,"Vehicle Successfully hired out");

		}
	}

//Function retrieves a customer id
	private int getCustomerID() {
		customerArrayList = readFromFile(customerArrayList, "files/customer.dat");
		for (int i = 0; i < customerArrayList.size(); i++) {
			Customer customer1 = new Customer();
			customer1 = customerArrayList.get(i);
			int id = (Integer.parseInt(customerID_tf.getText()));
			if (customer1.getId() == id) {
				return customer1.getId();
			}
		}
		JOptionPane.showMessageDialog(frame,"Invalid Customer ID","Error",JOptionPane.WARNING_MESSAGE);
		return 0;
	}
//Function retrieves car id
	private int getCarID() {
		carArrayList = readFromFile(carArrayList,"files/car.dat");
		for (int i = 0; i < carArrayList.size(); i++) {
			Car car = new Car();
			car = carArrayList.get(i);
			int id = (Integer.parseInt(vehicleID_tf.getText()));

			if (car.getRegNum() == id) {
				if(car.getHiredTo() == 0) {
					car.setHiredTo(Integer.parseInt(customerID_tf.getText()));
					writeToFile(carArrayList, "files/car.dat");
					return car.getRegNum();
				}
				else if(car.getHiredTo() != 0 ){
					JOptionPane.showMessageDialog(frame,"Car is already hired to a customer","Error", JOptionPane.INFORMATION_MESSAGE);
					return 0;
				}
			}

		}
		JOptionPane.showMessageDialog(frame,"Invalid car ID","Error",JOptionPane.WARNING_MESSAGE);
		return 0;
	}

	//Function retrieves minibus id
	private int getMiniBusID() {
			miniBusArrayList = readFromFile(miniBusArrayList, "files/minibus.dat");
			int seatingCapacity = Integer.parseInt(seatingCapacity_tf.getText());
			for (int i = 0; i < miniBusArrayList.size(); i++) {
				MiniBus miniBus = new MiniBus();
				miniBus = miniBusArrayList.get(i);
				int id = (Integer.parseInt(vehicleID_tf.getText()));

				if(miniBus.getRegNum() == id){
					if(miniBus.getHiredTo() == 0 && seatingCapacity <= miniBus.getSeatingCapacity()){
						miniBus.setHiredTo(Integer.parseInt(customerID_tf.getText()));
						writeToFile(miniBusArrayList, "files/minibus.dat");
						return miniBus.getRegNum();
					}
					else if(miniBus.getHiredTo() != 0){
						JOptionPane.showMessageDialog(frame, "MiniBus is already hired to a customer","Error", JOptionPane.INFORMATION_MESSAGE);
						return 0;
					}
					else if(seatingCapacity > miniBus.getSeatingCapacity()){
					JOptionPane.showMessageDialog(frame, "Seating Capacity exceeded","Error", JOptionPane.INFORMATION_MESSAGE);
					return 0;
					}
				}
			}
			JOptionPane.showMessageDialog(frame,"Invalid Minibus ID","Error",JOptionPane.WARNING_MESSAGE);
		return 0;
	}
	//Function retrieves lorry id
	private int getLorryID() {
		lorryArrayList = readFromFile(lorryArrayList,"files/lorry.dat");
		int loadCapacity = Integer.parseInt(loadCapacity_tf.getText());
		for (int i = 0; i < lorryArrayList.size(); i++) {
			Lorry lorry = new Lorry();
			lorry = lorryArrayList.get(i);
			int id = (Integer.parseInt(vehicleID_tf.getText()));

			if(lorry.getRegNum() == id){
				if(lorry.getHiredTo() == 0 && loadCapacity <= lorry.getLoadCapacity()){
					lorry.setHiredTo(Integer.parseInt(customerID_tf.getText()));
					writeToFile(lorryArrayList, "files/lorry.dat");
					return lorry.getRegNum();
				}
				else if(lorry.getHiredTo() != 0){
					JOptionPane.showMessageDialog(frame, "Lorry is already hired to a customer","Error", JOptionPane.INFORMATION_MESSAGE);
					return 0;
				}
				else if(loadCapacity > lorry.getLoadCapacity()){
					JOptionPane.showMessageDialog(frame, "Load Capacity exceeded","Error", JOptionPane.INFORMATION_MESSAGE);
					return 0;
				}
			}
			
		}
		JOptionPane.showMessageDialog(frame,"Invalid Lorry ID","Error",JOptionPane.WARNING_MESSAGE);
		return 0;
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
	//Function writes the contents onto a binary file
	public void writeToFile(ArrayList arrayList, String filepath) {
		try {
			FileOutputStream fos = new FileOutputStream(filepath);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arrayList);
			oos.close();

		} catch (FileNotFoundException e) {
			System.out.println("File doesn't exist");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//Function deletes record
	private void deleteRecord(int vehicleID, String vehicleType) {
		if(vehicleType.equals("Car")){
			carArrayList = readFromFile(carArrayList,"files/car.dat");
			for (int i = 0; i < carArrayList.size(); i++) {
				Car car1 = new Car();
				car1 = carArrayList.get(i);
				if(vehicleID == car1.getRegNum()){
					car1.setHiredTo(0);
					writeToFile(carArrayList,"files/car.dat");
					break;
				}
			}


		}
		else if(vehicleType.equals("Mini Bus")){
			miniBusArrayList = readFromFile(miniBusArrayList,"files/minibus.dat");
			for (int i = 0; i < miniBusArrayList.size(); i++) {
				MiniBus miniBus = new MiniBus();
				miniBus = miniBusArrayList.get(i);
				if (vehicleID == miniBus.getRegNum()) {
					miniBus.setHiredTo(0);
					writeToFile(miniBusArrayList, "files/minibus.dat");
					break;
				}
			}
		}
		else if(vehicleType.equals("Lorry")){
			lorryArrayList = readFromFile(lorryArrayList,"files/lorry.dat");
			for (int i = 0; i < lorryArrayList.size(); i++) {
				Lorry lorry = new Lorry();
				lorry = lorryArrayList.get(i);
				if(vehicleID== lorry.getRegNum()){
					lorry.setHiredTo(0);
					writeToFile(lorryArrayList,"files/lorry.dat");
					break;
				}

			}

		}
		else{
			JOptionPane.showMessageDialog(frame,"error");

		}

	}



}
