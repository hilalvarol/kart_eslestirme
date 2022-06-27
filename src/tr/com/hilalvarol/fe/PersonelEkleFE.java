package tr.com.hilalvarol.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import tr.com.hilalvarol.dal.KategoriDAL;
import tr.com.hilalvarol.dal.PersonelDAL;
import tr.com.hilalvarol.dal.UrunlerDAL;
import tr.com.hilalvarol.interfaces.FeInterfaces;
import tr.com.hilalvarol.types.KategoriEntity;
import tr.com.hilalvarol.types.PersonelEntity;
import tr.com.hilalvarol.types.UrunlerEntity;


public class PersonelEkleFE extends JDialog implements FeInterfaces {

	public PersonelEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Personel Ekle"));
		add(panel);
		setTitle("Personel Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE); 

		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel adiSoyadiLabel = new JLabel("Adı Soyadı:", JLabel.RIGHT);
		panel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(10);
		panel.add(adiSoyadiField);
		
		JLabel eMailLabel = new JLabel("Email:", JLabel.RIGHT);
		panel.add(eMailLabel);
		JTextField eMailField = new JTextField(10);
		panel.add(eMailField);
		

		JButton kaydetButton = new JButton("Kaydet");
		panel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton); 
		
		kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelEntity entity = new PersonelEntity();
				entity.setAdiSoyadi(adiSoyadiField.getText());
				entity.setEmail(eMailField.getText());
				
				new PersonelDAL().Insert(entity);
				JOptionPane.showMessageDialog(null, entity.getAdiSoyadi() +" adlı personel başarılı bir şekilde eklendi.");
				
				
			}
		});
		
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JTabbedPane initTabs() {
		// TODO Auto-generated method stub
		return null;
	}

}
