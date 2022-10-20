package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Teacher;
import connec.Connect;
import java.awt.Color;

@SuppressWarnings("serial")
public class RegisterTeacher extends JFrame {
	private JTextField jtDni;
	private JTextField jtName;
	private JTextField jtSurname;
	private JTextField jtEmail;
	private JPasswordField jpPassword;
	private Connect c = new Connect();
	private JLabel lblInvalidDNI ;

	public RegisterTeacher() {

		super("Register");
		setSize(500, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(76, 44, 82, 14);
		getContentPane().add(lblDni);

		jtDni = new JTextField();
		jtDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtDni.setBounds(190, 43, 124, 20);
		getContentPane().add(jtDni);
		jtDni.setColumns(10);

		JLabel lblNombre = new JLabel("NAME:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(76, 95, 82, 14);
		getContentPane().add(lblNombre);

		jtName = new JTextField();
		jtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtName.setBounds(190, 94, 124, 20);
		getContentPane().add(jtName);
		jtName.setColumns(10);

		JLabel lblApellidos = new JLabel("SURNAMES:");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(76, 148, 82, 14);
		getContentPane().add(lblApellidos);

		jtSurname = new JTextField();
		jtSurname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtSurname.setBounds(190, 147, 124, 20);
		getContentPane().add(jtSurname);
		jtSurname.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(76, 259, 82, 14);
		getContentPane().add(lblPassword);

		jpPassword = new JPasswordField();
		jpPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jpPassword.setBounds(190, 258, 124, 20);
		getContentPane().add(jpPassword);

		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(76, 201, 82, 14);
		getContentPane().add(lblEmail);

		jtEmail = new JTextField();
		jtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtEmail.setBounds(190, 200, 124, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);

		JButton btnRegister = new JButton();
		WindowPreset.buttonPreset(btnRegister, "Confirm", "files\\confirm.png");
		// btnRegister.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRegister.setBounds(237, 301, 26, 26);
		getContentPane().add(btnRegister);
		
		lblInvalidDNI = new JLabel("INVALID DNI");
		lblInvalidDNI.setForeground(new Color(255, 0, 0));
		lblInvalidDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInvalidDNI.setBounds(331, 46, 82, 14);
		lblInvalidDNI.setVisible(false);
		getContentPane().add(lblInvalidDNI);

		driverRegister dvRegister = new driverRegister();
		btnRegister.addActionListener(dvRegister);

		setVisible(true);
	}

	public class driverRegister implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			lblInvalidDNI.setVisible(false);
			String dni = jtDni.getText().toString();
			String name = jtName.getText().toString();
			String surname = jtSurname.getText().toString();
			String email = jtEmail.getText().toString();
			String password = new String(jpPassword.getPassword());
			
			if (Detector(dni, name, surname, email,password) == true) {
			Teacher t = new Teacher(dni, name, surname, email, password);
			c.insertTeacher(t);
			}
		}

	}
	
	public boolean Detector(String dni, String name, String surnames, String email,
			String valorPass) {
		boolean passed = true;
		if (dni.equalsIgnoreCase("") || name.equalsIgnoreCase("") || surnames.equalsIgnoreCase("")
				|| email.equalsIgnoreCase("") || valorPass.equalsIgnoreCase("") ) {
			passed = false;
			JOptionPane.showMessageDialog(null, "NO PUEDE HABER CAMPOS VACIOS", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		String dniRegexp = "\\d{8}[a-zA-Z]";
		if(Pattern.matches(dniRegexp, dni)==false) {
			lblInvalidDNI.setVisible(true);
			passed = false;
		}
		return passed;
	}
}
