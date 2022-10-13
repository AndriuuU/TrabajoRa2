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
	private JButton back;
	private JLabel lblAlumno;
	private boolean salir=false;

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

		
		String ra = RaProfessorView.codRa;
		if (ra != null) {
			mostrar.viewTeacherRaGrade(ra);
			
			salir=true;
			lblAlumno = new JLabel(ra);
		}else {
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
		
		back = new JButton("Return");
		back.setBounds(367, 267, 85, 21);
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
			if(salir) {
				new RaProfessorView();
			}else
				new TeacherView();
			
			setVisible(false);
		}

	}
}
