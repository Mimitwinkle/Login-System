import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class WelcomePage {
	
	// instantiate frame & label
	JFrame frame = new JFrame();
	JLabel welcomeLabel;
	
	WelcomePage(String userID) {
		// set position, size & font for label
		welcomeLabel.setBounds(0,0,200,35);
		welcomeLabel.setFont(new Font(null,Font.PLAIN,25));
		welcomeLabel.setText("Hello, " + userID);
		
		// add label
		// make frame exit on close, set size, & make visible
		frame.add(welcomeLabel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420,420);
		frame.setLayout(null);
		frame.setVisible(true);
	}
}
