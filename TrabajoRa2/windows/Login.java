package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JLabel jlUser, jlPassword;
	private JTextField jtUser;
	private JPasswordField jpPassword;
	private JButton jbLogin, jbRegister;

	public Login() {
		super("Login");
		setSize(300, 250);
		WindowPreset.preset(this);
		setLayout(null);
		

		jlUser = new JLabel("Username: ");
		jlUser.setBounds(31, 44, 90, 19);
		jtUser = new JTextField(10);
		jtUser.setBounds(121, 44, 143, 19);
		jtUser.setToolTipText("Enter Username");

		jlPassword = new JLabel("Password: ");
		jlPassword.setBounds(31, 76, 90, 19);
		jpPassword = new JPasswordField(10);
		jpPassword.setBounds(121, 76, 143, 19);
		jpPassword.setToolTipText("Enter Password");

		jbLogin = new JButton("Login");
		jbLogin.setBounds(40, 135, 100, 20);

		jbRegister = new JButton("Register");
		jbRegister.setBounds(160, 135, 100, 20);

		add(jlUser);
		add(jtUser);
		add(jlPassword);
		add(jpPassword);
		add(jbLogin);
		add(jbRegister);

		
		//addActionListeners
		driverRegister register = new driverRegister();
		jbRegister.addActionListener(register);
		
		driverLogin login = new driverLogin();
		jbLogin.addActionListener(login);

		
		setVisible(true);

	}
	
	
	//Driver to register
	public class driverRegister implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
		}
		
	}
	
	//Driver to login
	public class driverLogin implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}


	
	
	
}
