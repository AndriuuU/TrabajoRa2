package windows;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


@SuppressWarnings("serial")
public class RaStudentView extends JFrame {
	private TableModel model ;
	private String[] columNames ;
	private JTable table;
	private JButton back;


	public RaStudentView() {

		super("RaStudentView");
		setSize(500, 350);
		WindowPreset.preset(this);
		setLayout(null);
		
		columNames = new String[] {"Asignatura","Nota"};
		model = new DefaultTableModel (columNames, 0);
		table = new JTable(model);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(27, 30, 301, 258);
		add(scrollPane);
		scrollPane.setViewportView(table);
		
		back = new JButton("Return");
		back.setBounds(367, 267, 85, 21);
		ButtonManager buttonMana =new ButtonManager();
		back.addActionListener(buttonMana);
		back.setVisible(true);
		
		setVisible(true);
		
	}
	
	public class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			setVisible(false);
		}
		
	}
}
