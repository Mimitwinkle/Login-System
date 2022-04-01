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

public class LoginPage implements ActionListener {
	
	// initialize interface
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JButton newUserButton = new JButton("New User");
	JTextField usernameField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel usernameLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel();
	
	LoginPage() {
		
		// set positions & sizes of labels & fields
		usernameLabel.setBounds(50,100,200,25);
		userPasswordLabel.setBounds(50,150,200,25);
		
		messageLabel.setBounds(125,275,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		usernameField.setBounds(125,100,200,25);
		userPasswordField.setBounds(125,150,200,25);
		
		// set positions & sizes of buttons
		// add event listeners to buttons
		// prevent from being focusable (removes little box that appears when clicked
		loginButton.setBounds(125,200,100,25);
		loginButton.addActionListener(this);
		loginButton.setFocusable(false);
		resetButton.setBounds(225,200,100,25);
		resetButton.addActionListener(this);
		resetButton.setFocusable(false);
		newUserButton.setBounds(125, 240, 200, 25);
		newUserButton.addActionListener(this);
		newUserButton.setFocusable(false);
		
		// add everything to frame, make frame visible & exit on close
		frame.add(usernameLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(usernameField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.add(newUserButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the "reset" button is clicked
		if(e.getSource()==resetButton) {
			// reset fields to blank
			usernameField.setText("");
			userPasswordField.setText("");
		}
		// if the "login" button is clicked
		if(e.getSource()==loginButton) {
			// retrieve input from fields
			String username = usernameField.getText();
			// gets input from password field, converts to string & stores in a string
			String password = String.valueOf(userPasswordField.getPassword());
			
			
			// attempt login using input strings
			// call method which sends query to database
			if (tryLogin(username, password)) {
				// display success message
				messageLabel.setForeground(new Color(0,102,0));
				messageLabel.setText("Login successful");
				
				// closes login page
				frame.dispose();
				
				// open welcome page
				// pass username as an argument so it can be used on the welcome page
				WelcomePage welcomePage = new WelcomePage(username);
			}
			else {
				// if query is not successful:
				// display message
				messageLabel.setForeground(new Color(153,0,0));
				messageLabel.setText("username or password is incorrect");
			}
		}
		// if the "new user" button is clicked
		if(e.getSource()==newUserButton) {
			// open user registration frame
			RegisterPage registerPage = new RegisterPage();
		}
	}
	
	protected boolean tryLogin(String username, String password) {
		Connection connection = null;
		try {
			connection = JDBC.getConnection();		
			String query = 
					"SELECT username, password FROM java_demo.users "
					+ "WHERE username = ?"
					+ " AND password = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(query);
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			ResultSet resultSet = prepStatement.executeQuery();
			
			// if there is a row of data in the table where username & password match the user input, return true
			while (resultSet.next()) {
				username = resultSet.getString("username");
				password = resultSet.getString("password");
				return true;
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
		}
		// close database resources
		finally {
		    try { if (connection != null) connection.close(); } catch (Exception e) {};
		}
		// if no matching username & password are found, return false
		return false;
	}
	
}
