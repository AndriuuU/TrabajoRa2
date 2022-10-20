package windows;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class RaView extends JFrame{
	
	private JTextField txId, txName, txDescrip, txWeigth, txCodSubject;
	private JLabel lblId, lblName, lblDescription, lblCodsubject, lblWeighting;
	private JButton btReturn,btSave,btDelete,btModify;
	private JComboBox<String> cbSubject;
	public static String codRa,codSub;
	Connect c=new Connect();
	private Subjects sub;
	
	public RaView() {
		
		super("RaView");
		setSize(448, 360);
		WindowPreset.preset(this);
		getContentPane().setLayout(null);
		
		txId = new JTextField();
		txId.setBounds(165, 64, 116, 19);
		txId.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(txId);
		txId.setColumns(10);
		
		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(69, 67, 45, 13);
		getContentPane().add(lblId);
		
		txName = new JTextField();
		txName.setColumns(10);
		txName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txName.setBounds(165, 100, 183, 19);
		getContentPane().add(txName);
		
		lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblName.setBounds(69, 103, 45, 13);
		getContentPane().add(lblName);
		
		txDescrip = new JTextField();
		txDescrip.setBounds(165, 139, 191, 19);
		txDescrip.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(txDescrip);
		
		lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDescription.setBounds(69, 142, 76, 13);
		getContentPane().add(lblDescription);
		
		txWeigth = new JTextField();
		txWeigth.setBounds(165, 182, 96, 19);
		txWeigth.setFont(new Font("Tahoma", Font.PLAIN, 13));
		getContentPane().add(txWeigth);
		
		lblWeighting = new JLabel("Weighting:");
		lblWeighting.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWeighting.setBounds(69, 185, 76, 13);
		getContentPane().add(lblWeighting);
		
		ManeComboBox maneBox=new ManeComboBox();
		cbSubject = new JComboBox<String>();
		cbSubject.setBounds(165, 228, 180, 25);
		cbSubject.setFont(new Font("Tahoma", Font.PLAIN, 13));
		cbSubject.addItemListener(maneBox);
		getContentPane().add(cbSubject);
		
		List<String> listaString =new ArrayList<>(); 
		List<Subjects> subList=c.viewSubjects();
		for(Subjects s: subList) {
			listaString.add(s.getCodSubject()+", "+s.getName());
		}
		cbSubject.setModel(new DefaultComboBoxModel<String>(listaString.toArray(new String[0])));
		
		//
		
		lblCodsubject = new JLabel("CodSubject:");
		lblCodsubject.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodsubject.setBounds(69, 231, 76, 13);
		getContentPane().add(lblCodsubject);
		
		ReturnManager retuMan =new ReturnManager();
		
		btReturn = new JButton();
		btReturn.setBounds(60, 279, 85, 25);
		WindowPreset.buttonPreset(btReturn, "Back to menu", "files\\return.png");
		btReturn.addActionListener(retuMan);
		getContentPane().add(btReturn);
		
		SaveManager saveMan =new SaveManager();
		
		btSave = new JButton();
		btSave.setBounds(215, 279, 85, 25);
		WindowPreset.buttonPreset(btSave, "Confirm", "files\\confirm.png");
		btSave.addActionListener(saveMan);
		getContentPane().add(btSave);
		
		if(codRa!=null) {
			Ra ra=c.getRa(codRa);
			txId.setText(ra.getId());
			txId.setEditable(false);
			txName.setText(ra.getName());
			txDescrip.setText(ra.getDescription());
			txWeigth.setText(Float.toString(ra.getWeighting()));
			
			getContentPane().remove(btSave);
			ModifyManager modiMana=new ModifyManager();
			btModify =new JButton();
			btModify.setBounds(150, 279, 85, 25);
			WindowPreset.buttonPreset(btModify, "Update Ra.", "files\\update.png");
			btModify.addActionListener(modiMana);
			getContentPane().add(btModify);
			
			DeleteManager deleMan =new DeleteManager();
			btDelete =new JButton();
			btDelete.setBounds(240, 279, 85, 25);
			WindowPreset.buttonPreset(btDelete, "Delete Ra.", "files\\delete.png");
			btDelete.addActionListener(deleMan);
			getContentPane().add(btDelete);
			
			
			codRa=null;
			
		}
		
		
		setVisible(true);
	}
	
	public class ManeComboBox implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			String seleccionado=(String)cbSubject.getSelectedItem();
			String[] selec=seleccionado.split(", ");
			sub=c.getSubjectRa(selec[0]);
			
		}

		
		
	}
	
	public class ReturnManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			RaProfessorView.codRa=null;
			new RaProfessorView();
			setVisible(false);
			
		}
		
	}
	
	public class SaveManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			try {
				float number=Float.parseFloat(txWeigth.getText());
				
				if(c.getRa(txId.getText())==null) {
					
					Ra raNew =new Ra(txId.getText(),txName.getText(),txDescrip.getText(),number,sub);
					c.insertRa(raNew);
					
					RaProfessorView.codRa=null;
					new RaProfessorView();
					setVisible(false);
				}else
					JOptionPane.showMessageDialog(RaView.this, "Codigo ya en uso!", "Error id del RA",
							JOptionPane.WARNING_MESSAGE);
				
			}catch (Exception ex) {
				JOptionPane.showMessageDialog(RaView.this, "Has introducido mal los datos", "ERROR!",
						JOptionPane.WARNING_MESSAGE);
			}
			
			
			
			
		}
		
	}
	
	public class ModifyManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			float number=Float.parseFloat(txWeigth.getText());
			c.updateRa(new Ra(txId.getText(),txName.getText(),txDescrip.getText(),number,sub));
			new RaProfessorView();
			setVisible(false);
			
		}
		
	}
	
	public class DeleteManager implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RaProfessorView.codRa=null;
			c.deleteRa(txId.getText());
			new RaProfessorView();
			setVisible(false);
		}
		
	}
	
}
