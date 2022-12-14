package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Student;
import connec.Connect;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JLabel jlUser, jlPassword;
	public static JLabel lblIncorrect;
	private JLabel lblUserNotFound;
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

		jbLogin = new JButton();
		WindowPreset.buttonPreset(jbLogin, "Login", "files\\login1.png");
		jbLogin.setBounds(90, 135, 26, 26);

		jbRegister = new JButton();
		WindowPreset.buttonPreset(jbRegister, "Register", "files\\register.png");
		jbRegister.setBounds(165, 135, 26, 26);

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

	// Driver to login 	 and Students
	public class driverLogin implements ActionListener {
		@SuppressWarnings("deprecation")
		@Override
		public void actionPerformed(ActionEvent e) {
			String password = new String(jpPassword.getPassword());
			lblIncorrect.hide();
			lblUserNotFound.hide();
			try {
				String status = c.searchUser(jtUser.getText().toString(), password);
				if (jtUser.getText().equals("admin") && password.equals("admin")) {
					dispose();
					@SuppressWarnings("unused")
					AdminWindow aw = new AdminWindow();
				}
				if(status.equalsIgnoreCase(""))
					lblUserNotFound.show();
				if(status.equalsIgnoreCase("wrongpass"))
					lblIncorrect.show();
				if(status.equalsIgnoreCase("tloged")) {
					setVisible(false);
					TeacherView.dniTeacher = jtUser.getText();
					new TeacherView();
				}
				if(status.equalsIgnoreCase("sloged")) {
					setVisible(false);
					new StudentView(jtUser.getText().toString());
				}
				
	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

	// Driver to Register
	public class driverRegister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new RegisterStudent();
			} catch (SQLException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
