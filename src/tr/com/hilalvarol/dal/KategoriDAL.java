package tr.com.hilalvarol.dal;

import java.security.KeyStore;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.KategoriEntity;
import tr.com.hilalvarol.*;


public class KategoriDAL extends ObjectHelper implements DALInterfaces<KategoriEntity>  {

	@Override
	public void Insert(KategoriEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Kategori (Adi, ParentId) VALUES('" +  entity.getAdi() + "'," + entity.getParentId() + ")");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<KategoriEntity> GetAll() {
		List<KategoriEntity> dataContract = new ArrayList<KategoriEntity>();
		Connection connection = getConnection();
		KategoriEntity contract ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori");
            while(resultSet.next()) {
            contract = new KategoriEntity();
            contract.setId(resultSet.getInt("Id"));
            contract.setAdi(resultSet.getString("Adi"));
            contract.setParentId(resultSet.getInt("ParentId"));
            
            dataContract.add(contract);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}

	@Override
	public KategoriEntity Delete(KategoriEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

/*	@Override
	public void Update(KategoriEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("UPDATE Kategori SET Adi='" +entity.getAdi()+"',ParentId="+entity.getParentId()
			+" WHERE id="+entity.getId()+"");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}*/
	
	public void Update(KategoriEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("UPDATE Kategori SET Adi='" +entity.getAdi()+"',ParentId="+entity.getParentId()
			+" WHERE id="+entity.getId()+"");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<KategoriEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
public List<KategoriEntity> GetSearchKategori(String kategoriAdi){
	
	List<KategoriEntity> dataEntity = new ArrayList<KategoriEntity>();
	Connection connection = getConnection();
	
	try {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("SELECT * FROM Kategori WHERE Adi LIKE '"+ "%"+ kategoriAdi+"%"+"'");
		while (resultSet.next()) {
			KategoriEntity entity = new KategoriEntity();
			entity.setAdi(resultSet.getString("Adi"));
			entity.setParentId(resultSet.getInt("ParentId"));
			
			dataEntity.add(entity);
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return dataEntity;
}

	

}
