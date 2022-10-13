package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connec.Connect;

@SuppressWarnings("serial")
public class RaProfessorView extends JFrame {

	public String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back;
	private JLabel lblAlumno;
	static public String codRa;

	Connect mostrar = new Connect();
	private JButton btnShow;

	public RaProfessorView() {

		super("RaProfesorView");
		setSize(503, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		ButtonManager buttonMana = new ButtonManager();
		
		if(getTitle().equals("Final grade")) {
			
			columNames = new String[] { "Name", "note" };
			tablemodel = new DefaultTableModel(columNames, 0);
			setTabla(new JTable(tablemodel));
			table = new JTable(tablemodel);

			String asig = TeacherView.codAsigna;
			mostrar.viewTeacherRa(asig);
			
		}else {
			
			columNames = new String[] { "Ra", "Name" };
			tablemodel = new DefaultTableModel(columNames, 0);
			setTabla(new JTable(tablemodel));
			table = new JTable(tablemodel);

			String asig = TeacherView.codAsigna;
			mostrar.viewTeacherRa(asig);
			
			lblAlumno = new JLabel("(Nombre del profesor)");
			lblAlumno.setBounds(36, 29, 205, 13);
			getContentPane().add(lblAlumno);

			btnShow = new JButton("Show");
			btnShow.setBounds(367, 109, 85, 21);
			btnShow.addActionListener(buttonMana);
			getContentPane().add(btnShow);
		}
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 301, 280);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);

		

		back = new JButton("Return");
		back.setBounds(367, 267, 85, 21);
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
			if (e.getSource() == back) {
				setVisible(false);
				new TeacherView();
			} else {
				if (num >= 0) {
					setVisible(false);

					if (e.getSource() == btnShow) {
						codRa = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();

						new TeacherRaGrade();

					}
				} else
					JOptionPane.showMessageDialog(RaProfessorView.this, "Debes seleccionar una asignaruta!!",
							"Asignatura no seleccionada", JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
