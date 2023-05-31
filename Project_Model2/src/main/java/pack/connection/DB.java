//package pack.connection;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DB {
//	private static DB db = new DB();
//	
//	protected Connection conn;
//	protected Statement stmt;
//	protected PreparedStatement pstmt;
//	protected ResultSet rs;
//	
//	protected DB() {};
//	
//	public static DB getInstance() {
//		return db;
//	}
//	
//	public void mtd() {
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			
//			String url = "jdbc:mysql://localhost:3306/xpedb1";
//			url += "useUnicode=true&";
//			url += "useSSL=false&";
//			url += "characterEncoding=UTF8&";
//			url += "serverTimezone=UTC";
//			String uid = "root";
//			String upw = "1234";
//			
//			conn = DriverManager.getConnection(url, uid, upw);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//		
//	}
//}
