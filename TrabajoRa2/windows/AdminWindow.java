package windows;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

@SuppressWarnings("serial")
public class AdminWindow extends JFrame {

	private JComboBox<String> comboBox;
	private JPanel jptable, jpButton;
	private JTable jtable;
	private JButton jbInsert, jbDelete, jbDetails, jbUpdate, jbDis;
	private String[] options = { "Courses", "Professors", "Students" };
	private static int option = 0;
	private JScrollPane scrollPane;

	public AdminWindow() {

		super("AdminWindow");
		setSize(450, 300);
		WindowPreset.preset(this);

		// añadir (options) como parametro del constructor, una vez metido no deja abrir
		// windowBuilder
		comboBox = new JComboBox<String>();
		comboBox.setMaximumRowCount(3);
		comboBox.setToolTipText("Selector");
		comboBox.setBounds(10, 10, 130, 20);
		comboBox.setSelectedIndex(0);
		getContentPane().add(comboBox);
		comboBox.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				JComboBox<?> jcb1 = (JComboBox<?>) e.getSource();
				option = jcb1.getSelectedIndex();

			}
		});

		jptable = new JPanel();
		jptable.setBounds(10, 35, 290, 220);
		jpButton = new JPanel();
		jpButton.setBounds(309, 24, 117, 229);

		jtable = new JTable();
		jtable.setBounds(148, 6, 0, 0);
		scrollPane = new JScrollPane(jtable);
		scrollPane.setBounds(0, 0, 290, 220);
		jptable.add(scrollPane);

		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setRowHeaderView(scrollBar);

		jbInsert = new JButton("Insert");
		jbInsert.setBounds(10, 25, 97, 21);
		jbInsert.setToolTipText("Insert new row.");
		jbDelete = new JButton("Delete");
		jbDelete.setBounds(10, 56, 97, 21);
		jbDelete.setToolTipText("Delete selected row.");
		jbDetails = new JButton("Details");
		jbDetails.setBounds(10, 87, 97, 21);
		jbDetails.setToolTipText("Detail selected row.");
		jbUpdate = new JButton("Modify");
		jbUpdate.setBounds(10, 118, 97, 21);
		jbUpdate.setToolTipText("Modify selected row.");
		jbDis = new JButton("Disconnect");
		jbDis.setBounds(10, 198, 97, 21);
		jbDis.setToolTipText("Go to Login.");
		jptable.setLayout(null);

		jpButton.setLayout(null);
		jpButton.add(jbInsert);
		jpButton.add(jbDelete);
		jpButton.add(jbDetails);
		jpButton.add(jbUpdate);
		jpButton.add(jbDis);
		getContentPane().setLayout(null);

		getContentPane().add(jptable);
		getContentPane().add(jpButton);

		setVisible(true);

	}
}
