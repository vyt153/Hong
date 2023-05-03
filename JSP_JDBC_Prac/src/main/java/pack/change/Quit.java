package pack.change;

import java.sql.SQLException;

import pack.jdbc.DBConn;
import pack.member.MemberBean;

public class Quit extends DBConn{
	public boolean memUpdate(String uid,MemberBean bean) {
		int updateChk = 0;
		String sql = "delete from memberList where uid=? and upw=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getUid());
			pstmt.setString(2, bean.getUpw());
			updateChk = pstmt.executeUpdate();
			if(updateChk==1) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
