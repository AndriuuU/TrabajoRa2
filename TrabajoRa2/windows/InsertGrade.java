package windows;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import connec.Connect;

import javax.swing.JButton;

public class InsertGrade extends JFrame {
	private JLabel lblName, lblGrade, lblCod;
	private JTextField txName, txgrade;
	private JButton btnReturn, btnModify;

	Connect c = new Connect();

	public InsertGrade(String cod, String name, float grade) {

		super("Modify RA");
		setSize(406, 361);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		lblCod = new JLabel(cod);
		lblCod.setBounds(124, 32, 78, 13);
		getContentPane().add(lblCod);

		lblName = new JLabel("Name:");
		lblName.setBounds(80, 80, 68, 19);
		getContentPane().add(lblName);

		txName = new JTextField(name);
		txName.setBounds(158, 79, 96, 22);
		txName.setEditable(false);
		getContentPane().add(txName);

		lblGrade = new JLabel("Grade:");
		lblGrade.setBounds(80, 133, 68, 19);
		getContentPane().add(lblGrade);

		txgrade = new JTextField(grade + "");
		txgrade.setBounds(158, 132, 96, 22);
		getContentPane().add(txgrade);

		ModifyManager ModifyM = new ModifyManager();
		btnReturn = new JButton("");
		btnReturn.setBounds(43, 194, 85, 25);
		WindowPreset.buttonPreset(btnReturn, "Back to Ra Grade", "files\\return.png");
		btnReturn.addActionListener(ModifyM);
		getContentPane().add(btnReturn);

		btnModify = new JButton("");
		btnModify.setBounds(204, 194, 85, 25);
		WindowPreset.buttonPreset(btnModify, "Update Grade ra", "files\\update.png");
		btnModify.addActionListener(ModifyM);
		getContentPane().add(btnModify);

		setVisible(true);

	}

	private class ModifyManager implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnModify)
				c.updateGradeRa(c.getInfoStudentDNI(txName.getText()), lblCod.getText(),
						Float.parseFloat(txgrade.getText()));

			new TeacherRaGrade();
			setVisible(false);

		}
	}

}
