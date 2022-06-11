import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Admin.*;
import Customer.*;


public class Login {

	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	ArrayList<Customer> customers = new ArrayList();

	public Login() {
		initialize();
	}

	public void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBackground(Color.LIGHT_GRAY);
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameLabel.setBounds(102, 72, 96, 31);
		frame.getContentPane().add(usernameLabel);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBackground(Color.LIGHT_GRAY);
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordLabel.setBounds(102, 139, 96, 31);
		frame.getContentPane().add(passwordLabel);

		usernameField = new JTextField();
		usernameField.setBackground(Color.GRAY);
		usernameField.setForeground(new Color(255, 255, 255));
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		usernameField.setBounds(234, 66, 160, 33);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setForeground(new Color(255, 255, 255));
		passwordField.setBackground(Color.GRAY);
		passwordField.setBounds(234, 137, 160, 33);
		frame.getContentPane().add(passwordField);

		JButton loginBtn = new JButton("Login");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (usernameField.getText().isEmpty() == false && passwordField.getText().isEmpty() == false) {
					checkLoginDetails();
				} else {
					JOptionPane.showMessageDialog(frame, "enter username and password first");
				}
			}
		});
		loginBtn.setForeground(new Color(255, 255, 255));
		loginBtn.setBackground(Color.GRAY);
		loginBtn.setBounds(177, 199, 111, 31);
		frame.getContentPane().add(loginBtn);

		JLabel lblNewLabel = new JLabel("Northampton Car Rental Servie");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel.setBounds(102, 10, 292, 46);
		frame.getContentPane().add(lblNewLabel);

		frame.setVisible(true);
	}
//Function to check login details
	private void checkLoginDetails() {
		String username = usernameField.getText();
		String password = passwordField.getText();
		//check for admin
		if (username.equals("admin") && password.equals("admin")) {
			AdminDashboard adminDashboard = new AdminDashboard();
			frame.dispose();
		} else {
			try {
				//read from file customers
				FileInputStream fis = new FileInputStream("files/customer.dat");
				ObjectInputStream ois = new ObjectInputStream(fis);
				customers = (ArrayList<Customer>) ois.readObject();


				for (int i = 0; i < customers.size(); i++) {
					Customer customer = new Customer();
					customer = customers.get(i);
					//check customer credentials
					if (username.contains(customer.getUsername()) && password.contains(customer.getPassword())) {
						CustomerDashboard customerDashboard = new CustomerDashboard(customer.getId());
						frame.dispose();
						break;
					} else if (i == customers.size() - 1) {
						JOptionPane.showMessageDialog(frame, "Invalid login details");
						usernameField.setText(null);
						passwordField.setText(null);
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Customer.dat file does not exist");
				;
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
