package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import clases.Subjects;
import clases.Teacher;
import connec.Connect;

@SuppressWarnings("serial")
public class Admin_Update_Subject extends JFrame {

	private JLabel jlcod, jlname, jlhours, jldni;
	private JTextField jtcod, jtname, jthours, jtDniTeacher;
	private JButton jbCancel, jbConfirm;
	private List<String> listDNI;
	private Connect c = new Connect();
	private static String dniProfessor;

	public Admin_Update_Subject(Subjects s) {
		super("Admin_Update_Subject");
		setSize(400, 300);
		WindowPreset.preset(this);

		listDNI = new ArrayList<String>();

		for (Teacher t : c.viewTeacher()) {
			listDNI.add(t.getDni());
		}

		String[] comboBox = new String[listDNI.size()];

		for (int i = 0; i < listDNI.size(); i++) {
			comboBox[i] = listDNI.get(i);
		}

		jtDniTeacher = new JTextField(10);
		jtDniTeacher.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jtDniTeacher.setToolTipText("Insert Teacher");
		jtDniTeacher.setBounds(182, 151, 140, 21);
		getContentPane().setLayout(null);
		getContentPane().add(jtDniTeacher);

		jthours = new JTextField(10);
		jthours.setFont(new Font("Tahoma", Font.PLAIN, 14));
		jthours.setToolTipText("Insert hours");
		jthours.setBounds(182, 110, 140, 19);
		getContentPane().add(jthours);

		jtname = new JTextField(10);
		jtname.setToolTipText("Insert Name");
		jtname.setBounds(182, 65, 140, 19);
		jtname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(jtname);

		jtcod = new JTextField(10);
		jtcod.setToolTipText("Insert COD");
		jtcod.setBounds(182, 26, 140, 19);
		jtcod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(jtcod);

		jlcod = new JLabel("COD: ");
		jlcod.setHorizontalAlignment(SwingConstants.RIGHT);
		jlcod.setBounds(58, 29, 84, 13);
		jlcod.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(jlcod);

		jlname = new JLabel("NAME: ");
		jlname.setHorizontalAlignment(SwingConstants.RIGHT);
		jlname.setBounds(58, 68, 84, 13);
		jlname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(jlname);

		jlhours = new JLabel("HOURS: ");
		jlhours.setHorizontalAlignment(SwingConstants.RIGHT);
		jlhours.setBounds(58, 113, 84, 13);
		jlhours.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(jlhours);

		jldni = new JLabel("PROFESSOR: ");
		jldni.setHorizontalAlignment(SwingConstants.RIGHT);
		jldni.setBounds(25, 153, 117, 13);
		jldni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(jldni);

		jbCancel = new JButton();
		jbCancel.setBounds(116, 210, 26, 26);
		WindowPreset.buttonPreset(jbCancel, "Cancel the Operation", "files\\return.png");
		jbConfirm = new JButton();
		jbConfirm.setBounds(219, 210, 26, 26);
		WindowPreset.buttonPreset(jbConfirm, "Insert the Subject.", "files\\confirm.png");

		getContentPane().add(jbCancel);
		getContentPane().add(jbConfirm);

		ButtonManager bm = new ButtonManager();

		jbCancel.addActionListener(bm);
		jbConfirm.addActionListener(bm);

		jtcod.setText(s.getCodSubject());
		jtcod.setEditable(false);
		jtname.setText(s.getName());
		jthours.setText(String.valueOf(s.getHours()));
		jtDniTeacher.setText(s.getDniProfessor());
		jtDniTeacher.setEditable(false);

		setVisible(true);
	}

	class ButtonManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			JButton b = (JButton) e.getSource();

			if (b.getToolTipText().equals("Cancel the Operation")) {
				dispose();
			} else if (b.getToolTipText().equals("Insert the Subject.")) {
				Subjects s = new Subjects(jtcod.getText(), jtname.getText(), Integer.parseInt(jthours.getText()),
						dniProfessor);
				c.updateSubject(s);
				AdminWindow.changeTable(0);
				dispose();
			} else {
				JOptionPane.showMessageDialog(rootPane, "Error");

			}

		}

	}

}
