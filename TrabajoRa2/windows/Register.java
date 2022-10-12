package windows;


import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import clases.Student;
import clases.Teacher;
import connec.Connect;

@SuppressWarnings("serial")
public class Register extends JFrame {
	private static JTextField jtDni;
	private static JTextField jtName;
	private static JTextField jtSurname;
	private static JTextField jtEmail;
	private static JTextField jtTelefono;
	private Connect c = new Connect();
	private JButton btnImg;
	private JButton btnAgregar;
	private JDateChooser date;
	private JComboBox cbTipo;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	private JLabel lblImg;
	private JLabel lblTipo;
	private JLabel lblTlf;
	private JLabel lblDate;
	private JLabel lblEmail;
	private JLabel lblSurnames;
	private JLabel lblName;
	private JLabel lblDni;

	public Register() {

		super("Register");
		setSize(600,400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);
		
		
		lblDni = new JLabel("DNI");
		lblDni.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDni.setBounds(130, 70, 25, 14);
		getContentPane().add(lblDni);
		
		jtDni = new JTextField();
		jtDni.setBounds(171, 68, 123, 20);
		getContentPane().add(jtDni);
		jtDni.setColumns(10);
		
		lblName = new JLabel("NOMBRE");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(101, 99, 54, 14);
		getContentPane().add(lblName);
		
		jtName = new JTextField();
		jtName.setBounds(171, 97, 123, 20);
		getContentPane().add(jtName);
		jtName.setColumns(10);
		
		lblSurnames = new JLabel("APELLIDOS");
		lblSurnames.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSurnames.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSurnames.setBounds(85, 130, 70, 14);
		getContentPane().add(lblSurnames);
		
		jtSurname = new JTextField();
		jtSurname.setBounds(171, 128, 189, 20);
		getContentPane().add(jtSurname);
		jtSurname.setColumns(10);
		
		lblEmail = new JLabel("EMAIL");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(109, 163, 46, 14);
		getContentPane().add(lblEmail);
		
		jtEmail = new JTextField();
		jtEmail.setBounds(171, 161, 189, 20);
		getContentPane().add(jtEmail);
		jtEmail.setColumns(10);
		
		lblDate = new JLabel("FECHA NACIMIENTO");
		lblDate.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(41, 233, 120, 14);
		getContentPane().add(lblDate);
		
		date = new JDateChooser();
		date.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
		date.setDateFormatString("yyyy/dd/MM");
		date.setBounds(171, 227, 110, 20);
		getContentPane().add(date);
		
		lblTlf = new JLabel("TELEFONO");
		lblTlf.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTlf.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTlf.setBounds(85, 198, 71, 14);
		getContentPane().add(lblTlf);
		
		jtTelefono = new JTextField();
		jtTelefono.setBounds(171, 196, 86, 20);
		getContentPane().add(jtTelefono);
		jtTelefono.setColumns(10);
		
		lblImg = new JLabel("ADD IMAGEN");
		lblImg.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImg.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImg.setBounds(69, 262, 92, 14);
		getContentPane().add(lblImg);
		
		btnImg = new JButton("Imagen");
		btnImg.setBounds(171, 259, 89, 23);
		getContentPane().add(btnImg);
		
		
		String tipo[] = {"Alumno","Profesor"};
		cbTipo = new JComboBox<Object>(tipo);
		cbTipo.setBounds(440, 67, 110, 22);
		getContentPane().add(cbTipo);
		
		lblTipo = new JLabel("TIPO");
		lblTipo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblTipo.setBounds(469, 42, 46, 14);
		getContentPane().add(lblTipo);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(440, 259, 110, 23);
		getContentPane().add(btnAgregar);
		
		
		driverRegister dRegister = new driverRegister();
		cbTipo.addItemListener(dRegister);
		
		driverAdd dAgregar = new driverAdd();
		btnAgregar.addActionListener(dAgregar);
		
		setVisible(true);
	}
	
	public class driverRegister implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(cbTipo.getSelectedItem().toString());
			String tipo = cbTipo.getSelectedItem().toString();
			if(tipo.equalsIgnoreCase("Profesor")) {
				lblTlf.setEnabled(false);
				jtTelefono.setEnabled(false);
				jtTelefono.setText("");
				lblDate.setEnabled(false);
				date.setEnabled(false);
				date.setDate(null);
				lblImg.setEnabled(false);
				btnImg.setEnabled(false);
			}else {
				lblTlf.setEnabled(true);
				jtTelefono.setEnabled(true);
				lblDate.setEnabled(true);
				date.setEnabled(true);
				lblImg.setEnabled(true);
				btnImg.setEnabled(true);
			}
			
		}
		
	}
	
	public class driverAdd implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat sdf = new SimpleDateFormat(date.getDateFormatString());
			if(cbTipo.getSelectedItem().toString().equalsIgnoreCase("Alumno")) {
				//String dni, String nombre, String apellidos, String email, String fecha_nac, String foto, int telefono
				String dni = jtDni.getText().toString();
				String name = jtName.getText().toString();
				String surnames = jtSurname.getText().toString();
				String email = jtEmail.getText().toString();
				String date_birth = sdf.format(date.getDate());
				String pic = "foto";
				int telefono = Integer.parseInt(jtTelefono.getText().toString());
				if(dniTester()) {
					Student s = new Student(dni,name,surnames,email,date_birth,pic,telefono);
					c.insertStudent(s);
				}
			}else {
				System.out.println("Profesor");
				String dni = jtDni.getText().toString();
				String name = jtName.getText().toString();
				String surnames = jtSurname.getText().toString();
				String email = jtEmail.getText().toString();
				Teacher t = new Teacher(dni,name,surnames,email);
				c.insertTeacher(t);
			}
			
		}
		
	}
	
	public static boolean dniTester() {
		if(jtDni.getText().toString().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "El campo dni no puede estar vacio", "Error", JOptionPane.WARNING_MESSAGE);
			return false;
		}else{
			return jtDni.getText().toString().matches("[0-9]{7,8}[A-Z a-z]");
		}
		
		
	}
}
