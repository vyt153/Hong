package pack.comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


import pack.dbcp.DBConnectionMgr;

public class ComMgr {

	Connection conn;
	Statement stmt;
	ResultSet rs;
	ResultSetMetaData rsmd;
	PreparedStatement pstmt;
	DBConnectionMgr pool;
	
	public ComMgr() {
		try {
			pool = DBConnectionMgr.getInstance();
			conn = pool.getConnection();		
		} catch (Exception e) {
			System.out.println("exception : " +e.getMessage());
		}
		
		System.out.println("DB Access OK!!");
	}
	
	// 댓글 입력
	public boolean insertComment(ComBean bean) {
		boolean flag = false;
		String sql;
		
		sql = "select max(cnum) from comments";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int ref = 1;
			if (rs.next()) {
				ref = rs.getInt(1) + 1;
			}
			
			sql = "insert into comments(" ;
			sql += "listnum, uid, uname, comment, ref, pos, depth, regtm, ip) values (";
			sql += "?,?,?,?,?,0,0,now(),?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getListnum());
			pstmt.setString(2, bean.getUid());
			pstmt.setString(3, bean.getUname());
			pstmt.setString(4, bean.getComment());
			pstmt.setInt(5, ref);
			pstmt.setString(6, bean.getIp());
			int exeCnt = pstmt.executeUpdate();
			if(exeCnt==1) flag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return flag;
	}
	
	// 댓글 삭제
	
	public void delCom(int cnum) {
		
		String sql ;
		
		sql = "delete from comments where cnum = " +cnum;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	// 댓글 출력
	
	public Vector<ComBean> comList(int sqlListnum) {
		Vector<ComBean> list = new Vector<>();
		
		String sql;
		
		
		try {
			
			sql = "select * from comments where listnum = " + sqlListnum;
			sql +=" order by cnum desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				ComBean bean = new ComBean();
				bean.setCnum(rs.getInt("cnum"));
				bean.setListnum(rs.getInt("listnum"));
				bean.setUid(rs.getString("uid"));
				bean.setUname(rs.getString("uname"));
				bean.setComment(rs.getString("comment"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegtm(rs.getString("regtm"));
				bean.setIp(rs.getString("ip"));
				list.add(bean);
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
