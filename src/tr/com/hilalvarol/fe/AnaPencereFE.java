package tr.com.hilalvarol.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import tr.com.hilalvarol.complex.types.SatisEntityComplex;
import tr.com.hilalvarol.complex.types.StockEntityComplex;
import tr.com.hilalvarol.complex.types.StokEntityTotalComplex;
import tr.com.hilalvarol.dal.SatisDAL;
import tr.com.hilalvarol.dal.StokDAL;
import tr.com.hilalvarol.dal.UrunlerDAL;
import tr.com.hilalvarol.interfaces.FeInterfaces;
import tr.com.hilalvarol.types.PersonelEntity;
import tr.com.hilalvarol.types.SatisEntity;
import tr.com.hilalvarol.types.StokEntity;
import tr.com.hilalvarol.types.UrunlerEntity;
import tr.com.hilalvarol.utilities.MenulerCom;

public class AnaPencereFE extends JFrame implements FeInterfaces {

	public AnaPencereFE() {
		initPencere();
	}

	@Override
	public void initPencere() {
		JPanel panel = initPanel();
		JMenuBar bar = initBar();
		
		add(panel);
		setJMenuBar(bar);
		setTitle("Depo Yönetim Otomasyonu"); // başlık
		setSize(800,600);
		setVisible(true); // pencerenin açılıp görülmesini sağlar
		setLocationRelativeTo(null); //pencerenin konumunun ortada olmasını belirttik
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
	}

	@Override
	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JTabbedPane pane = initTabs();
		panel.add(pane, BorderLayout.CENTER);
		return panel;
	}

	@Override
	public JMenuBar initBar() {
		JMenuBar bar = MenulerCom.initBar();
		
		return bar;
	}

	@Override
	public JTabbedPane initTabs() {
		JTabbedPane pane = new JTabbedPane();
		
		
		ImageIcon icon = new ImageIcon("icons/stock.png");
		ImageIcon icon2 = new ImageIcon("icons/stock.png"); 
		
		
		
		JPanel stokPanel = new JPanel(new BorderLayout());
		JPanel satisPanel = new JPanel(new BorderLayout());
		
		/*Stok Islemleri*/
		JPanel stokSolPanel = new JPanel(new BorderLayout());
		JPanel stokSolUstPanel = new JPanel(new GridLayout(5,2));
		JPanel stokSolAltPanel = new JPanel();
		
		stokSolPanel.setBorder(BorderFactory.createTitledBorder("Stok İşlemleri"));
		Object [] stokKolonlar = {"Id","Ürün Adı","Personel Adı","Adet","Tarih","Toplam"};
		DefaultTableModel model = new DefaultTableModel(stokKolonlar,0);		
		JTable table = new JTable(model);
		JScrollPane stokTablePane = new JScrollPane(table);
		
		for(StockEntityComplex contract : new StokDAL().GetAllStock()) {
			model.addRow(contract.getVeriler());
		}
			
			
		JLabel stokUrunAdiLabel = new JLabel("Ürün Adı: ", JLabel.RIGHT);
		stokSolUstPanel.add(stokUrunAdiLabel);
		JComboBox stokUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		stokSolUstPanel.add(stokUrunAdiBox);
		
		JLabel stokAdetLabel = new JLabel("Adet: ", JLabel.RIGHT);
		stokSolUstPanel.add(stokAdetLabel);
		JTextField stokAdetField = new JTextField(10);
		stokSolUstPanel.add(stokAdetField);
		JLabel stokTarihiLabel = new JLabel("Stok Tarihi:",JLabel.RIGHT);
		stokSolUstPanel.add(stokTarihiLabel);
		JDateChooser stokTarihi = new JDateChooser();	
		stokSolUstPanel.add(stokTarihi);
		
		JButton stokEkleButton = new JButton("Stok Ekle");
		stokSolUstPanel.add(stokEkleButton);
		
		JButton stokYenileButton = new JButton("Yenile");
		stokSolUstPanel.add(stokYenileButton);
		
		stokYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for(int i=0;i<satir;i++) {
					model.removeRow(0);
				}
				
				for(StockEntityComplex compEntity : new StokDAL().GetAllStock()) {
					
					model.addRow(compEntity.getVeriler());
				}
				
			}
		});
				
		JButton stokTotalButton = new JButton("Stok Toplam Ürün");
		stokSolUstPanel.add(stokTotalButton);
		
		stokTotalButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = model.getRowCount();
				for (int i = 0; i < satir; i++) {
					model.removeRow(0);
				}
				for (StokEntityTotalComplex total : new StokDAL().GetTotalStock()) {
					model.addRow(total.getVeriler());
				}
			}
		});
		
	
		stokEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				StokEntity entity = new StokEntity();
				UrunlerEntity uEntity = (UrunlerEntity) stokUrunAdiBox.getSelectedItem();
				PersonelEntity pEntity = (PersonelEntity) LoginFE.emailBox.getSelectedItem();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(stokTarihi.getDate());
				
				entity.setPersonelId(pEntity.getId());
				entity.setUrunId(uEntity.getId());
			    entity.setTarih(date);
			    entity.setAdet(Integer.parseInt(stokAdetField.getText()));
				
				new StokDAL().Insert(entity);
				
				JOptionPane.showMessageDialog(null, uEntity.getAdi()+ " adlı ürün eklenmiştir.");
				
		/*		int satir = model.getRowCount();
				for(int i=0;i<satir;i++) {
					model.removeRow(0);
				}
				
				for(StockEntityComplex compEntity : new StokDAL().GetAllStock()) {
					
					model.addRow(compEntity.getVeriler());
				} */
		
			}
		});
		
		
		/*Satış İşlemleri*/
		JPanel satisSagPanel = new JPanel(new BorderLayout());
		JPanel satisSagUstPanel = new JPanel(new GridLayout(4,2));
		JPanel satisSagAltPanel = new JPanel();
		
		satisSagPanel.setBorder(BorderFactory.createTitledBorder("Satış İşlemleri"));
		Object [] satisKolonlar = {"Id","Personel Adı","Ürün Adı","Adet","Tarih"};
		DefaultTableModel satisModel = new DefaultTableModel(satisKolonlar,0);		
		JTable satisTable = new JTable(satisModel);
		JScrollPane satisTablePane = new JScrollPane(satisTable);
		
		JLabel satisUrunAdiLabel = new JLabel("Ürün Adı: ", JLabel.RIGHT);
		satisSagUstPanel.add(satisUrunAdiLabel);
		JComboBox satisUrunAdiBox = new JComboBox(new UrunlerDAL().GetAll().toArray());
		satisSagUstPanel.add(satisUrunAdiBox);
		
		JLabel satisAdetLabel = new JLabel("Adet: ", JLabel.RIGHT);
		satisSagUstPanel.add(satisAdetLabel);
		JTextField satisAdetField = new JTextField(10);
		satisSagUstPanel.add(satisAdetField);
		JLabel satisTarihiLabel = new JLabel("Satış Tarihi:",JLabel.RIGHT);
		satisSagUstPanel.add(satisTarihiLabel);
		JDateChooser satisTarihi = new JDateChooser();	
		satisSagUstPanel.add(satisTarihi);
		
		JButton satisEkleButton = new JButton("Satış Yap");
		satisSagUstPanel.add(satisEkleButton);
		
		for(SatisEntityComplex compEntity : new SatisDAL().GetAllSatis()) {
			
			satisModel.addRow(compEntity.getVeriler());
		} 
		
		satisEkleButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PersonelEntity pEntity = (PersonelEntity) LoginFE.emailBox.getSelectedItem();
				UrunlerEntity uEntity = (UrunlerEntity) satisUrunAdiBox.getSelectedItem();				
			    SatisEntity entity = new SatisEntity();
			    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				
				String date = format.format(satisTarihi.getDate());
				
			    
			    entity.setPersonelId(pEntity.getId());
			    entity.setUrunId(uEntity.getId());
			    entity.setAdet(Integer.parseInt(satisAdetField.getText()));
			    entity.setTarih(date);
			    
			    new SatisDAL().Insert(entity);
			    StokEntity stokEntity = new StokEntity();
			    
			    stokEntity.setPersonelId(pEntity.getId());
			    stokEntity.setUrunId(uEntity.getId());
			    stokEntity.setAdet(-Integer.parseInt(satisAdetField.getText()));
			    stokEntity.setTarih(date);
			    
			    new StokDAL().Insert(stokEntity);
				JOptionPane.showMessageDialog(null, "Satış başarılı bir şekilde gerçekleştirildi.\n" + uEntity.getAdi() + " adlı üründen " + entity.getAdet() +" adet satış yapılmıştır. ");
			    
				int satir = satisModel.getRowCount();
				for(int i=0;i<satir;i++) {
					satisModel.removeRow(0);
				}
				
				for(SatisEntityComplex yenileEntity : new SatisDAL().GetAllSatis()) {
					
					satisModel.addRow(yenileEntity.getVeriler());
				} 
			}
		});
		
		JButton satisYenileButton = new JButton("Yenile");
		satisSagUstPanel.add(satisYenileButton);
		satisYenileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int satir = satisModel.getRowCount();
				for(int i=0;i<satir;i++) {
					satisModel.removeRow(0);
				}
				
				for(SatisEntityComplex compEntity : new SatisDAL().GetAllSatis()) {
					
					satisModel.addRow(compEntity.getVeriler());
				} 
				
			}
		});
		
		
		
		
		stokPanel.add(stokSolPanel, BorderLayout.WEST);
		stokPanel.add(stokTablePane, BorderLayout.CENTER);
		
		satisPanel.add(satisSagPanel, BorderLayout.EAST);
		satisPanel.add(satisTablePane, BorderLayout.CENTER);
		
		satisSagPanel.add(satisSagUstPanel, BorderLayout.NORTH);
		satisSagPanel.add(satisSagAltPanel, BorderLayout.SOUTH);
		
		
		
		
		stokSolPanel.add(stokSolUstPanel, BorderLayout.NORTH);
		stokSolPanel.add(stokSolAltPanel, BorderLayout.SOUTH);
		
		
		pane.addTab("Stoklar ", icon , stokPanel , "Stoklar");
		pane.addTab("Satışlar ", icon2 , satisPanel , "Satışlar");
		
		return pane;
	}

}
