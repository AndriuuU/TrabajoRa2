package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import clases.Teacher;
import connec.Connect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class TeacherView extends JFrame {

	private String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back, btnLook, btnStudent;
	private JLabel lblTeacher;
	public static String codAsigna;
	public static String dniTeacher;

	Connect mostrar = new Connect();

	public TeacherView() {
		super("TeacherView");
		setSize(600, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		columNames = new String[] { "Cod", "Subject" ,"Hours"};
		tablemodel = new DefaultTableModel(columNames, 0);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);

		mostrar.viewTeacher(dniTeacher);
		Teacher t=mostrar.getTeacher(dniTeacher);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 393, 258);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		lblTeacher = new JLabel(t.getName()+" "+t.getSurname());
		lblTeacher.setBounds(36, 29, 205, 21);
		lblTeacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(lblTeacher);

		ButtonManager buttonMana = new ButtonManager();

		btnLook = new JButton();
		btnLook.setBounds(461, 101, 85, 21);
		WindowPreset.buttonPreset(btnLook, "Detail of the Selected Row.", "files\\details.png");
		btnLook.addActionListener(buttonMana);
		getContentPane().add(btnLook);

		btnStudent = new JButton();
		btnStudent.setBounds(448, 140, 107, 25);
		WindowPreset.buttonPreset(btnStudent, "Show all grade", "files\\showMore.png");
		btnStudent.addActionListener(buttonMana);
		getContentPane().add(btnStudent);
		

		back = new JButton();
		back.setBounds(461, 268, 85, 21);
		WindowPreset.buttonPreset(back, "Go to Login.", "files\\disconnect.png");
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

				dniTeacher=null;
				codAsigna=null;
				new Login();
				setVisible(false);
				
			} else if (num >= 0) {
				setVisible(false);
				codAsigna = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();
				
				
				if (e.getSource() == btnLook) {
					new RaProfessorView();

				} else if (e.getSource() == btnStudent) {
					
					new TeacherRaGrade();
				} 
			} else
				JOptionPane.showMessageDialog(TeacherView.this, "Debes seleccionar una asignaruta!!",
						"Asignatura no seleccionada", JOptionPane.WARNING_MESSAGE);

		}
	}
}
