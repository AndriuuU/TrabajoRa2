package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.*;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import clases.Student;
import connec.Connect;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class RegisterStudent extends JFrame {
	private JTextField jtDni;
	private JTextField jtName;
	private JTextField jtSurname;
	private JTextField jtEmail;
	private JTextField jtTelefono;
	private Connect c = new Connect();
	private JButton btnImg;
	private JButton btnAgregar;
	private JDateChooser date;

	private JLabel lblImg;
	private JLabel lblTlf;
	private JLabel lblDate;
	private JLabel lblEmail;
	private JLabel lblSurnames;
	private JLabel lblName;
	private JLabel lblDni;
	private JLabel lblFoto;
	private JLabel lblPass;
	private JPasswordField jtPass;

	public RegisterStudent() {

		super("Register");
		setSize(600, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(130, 57, 25, 14);
		getContentPane().add(lblDni);

		jtDni = new JTextField();
		jtDni.setBounds(171, 56, 189, 20);
		getContentPane().add(jtDni);
		jtDni.setColumns(10);

		lblName = new JLabel("NOMBRE");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(101, 94, 54, 14);
		getContentPane().add(lblName);

		jtName = new JTextField();
		jtName.setBounds(171, 93, 189, 20);
		getContentPane().add(jtName);
		jtName.setColumns(10);

		lblSurnames = new JLabel("APELLIDOS");
		lblSurnames.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurnames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSurnames.setBounds(85, 130, 70, 14);
		getContentPane().add(lblSurnames);

		jtSurname = new JTextField();
		jtSurname.setBounds(171, 128, 189, 20);
		getContentPane().add(jtSurname);
		jtSurname.setColumns(10);

		lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(109, 163, 46, 14);
		getContentPane().add(lblEmail);

		jtEmail = new JTextField();
		jtEmail.setBounds(171, 161, 189, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);

		lblDate = new JLabel("FECHA NACIMIENTO");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(15, 233, 140, 14);
		getContentPane().add(lblDate);

		date = new JDateChooser();
		date.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		date.setDateFormatString("yyyy/dd/MM");
		date.setBounds(171, 227, 120, 20);
		getContentPane().add(date);

		lblTlf = new JLabel("TELEFONO");
		lblTlf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTlf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTlf.setBounds(85, 198, 71, 14);
		getContentPane().add(lblTlf);

		jtTelefono = new JTextField();
		jtTelefono.setBounds(171, 196, 120, 20);
		getContentPane().add(jtTelefono);
		jtTelefono.setColumns(10);

		lblImg = new JLabel("ADD IMAGEN");
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImg.setBounds(347, 233, 92, 14);
		getContentPane().add(lblImg);

		btnImg = new JButton("Picture");
		btnImg.setBounds(449, 231, 89, 23);
		getContentPane().add(btnImg);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregar.setBounds(239, 309, 110, 23);
		getContentPane().add(btnAgregar);

		lblFoto = new JLabel("Foto");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(432, 56, 128, 156);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblFoto);

		lblPass = new JLabel("CONTRASEÑA");
		lblPass.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setBounds(66, 267, 89, 14);
		getContentPane().add(lblPass);

		jtPass = new JPasswordField();
		jtPass.setBounds(171, 266, 189, 20);
		getContentPane().add(jtPass);

		driverAdd dAgregar = new driverAdd();
		btnAgregar.addActionListener(dAgregar);

		setVisible(true);
	}

	public class driverAdd implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat sdf = new SimpleDateFormat(date.getDateFormatString());
			String dni = jtDni.getText().toString();
			String name = jtName.getText().toString();
			String surnames = jtSurname.getText().toString();
			String email = jtEmail.getText().toString();
			String date_birth = sdf.format(date.getDate());
			String pic = "foto";
			String valorPass = new String(jtPass.getPassword());
			int telefono = Integer.parseInt(jtTelefono.getText().toString());
			Student s = new Student(dni, name, surnames, email, date_birth, pic, telefono, valorPass);
			//System.out.println(s.toString());
			c.insertStudent(s);

		}

	}
}
