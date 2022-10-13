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
public class StudentView extends JFrame {

	private String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back, btnLook;
	private JLabel lblAlumno;
	static public String codMatricula;

	Connect mostrar = new Connect();

	public StudentView() {
		super("StudentView");
		setSize(600, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		columNames = new String[] { "Cod", "Subject", "Grade" };
		tablemodel = new DefaultTableModel(columNames, 0);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);

		mostrar.viewStudents("123456789A");

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 393, 258);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		lblAlumno = new JLabel("(Nombre del alumno)");
		lblAlumno.setBounds(36, 29, 205, 13);
		getContentPane().add(lblAlumno);

		ButtonManager buttonMana = new ButtonManager();

		btnLook = new JButton("Show Ra");
		btnLook.setBounds(461, 101, 85, 21);
		btnLook.addActionListener(buttonMana);
		getContentPane().add(btnLook);

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

			if (num >=0) {
				setVisible(false);

				if (e.getSource() == btnLook) {
					codMatricula = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();
					System.out.println(codMatricula);
					new RaStudentView();

				} else if (e.getSource() == back) {

				}
			}else
				JOptionPane.showMessageDialog(StudentView.this, "Debes seleccionar una asignaruta!!", "Asignatura no seleccionada", JOptionPane.WARNING_MESSAGE);
		}
	}
}
