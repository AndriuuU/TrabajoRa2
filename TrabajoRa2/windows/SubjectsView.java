package windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import clases.Subjects;
import connec.Connect;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class SubjectsView extends JFrame {
	Connect c = new Connect();
	ButtonGroup group = new ButtonGroup();
	JButton volver;
	JPanel panel;
	public static List<String> subjectCod = new ArrayList<String>();
	int pos = 0;
	private List<Subjects> asig;
	private List<Subjects> notEnrrolled;
	private DefaultTableModel tableModel;
	private DefaultTableModel tableModel2;
	private JTable enrolledSubjects;
	private JTable notEnrolledSubjects;
	private JScrollPane scrollPane;
	private JScrollPane scnotEnrrolledSubjects;
	private JButton toNotEnrrolled, toEnrrolled;
	private String dniA;

	public SubjectsView(String dni) throws SQLException {
		super("List of Subjects");
		setSize(600, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(new BorderLayout());
		dniA=dni;

		String[] columna = new String[] { "codAsig", "Name" };
		tableModel = new DefaultTableModel(columna, 0);
		String[] columna2 = new String[] { "codAsig", "Name" };
		tableModel2 = new DefaultTableModel(columna2, 0);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		asig = c.getEnrrolled(dni);
		notEnrrolled = c.getNotEnrrolled(asig);
		insertarAsig();

		enrolledSubjects = new JTable(tableModel);
		enrolledSubjects.getColumnModel().getColumn(0).setPreferredWidth(10);
		enrolledSubjects.getColumnModel().getColumn(1).setPreferredWidth(120);
		enrolledSubjects.setBounds(69, 267, 151, -172);
		enrolledSubjects.setBackground(new Color(192, 192, 192));

		notEnrolledSubjects = new JTable(tableModel2);
		notEnrolledSubjects.getColumnModel().getColumn(0).setPreferredWidth(10);
		notEnrolledSubjects.getColumnModel().getColumn(1).setPreferredWidth(120);
		notEnrolledSubjects.setBackground(new Color(192, 192, 192));
		notEnrolledSubjects.setBounds(375, 267, 151, -172);
		panel.add(notEnrolledSubjects);

		scrollPane = new JScrollPane(enrolledSubjects);
		scrollPane.setBounds(24, 66, 227, 220);
		panel.add(scrollPane);

		scnotEnrrolledSubjects = new JScrollPane(notEnrolledSubjects);
		scnotEnrrolledSubjects.setBounds(360, 66, 222, 220);
		panel.add(scnotEnrrolledSubjects);

		volver = new JButton("Volver");
		volver.setBounds(0, 348, 592, 23);
		panel.add(volver);

		toNotEnrrolled = new JButton("----->");
		toNotEnrrolled.setBounds(261, 145, 89, 23);
		panel.add(toNotEnrrolled);

		toEnrrolled = new JButton("<-----");
		toEnrrolled.setBounds(261, 197, 89, 23);
		panel.add(toEnrrolled);

		btntoNotEnrolled btntoNot = new btntoNotEnrolled();
		toNotEnrrolled.addActionListener(btntoNot);
		btntoEnrrolled btntoEnrrolled = new btntoEnrrolled();
		toEnrrolled.addActionListener(btntoEnrrolled);

		driverAccept btnAccept = new driverAccept();
		volver.addActionListener(btnAccept);
		setVisible(true);
	}

	public class driverAccept implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}

	}

	public class btntoNotEnrolled implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (enrolledSubjects.getSelectedRow() != -1) {
				String codigo = (String) tableModel.getValueAt(enrolledSubjects.getSelectedRow(), 0);
				try {
					c.deleteMatricula(dniA.toString(),codigo.toString());
					asig = c.getEnrrolled(dniA);
					notEnrrolled = c.getNotEnrrolled(asig);
					insertarAsig();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "Nothing has been selected");
			}
		}
	}
	public class btntoEnrrolled implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (notEnrolledSubjects.getSelectedRow() != -1) {
				try {
					String codigo = (String) tableModel2.getValueAt(notEnrolledSubjects.getSelectedRow(), 0);
					c.insertMatricula(dniA.toString(),codigo.toString());
					asig = c.getEnrrolled(dniA);
					notEnrrolled = c.getNotEnrrolled(asig);
					insertarAsig();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}else {
				JOptionPane.showMessageDialog(null, "Nothing has been selected");
			}
		}
	}

	public void insertarAsig() {
		while (tableModel.getRowCount() > 0) {
			tableModel.removeRow(0);
		}
		for (int i = 0; i < asig.size(); i++) {
			String codAsig = asig.get(i).getCodSubject();
			String name = asig.get(i).getName();
			Object[] data = { codAsig, name };
			tableModel.addRow(data);
		}
		while (tableModel2.getRowCount() > 0) {
			tableModel2.removeRow(0);
		}
		for (int i = 0; i < notEnrrolled.size(); i++) {
			// String nombre,marca,modelo,color,imgRuta;
			String codAsig = notEnrrolled.get(i).getCodSubject();
			String name = notEnrrolled.get(i).getName();
			Object[] data = { codAsig, name };
			tableModel2.addRow(data);
		}

	}
}
