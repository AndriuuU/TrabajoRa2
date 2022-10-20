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

import clases.Teacher;
import connec.Connect;

@SuppressWarnings("serial")
public class RaProfessorView extends JFrame {

	public String[] columNames;
	static public JTable table;
	static public DefaultTableModel tablemodel;
	private JButton back, btnRa, btnShow;
	private JLabel lblAlumno;
	static public String codRa;
	private String asig;
	public boolean entra;

	Connect mostrar = new Connect();

	public RaProfessorView() {

		setTitle("RaProfesorView");
		setSize(503, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

	
		//columNames = new String[] { "Ra", "Name" };
		//tablemodel = new DefaultTableModel(columNames, 0);
		changeTable();
		//setTabla(new JTable(tablemodel));
		//table = new JTable(tablemodel);

		

		lblAlumno = new JLabel(asig);
		lblAlumno.setBounds(36, 29, 205, 21);
		getContentPane().add(lblAlumno);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 301, 280);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		ButtonManager buttonMana = new ButtonManager();
		
		btnShow = new JButton();
		btnShow.setBounds(367, 109, 90, 25);
		WindowPreset.buttonPreset(btnShow, "Show all grade RA", "files\\showMore.png");
		btnShow.addActionListener(buttonMana);
		getContentPane().add(btnShow);

		btnRa = new JButton();
		btnRa.setBounds(360, 150, 107, 25);
		WindowPreset.buttonPreset(btnRa, "Insert or edit or delete RA", "files\\insert.png");
		btnRa.addActionListener(buttonMana);
		getContentPane().add(btnRa);

		back = new JButton();
		back.setBounds(367, 267, 85, 21);
		WindowPreset.buttonPreset(back, "Back to menu", "files\\return.png");
		back.addActionListener(buttonMana);
		getContentPane().add(back);

		setVisible(true);

	}
	public void changeTable() {
		columNames = new String[] { "Ra", "Name" };
		tablemodel = new DefaultTableModel(columNames, 0);
		asig = TeacherView.codAsigna;
		mostrar.viewTeacherRa(asig);
		setTabla(new JTable(tablemodel));
		table = new JTable(tablemodel);
		
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
				codRa=null;
				setVisible(false);
				new TeacherView();
			} else {
				if (num >= 0) {
					setVisible(false);

					if (e.getSource() == btnShow) {
						codRa = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();

						new TeacherRaGrade();

					}else if(e.getSource()== btnRa) {
						codRa = getTabla().getValueAt(getTabla().getSelectedRow(), 0).toString();
						RaView.codRa=codRa;
						new RaView();
						
					}
				} else if(e.getSource()== btnRa) {
					codRa=null;
					
					new RaView();
					
				}else
					JOptionPane.showMessageDialog(RaProfessorView.this, "SELECT RA!!",
							"¡ERROR!", JOptionPane.ERROR_MESSAGE);
			}
			
			changeTable();
			
		}
		
	}
}
