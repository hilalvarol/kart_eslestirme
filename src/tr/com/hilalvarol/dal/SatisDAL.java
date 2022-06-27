package tr.com.hilalvarol.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.hilalvarol.complex.types.SatisEntityComplex;
import tr.com.hilalvarol.complex.types.StockEntityComplex;
import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.KategoriEntity;
import tr.com.hilalvarol.types.SatisEntity;

public class SatisDAL extends ObjectHelper implements DALInterfaces<SatisEntity> {

	@Override
	public void Insert(SatisEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Satis (UrunId,Tarih, Adet, PersonelId) VALUES(" +  entity.getUrunId() + ",'" + entity.getTarih() + "'," + entity.getAdet() + "," + entity.getPersonelId() + ")");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public List<SatisEntityComplex> GetAllSatis() {
		List<SatisEntityComplex> dataEntity = new ArrayList<SatisEntityComplex>();
		
		Connection connection = getConnection();
		SatisEntityComplex entity ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT satis.Id, personel.AdiSoyadi ,Adi, Adet, satis.Tarih FROM satis "
					+ "LEFT JOIN urunler on satis.UrunId = urunler.Id "
					+ "LEFT JOIN personel on satis.PersonelId = personel.Id ORDER BY satis.id DESC ");
            while(resultSet.next()) {
            entity = new SatisEntityComplex();
            
            entity.setId(resultSet.getInt("satis.Id"));
            entity.setPersonelAdi(resultSet.getString("personel.AdiSoyadi"));
            entity.setUrunAdi(resultSet.getString("Adi"));
            entity.setAdet(resultSet.getInt("Adet"));
            entity.setTarih(resultSet.getString("satis.Tarih"));

            dataEntity.add(entity);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataEntity;
	}
	@Override
	public List<SatisEntity> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SatisEntity Delete(SatisEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(SatisEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SatisEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
