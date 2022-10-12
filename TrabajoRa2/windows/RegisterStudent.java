package windows;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.text.*;
import java.util.ArrayList;

import java.util.List;


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
	private JButton btnSubjectsView;
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

	public static List<String> listOfSubjects = new ArrayList<String>();
	public RegisterStudent() throws SQLException {

		super("Register");
		setSize(630, 430);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(22, 46, 28, 14);
		getContentPane().add(lblDni);

		jtDni = new JTextField();
		jtDni.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtDni.setBounds(60, 44, 92, 20);
		getContentPane().add(jtDni);
		jtDni.setColumns(10);

		lblName = new JLabel("NOMBRE:");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(22, 86, 66, 14);
		getContentPane().add(lblName);

		jtName = new JTextField();
		jtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtName.setBounds(98, 84, 99, 20);
		getContentPane().add(jtName);
		jtName.setColumns(10);

		lblSurnames = new JLabel("APELLIDOS:");
		lblSurnames.setHorizontalAlignment(SwingConstants.LEFT);
		lblSurnames.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSurnames.setBounds(207, 86, 75, 14);
		getContentPane().add(lblSurnames);

		jtSurname = new JTextField();
		jtSurname.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtSurname.setBounds(294, 84, 120, 20);
		getContentPane().add(jtSurname);
		jtSurname.setColumns(10);

		lblEmail = new JLabel("EMAIL:");
		lblEmail.setHorizontalAlignment(SwingConstants.LEFT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(22, 173, 49, 14);
		getContentPane().add(lblEmail);

		jtEmail = new JTextField();
		jtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtEmail.setBounds(81, 171, 189, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);

		lblDate = new JLabel("FECHA NACIMIENTO:");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(22, 133, 140, 14);
		getContentPane().add(lblDate);

		date = new JDateChooser();
		date.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		date.setDateFormatString("yyyy/dd/MM");
		date.setBounds(175, 127, 128, 20);
		getContentPane().add(date);

		lblTlf = new JLabel("TELEFONO:");
		lblTlf.setHorizontalAlignment(SwingConstants.LEFT);
		lblTlf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTlf.setBounds(22, 222, 82, 14);
		getContentPane().add(lblTlf);

		jtTelefono = new JTextField();
		jtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtTelefono.setBounds(114, 220, 80, 20);
		getContentPane().add(jtTelefono);
		jtTelefono.setColumns(10);

		lblImg = new JLabel("ADD IMAGEN");
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImg.setBounds(470, 251, 92, 14);
		getContentPane().add(lblImg);

		btnImg = new JButton("Picture");
		btnImg.setFont(new Font("Tahoma", Font.PLAIN, 13));
		WindowPreset.buttonPreset(btnImg, "Add your pic");
		btnImg.setBounds(470, 276, 89, 23);
		getContentPane().add(btnImg);

		btnAgregar = new JButton("Agregar");
		WindowPreset.buttonPreset(btnAgregar, "Add the new student to the database");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnAgregar.setBounds(251, 367, 110, 23);
		getContentPane().add(btnAgregar);

		lblFoto = new JLabel("Foto");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(456, 84, 128, 156);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblFoto);

		lblPass = new JLabel("CONTRASEÑA:");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setBounds(22, 265, 101, 14);
		getContentPane().add(lblPass);

		jtPass = new JPasswordField();
		jtPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtPass.setBounds(133, 263, 189, 20);
		getContentPane().add(jtPass);

		btnSubjectsView = new JButton("SELECCIONAR ASIGNATURAS");
		WindowPreset.buttonPreset(btnSubjectsView, "Select your subjects");
		btnSubjectsView.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSubjectsView.setBounds(22, 307, 223, 23);
		getContentPane().add(btnSubjectsView);

		driverAdd dAgregar = new driverAdd();
		btnAgregar.addActionListener(dAgregar);

		driverSubjects dSubjects = new driverSubjects();
		btnSubjectsView.addActionListener(dSubjects);
		setVisible(true);
	}

	// Add the new user if all of the jtextfields are filled
	public class driverAdd implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat sdf = new SimpleDateFormat(date.getDateFormatString());
			String dni = jtDni.getText().toString();
			String name = jtName.getText().toString();
			String surnames = jtSurname.getText().toString();
			String email = jtEmail.getText().toString();
			String date_birth = date.getDateFormatString();
			String pic = "foto";
			String valorPass = new String(jtPass.getPassword());
			String telefono = jtTelefono.getText().toString();
			if (emptyDetector(dni, name, surnames, email, date_birth, pic, valorPass, telefono) == true) {
				date_birth = sdf.format(date.getDate());
				Student s = new Student(dni, name, surnames, email, date_birth, pic, Integer.parseInt(telefono),
						valorPass);
				c.insertStudent(s);
				try {
					c.insertMatricula(dni);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "NO PUEDE HABER CAMPOS VACIOS", "Error", JOptionPane.ERROR_MESSAGE);
			}
			

		}
	}

	// To call the subjectview frame
	public class driverSubjects implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				c.boxFiller();
				new SubjectsView();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	// To detect if some of the jtextfields are empty
	public boolean emptyDetector(String dni, String name, String surnames, String email, String date_birth, String pic,
			String valorPass, String telefono) {
		boolean passed = true;
		if (dni.equalsIgnoreCase("") || name.equalsIgnoreCase("") || surnames.equalsIgnoreCase("")
				|| email.equalsIgnoreCase("") || date_birth.equalsIgnoreCase("") || pic.equalsIgnoreCase("")
				|| valorPass.equalsIgnoreCase("") || telefono.equalsIgnoreCase(""))
			passed = false;
		return passed;
	}
}
