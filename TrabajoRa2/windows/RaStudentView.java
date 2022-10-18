package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Student;
import connec.Connect;

@SuppressWarnings("serial")
public class RaStudentView extends JFrame {

	private String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back;
	private JLabel lblStudent;
	private Student s;

	Connect mostrar = new Connect();

	public RaStudentView(String dni) {

		super("RaStudentView");
		setSize(503, 400);
		WindowPreset.preset(this);
		setLayout(null);

		columNames = new String[] { "Ra", "Name", "Grade", "Percentage" };
		tablemodel = new DefaultTableModel(columNames, 0);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);

		String asig = StudentView.codEnrolment;
		mostrar.viewStudentsRA(dni, asig);
		s = mostrar.getInfoStudent(dni, mostrar.viewStudents());

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 301, 280);
		add(scrollPane);
		scrollPane.setViewportView(table);

		lblStudent = new JLabel();
		lblStudent.setBounds(36, 29, 205, 13);
		getContentPane().add(lblStudent);

		back = new JButton();
		back.setBounds(367, 267, 85, 21);
		WindowPreset.buttonPreset(back, "Back to Student", "files\\return.png");
		ButtonManager buttonMana = new ButtonManager();
		back.addActionListener(buttonMana);
		getContentPane().add(back);

		setVisible(true);

	}

	public static JTable getTabla() {
		return table;
	}

	private void setTabla(JTable jTable) {
		this.table = jTable;

	}

	public class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new StudentView(s.getDni());
			setVisible(false);
		}

	}
}
