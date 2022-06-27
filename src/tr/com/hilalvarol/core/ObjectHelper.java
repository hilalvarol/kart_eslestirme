package tr.com.hilalvarol.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import tr.com.hilalvarol.interfaces.CoreInterfaces;

public class ObjectHelper extends CoreFields implements CoreInterfaces {

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // mysql e bağlanmamızı sağlar       
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 

	@Override
	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(getUrl(), getUserName(), getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

}
