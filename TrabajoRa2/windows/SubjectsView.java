package windows;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import connec.Connect;

@SuppressWarnings("serial")
public class SubjectsView extends JFrame {
	Connect c = new Connect();
	ButtonGroup group = new ButtonGroup();
	JButton aceptar;
	JPanel panel ;
	JPanel panel2;
	public static JCheckBox[] jcSubjects;
	public static List<String> subjectCod = new ArrayList<String>();
	int pos = 0;
	
	public SubjectsView() throws SQLException {
		super("List of Subjects");
		setSize(300, 300);
		WindowPreset.preset(this);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		
		
		int tamaño = c.boxFiller().size();
		panel = new JPanel(new GridLayout(tamaño,1));
		panel2 = new JPanel(new BorderLayout());
		add(panel,BorderLayout.CENTER);
		add(panel2,BorderLayout.SOUTH);
		jcSubjects = new JCheckBox[tamaño];
		
		
		Iterator<String> iterator = c.subjectContent.values().iterator();
	    while (iterator.hasNext()) {
	    	jcSubjects[pos] = new JCheckBox();
	    	jcSubjects[pos].setText(iterator.next());
	    	panel.add(jcSubjects[pos]);
	    	pos++;
	    }
//		for(int i = 0 ; i<c.boxFiller().size();i++) {
//			jcSubjects[i] = new JCheckBox();
//			jcSubjects[i].setText(c.boxFiller().get(i).toString());
//			panel.add(jcSubjects[i]);
//		}
		
		aceptar = new JButton("Aceptar");
		panel2.add(aceptar);
		
		driverAccept btnAccept = new driverAccept();
		aceptar.addActionListener(btnAccept);
		setVisible(true);
	}
	
	public class driverAccept implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}
}
