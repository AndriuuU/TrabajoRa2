package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import connec.Connect;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JLabel jlUser, jlPassword, lblIncorrect, lblUserNotFound;
	private JTextField jtUser;
	private JPasswordField jpPassword;
	private JButton jbLogin, jbRegister;
	private Connect c = new Connect();

	@SuppressWarnings("deprecation")
	public Login() {
		super("Login");
		setSize(300, 250);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		jlUser = new JLabel("Username: ");
		jlUser.setBounds(31, 44, 90, 19);
		jtUser = new JTextField(10);
		jtUser.setBounds(121, 44, 143, 19);
		jtUser.setToolTipText("Enter Username");

		jlPassword = new JLabel("Password: ");
		jlPassword.setBounds(31, 88, 90, 19);
		jpPassword = new JPasswordField(10);
		jpPassword.setBounds(121, 87, 143, 19);
		jpPassword.setToolTipText("Enter Password");

		jbLogin = new JButton("Login");
		jbLogin.setBounds(40, 135, 100, 20);

		jbRegister = new JButton("Register");
		jbRegister.setBounds(160, 135, 100, 20);

		getContentPane().add(jlUser);
		getContentPane().add(jtUser);
		getContentPane().add(jlPassword);
		getContentPane().add(jpPassword);
		getContentPane().add(jbLogin);
		getContentPane().add(jbRegister);

		lblIncorrect = new JLabel("Incorrect Password");
		lblIncorrect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblIncorrect.setForeground(new Color(255, 0, 0));
		lblIncorrect.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncorrect.setBounds(121, 110, 110, 14);
		lblIncorrect.hide();
		;
		getContentPane().add(lblIncorrect);

		lblUserNotFound = new JLabel("USER NOT FOUND");
		lblUserNotFound.setForeground(new Color(255, 0, 0));
		lblUserNotFound.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserNotFound.setBounds(121, 63, 143, 14);
		lblUserNotFound.hide();
		getContentPane().add(lblUserNotFound);

		// addActionListeners
		driverRegister register = new driverRegister();
		jbRegister.addActionListener(register);

		driverLogin login = new driverLogin();
		jbLogin.addActionListener(login);

		setVisible(true);

	}

	// Driver to login Teacher and Students
	public class driverLogin implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			String password = new String(jpPassword.getPassword());
			lblIncorrect.hide();
			lblUserNotFound.hide();
			try {
				c.searchUser(jtUser.getText().toString(), password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			try {
				if (c.searchUser(jtUser.getText().toString(), password).equalsIgnoreCase("studentaccepted")) {
					new RaStudentView();
					getContentPane().hide();
				} else if (c.searchUser(jtUser.getText().toString(), password).equalsIgnoreCase("teacheraccepted")) {
					new RaProfessorView();
					getContentPane().hide();
				} else if (c.searchUser(jtUser.getText().toString(), password).equalsIgnoreCase("notaccepted")) {
					lblIncorrect.show();
				}else if (c.searchUser(jtUser.getText().toString(), password).equalsIgnoreCase("")) {
					lblUserNotFound.show();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// Driver to login
	public class driverRegister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}
}
