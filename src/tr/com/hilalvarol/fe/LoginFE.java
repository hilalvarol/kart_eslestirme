package tr.com.hilalvarol.fe;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import tr.com.hilalvarol.dal.AccountsDAL;
import tr.com.hilalvarol.dal.KategoriDAL;
import tr.com.hilalvarol.dal.PersonelDAL;
import tr.com.hilalvarol.interfaces.FeInterfaces;
import tr.com.hilalvarol.types.PersonelEntity;

public class LoginFE extends JDialog implements FeInterfaces {

	public static JComboBox emailBox;
	public LoginFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
        JPanel panel = initPanel();
		
	    panel.setBorder(BorderFactory.createTitledBorder("Giriş"));
		add(panel);
		setTitle("Lütfen Giriş Yapınız");
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(HIDE_ON_CLOSE);

		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new GridLayout(3,2));
		
		JLabel emailLabel = new JLabel("Email:", JLabel.RIGHT);
		panel.add(emailLabel);
		
		emailBox = new JComboBox(new PersonelDAL().GetAll().toArray());
		panel.add(emailBox);
		
		JLabel passwordLabel = new JLabel("Şifre:", JLabel.RIGHT);
		panel.add(passwordLabel);
		JPasswordField passwordField = new JPasswordField(15);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("Giriş Yap");
		panel.add(loginButton);
		JButton iptalButton = new JButton("İptal");
		panel.add(iptalButton);
		
		iptalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
				
			}
		});
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				
				PersonelEntity entity = (PersonelEntity) emailBox.getSelectedItem();
				String  sifre = passwordField.getText();
				if(new AccountsDAL().GetPersonelIdveSifre(entity.getId(), sifre).getId()!=0 ) {
					new AnaPencereFE();
				}else {
					JOptionPane.showMessageDialog(null, "Giriş Başarısız");
				}
				
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
