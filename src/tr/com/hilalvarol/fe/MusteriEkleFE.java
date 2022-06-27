package tr.com.hilalvarol.fe;

import java.awt.BorderLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.com.hilalvarol.dal.MusteriDAL;
import tr.com.hilalvarol.dal.PersonelDAL;
import tr.com.hilalvarol.dal.YetkilerDAL;
import tr.com.hilalvarol.interfaces.FeInterfaces;
import tr.com.hilalvarol.types.MusteriEntity;
import tr.com.hilalvarol.types.PersonelEntity;
import tr.com.hilalvarol.types.YetkilerEntity;

public class MusteriEkleFE extends JDialog implements FeInterfaces {

	public MusteriEkleFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();

		panel.setBorder(BorderFactory.createTitledBorder("Müşteri Ekle"));
		add(panel);
		setTitle("Müşteri Ekle");
		pack();
		setModalityType(DEFAULT_MODALITY_TYPE);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE); 

		
	}

	@Override
	public JPanel initPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel fieldPanel = new JPanel(new GridLayout(5,2));
        JPanel buttonPanel = new JPanel(new GridLayout(1,2));

		
		JLabel adiSoyadiLabel = new JLabel("Adı Soyadı:", JLabel.RIGHT);
		fieldPanel.add(adiSoyadiLabel);
		JTextField adiSoyadiField = new JTextField(15);
		fieldPanel.add(adiSoyadiField);
		
		JLabel telefonLabel = new JLabel("Telefon:", JLabel.RIGHT);
		fieldPanel.add(telefonLabel);
		JTextField telefonField = new JTextField(15);
		fieldPanel.add(telefonField);
		
		JLabel sehirSecLabel = new JLabel("Şehir Seç:", JLabel.RIGHT);
		fieldPanel.add(sehirSecLabel);
		JComboBox sehirlerBox = new JComboBox(new MusteriDAL().SehirGetir());
		fieldPanel.add(sehirlerBox);
		

		JLabel adresLabel = new JLabel("Adres:", JLabel.RIGHT);
		fieldPanel.add(adresLabel);
		JTextArea adresArea = new JTextArea(7,1);
		JScrollPane pane = new JScrollPane(adresArea);
		pane.setBorder(BorderFactory.createTitledBorder("Adres Bilgisi"));
		
		JButton kaydetButton = new JButton("Kaydet");
		buttonPanel.add(kaydetButton);
		JButton iptalButton = new JButton("İptal");
		buttonPanel.add(iptalButton); 
		
		panel.add(fieldPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
	    kaydetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MusteriEntity entity = new MusteriEntity();
				
				entity.setAdiSoyadi(adiSoyadiField.getText());
				entity.setTelefon(telefonField.getText());
				entity.setSehirId(entity.getSehirId());
				entity.setAdres(adresArea.getText());
				
				new MusteriDAL().Insert(entity);
				JOptionPane.showMessageDialog(null, entity.getAdiSoyadi() +" adlı müşteri başarılı bir şekilde eklendi.");
				
				
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
