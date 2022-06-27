package tr.com.hilalvarol.dal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.MusteriEntity;

public class MusteriDAL extends ObjectHelper implements DALInterfaces<MusteriEntity>{

	@Override
	public void Insert(MusteriEntity entity) {
Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Musteri(AdiSoyadi, Telefon, Adres, SehirId) VALUES ('" +entity.getAdiSoyadi()
			+ "','" + entity.getTelefon() + "','"+ entity.getAdres() +"','"+ entity.getSehirId() + "')");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<MusteriEntity> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}
	public String[] SehirGetir(){
		
		String[] sehirler ={"Adana","Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
	            "Aydın", "Balıkesir","Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
	            "Çankırı", "Çorum","Denizli","Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum ", "Eskişehir",
	            "Gaziantep", "Giresun","Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
	            "Kars", "Kastamonu", "Kayseri","Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya ", "Malatya",
	            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
	            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon  ", "Tunceli", "Şanlıurfa", "Uşak",
	            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt ", "Karaman", "Kırıkkale", "Batman", "Şırnak",
	            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük ", "Kilis", "Osmaniye ", "Düzce"};
		return sehirler;
	}

	@Override
	public MusteriEntity Delete(MusteriEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(MusteriEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<MusteriEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
