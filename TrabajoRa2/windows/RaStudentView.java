package windows;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import connec.Connect;


@SuppressWarnings("serial")
public class RaStudentView extends JFrame {
	private TableModel model ;
	private String[] columNames ;
	private JTable table;
	private JButton back;
	private JLabel lblAlumno;


	public RaStudentView() {

		super("RaStudentView");
		setSize(503, 400);
		WindowPreset.preset(this);
		setLayout(null);
		
		Connect mostrar=new Connect();
		Map<String,String> ras=mostrar.viewStudentsRA("123456789A");
		
		for (String key : ras.keySet()) {
			System.out.println(key);
			
		}
		
		columNames = new String[] {"Ra","Name","Grade"};
		model = new DefaultTableModel (columNames, 0);
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 63, 301, 280);
		add(scrollPane);
		scrollPane.setViewportView(table);
		
		lblAlumno = new JLabel("(Nombre del alumno)");
		lblAlumno.setBounds(36, 29, 205, 13);
		getContentPane().add(lblAlumno);
		
		back = new JButton("Return");
		back.setBounds(367, 267, 85, 21);
		ButtonManager buttonMana =new ButtonManager();
		back.addActionListener(buttonMana);
		getContentPane().add(back);
		
		setVisible(true);
		
	}
	
	public class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			new StudentView();
			setVisible(false);
		}
		
	}
}