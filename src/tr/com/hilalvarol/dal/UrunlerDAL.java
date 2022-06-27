package tr.com.hilalvarol.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.KategoriEntity;
import tr.com.hilalvarol.types.UrunlerEntity;;

public class UrunlerDAL extends ObjectHelper implements DALInterfaces<UrunlerEntity> {

	@Override
	public void Insert(UrunlerEntity entity) {
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO Urunler (Adi, KategoriId, Tarih, Fiyat)" 
			+ "VALUES('"
			+entity.getAdi()
			+"','"
			+entity.getKategoriId()
			+"','"+entity.getTarih()
			+"','"+entity.getFiyat()
			+ "')");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<UrunlerEntity> GetAll() {
		List<UrunlerEntity> dataContract = new ArrayList<UrunlerEntity>();
		Connection connection = getConnection();
		UrunlerEntity contract ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Urunler");
            while(resultSet.next()) {
            contract = new UrunlerEntity();
            contract.setId(resultSet.getInt("Id"));
            contract.setAdi(resultSet.getString("Adi"));
            contract.setKategoriId(resultSet.getInt("KategoriId"));
            contract.setTarih(resultSet.getString("Tarih"));
            dataContract.add(contract);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}

	@Override
	public UrunlerEntity Delete(UrunlerEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(UrunlerEntity entity) {
Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE Urunler SET (Adi, KategoriId, Tarih, Fiyat)" 
			+ "VALUES('"
			+entity.getAdi()
			+"','"
			+entity.getKategoriId()
			+"','"+entity.getTarih()
			+"','"+entity.getFiyat()
			+ "')");
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public List<UrunlerEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
