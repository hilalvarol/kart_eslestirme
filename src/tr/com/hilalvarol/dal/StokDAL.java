package tr.com.hilalvarol.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import tr.com.hilalvarol.complex.types.StockEntityComplex;
import tr.com.hilalvarol.complex.types.StokEntityTotalComplex;
import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.SatisEntity;
import tr.com.hilalvarol.types.StokEntity;

public class StokDAL extends ObjectHelper implements DALInterfaces<StokEntity> {

	@Override
	public void Insert(StokEntity entity) {
		
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Stok (PersonelId,UrunId,Tarih,Adet) VALUES('" +  entity.getPersonelId()  + "','" + entity.getUrunId() + "','" + entity.getTarih() + "','" + entity.getAdet() + "')");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}           
   
	public List<StockEntityComplex> GetAllStock(){
		List<StockEntityComplex> dataEntity = new ArrayList<StockEntityComplex>();
		Connection connection = getConnection();
		StockEntityComplex entity ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT stok.Id, personel.AdiSoyadi, urunler.Adi, Adet, stok.Tarih FROM stok  "
					+ "LEFT JOIN urunler on stok.UrunId=urunler.Id "
					+ "LEFT JOIN personel on stok.PersonelId=personel.Id ORDER BY stok.id DESC");
            while(resultSet.next()) {
            entity = new StockEntityComplex();
            
            entity.setId(resultSet.getInt("Id"));
            entity.setPersonelAdi(resultSet.getString("AdiSoyadi"));
            entity.setUrunAdi(resultSet.getString("urunler.Adi"));
            entity.setAdet(resultSet.getInt("Adet"));
            entity.setTarih(resultSet.getString("stok.Tarih"));

            dataEntity.add(entity);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataEntity;
	}
	
	
	public List<StokEntityTotalComplex> GetTotalStock(){
		List<StokEntityTotalComplex> dataContract = new ArrayList<StokEntityTotalComplex>();
		Connection connection = getConnection();
		StokEntityTotalComplex contract ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT Sum(Adet) as toplam ,stok.Id , AdiSoyadi, Adi, Adet, stok.Tarih FROM stok "
					+ "LEFT JOIN urunler on stok.UrunId=urunler.Id "
					+ "LEFT JOIN personel on stok.PersonelId=personel.Id GROUP BY urunId");
            while(resultSet.next()) {
            contract = new StokEntityTotalComplex();
            contract.setId(resultSet.getInt("Id"));
            contract.setPersonelAdi(resultSet.getString("AdiSoyadi"));
            contract.setUrunAdi(resultSet.getString("urunler.Adi"));
           // contract.setAdet(resultSet.getInt("Adet"));
            contract.setTarih(resultSet.getString("stok.Tarih"));
            contract.setToplam(resultSet.getInt("toplam"));

            dataContract.add(contract);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}



	@Override
	public List<StokEntity> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StokEntity Delete(StokEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(StokEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StokEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
