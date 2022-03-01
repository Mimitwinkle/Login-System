import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPage implements ActionListener {
	// instantiate frame & label
	JFrame frame = new JFrame();
	
	// buttons
	JButton createUserButton = new JButton("Create");
	JButton resetButton = new JButton("Reset");
	JButton closeButton = new JButton("Close");
	
	// fields and labels
	JTextField usernameField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JTextField userEmailField = new JTextField();
	JLabel usernameLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel emailLabel = new JLabel("Email:");
	JLabel directionLabel = new JLabel("Create a new user:");
	JLabel messageLabel = new JLabel();
	
	RegisterPage() {
		
		// set positions & sizes of buttons
		// add event listeners to buttons
		// prevent from being focusable (removes little box that appears when clicked
		createUserButton.setBounds(125,250,100,25);
		createUserButton.addActionListener(this);
		createUserButton.setFocusable(false);
		resetButton.setBounds(225,250,100,25);
		resetButton.addActionListener(this);
		resetButton.setFocusable(false);
		closeButton.setBounds(125, 290, 200, 25);
		closeButton.addActionListener(this);
		closeButton.setFocusable(false);
		
		
		// fields
		usernameField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		userEmailField.setBounds(125,200,200,25);
		// labels
		usernameLabel.setBounds(50,100,200,25);
		userPasswordLabel.setBounds(50,150,200,25);
		emailLabel.setBounds(50,200,200,25);
		directionLabel.setBounds(50,50,200,25);
		directionLabel.setFont(new Font(null,Font.ITALIC,20));
		messageLabel.setBounds(125,325,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,20));
		
		// make frame exit on close, set size, & make visible
		frame.add(createUserButton);
		frame.add(resetButton);
		frame.add(closeButton);
		frame.add(usernameField);
		frame.add(userPasswordField);
		frame.add(userEmailField);
		frame.add(usernameLabel);
		frame.add(userPasswordLabel);
		frame.add(emailLabel);
		frame.add(directionLabel);
		frame.add(messageLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==resetButton) {
			// reset fields to blank
			usernameField.setText("");
			userPasswordField.setText("");
		}
		
		if(e.getSource()==createUserButton) {
			// retrieve input from fields
			String username = usernameField.getText();
			// gets input from password field, converts to string & stores in a string
			String password = String.valueOf(userPasswordField.getPassword());
			// retrieve input from email field
			String email = userEmailField.getText();
			
			// this method will crate a new user
			createUser(username, password, email);
			
		}
		// if the "close" button is clicked
		if(e.getSource()==closeButton) {
			// closes registration page
			frame.dispose();
		}
	}
	
	protected void createUser(String username, String password, String email) {
		// first, check if username already exists:
		// if username exists, show message & break out of method
		try {
			Connection connection = JDBC.getConnection();		
			String query = 
					"SELECT username FROM java_demo.users "
					+ "WHERE username = '" + username + "'";
			
			PreparedStatement prepStatement = connection.prepareStatement(query);
			//prepStatement.setString(1, username);
			ResultSet resultSet = prepStatement.executeQuery();
			
			while (resultSet.next()) {
				username = resultSet.getString("username");
				// show message
				messageLabel.setForeground(new Color(153,0,0));
				messageLabel.setText("Username already exists");
				return;
			}
			
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
			messageLabel.setForeground(new Color(153,0,0));
			messageLabel.setText("Database error");
		}
		
		
		// if username does not exist, create new user
		try {
			Connection connection = JDBC.getConnection();		
			String update = "INSERT INTO java_demo.users (username, password, email) "
					+ "VALUES ('" + username + "' , '" + password + "' , '" + email + "')";
			
			PreparedStatement prepStatement = connection.prepareStatement(update);
			
			// I had issues using the below method of inserting the variables with question marks. I'll try to figure it out later.
			//prepStatement.setString(1, username);
			//prepStatement.setString(2, password);
			//prepStatement.setString(3, email);
			
			prepStatement.executeUpdate(update);
			
			// show message
			messageLabel.setForeground(new Color(0,102,0));
			messageLabel.setText("New user created");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
			messageLabel.setForeground(new Color(153,0,0));
			messageLabel.setText("Database error");
		}
		
	}
	
	
	// check if email is valid
	
	
	
}