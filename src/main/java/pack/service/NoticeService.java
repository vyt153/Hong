package pack.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pack.entity.Notice;
import pack.entity.NoticeView;

public class NoticeService {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public void mtdConn() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/xeqdb1?";
				url += "useUnicode=true&";
				url += "useSSL=false&";
				url += "characterEncoding=UTF8&";
				url += "serverTimezone=UTC&";
				url += "allowPublicKeyRetrieval=True";
				String uid = "root";
				String upw = "dnflrkwhr12@";
				conn = DriverManager.getConnection(url, uid, upw);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
	}
	
	public int removeNoticeAll(int[] ids) {
		return 0;
	}
	
	public int pubNoticeAll(int[] oids, int[] cids) {
		List<String> oidsList = new ArrayList<String>();
		for(int i=0;i<oids.length;i++)
			oidsList.add(String.valueOf(oids[i]));
		
		List<String> cidsList = new ArrayList<String>();
		for(int i=0;i<cids.length;i++)
			cidsList.add(String.valueOf(cids[i]));
		
		String oidsCSV = String.join(",", oidsList);
		String cidsCSV = String.join(",", cidsList);
		
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	public int pubNoticeAll(List<String> oids, List<String> cids) {
		String oidsCSV = String.join(",", oids);
		String cidsCSV = String.join(",", cids);
		
		return pubNoticeAll(oidsCSV, cidsCSV);
	}
	public int pubNoticeAll(String oidsCSV, String cidsCSV) {
		mtdConn();
		int result = 0;
		String sqlOpen = String.format("update notice set pub = 1 where id in (%s)", oidsCSV);
		String sqlClose = String.format("update notice set pub = 0 where id in (%s)", cidsCSV);
		try {
			stmt = conn.createStatement();
			result += stmt.executeUpdate(sqlOpen);
			
			stmt = conn.createStatement();
			result += stmt.executeUpdate(sqlClose);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int insertNotice(Notice notice) {
		mtdConn();
		int result = 0;
		String sql = "insert into notice(title, content, writer_ID, pub, files, regdate, hit)"
				+ " values(?,?,?,?,?,now(),0)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, notice.getTitle());
			pstmt.setString(2, notice.getContent());
			pstmt.setString(3, notice.getWriter_ID());
			pstmt.setBoolean(4, notice.getPub());
			pstmt.setString(5, notice.getFiles());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteNotice(int id) {
		return 0;
	}
	
	public int updateNotice(Notice notice) {
		return 0;
	}
	
	public List<Notice> getNoticeNewestList(){
		return null;
	}
	
	public List<NoticeView> getNoticeList(){
		return getNoticeList("title","",1);
	}
	public List<NoticeView> getNoticeList(int page){
		return getNoticeList("title","",1);
	}
	
	public List<NoticeView> getNoticeList(String field, String query, int page){
		List<NoticeView> list = new  ArrayList<>();
		String sql = "select tmp.* from( "
				+ "select @rownum := @rownum + 1 num, n.*  "
				+ "from (select * from notice_view where "+field+" like ? order by regdate desc) n, (select @rownum := 0) num "
				+ ") tmp "
				+ "where num between ? and ?";
		try {
			mtdConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_ID = rs.getString("writer_ID");
				String files = rs.getString("files");
				int hit = rs.getInt("hit");
				Date regdate = rs.getDate("regdate");
				int cmtCount = rs.getInt("cmt_count");
				boolean pub = rs.getBoolean("pub");
				NoticeView notice = new NoticeView(id, title, writer_ID, hit, files, regdate, "", pub, cmtCount);
				list.add(notice);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public List<NoticeView> getPubNoticeList(String field, String query, int page) {
		List<NoticeView> list = new  ArrayList<>();
		String sql = "select tmp.* from( "
				+ "select @rownum := @rownum + 1 num, n.*  "
				+ "from (select * from notice_view where "+field+" like ? order by regdate desc) n, (select @rownum := 0) num "
				+ ") tmp "
				+ "where pub=1 and num between ? and ?";
		try {
			mtdConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			pstmt.setInt(2, 1+(page-1)*10);
			pstmt.setInt(3, page*10);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String writer_ID = rs.getString("writer_ID");
				String files = rs.getString("files");
				int hit = rs.getInt("hit");
				Date regdate = rs.getDate("regdate");
				int cmtCount = rs.getInt("cmt_count");
				boolean pub = rs.getBoolean("pub");
				NoticeView notice = new NoticeView(id, title, writer_ID, hit, files, regdate, "", pub, cmtCount);
				list.add(notice);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title","");
	}
	public int getNoticeCount(String field, String query) {
		int count = 0;
		String sql = "select count(*)  "
				+ "from (select * from notice where "+field+" like ? order by regdate desc) n";
		try {
			mtdConn();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+query+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt(1);
			
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public Notice getNotice(int id) {
		Notice notice = null;
		
		String sql = "select * from notice where id=?";
		try {
			mtdConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_ID = rs.getString("writer_ID");
				String files = rs.getString("files");
				int hit = rs.getInt("hit");
				Date regdate = rs.getDate("regdate");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				notice = new Notice(nid, title, writer_ID, files, hit, regdate, content, pub);
			}
			pstmt.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	public Notice getNextNotice(int id) {
		Notice notice = null;
		String sql = "select n.* from("
				+ "select @rownum:=@rownum+1 rownum, notice.* from notice, (select @rownum:=0) rownum "
				+ "where regdate > (select regdate from notice where id = ?) "
				+ ") n where rownum = 1";
		try {
			mtdConn();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_ID = rs.getString("writer_ID");
				String files = rs.getString("files");
				int hit = rs.getInt("hit");
				Date regdate = rs.getDate("regdate");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				notice = new Notice(nid, title, writer_ID, files, hit, regdate, content, pub);
			}
			pstmt.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}
	public Notice getPrevNotice(int id) {
		Notice notice = null;
		String sql = "select n.* from("
				+ "select @rownum:=@rownum+1 rownum, notice.* from notice, (select @rownum:=0) rownum "
				+ "where regdate < (select regdate from notice where id = ?) order by regdate desc"
				+ ") n where rownum = 1";
		try {
			mtdConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if(rs.next()) {
				int nid = rs.getInt("id");
				String title = rs.getString("title");
				String writer_ID = rs.getString("writer_ID");
				String files = rs.getString("files");
				int hit = rs.getInt("hit");
				Date regdate = rs.getDate("regdate");
				String content = rs.getString("content");
				boolean pub = rs.getBoolean("pub");
				notice = new Notice(nid, title, writer_ID, files, hit, regdate, content, pub);
			}
			pstmt.close();
			rs.close();
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return notice;
	}

	public int deleteNoticeAll(int[] ids) {
		mtdConn();
		int result = 0;
		String params = "";
		
		for(int i=0;i<ids.length;i++) {
			params += ids[i];
			if(i<ids.length-1) {
				params += ",";
			}
		}
		String sql = "delete from notice where id in ("+params+")";
		try {
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
