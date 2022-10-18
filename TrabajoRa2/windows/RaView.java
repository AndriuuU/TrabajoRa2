package windows;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import clases.Ra;
import clases.Subjects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class RaView extends JFrame{
	private JTextField txId, txName, txDescrip, txWeigth, txCodSubject;
	private JLabel lblId, lblName, lblDescription, lblCodsubject, lblWeighting;
	private JButton btReturn,btSave,btDelete;
	public static Ra ra;
	private Subjects subject;
	
	
	public RaView(String codSub) {
		
		super("RaView");
		setSize(448, 360);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);
		
		txId = new JTextField();
		txId.setBounds(165, 64, 116, 19);
		getContentPane().add(txId);
		txId.setColumns(10);
		
		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(69, 67, 45, 13);
		getContentPane().add(lblId);
		
		txName = new JTextField();
		txName.setColumns(10);
		txName.setBounds(165, 100, 183, 19);
		getContentPane().add(txName);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(69, 103, 45, 13);
		getContentPane().add(lblName);
		
		txDescrip = new JTextField();
		txDescrip.setBounds(165, 139, 191, 19);
		getContentPane().add(txDescrip);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(69, 142, 76, 13);
		getContentPane().add(lblDescription);
		
		txWeigth = new JTextField();
		txWeigth.setBounds(165, 182, 96, 19);
		getContentPane().add(txWeigth);
		
		lblWeighting = new JLabel("Weighting:");
		lblWeighting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeighting.setBounds(69, 185, 76, 13);
		getContentPane().add(lblWeighting);
		
		txCodSubject = new JTextField();
		txCodSubject.setBounds(165, 228, 96, 19);
		txCodSubject.setText(codSub);
		txCodSubject.setEditable(false);
		getContentPane().add(txCodSubject);
		
		lblCodsubject = new JLabel("CodSubject:");
		lblCodsubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodsubject.setBounds(69, 231, 76, 13);
		getContentPane().add(lblCodsubject);
		
		ReturnManager retuMan =new ReturnManager();
		
		btReturn = new JButton("Return");
		btReturn.setBounds(60, 279, 85, 21);
		btReturn.addActionListener(retuMan);
		getContentPane().add(btReturn);
		
		SaveManager saveMan =new SaveManager();
		
		btSave = new JButton("Save");
		btSave.setBounds(215, 279, 85, 21);
		btSave.addActionListener(saveMan);
		getContentPane().add(btSave);
		
		if(ra!=null) {
			txId.setText(ra.getId());
			txId.setEditable(false);
			txName.setText(ra.getName());
			txDescrip.setText(ra.getDescription());
			txWeigth.setText(Float.toString(ra.getWeighting()));
			
			btSave.setText("Modify");
			btSave.setBounds(150, 279, 85, 21);
			
			DeleteManager deleMan =new DeleteManager();
			btDelete =new JButton("Delete");
			btDelete.setBounds(240, 279, 85, 21);
			btDelete.addActionListener(deleMan);
			getContentPane().add(btDelete);
			
			
			ra=null;
			
		}
		
		
		setVisible(true);
	}
	
	public class ReturnManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
	
	public class SaveManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			//Ra raNew =new Ra(txId.getText(),txName.getText(),txDescrip.getText(),Float.parseFloat(txWeigth.getText()),);
			
		}
		
	}
	
	public class DeleteManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		
	}
}
