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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Student;
import connec.Connect;

@SuppressWarnings("serial")
public class Admin_Details_Student extends JFrame {
	private JLabel lblImg, lblTlf, lblDate, lblEmail, lblSurnames, lblName, lblDni, lblPass;
	private JTextField jtDni, jtName, jtSurname, jtEmail, jtTelefono, jtDate;
	private Connect c = new Connect();
	private JButton btnReturn;
	public File destination;
	private JPasswordField jtPass;
	public static JLabel lblFoto;
	public static Student student;
	public static List<String> listOfSubjects = new ArrayList<String>();

	public Admin_Details_Student(Student s) throws SQLException, ParseException {

		super("Admin_Details_Student");
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
		jtName.setEditable(false);
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
		jtSurname.setEditable(false);
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
		jtEmail.setEditable(false);
		jtEmail.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtEmail.setBounds(81, 171, 189, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);

		lblDate = new JLabel("FECHA NACIMIENTO:");
		lblDate.setHorizontalAlignment(SwingConstants.LEFT);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDate.setBounds(22, 133, 140, 14);
		getContentPane().add(lblDate);

		jtDate = new JTextField();
		jtDate.setText(s.getB_date());
		jtDate.setEditable(false);
		jtDate.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtDate.setBounds(175, 127, 128, 20);
		getContentPane().add(jtDate);

		lblTlf = new JLabel("TELEFONO:");
		lblTlf.setHorizontalAlignment(SwingConstants.LEFT);
		lblTlf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTlf.setBounds(22, 222, 82, 14);
		getContentPane().add(lblTlf);

		jtTelefono = new JTextField();
		jtTelefono.setText(String.valueOf(s.getPhone()));
		jtTelefono.setEditable(false);
		jtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtTelefono.setBounds(114, 220, 80, 20);
		getContentPane().add(jtTelefono);
		jtTelefono.setColumns(10);

		lblImg = new JLabel("ADD IMAGEN");
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImg.setBounds(470, 251, 92, 14);
		getContentPane().add(lblImg);

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
		jtPass.setEditable(false);
		jtPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jtPass.setBounds(133, 263, 189, 20);
		getContentPane().add(jtPass);

		btnReturn = new JButton();
		btnReturn.setBounds(100, 300, 85, 25);
		WindowPreset.buttonPreset(btnReturn, "Back to menu", "files\\return.png");
		btnReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		getContentPane().add(btnReturn);
		setVisible(true);

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
}
