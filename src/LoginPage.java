import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
	
	// initialize global TreeMap
	Map<String, String> loginInfo = new TreeMap<>();
	
	LoginPage(Map<String, String> loginInfoOriginal) {
		// create copy of original TreeMap with login info
		// copy is globally available within login page
		loginInfo = loginInfoOriginal;
		
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
			String userID = userIDField.getText();
			// gets input from password field, converts to string & stores in a string
			String password = String.valueOf(userPasswordField.getPassword());
			
			if(loginInfo.containsKey(userID)) { // if userID input is a key in loginInfo
				if(loginInfo.get(userID).equals(password)) { // & if password input is the correct value
					// display success message
					messageLabel.setForeground(new Color(0,102,0));
					messageLabel.setText("Login successful");
					
					// close login page
					frame.dispose();
					
					// open welcome page
					// pass userID as an argument so it can be used on the welcome page
					WelcomePage welcomePage = new WelcomePage(userID);
				}
				else { // if key is correct, but password is not
					// display message
					messageLabel.setForeground(new Color(153,0,0));
					messageLabel.setText("Wrong password");
				}
			}
			else { // if neither ID nor password are correct
				// display message
				messageLabel.setForeground(new Color(153,0,0));
				messageLabel.setText("userID not found");
			}
			
		}
	}
}
