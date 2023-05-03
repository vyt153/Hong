package pack.change;

import java.sql.SQLException;

import pack.jdbc.DBConn;
import pack.member.MemberBean;

public class ChangeEmail extends DBConn{
	public boolean memUpdate(String uid,MemberBean bean) {
		int updateChk = 0;
		String sql = "update memberList set uemail = ? where uemail= ? and uid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getNewEmail());
			pstmt.setString(2, bean.getUemail());
			System.out.println(bean.getUemail());
			pstmt.setString(3, uid);
			updateChk = pstmt.executeUpdate();
			if(updateChk==1) return true;
			else return false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
