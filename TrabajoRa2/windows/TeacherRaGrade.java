package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connec.Connect;

@SuppressWarnings("serial")
public class TeacherRaGrade extends JFrame {

	public String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back, btInsert;
	private JLabel lblAlumno;
	private boolean salir = false;
	private String ra;

	Connect mostrar = new Connect();

	public TeacherRaGrade() {

		super("Teacher Grade");
		setSize(503, 400);
		WindowPreset.preset(this);
		setLayout(null);

		columNames = new String[] { "Name", "Grade" };
		tablemodel = new DefaultTableModel(columNames, 0);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);

		ra = RaProfessorView.codRa;
		if (ra != null) {
			mostrar.viewTeacherRaGrade(ra);

			
			salir = true;
			lblAlumno = new JLabel(ra);

			ButtonManager buttonMana = new ButtonManager();

			btInsert = new JButton();
			btInsert.setBounds(367, 160, 85, 21);
			WindowPreset.buttonPreset(btInsert, "Insert grade RA", "files\\insert.png");
			btInsert.addActionListener(buttonMana);
			getContentPane().add(btInsert);

		} else {
			String asig = TeacherView.codAsigna;
			mostrar.viewTeacherFinalGrade(asig);
			lblAlumno = new JLabel(asig);

		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 301, 280);
		add(scrollPane);
		scrollPane.setViewportView(table);

		lblAlumno.setBounds(36, 29, 205, 13);
		getContentPane().add(lblAlumno);

		ButtonManager buttonMana = new ButtonManager();

		back = new JButton();
		back.setBounds(367, 267, 85, 21);
		WindowPreset.buttonPreset(back, "Back to menu", "files\\return.png");
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

			int num = getTabla().getSelectedRow();
			if (num >= 0) {
				if (e.getSource() == btInsert) {
					String name = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();
					Float grade = Float.parseFloat(getTabla().getValueAt(getTabla().getSelectedRow(), 1).toString());
					new InsertGrade(ra,name,grade);
					setVisible(false);
				}
			}
			if (e.getSource() == back) {
				setVisible(false);
				if (salir) {
					RaProfessorView.codRa = null;
					new RaProfessorView();
				} else
					new TeacherView();

			}

		}

	}

}
