import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

public class LoginPage implements ActionListener {
	
	// initialize interface
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("userID:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel();
	
	LoginPage() {
		
		// set positions & sizes of labels & fields
		userIDLabel.setBounds(50,100,200,25);
		userPasswordLabel.setBounds(50,150,200,25);
		
		messageLabel.setBounds(125,250,250,35);
		messageLabel.setFont(new Font(null,Font.ITALIC,25));
		
		userIDField.setBounds(125,100,200,25);
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
		
		// add everything to frame, make frame visible & exit on close
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the event occurs on the reset button:
		if(e.getSource()==resetButton) {
			// reset fields to blank
			userIDField.setText("");
			userPasswordField.setText("");
		}
		// if the event occurs on the log:
		if(e.getSource()==loginButton) {
			// retrieve input from fields
			String username = userIDField.getText();
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
				// pass userID as an argument so it can be used on the welcome page
				WelcomePage welcomePage = new WelcomePage(username);
			}
			else {
				// if query is not successful:
				// display message
				messageLabel.setForeground(new Color(153,0,0));
				messageLabel.setText("userID or password is incorrect");
			}
			
		}
	}
	
	public boolean tryLogin(String username, String password) {
		try {
			Connection connection = JDBC.getConnection();		
			String query = 
					"SELECT username, password FROM java_demo.users "
					+ "WHERE username = ?"
					+ " AND password = ?";
			
			PreparedStatement prepStatement = connection.prepareStatement(query);
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			ResultSet resultSet = prepStatement.executeQuery();
			
			while (resultSet.next()) {
				username = resultSet.getString("username");
				password = resultSet.getString("password");
				return true;
			}
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(frame, "Database error: " + e.getMessage());
		}
		
		return false;
	}
	
	
}
