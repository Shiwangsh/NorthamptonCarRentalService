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

import Vehicles.*;
public class AdminDashboard {

	private JFrame frame;
	private JTextField regNum_tf;
	private JTextField make_tf;
	private JTextField model_tf;
	private JTextField topSpeed_tf;
	private JTextField hireRate_tf;
	private JTextField NumDoors_tf;
	private JTextField seatingCap_tf;
	private JTextField loadCap_tf;
	private JComboBox fuelType_cb;
	private JComboBox vehicleType_cb;

	//Array list to store and retrieve data of different  types from binary file
	ArrayList<Car> cars = new ArrayList();
	ArrayList<MiniBus> miniBuses = new ArrayList();
	ArrayList<Lorry> lorries = new ArrayList();

	private JTable table_1;
	DefaultTableModel vehiclesTableModel;

	public AdminDashboard() {
		initialize();
		loadVehicles();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frame = new JFrame("Add Vehicles");
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setLayout(null);

		JPanel vehicePanel = new JPanel();
		vehicePanel.setBackground(Color.LIGHT_GRAY);
		vehicePanel.setLayout(null);
		vehicePanel.setBounds(127, 10, 659, 364);
		frame.getContentPane().add(vehicePanel);

		JLabel regLbl = new JLabel("Reg. Number");
		regLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		regLbl.setBounds(10, 63, 128, 22);
		vehicePanel.add(regLbl);

		regNum_tf = new JTextField();
		regNum_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		regNum_tf.setColumns(10);
		regNum_tf.setBounds(10, 95, 153, 31);
		vehicePanel.add(regNum_tf);

		JLabel makeLbl = new JLabel("Make");
		makeLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		makeLbl.setBounds(180, 63, 85, 22);
		vehicePanel.add(makeLbl);

		JLabel modelLbl = new JLabel("Model");
		modelLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		modelLbl.setBounds(352, 63, 85, 22);
		vehicePanel.add(modelLbl);

		JLabel topSpeedLbl = new JLabel("Top Speed(KMPH)");
		topSpeedLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		topSpeedLbl.setBounds(10, 136, 153, 22);
		vehicePanel.add(topSpeedLbl);

		JLabel fuelTypeLbl = new JLabel("Fuel Type");
		fuelTypeLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fuelTypeLbl.setBounds(180, 136, 85, 22);
		vehicePanel.add(fuelTypeLbl);

		JLabel numDoorsLbl = new JLabel("Number of doors");
		numDoorsLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		numDoorsLbl.setBounds(480, 136, 123, 22);
		vehicePanel.add(numDoorsLbl);

		make_tf = new JTextField();
		make_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		make_tf.setColumns(10);
		make_tf.setBounds(173, 95, 153, 31);
		vehicePanel.add(make_tf);

		model_tf = new JTextField();
		model_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		model_tf.setColumns(10);
		model_tf.setBounds(346, 95, 153, 31);
		vehicePanel.add(model_tf);

		JLabel seataingCapLbl = new JLabel("Seating Capacity");
		seataingCapLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		seataingCapLbl.setBounds(10, 228, 128, 22);
		seataingCapLbl.setVisible(false);
		vehicePanel.add(seataingCapLbl);

		seatingCap_tf = new JTextField();
		seatingCap_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		seatingCap_tf.setColumns(10);
		seatingCap_tf.setBounds(10, 260, 153, 31);
		seatingCap_tf.setVisible(false);
		vehicePanel.add(seatingCap_tf);

		JLabel loadCapLbl = new JLabel("Load Capacity(KG)");
		loadCapLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		loadCapLbl.setBounds(10, 228, 135, 22);
		loadCapLbl.setVisible(false);
		vehicePanel.add(loadCapLbl);

		loadCap_tf = new JTextField();
		loadCap_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		loadCap_tf.setColumns(10);
		loadCap_tf.setBounds(10, 260, 153, 31);
		loadCap_tf.setVisible(false);
		vehicePanel.add(loadCap_tf);


		JLabel vehicleTypleLbl = new JLabel("Vehicle type");
		vehicleTypleLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		vehicleTypleLbl.setBounds(515, 63, 110, 22);
		vehicePanel.add(vehicleTypleLbl);

		vehicleType_cb = new JComboBox();
		vehicleType_cb.setModel(new DefaultComboBoxModel(new String[]{"Car", "MiniBus", "Lorry"}));
		vehicleType_cb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		vehicleType_cb.setBounds(509, 95, 116, 27);
		vehicePanel.add(vehicleType_cb);
		vehicleType_cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (vehicleType_cb.getSelectedIndex() == 1 && vehicleType_cb.getSelectedIndex() != 2) {
					seataingCapLbl.setVisible(true);
					seatingCap_tf.setVisible(true);
					loadCapLbl.setVisible(false);
					loadCap_tf.setVisible(false);
					try {

					} catch (Exception ex) {

					}
				} else if (vehicleType_cb.getSelectedIndex() == 2) {
					loadCapLbl.setVisible(true);
					loadCap_tf.setVisible(true);
					seataingCapLbl.setVisible(false);
					seatingCap_tf.setVisible(false);
				} else {
					seataingCapLbl.setVisible(false);
					seatingCap_tf.setVisible(false);
					loadCapLbl.setVisible(false);
					loadCap_tf.setVisible(false);
				}
			}
		});


		topSpeed_tf = new JTextField();
		topSpeed_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		topSpeed_tf.setColumns(10);
		topSpeed_tf.setBounds(10, 168, 153, 31);
		vehicePanel.add(topSpeed_tf);

		fuelType_cb = new JComboBox();
		fuelType_cb.setModel(new DefaultComboBoxModel(new String[]{"Petrol", "Diesel"}));
		fuelType_cb.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fuelType_cb.setBounds(180, 168, 116, 31);
		vehicePanel.add(fuelType_cb);

		JLabel hireRateLbl = new JLabel("Hire Rate");
		hireRateLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		hireRateLbl.setBounds(318, 136, 123, 22);
		vehicePanel.add(hireRateLbl);

		hireRate_tf = new JTextField();
		hireRate_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		hireRate_tf.setColumns(10);
		hireRate_tf.setBounds(318, 168, 153, 31);
		vehicePanel.add(hireRate_tf);

		NumDoors_tf = new JTextField();
		NumDoors_tf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		NumDoors_tf.setColumns(10);
		NumDoors_tf.setBounds(480, 168, 145, 31);
		vehicePanel.add(NumDoors_tf);


		JButton resetBtn = new JButton("Reset");
		resetBtn.setBackground(Color.GRAY);
		resetBtn.setBounds(10, 301, 100, 42);
		vehicePanel.add(resetBtn);

		//Reset the text fields contents
		resetBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				regNum_tf.setText(null);
				make_tf.setText(null);
				model_tf.setText(null);
				topSpeed_tf.setText(null);
				hireRate_tf.setText(null);
				NumDoors_tf.setText(null);
				seatingCap_tf.setText(null);
				loadCap_tf.setText(null);
				fuelType_cb.setSelectedItem(null);
				vehicleType_cb.setSelectedItem(null);
			}
		});

		JButton deleteBtn = new JButton("Delete");
		deleteBtn.setBackground(Color.GRAY);
		deleteBtn.setBounds(243, 301, 100, 42);
		vehicePanel.add(deleteBtn);

		JLabel addVehicle_lbl = new JLabel("Add Vehicle");
		addVehicle_lbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
		addVehicle_lbl.setBounds(234, 10, 145, 43);
		vehicePanel.add(addVehicle_lbl);


		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int rowIndex = table_1.getSelectedRow();
					int columnIndex = 3;
					deleteRecord(rowIndex, columnIndex);
				}catch (Exception ex){
					JOptionPane.showMessageDialog(frame,"Please select a record from the table below to delete","Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		JButton submitBtn = new JButton("Submit");
		submitBtn.setBackground(Color.GRAY);
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (vehicleType_cb.getSelectedIndex() == 0 ) {
					//If selected car, add car
					addCar();
				} else if (vehicleType_cb.getSelectedIndex() == 1) {
					//If selected minibus, add minibus
					addMiniBus();
				} else if (vehicleType_cb.getSelectedIndex() == 2 ) {
					//If selected lorry, add lorry
					addLorry();
				}
			}
		});
		submitBtn.setBounds(120, 301, 100, 42);
		vehicePanel.add(submitBtn);

		JPanel tabelPanel = new JPanel();
		tabelPanel.setBounds(10, 384, 776, 178);
		frame.getContentPane().add(tabelPanel);
		tabelPanel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		tabelPanel.add(scrollPane);

		vehiclesTableModel = new DefaultTableModel();
		vehiclesTableModel.setDataVector(
				new Object[][]{
						},
				new String[]{
						"Reg number", "Make", "Model", "Vehicle type", "Top speed", "Fuel Type", "Hire Rate", "Number of doors", "Seating Capacity", "Load Capacity"
				}
		);

		table_1 = new JTable(vehiclesTableModel);
		table_1.setBackground(Color.LIGHT_GRAY);
		scrollPane.setViewportView(table_1);

		JPanel sidebarPanel = new JPanel();
		sidebarPanel.setBackground(Color.LIGHT_GRAY);
		sidebarPanel.setBounds(10, 10, 107, 364);
		frame.getContentPane().add(sidebarPanel);
		sidebarPanel.setLayout(null);

		JButton btnNewButton = new JButton("Add Vehicle");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 10));
		btnNewButton.setBounds(10, 43, 89, 40);
		sidebarPanel.add(btnNewButton);

		JButton btnAddCustomer = new JButton("Add Customer");
		btnAddCustomer.setBackground(Color.GRAY);
		btnAddCustomer.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnAddCustomer.setBounds(10, 120, 89, 40);
		sidebarPanel.add(btnAddCustomer);
		btnAddCustomer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddCustomer add = new AddCustomer();
				frame.dispose();
			}
		});

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
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QueryMenu qMenu = new QueryMenu();
				frame.dispose();
			}
		});
		btnQuery.setBackground(Color.GRAY);
		btnQuery.setFont(new Font("Times New Roman", Font.BOLD, 10));
		btnQuery.setBounds(10, 267, 89, 40);
		sidebarPanel.add(btnQuery);

		frame.setBounds(100, 100, 800, 600);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Function to delete selected vehicle
		public void deleteRecord(int rowIndex, int columnIndex) {
		String value = table_1.getModel().getValueAt(rowIndex, columnIndex).toString();
		String regNum = table_1.getModel().getValueAt(rowIndex, 0).toString();
		if(value.equals("Car")){
			cars = readFromFile(cars,"files/car.dat");
			for (int i = 0; i < cars.size(); i++) {
				Car car1 = new Car();
				car1 = cars.get(i);
				if(Integer.parseInt(regNum)== car1.getRegNum()){
					System.out.println(car1.getRegNum() + " Deleted");
					cars.remove(car1);
					DefaultTableModel model = (DefaultTableModel)this.table_1.getModel();
					model.removeRow(rowIndex);
				}
				writeToFile(cars,"files/car.dat");
			}

		}
		else if(value.equals("MiniBus")){
			//read from binary file
			cars = readFromFile(miniBuses,"files/minibus.dat");
			for (int i = 0; i < miniBuses.size(); i++) {
				MiniBus miniBus = new MiniBus();
				miniBus = miniBuses.get(i);
				if(Integer.parseInt(regNum)== miniBus.getRegNum()){
					System.out.println(miniBus.getRegNum() + " Deleted");
					miniBuses.remove(miniBus);
					DefaultTableModel model = (DefaultTableModel)this.table_1.getModel();
					model.removeRow(rowIndex);
				}
				writeToFile(miniBuses,"files/miniBus.dat");
			}

		}
		else if(value.equals("Lorry")){
			//read from binary file
			lorries = readFromFile(lorries,"files/lorry.dat");
			for (int i = 0; i < lorries.size(); i++) {
				Lorry lorry = new Lorry();
				lorry = lorries.get(i);
				if(Integer.parseInt(regNum)== lorry.getRegNum()){
					System.out.println(lorry.getRegNum()+ " Deleted");
					lorries.remove(lorry);
					DefaultTableModel model = (DefaultTableModel)this.table_1.getModel();
					model.removeRow(rowIndex);
				}
				writeToFile(cars,"files/lorry.dat");
			}

		}
			else{
			JOptionPane.showMessageDialog(frame,"error");

		}

	}
	//Add vehicle methods
	private void addCar() {
		try{
			Car car = new Car();
			car.setRegNum(Integer.parseInt(regNum_tf.getText()));
			car.setMake(make_tf.getText());
			car.setModel(model_tf.getText());
			car.setVehicleType(String.valueOf(vehicleType_cb.getSelectedItem()));
			car.setTopSpeed(Integer.parseInt(topSpeed_tf.getText()));
			car.setFuelType(String.valueOf(fuelType_cb.getSelectedItem()));
			car.setHireRate(Double.parseDouble(hireRate_tf.getText()));
			car.setNumOfDoors(Integer.parseInt(NumDoors_tf.getText()));
			//Add the contents into car arraylist
			cars.add(car);

			//Add car arraylist to car file
			writeToFile(cars,"files/car.dat");
		DefaultTableModel model = (DefaultTableModel)this.table_1.getModel();
		model.addRow(new Object[]{car.getRegNum(), car.getMake(), car.getModel(), this.vehicleType_cb.getSelectedItem(), car.getTopSpeed(), car.getFuelType(), car.getHireRate(), car.getNumOfDoors(),"--","--"});
	} catch (Exception e) {
		JOptionPane.showMessageDialog(frame,"Please fill in all the fields","Error", JOptionPane.INFORMATION_MESSAGE);
	}}
	private void addMiniBus() {
		try{
			MiniBus miniBus = new MiniBus();
			miniBus.setRegNum(Integer.parseInt(regNum_tf.getText()));
			miniBus.setMake(make_tf.getText());
			miniBus.setModel(model_tf.getText());
			miniBus.setVehicleType(String.valueOf(vehicleType_cb.getSelectedItem()));
			miniBus.setTopSpeed(Integer.parseInt(topSpeed_tf.getText()));
			miniBus.setFuelType(String.valueOf(fuelType_cb.getSelectedItem()));
			miniBus.setHireRate(Double.parseDouble(hireRate_tf.getText()));
			miniBus.setNumOfDoors(Integer.parseInt(NumDoors_tf.getText()));
			miniBus.setSeatingCapacity(Integer.parseInt(seatingCap_tf.getText()));
			//Add the contents into car arraylist
			miniBuses.add(miniBus);

			//Add car arraylist to minibus file
			writeToFile(miniBuses,"files/minibus.dat");
		DefaultTableModel model = (DefaultTableModel)this.table_1.getModel();
		model.addRow(new Object[]{miniBus.getRegNum(), miniBus.getMake(), miniBus.getModel(), this.vehicleType_cb.getSelectedItem(), miniBus.getTopSpeed(), miniBus.getFuelType(), miniBus.getHireRate(), miniBus.getNumOfDoors(), miniBus.getSeatingCapacity()});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Please fill in all the fields","Error", JOptionPane.INFORMATION_MESSAGE);
		}}
	private void addLorry() {
		try {
			Lorry lorry = new Lorry();
			lorry.setRegNum(Integer.parseInt(regNum_tf.getText()));
			lorry.setMake(make_tf.getText());
			lorry.setModel(model_tf.getText());
			lorry.setVehicleType(String.valueOf(vehicleType_cb.getSelectedItem()));
			lorry.setTopSpeed(Integer.parseInt(topSpeed_tf.getText()));
			lorry.setFuelType(String.valueOf(fuelType_cb.getSelectedItem()));
			lorry.setHireRate(Double.parseDouble(hireRate_tf.getText()));
			lorry.setNumOfDoors(Integer.parseInt(NumDoors_tf.getText()));
			lorry.setLoadCapacity(Integer.parseInt(loadCap_tf.getText()));
			//Add the contents into lorry arraylist
			lorries.add(lorry);
			//Add car arraylist to lorry file
			writeToFile(lorries, "files/lorry.dat");
			DefaultTableModel model = (DefaultTableModel) this.table_1.getModel();
			model.addRow(new Object[]{lorry.getRegNum(), lorry.getMake(), lorry.getModel(), this.vehicleType_cb.getSelectedItem(), lorry.getTopSpeed(), lorry.getFuelType(), lorry.getHireRate(), lorry.getNumOfDoors(), "--", lorry.getLoadCapacity()});
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame,"Please fill in all the fields","Error", JOptionPane.INFORMATION_MESSAGE);
		}
	}

//Function to display vehicles
	private void loadVehicles() {
		try {
			cars = readFromFile(cars, "files/car.dat");
			for (int i = 0; i < cars.size(); i++) {
				Car car1 = new Car();
				car1 = cars.get(i);
				vehiclesTableModel.addRow(new Object[]{
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
		}catch (ClassCastException c){
			System.out.println("No cars currently in storage");
		}
try{
		miniBuses = readFromFile(miniBuses, "files/minibus.dat");
		for (int i = 0; i < miniBuses.size(); i++) {
			MiniBus minibus = new MiniBus();
			minibus = miniBuses.get(i);
			vehiclesTableModel.addRow(new Object[]{
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
		}}catch (ClassCastException c){
	System.out.println("No mini bus currently in storage");
}
			try {
				lorries = readFromFile(lorries, "files/lorry.dat");
				for (int i = 0; i < lorries.size(); i++) {
					Lorry lorry = new Lorry();
					lorry = lorries.get(i);
					vehiclesTableModel.addRow(new Object[]{
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
			catch (ClassCastException c){
				System.out.println("No lorries currently in storage");
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
			System.out.println("File doesn't exist");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}



