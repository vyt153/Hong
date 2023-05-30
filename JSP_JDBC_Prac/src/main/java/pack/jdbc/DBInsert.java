package pack.jdbc;

import java.sql.SQLException;

import pack.member.MemberBean;

public class DBInsert extends DBConn{
	public boolean memInsert(MemberBean bean) {
		String sql = "insert into memberList(uid, upw, uemail) values(?,?,?)";
		int putData = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getUid());
			pstmt.setString(2, bean.getUpw());
			pstmt.setString(3, bean.getUemail());
			putData = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(putData==1) return true;
		else return false;
	}
}
