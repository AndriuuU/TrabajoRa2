package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.Ra;
import connec.Connect;

@SuppressWarnings("serial")
public class Admin_Details_RA extends JFrame {

	private JTextField txId, txName, txDescrip, txWeigth, txCodSubject;
	private JLabel lblId, lblName, lblDescription, lblCodsubject, lblWeighting;
	private JButton btReturn;
	public static String codRa, codSub;
	public static int option = 0;
	Connect c = new Connect();

	public Admin_Details_RA() {

		super("Details_RA");
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

		txCodSubject = new JTextField();
		txCodSubject.setBounds(165, 228, 180, 25);
		txCodSubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		getContentPane().add(txCodSubject);

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

		Ra ra = c.getRa(codRa);
		txId.setText(ra.getId());
		txId.setEditable(false);
		txName.setText(ra.getName());
		txName.setEditable(false);
		txDescrip.setText(ra.getDescription());
		txDescrip.setEditable(false);
		txWeigth.setText(Float.toString(ra.getWeighting()));
		txWeigth.setEditable(false);
		txCodSubject.setText(ra.getCodSubject().getCodSubject());
		txCodSubject.setEditable(false);

		setVisible(true);
	}

}
