package tr.com.hilalvarol.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.PersonelEntity;
import tr.com.hilalvarol.types.YetkilerEntity;

public class YetkilerDAL extends ObjectHelper implements DALInterfaces<YetkilerEntity> {

	@Override
	public void Insert(YetkilerEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Yetkiler (Adi) VALUES('" +  entity.getAdi()+ "')");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<YetkilerEntity> GetAll() {
		List<YetkilerEntity> dataContract = new ArrayList<YetkilerEntity>();
		Connection connection = getConnection();
		YetkilerEntity contract ;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM Yetkiler");
            while(resultSet.next()) {
            contract = new YetkilerEntity();
            contract.setId(resultSet.getInt("Id"));
            contract.setAdi(resultSet.getString("Adi"));
          
            dataContract.add(contract);
            
            }
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dataContract;
	}

	@Override
	public YetkilerEntity Delete(YetkilerEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(YetkilerEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<YetkilerEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
