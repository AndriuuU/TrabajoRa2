package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import clases.Ra;
import clases.Student;
import clases.Subjects;
import clases.Teacher;
import connec.Connect;

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	private JComboBox<String> comboBox;
	private JPanel jptable, jpButton;
	private JButton jbInsert, jbDelete, jbDetails, jbUpdate, jbDis;

	private String[] options = { "Subjects", "RAs", "Professors", "Students" };
	private static Object o;
	private String dniTeacher;

	// Table's Attributes.

	private static String[] columnsName;
	private static JScrollPane scrollPane;
	private static JTable jtable;
	private static Connect c = new Connect();

	public AdminWindow() {
		super("AdminWindow");
		setSize(700, 500);
		WindowPreset.preset(this);
		// añadir (options) como parametro del constructor, una vez metido no deja abrir
		// windowBuilder
		comboBox = new JComboBox<String>(options);
		comboBox.setMaximumRowCount(4);
		comboBox.setToolTipText("Selector");
		comboBox.setBounds(40, 20, 130, 20);
		comboBox.setSelectedIndex(0);
		comboBox.setBackground(Color.LIGHT_GRAY);
		add(comboBox);
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				JComboBox<?> jcb1 = (JComboBox<?>) e.getSource();
				changeTable(jcb1.getSelectedIndex());
			}
		});

		jptable = new JPanel();
		jptable.setBounds(21, 46, 510, 400);
		jpButton = new JPanel();
		jpButton.setBackground(Color.LIGHT_GRAY);
		jpButton.setBounds(540, 46, 136, 400);

		jtable = new JTable();
		jtable.setBounds(148, 6, 0, 0);
		scrollPane = new JScrollPane(jtable);
		scrollPane.setBounds(10, 10, 489, 380);
		jptable.add(scrollPane);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		changeTable(comboBox.getSelectedIndex());

		jbInsert = new JButton();
		jbInsert.setBounds(55, 50, 26, 26);
		WindowPreset.buttonPreset(jbInsert, "Insert New Row.", "files\\insert.png");
		jbDelete = new JButton();
		jbDelete.setBounds(55, 100, 26, 26);
		WindowPreset.buttonPreset(jbDelete, "Delete Selected Row.", "files\\delete.png");
		jbDetails = new JButton();
		jbDetails.setBounds(55, 150, 26, 26);
		WindowPreset.buttonPreset(jbDetails, "Detail of the Selected Row.", "files\\details.png");
		jbUpdate = new JButton();
		jbUpdate.setBounds(55, 200, 26, 26);
		WindowPreset.buttonPreset(jbUpdate, "Update Selected Row.", "files\\update.png");
		jbDis = new JButton();
		jbDis.setBounds(55, 330, 26, 26);
		WindowPreset.buttonPreset(jbDis, "Go to Login.", "files\\disconnect.png");
		jbDis.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				@SuppressWarnings("unused")
				Login l = new Login();
			}
		});
		jptable.setLayout(null);

		jpButton.setLayout(null);
		jpButton.add(jbInsert);
		jpButton.add(jbDelete);
		jpButton.add(jbDetails);
		jpButton.add(jbUpdate);
		jpButton.add(jbDis);
		getContentPane().setLayout(null);

		getContentPane().add(jptable);
		getContentPane().add(jpButton);

		ButtonManager bm = new ButtonManager();
		jbInsert.addActionListener(bm);
		jbDelete.addActionListener(bm);
		jbDetails.addActionListener(bm);
		jbUpdate.addActionListener(bm);
		setVisible(true);

	}

	public static void changeTable(int index) {
		DefaultTableModel model = new DefaultTableModel();
		if (index == 0) {
			o = new Subjects();
			columnsName = new String[] { "ID", "Name", "dniProfesor" };

			model.setColumnIdentifiers(columnsName);
			jtable = new JTable(model);
			scrollPane.setViewportView(jtable);
			for (Subjects as : c.viewSubjects()) {
				Object[] row = new Object[3];
				row[0] = as.getCodSubject();
				row[1] = as.getName();
				row[2] = as.getDniProfessor();

				model.addRow(row);
			}

		} else if (index == 1) {
			o = new Ra();
			columnsName = new String[] { "ID", "Name", "Weighting" };
			model.setColumnIdentifiers(columnsName);
			jtable = new JTable(model);
			scrollPane.setViewportView(jtable);
//			for (Ra r : c.ra) { // método connect para devolver lista de RA
//				Object[] row = new Object[3];
//				row[0] = r.getId();
//				row[1] = r.getName();
//				row[2] = r.getWeighting();
//				model.addRow(row);
//			}

		} else if (index == 2) {
			o = new Teacher();
			columnsName = new String[] { "NIF", "Name", "Surname" };
			model.setColumnIdentifiers(columnsName);
			jtable = new JTable(model);
			scrollPane.setViewportView(jtable);
			for (Teacher t : c.viewTeacher()) {
				Object[] row = new Object[3];
				row[0] = t.getDni();
				row[1] = t.getName();
				row[2] = t.getSurname();
				model.addRow(row);
			}

		} else if (index == 3) {
			o = new Student();
			columnsName = new String[] { "NIF", "Name", "Surname" };
			model.setColumnIdentifiers(columnsName);
			jtable = new JTable(model);
			scrollPane.setViewportView(jtable);
			for (Student s : c.viewStudents()) {
				Object[] row = new Object[3];
				row[0] = s.getDni();
				row[1] = s.getName();
				row[2] = s.getSurname();
				model.addRow(row);
			}
		}
	}

	public class TableManager implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			dniTeacher = (String) jtable.getValueAt(jtable.getSelectedRow(), 0);
			System.out.println(dniTeacher);

		}

	}

	class ButtonManager implements ActionListener {

		@SuppressWarnings("unused")

		@Override
		public void actionPerformed(ActionEvent e) {

			// Class selector.

			String selector = o.getClass().toString();
			JButton b = (JButton) e.getSource();

			// Button selector.

			String insert = "Insert New Row.";
			String delete = "Delete Selected Row.";
			String details = "Detail of the Selected Row.";
			String update = "Update Selected Row.";

			// Class Subjects

			if (selector.equals("class clases.Subjects")) {
				if (b.getToolTipText().equals(insert)) {
					InsertSubject is = new InsertSubject();
				} else if (b.getToolTipText().equals(delete)) {
					System.out.println("hola");
					String codSubject = jtable.getValueAt(jtable.getSelectedRow(), 0).toString();
					System.out.println(codSubject);
					c.deleteSubject(codSubject);
				} else if (b.getToolTipText().equals(details)) {

				} else if (b.getToolTipText().equals(update)) {

				} else {
					System.out.println("Error");
				}

				// Class Teacher

			} else if (selector.equals("class clases.Teacher")) {

				if (b.getToolTipText().equals(insert)) {
					RegisterTeacher rt = new RegisterTeacher();
				} else if (b.getToolTipText().equals(delete)) {

					String dni = jtable.getValueAt(jtable.getSelectedRow(), 0).toString();
					c.deleteTeacher(dni);

				} else if (b.getToolTipText().equals(details)) {

				} else if (b.getToolTipText().equals(update)) {

				} else {
					System.out.println("Error");
				}

				// Class Student

			} else if (selector.equals("class clases.Student")) {
				if (b.getToolTipText().equals(insert)) {
					try {
						RegisterStudent rs = new RegisterStudent();
					} catch (SQLException | ParseException e1) {
						e1.printStackTrace();
					}

				} else if (b.getToolTipText().equals(delete)) {

					String dni = jtable.getValueAt(jtable.getSelectedRow(), 0).toString();
					c.deleteStudent(dni);

				} else if (b.getToolTipText().equals(details)) {

				} else if (b.getToolTipText().equals(update)) {
					try {
						String dni = jtable.getValueAt(jtable.getSelectedRow(), 0).toString();
						Student s = new Student();
						s = c.getInfoStudent(dni, c.viewStudents());
						RegisterStudent.student = s;
						RegisterStudent rs = new RegisterStudent();
					} catch (SQLException | ParseException e1) {
						e1.printStackTrace();
					}catch (ArrayIndexOutOfBoundsException eX) {
						JOptionPane.showMessageDialog(AdminWindow.this, "No hay nada seleccionado");
					}

				} else {
					System.out.println("Error");
				}
			} else if (selector.equals("class clases.Ra")) {
				if (b.getToolTipText().equals(insert)) {

				} else if (b.getToolTipText().equals(delete)) {

				} else if (b.getToolTipText().equals(details)) {

				} else if (b.getToolTipText().equals(update)) {

				} else {
					System.out.println("Error");
				}
			}

			// Refresh Table

			changeTable(comboBox.getSelectedIndex());

		}
	}
}
