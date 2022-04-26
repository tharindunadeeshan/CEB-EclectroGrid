package DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionInterrupt {

	public Connection connect() {
		Connection con = null;

		try {

			Class.forName("com.mysql.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/interrupt", "root", "projectdatabases");
			System.out.print("Successfully Connected");
			

		} catch (Exception e) {

			System.out.print("Connection Failed");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
}
