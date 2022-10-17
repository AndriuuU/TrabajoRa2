package windows;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Subjects;
import clases.Student;
import clases.Teacher;
import connec.Connect;

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	private JComboBox<String> comboBox;
	private JPanel jptable, jpButton;
	private JButton jbInsert, jbDelete, jbDetails, jbUpdate, jbDis;
	private String[] options = { "Courses", "Professors", "Students" };
	private static Object o;

	// Table's Attributes.

	private static String[] columnsName;
	private static JScrollPane scrollPane;
	private static JTable jtable;
	private static Connect c = new Connect();

	public AdminWindow() {

		super("AdminWindow");
		setSize(450, 300);
		WindowPreset.preset(this);

		// añadir (options) como parametro del constructor, una vez metido no deja abrir
		// windowBuilder
		comboBox = new JComboBox<String>(options);
		comboBox.setMaximumRowCount(3);
		comboBox.setToolTipText("Selector");
		comboBox.setBounds(10, 10, 130, 20);
		comboBox.setSelectedIndex(0);
		comboBox.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(comboBox);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				JComboBox<?> jcb1 = (JComboBox<?>) e.getSource();
				changeTable(jcb1.getSelectedIndex());

			}
		});

		jptable = new JPanel();
		jptable.setBounds(10, 35, 290, 220);
		jpButton = new JPanel();
		jpButton.setBounds(309, 24, 117, 229);

		jtable = new JTable();
		jtable.setBounds(148, 6, 0, 0);
		scrollPane = new JScrollPane(jtable);
		scrollPane.setBounds(0, 0, 290, 220);
		jptable.add(scrollPane);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		changeTable(comboBox.getSelectedIndex());

		jbInsert = new JButton("Insert");
		jbInsert.setBounds(10, 25, 100, 20);
		WindowPreset.buttonPreset(jbInsert, "Insert new row.");
		jbDelete = new JButton("Delete");
		jbDelete.setBounds(10, 56, 100, 20);
		WindowPreset.buttonPreset(jbDelete, "Delete selected row.");
		jbDetails = new JButton("Details");
		jbDetails.setBounds(10, 87, 100, 20);
		WindowPreset.buttonPreset(jbDetails, "Detail selected row.");
		jbUpdate = new JButton("Update");
		jbUpdate.setBounds(10, 118, 100, 20);
		WindowPreset.buttonPreset(jbUpdate, "Modify selected row.");
		jbDis = new JButton("Disconnect");
		jbDis.setBounds(10, 198, 100, 20);
		WindowPreset.buttonPreset(jbDis, "Go to Login.");
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

		setVisible(true);

	}

	public static void changeTable(int index) {
		DefaultTableModel model = new DefaultTableModel() {
			
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		if (index == 0) {
			o = new Subjects();
			columnsName = new String[] { "Name", "dniProfesor" };
			model.setColumnIdentifiers(columnsName);
			jtable = new JTable(model);
			scrollPane.setViewportView(jtable);
			for (Subjects as : c.viewSubjects()) {
				Object[] row = new Object[2];
				row[0] = as.getName();
				row[1] = as.getDniProfessor();
				model.addRow(row);
			}
			
		} else if (index == 1) {
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
			
		} else if (index == 2) {
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

	class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			String selector = o.getClass().toString();
			JButton b = (JButton) e.getSource();

			if (selector.equals("class clases.Asignatura")) {
				if (b.getName().equals("Insert")) {

				} else if (b.getName().equals("Delete")) {

				} else if (b.getName().equals("Details")) {

				} else if (b.getName().equals("Update")) {

				} else {
					System.out.println("Error");
				}
			} else if (selector.equals("class clases.Teacher")) {
				if (b.getName().equals("Insert")) {

				} else if (b.getName().equals("Delete")) {

				} else if (b.getName().equals("Details")) {

				} else if (b.getName().equals("Update")) {

				} else {
					System.out.println("Error");
				}
			} else if (selector.equals("class classes.Student")) {
				if (b.getName().equals("Insert")) {

				} else if (b.getName().equals("Delete")) {

				} else if (b.getName().equals("Details")) {

				} else if (b.getName().equals("Update")) {

				} else {
					System.out.println("Error");
				}
			}

		}

	}
}
