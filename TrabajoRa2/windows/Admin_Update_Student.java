package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import clases.Student;
import connec.Connect;

@SuppressWarnings("serial")
public class Admin_Update_Student extends JFrame {
	private JLabel lblImg, lblTlf, lblDate, lblEmail, lblSurnames, lblName, lblDni, lblPass;
	private JTextField jtDni, jtName, jtSurname, jtEmail, jtTelefono;
	private Connect c = new Connect();
	private JButton btnImg, btnAgregar;
	private JDateChooser date;
	public File destination;
	private JPasswordField jtPass;
	public static JLabel lblFoto;
	public static Student student;
	public static List<String> listOfSubjects = new ArrayList<String>();

	public Admin_Update_Student(Student s) throws SQLException, ParseException {

		super("Admin_Update_Student");
		setSize(630, 450);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		lblDni = new JLabel("DNI:");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(22, 46, 28, 14);
		getContentPane().add(lblDni);

		jtDni = new JTextField();
		jtDni.setText(s.getDni());
		jtDni.setEditable(false);
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
		jtName.setText(s.getName());
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
		jtSurname.setText(s.getSurname());
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
		jtEmail.setText(s.getEmail());
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
		jtTelefono.setText(String.valueOf(s.getPhone()));
		jtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtTelefono.setBounds(114, 220, 80, 20);
		getContentPane().add(jtTelefono);
		jtTelefono.setColumns(10);

		lblImg = new JLabel("ADD IMAGEN");
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImg.setBounds(470, 251, 92, 14);
		getContentPane().add(lblImg);

		btnImg = new JButton();
		btnImg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		WindowPreset.buttonPreset(btnImg, "Add your pic", "files\\image.png");
		btnImg.setBounds(470, 276, 92, 26);
		getContentPane().add(btnImg);

		btnAgregar = new JButton();
		WindowPreset.buttonPreset(btnAgregar, "Add the new student to the database", "files\\insert.png");
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAgregar.setBounds(251, 367, 82, 26);
		getContentPane().add(btnAgregar);

		lblFoto = new JLabel("Foto");
		lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblFoto.setBounds(456, 86, 128, 156);
		lblFoto.setBorder(BorderFactory.createLineBorder(Color.black));
		getContentPane().add(lblFoto);

		lblPass = new JLabel("CONTRASEÑA:");
		lblPass.setHorizontalAlignment(SwingConstants.LEFT);
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPass.setBounds(22, 265, 101, 14);
		getContentPane().add(lblPass);

		jtPass = new JPasswordField();
		jtPass.setText(s.getPassw());
		jtPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtPass.setBounds(133, 263, 189, 20);
		getContentPane().add(jtPass);

		driverAdd dAgregar = new driverAdd();
		btnAgregar.addActionListener(dAgregar);

		setVisible(true);

		driverImage dImage = new driverImage();
		btnImg.addActionListener(dImage);

		// To delete de temp img created to show de picture in the lblImage
		this.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent e) {
				File file = new File("files/tempSelfies/imgTemp" + fileChooser.extension);
				if (file.exists())
					file.delete();
			}
		});
	}

	// Add the new user if all of the jtextfields are filled
	public class driverAdd implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnAgregar) {
				SimpleDateFormat sdf = new SimpleDateFormat(date.getDateFormatString());
				String dni = jtDni.getText().toString();
				String name = jtName.getText().toString();
				tempImg(dni, name);
				String surnames = jtSurname.getText().toString();
				String email = jtEmail.getText().toString();
				String date_birth = date.getDateFormatString();
				String pic = destination.toString().replace("\\", "/");
				System.out.println(pic);
				String valorPass = new String(jtPass.getPassword());
				String telefono = jtTelefono.getText().toString();

				if (emptyDetector(dni, name, surnames, email, date_birth, pic, valorPass, telefono) == true) {
					date_birth = sdf.format(date.getDate());
					Student s = new Student(dni, name, surnames, email, date_birth, pic, Integer.parseInt(telefono),
							valorPass);
					c.updateStudent(s);
					AdminWindow.changeTable(3);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "NO PUEDE HABER CAMPOS VACIOS", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}

	public void tempImg(String dni, String name) {
		File imagenes = new File("files/tempSelfies/imgTemp" + fileChooser.extension);
		File sourcer = new File(imagenes.toPath().toString());
		// Move the temp pic to files
		// In case the user change the dni of the name after selecting the pic, the pic
		// will be saved with all of the changes
		System.out.println(fileChooser.extension);
		if (fileChooser.extension != null) {
			destination = new File("files/pics/" + dni.toString() + name.toString().replace(" ", "")
					+ fileChooser.extension.toString());
			// System.out.println("estadoo" + destination.exists());
			try {
				if (destination.exists())
					destination.delete();
				Files.copy(sourcer.toPath(), destination.toPath());
				Files.delete(sourcer.toPath());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			destination = new File("files/pics/sinfoto.png");
		}

	}

	public class driverModify implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equalsIgnoreCase("Modify")) {
				SimpleDateFormat sdf = new SimpleDateFormat(date.getDateFormatString());
				String dni = jtDni.getText().toString();
				String name = jtName.getText().toString();
				tempImg(dni, name);
				String surnames = jtSurname.getText().toString();
				String email = jtEmail.getText().toString();
				String date_birth = date.getDateFormatString();
				date_birth = sdf.format(date.getDate());
				String pic;
				if (destination.toString().equalsIgnoreCase("files/pics/sinfoto.png")) {
					pic = destination.toString().replace("\\", "/");
				} else {
					String dir = "files/pics/" + dni + name + ".jpg";
					pic = dir.replace(" ", "");
				}
				System.out.println(pic);
				String valorPass = new String(jtPass.getPassword());
				String telefono = jtTelefono.getText().toString();
				Student s = new Student(dni, name, surnames, email, date_birth, pic, Integer.parseInt(telefono),
						valorPass);
				c.updateStudent(s);
				AdminWindow.changeTable(3);
				try {
					new SubjectsView(dni);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}

	}

	public class ReturnModify implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			new StudentView(jtDni.getText());
			setVisible(false);
		}
	}

	public class driverImage implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fileChooser.start();
		}
	}

	// To call the subjectview frame
	public class driverSubjects implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				c.boxFiller();
				new SubjectsView(jtDni.getText().toString());
			} catch (SQLException e1) {
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
