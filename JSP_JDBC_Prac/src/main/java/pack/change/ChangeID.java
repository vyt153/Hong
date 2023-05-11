package pack.change;

import java.sql.SQLException;

import pack.jdbc.DBConn;
import pack.member.MemberBean;

public class ChangeID extends DBConn{
	public boolean memUpdate(String uid,MemberBean bean) {
		int updateChk = 0;
		String sql = "update memberList set uid = ? where binary uid= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getNewId());
			pstmt.setString(2, uid);
			updateChk = pstmt.executeUpdate();
			if(updateChk==1) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
