package tr.com.hilalvarol.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.hilalvarol.core.ObjectHelper;
import tr.com.hilalvarol.interfaces.DALInterfaces;
import tr.com.hilalvarol.types.AccountsEntity;

public class AccountsDAL extends ObjectHelper implements DALInterfaces<AccountsEntity>{

	@Override
	public void Insert(AccountsEntity entity) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();

			statement.executeUpdate("INSERT INTO Accounts (PersonelId, YetkiId, Sifre) VALUES (" +  entity.getPersonelId() + "," + entity.getYetkiId() + ",'" + entity.getSifre() + "')");
		    statement.close();
		    connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public AccountsEntity GetPersonelIdveSifre(int personelId , String sifre){
		
		AccountsEntity entity = new AccountsEntity();
		List<AccountsEntity> listele = new ArrayList<AccountsEntity>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE PersonelId="+personelId+" AND Sifre='"+sifre.trim()+"'");
            	
            
			while(rs.next()){
			  entity.setId(rs.getInt("Id"));
			  entity.setPersonelId(rs.getInt("PersonelId"));
			  entity.setSifre(rs.getString("Sifre"));
			  entity.setYetkiId(rs.getInt("YetkiId"));
			  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	}
	
	public AccountsEntity GetYetkiId(int personelId){
		
		AccountsEntity entity = new AccountsEntity();
		List<AccountsEntity> listele = new ArrayList<AccountsEntity>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM accounts WHERE PersonelId="+personelId+"");
            	
            
			while(rs.next()){
			  entity.setId(rs.getInt("Id"));
			  entity.setPersonelId(rs.getInt("PersonelId"));
			  entity.setYetkiId(rs.getInt("YetkiId"));
			  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
	}

	@Override
	public List<AccountsEntity> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountsEntity Delete(AccountsEntity Entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(AccountsEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<AccountsEntity> GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
