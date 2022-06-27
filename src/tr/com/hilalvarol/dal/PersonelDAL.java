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
import tr.com.hilalvarol.types.PersonelEntity;

public class PersonelDAL extends ObjectHelper implements DALInterfaces<PersonelEntity> {

	@Override
	public void Insert(PersonelEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Personel (AdiSoyadi, Email) VALUES('" +  entity.getAdiSoyadi() + "','" + entity.getEmail() + "')");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<PersonelEntity> GetAll() {
		
		List<PersonelEntity> dataContract = new ArrayList<PersonelEntity>();
		Connection connection = getConnection();
		PersonelEntity contract ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Personel");
            while(resultSet.next()) {
            contract = new PersonelEntity();
            contract.setId(resultSet.getInt("Id"));
            contract.setAdiSoyadi(resultSet.getString("AdiSoyadi"));
            contract.setEmail(resultSet.getString("Email"));
          
            dataContract.add(contract);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}

	@Override
	public PersonelEntity Delete(PersonelEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(PersonelEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PersonelEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
