package pack.jdbc;

import java.sql.SQLException;

import pack.member.MemberBean;

public class DBLogin extends DBConn{
	public boolean memSelect(MemberBean bean) {
		String sql = "select uid, upw from memberList where binary uid='"+bean.getUid()+"' and upw='"+bean.getUpw()+"'";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) return true;
			else return false;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
