package pack.change;

import java.sql.SQLException;

import pack.jdbc.DBConn;
import pack.member.MemberBean;

public class ChangePw extends DBConn{
	public boolean memUpdate(String uid,MemberBean bean) {
		int updateChk = 0;
		String sql = "update memberList set upw = ? where uid= ? and upw= ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getNewPw());
			pstmt.setString(2, uid);
			pstmt.setString(3, bean.getUpw());
			updateChk = pstmt.executeUpdate();
			if(updateChk==1) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
