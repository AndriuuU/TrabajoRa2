package windows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Student;
import connec.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class StudentView extends JFrame {

	private String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back, btnCrud, btnLook;
	private JLabel lblStudent, name;
	static public String codEnrolment;
	private Student s;

	Connect mostrar = new Connect();

	public StudentView(String dni) {
		super("StudentView");
		setSize(600, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		columNames = new String[] { "Cod", "Subject", "Grade" };
		tablemodel = new DefaultTableModel(columNames, 0);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);

		mostrar.viewStudents(dni);
		s = mostrar.getInfoStudent(dni, mostrar.viewStudents());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 393, 258);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		lblStudent = new JLabel(s.getName() + " " + s.getSurname());
		lblStudent.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStudent.setBounds(36, 29, 205, 13);
		getContentPane().add(lblStudent);

		ButtonManager buttonMana = new ButtonManager();

		btnCrud = new JButton();
		btnCrud.setBounds(461, 166, 26, 26);
		WindowPreset.buttonPreset(btnCrud, "Update information", "files\\update.png");
		btnCrud.addActionListener(buttonMana);
		getContentPane().add(btnCrud);

		btnLook = new JButton();
		btnLook.setBounds(461, 196, 26, 26);
		WindowPreset.buttonPreset(btnLook, "Show all grade RA", "files\\showMore.png");
		btnLook.addActionListener(buttonMana);
		getContentPane().add(btnLook);

		back = new JButton();
		back.setBounds(461, 268, 26, 26);
		back.addActionListener(buttonMana);
		WindowPreset.buttonPreset(back, "Back to menu", "files\\return.png");
		getContentPane().add(back);

		name = new JLabel();
		name.setBounds(456, 35, 97, 90);
		name.setBorder(BorderFactory.createLineBorder(Color.black));
		insertFoto();
		getContentPane().add(name);

		setVisible(true);
	}

	private void insertFoto() {
		ImageIcon imageIcon = new ImageIcon(s.getPhoto().toString());
		if (imageIcon.getImage() != null) {
			imageIcon = new ImageIcon("files/Perfil.png");
		}
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(94, 110, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIcon2 = new ImageIcon(newimg);

		name.setIcon(imageIcon2);
	}

	public static JTable getTabla() {
		return table;
	}

	private void setTabla(JTable jTable) {
		StudentView.table = jTable;

	}

	private class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = getTabla().getSelectedRow();
			if (e.getSource() == btnCrud) {
				try {

					RegisterStudent.student = s;
					new RegisterStudent();

				} catch (SQLException | ParseException e1) {

					e1.printStackTrace();
				}
				setVisible(false);
			} else if (num >= 0) {
				setVisible(false);

				if (e.getSource() == btnLook) {
					codEnrolment = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();

					new RaStudentView(s.getDni());

				} else if (e.getSource() == back) {
					new Login();
				}
			} else
				JOptionPane.showMessageDialog(StudentView.this, "You must select a subject!!", "Unselected subject",
						JOptionPane.WARNING_MESSAGE);
		}
	}
}
