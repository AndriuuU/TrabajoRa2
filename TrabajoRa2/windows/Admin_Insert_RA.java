package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import clases.Ra;
import clases.Subjects;
import connec.Connect;

@SuppressWarnings("serial")
public class Admin_Insert_RA extends JFrame {

	private JTextField txId, txName, txDescrip, txWeigth;
	private JLabel lblId, lblName, lblDescription, lblCodsubject, lblWeighting;
	private JButton btReturn, btSave;
	private JComboBox<String> cbSubject;
	public static String codRa, codSub;
	Connect c = new Connect();

	public Admin_Insert_RA() {

		super("Admin_Insert_RA");
		setSize(450, 360);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);

		txId = new JTextField();
		txId.setBounds(165, 64, 116, 19);
		txId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txId);
		txId.setColumns(10);

		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(69, 67, 45, 13);
		getContentPane().add(lblId);

		txName = new JTextField();
		txName.setColumns(10);
		txName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txName.setBounds(165, 100, 183, 19);
		getContentPane().add(txName);

		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(69, 103, 45, 13);
		getContentPane().add(lblName);

		txDescrip = new JTextField();
		txDescrip.setBounds(165, 139, 191, 19);
		txDescrip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txDescrip);

		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(69, 142, 76, 13);
		getContentPane().add(lblDescription);

		txWeigth = new JTextField();
		txWeigth.setBounds(165, 182, 96, 19);
		txWeigth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txWeigth);

		lblWeighting = new JLabel("Weighting:");
		lblWeighting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeighting.setBounds(69, 185, 76, 13);
		getContentPane().add(lblWeighting);

		cbSubject = new JComboBox<String>();
		cbSubject.setBounds(165, 228, 180, 25);
		cbSubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(cbSubject);

		List<String> listaString = new ArrayList<>();
		List<Subjects> subList = c.viewSubjects();
		for (Subjects s : subList) {
			listaString.add(s.getCodSubject());
		}
		cbSubject.setModel(new DefaultComboBoxModel<String>(listaString.toArray(new String[0])));

		//

		lblCodsubject = new JLabel("CodSubject:");
		lblCodsubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodsubject.setBounds(69, 231, 76, 13);
		getContentPane().add(lblCodsubject);

		btReturn = new JButton();
		btReturn.setBounds(60, 279, 85, 25);
		WindowPreset.buttonPreset(btReturn, "Back to menu", "files\\return.png");
		btReturn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();

			}
		});
		getContentPane().add(btReturn);

		SaveManager saveMan = new SaveManager();

		btSave = new JButton();
		btSave.setBounds(215, 279, 85, 25);
		WindowPreset.buttonPreset(btSave, "Confirm", "files\\confirm.png");
		btSave.addActionListener(saveMan);
		getContentPane().add(btSave);

		setVisible(true);
	}

	public class SaveManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Subjects sub = new Subjects();
			sub = c.getSubjectRa(cbSubject.getSelectedItem().toString());
			try {
				float number = Float.parseFloat(txWeigth.getText());

				if (c.getRa(txId.getText()) == null) {
					Ra raNew = new Ra(txId.getText(), txName.getText(), txDescrip.getText(), number, sub);
					c.insertRa(raNew);
					AdminWindow.changeTable(1);
					dispose();
				} else
					JOptionPane.showMessageDialog(Admin_Insert_RA.this, "A RA with this id exist.", "Error id del RA",
							JOptionPane.WARNING_MESSAGE);

			} catch (Exception ex) {
				JOptionPane.showMessageDialog(Admin_Insert_RA.this, "Check if you are inserting properly each field",
						"ERROR!", JOptionPane.WARNING_MESSAGE);
			}

		}

	}

}
