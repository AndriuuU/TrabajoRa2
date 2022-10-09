package windows;



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
		
		
		setVisible(true);
	}
}
