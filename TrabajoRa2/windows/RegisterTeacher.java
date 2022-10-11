package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class RegisterTeacher extends JFrame{
	private JTextField jtDni;
	private JTextField jtName;
	private JTextField jtSurname;
	private JTextField jtEmail;
	private JPasswordField jpPassword;
	public RegisterTeacher() {

		super("Register GitHub");
		setSize(500, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(76, 44, 82, 14);
		getContentPane().add(lblDni);
		
		jtDni = new JTextField();
		jtDni.setBounds(190, 43, 124, 20);
		getContentPane().add(jtDni);
		jtDni.setColumns(10);
		
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(76, 95, 82, 14);
		getContentPane().add(lblNombre);
		
		jtName = new JTextField();
		jtName.setBounds(190, 94, 124, 20);
		getContentPane().add(jtName);
		jtName.setColumns(10);
		
		JLabel lblApellidos_1 = new JLabel("APELLIDOS");
		lblApellidos_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos_1.setBounds(76, 148, 82, 14);
		getContentPane().add(lblApellidos_1);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(76, 259, 82, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(76, 201, 82, 14);
		getContentPane().add(lblEmail);
		
		jtSurname = new JTextField();
		jtSurname.setBounds(190, 147, 124, 20);
		getContentPane().add(jtSurname);
		jtSurname.setColumns(10);
		
		jtEmail = new JTextField();
		jtEmail.setBounds(190, 200, 124, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);
		
		jpPassword = new JPasswordField();
		jpPassword.setBounds(190, 258, 124, 20);
		getContentPane().add(jpPassword);
		
		JButton btnNewButton = new JButton("REGISTRAR");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(190, 311, 124, 23);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
}
