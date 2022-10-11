package windows;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class StudentView extends JFrame {

	private TableModel model ;
	private String[] columNames ;
	private JTable table;
	private JButton back ,btnLook;
	private JLabel lblAlumno;
	
	public StudentView() {
		super("StudentView");
		setSize(530, 400);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);
		
		columNames = new String[] {"Subject","Grade"};
		model = new DefaultTableModel (columNames, 0);
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 301, 258);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		
		lblAlumno = new JLabel("(Nombre del alumno)");
		lblAlumno.setBounds(36, 29, 205, 13);
		getContentPane().add(lblAlumno);
		
		ButtonManager buttonMana =new ButtonManager();
		
		btnLook = new JButton("Show Ra");
		btnLook.setBounds(367, 100, 85, 21);
		btnLook.addActionListener(buttonMana);
		getContentPane().add(btnLook);
		
		back = new JButton("Return");
		back.setBounds(367, 267, 85, 21);
		back.addActionListener(buttonMana);
		getContentPane().add(back);
		
		setVisible(true);
	}
	
	private class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			setVisible(false);
			
			if(e.getSource()==btnLook) {
				new RaStudentView();
				
			}else if(e.getSource()==back) {
				
			}
		}
	}
}
