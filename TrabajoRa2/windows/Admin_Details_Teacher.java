package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Teacher;

@SuppressWarnings("serial")
public class Admin_Details_Teacher extends JFrame {
	private JTextField jtDni;
	private JTextField jtName;
	private JTextField jtSurname;
	private JTextField jtEmail;
	private JPasswordField jpPassword;

	public Admin_Details_Teacher(Teacher s) {

		super("Admin_Details_Teacher");
		setSize(500, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		JLabel lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(76, 44, 82, 14);
		getContentPane().add(lblDni);

		jtDni = new JTextField();
		jtDni.setText(s.getDni());
		jtDni.setEditable(false);
		jtDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtDni.setBounds(190, 43, 124, 20);
		getContentPane().add(jtDni);
		jtDni.setColumns(10);

		JLabel lblNombre = new JLabel("NAME:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(76, 95, 82, 14);
		getContentPane().add(lblNombre);

		jtName = new JTextField();
		jtName.setText(s.getName());
		jtName.setEditable(false);
		jtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtName.setBounds(190, 94, 124, 20);
		getContentPane().add(jtName);
		jtName.setColumns(10);

		JLabel lblApellidos = new JLabel("SURNAMES:");
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(76, 148, 82, 14);
		getContentPane().add(lblApellidos);

		jtSurname = new JTextField();
		jtSurname.setText(s.getSurname());
		jtSurname.setEditable(false);
		jtSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtSurname.setBounds(190, 147, 124, 20);
		getContentPane().add(jtSurname);
		jtSurname.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(76, 259, 82, 14);
		getContentPane().add(lblPassword);

		jpPassword = new JPasswordField();
		jpPassword.setText(s.getPasswd());
		jpPassword.setEditable(false);
		jpPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jpPassword.setBounds(190, 258, 124, 20);
		getContentPane().add(jpPassword);

		JLabel lblEmail = new JLabel("EMAIL:");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(76, 201, 82, 14);
		getContentPane().add(lblEmail);

		jtEmail = new JTextField();
		jtEmail.setText(s.getEmail());
		jtEmail.setEditable(false);
		jtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtEmail.setBounds(190, 200, 124, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);

		JButton btReturn = new JButton();
		btReturn.setBounds(90, 301, 85, 25);
		WindowPreset.buttonPreset(btReturn, "Back to menu", "files\\return.png");
		btReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		getContentPane().add(btReturn);

		setVisible(true);
	}

}
