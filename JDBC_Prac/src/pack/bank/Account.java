package pack.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {
	private static Account obj = new Account();

	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	protected Account() {
	}

	public static Account getInstance() {
		return obj;
	}

	public void mtd() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://localhost:3306/memberShip?";
			url += "useUnicode=true&";
			url += "useSSL=false&";
			url += "characterEncoding=UTF8&";
			url += "serverTimezone=UTC";
			String uid = "root";
			String upw = "dnflrkwhr12@";

			conn = DriverManager.getConnection(url, uid, upw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void login(String id, String pw) {
	}

	public void login(String id, String pw, String name, String address) {
	}
}
