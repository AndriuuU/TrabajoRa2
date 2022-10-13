package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connec.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TeacherView extends JFrame {

	private String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back, btnLook, btnStudent;
	private JLabel lblAlumno;
	static public String codAsigna;

	Connect mostrar = new Connect();

	public TeacherView() {
		super("TeacherView");
		setSize(600, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		columNames = new String[] { "Cod", "Subject" };
		tablemodel = new DefaultTableModel(columNames, 0);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);

		mostrar.viewTeacher("74544252J");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 393, 258);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		lblAlumno = new JLabel("(Nombre del profesor)");
		lblAlumno.setBounds(36, 29, 205, 13);
		getContentPane().add(lblAlumno);

		ButtonManager buttonMana = new ButtonManager();

		btnLook = new JButton("SHOW");
		btnLook.setBounds(461, 101, 85, 21);
		btnLook.addActionListener(buttonMana);
		getContentPane().add(btnLook);

		btnStudent = new JButton("NOTE GRADE");
		btnStudent.setBounds(452, 145, 107, 21);
		btnStudent.addActionListener(buttonMana);
		getContentPane().add(btnStudent);

		back = new JButton("Return");
		back.setBounds(461, 268, 85, 21);
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

	private class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int num = getTabla().getSelectedRow();
			if (e.getSource() == back) {

				// Borrar profesor
				
			} else if (num >= 0) {
				setVisible(false);
				codAsigna = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();
				
				if (e.getSource() == btnLook) {
					new RaProfessorView();

				} else if (e.getSource() == btnStudent) {
					
					new TeacherRaGrade();
					
					//notaFinal.columNames = new String[] { "Student", "Final" };

				}
			} else
				JOptionPane.showMessageDialog(TeacherView.this, "Debes seleccionar una asignaruta!!",
						"Asignatura no seleccionada", JOptionPane.WARNING_MESSAGE);

		}
	}
}
