package pack.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pack.member.MemberBean;

public class DBConn extends HttpServlet{
	protected Connection conn = null;
	protected Statement stmt = null;
	protected ResultSet rs = null;
	protected PreparedStatement pstmt = null;

	public DBConn() {
		try {
			DBConnectionMgr pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();

			System.out.println("DB Access OK!!");

		} catch (Exception e) {
			System.out.println("exception : " + e.getMessage());
		}
	}

	///////////////////// ↓↓↓ 관련 작업 시작 ↓↓↓ /////////////////////
	
	public boolean memInsert(MemberBean bean) {
		return false;
	}
	public boolean memSelect(MemberBean bean) {
		return false;
	}
	public boolean memUpdate(String uid, MemberBean bean) {
		return false;
	}
}
