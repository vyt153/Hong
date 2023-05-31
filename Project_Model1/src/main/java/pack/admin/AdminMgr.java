package pack.admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pack.bbs.BoardBean;
import pack.bbs.UtilMgr;
import pack.dbcp.DBConnectionMgr;
import pack.gallery.GalleryBean;
import pack.member.MemberBean;
import pack.member.MemberLoginBean;

public class AdminMgr {
	Connection conn;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet rs;
	DBConnectionMgr pool;
	MultipartRequest multi;
	
	private static final String SAVEFOLDER = "D:/AJR_20230126/Hong/silsp/p08_JSP/Project_Model1/src/main/webapp/noticeUpload";
	private static String encType = "UTF-8";
	private static int maxSize = 50 * 1024 * 1024; // 5mbyte 제한
	public AdminMgr() {
		pool = DBConnectionMgr.getInstance();
		try {
			conn = pool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean login(String id, String pw) {
		boolean flag = false;
		String sql;
		try {
			sql = "select * from admin where admid = ? and admpw = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			if(rs.next()) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public void insertNotice(HttpServletRequest request) {
		String sql;
		String filename = null;
		String oriFilename = null;
		int filesize = 0;
		try {
			multi = new MultipartRequest(request, SAVEFOLDER, maxSize, encType, new DefaultFileRenamePolicy());
			int post = Integer.parseInt(multi.getParameter("post"));
			if(post==1) {
				sql = "insert into notice (admid, admname, subject, content, ";
				sql += "readcnt, filename, oriFilename, filesize, post, fixed, ";
				sql += "type, saveTM, postTM) values(?,?,?,?,0,?,?,?,?,?,?,now(), now())";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "insert into notice (admid, admname, subject, content, ";
				sql += "readcnt, filename, oriFilename, filesize, post, fixed, ";
				sql += "type, saveTM) values(?,?,?,?,0,?,?,?,?,?,?,now())";
				pstmt = conn.prepareStatement(sql);
			}
			
			File file = new File(SAVEFOLDER);
			
			if(!file.exists()) {
				file.mkdir();
			}
			
			if(multi.getFilesystemName("filename")!=null) {
				filename = multi.getFilesystemName("filename");
				oriFilename = multi.getOriginalFileName("filename");
				filesize = (int)multi.getFile("filename").length();
			}
			pstmt.setString(1, multi.getParameter("admId"));
			pstmt.setString(2, multi.getParameter("admName"));
			pstmt.setString(3, multi.getParameter("subject"));
			pstmt.setString(4, multi.getParameter("content"));
			pstmt.setString(5, filename);
			pstmt.setString(6, oriFilename);
			pstmt.setInt(7, filesize);
			pstmt.setInt(8, Integer.parseInt(multi.getParameter("post")));
			pstmt.setInt(9, Integer.parseInt(multi.getParameter("fixed")));
			pstmt.setInt(10, Integer.parseInt(multi.getParameter("type")));
			
			pstmt.executeUpdate();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<AdmBoardBean> getNoticeList(String keyWord, String keyField, int start, int end){
		String sql;
		List<AdmBoardBean> list = new ArrayList<>(); 
		try {
			stmt = conn.createStatement();
			if(keyWord==null||keyWord.equals("")) {
				sql = "select * from notice order by num desc";
			} else {
				sql = "select * from notice where "+keyField+" like '"+"%"+keyWord+"%"+"' ";
				sql += "order by num desc limit "+start+","+end+"";
			}
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int num = rs.getInt("num");
				String admId = rs.getString("admId");
				String admName = rs.getString("admName");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				String filename = rs.getString("filename");
				String oriFilename = rs.getString("oriFilename");
				int filesize = rs.getInt("filesize");
				int post = rs.getInt("post");
				int fixed = rs.getInt("fixed");
				int type = rs.getInt("type");
				String saveTM = rs.getString("saveTM");
				String postTM = rs.getString("postTM");
				AdmBoardBean bean = new AdmBoardBean(num, admId, admName, subject, content, readcnt, filename, oriFilename, filesize, post, fixed, type, saveTM, postTM);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<AdmBoardBean> userGetNoticeList(String keyWord, String keyField, int start, int end){
		String sql;
		List<AdmBoardBean> list = new ArrayList<>(); 
		try {
			stmt = conn.createStatement();
			if(keyWord==null||keyWord.equals("")) {
				sql = "select * from notice where post = 1 order by num desc";
			} else {
				sql = "select * from notice where "+keyField+" like '"+"%"+keyWord+"%"+"' ";
				sql += "and post = 1 order by num desc limit "+start+","+end+"";
			}
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int num = rs.getInt("num");
				String admId = rs.getString("admId");
				String admName = rs.getString("admName");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				String filename = rs.getString("filename");
				String oriFilename = rs.getString("oriFilename");
				int filesize = rs.getInt("filesize");
				int post = rs.getInt("post");
				int fixed = rs.getInt("fixed");
				int type = rs.getInt("type");
				String saveTM = rs.getString("saveTM");
				String postTM = rs.getString("postTM");
				AdmBoardBean bean = new AdmBoardBean(num, admId, admName, subject, content, readcnt, filename, oriFilename, filesize, post, fixed, type, saveTM, postTM);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public List<AdmBoardBean> getNoticeList(){
		String sql;
		List<AdmBoardBean> list = new ArrayList<>(); 
		try {
			stmt = conn.createStatement();
			sql = "select * from notice where fixed = 1 and post = 1 order by num desc";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int num = rs.getInt("num");
				String admId = rs.getString("admId");
				String admName = rs.getString("admName");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				String filename = rs.getString("filename");
				String oriFilename = rs.getString("oriFilename");
				int filesize = rs.getInt("filesize");
				int post = rs.getInt("post");
				int fixed = rs.getInt("fixed");
				int type = rs.getInt("type");
				String saveTM = rs.getString("saveTM");
				String postTM = rs.getString("postTM");
				AdmBoardBean bean = new AdmBoardBean(num, admId, admName, subject, content, readcnt, filename, oriFilename, filesize, post, fixed, type, saveTM, postTM);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	public AdmBoardBean getNotice(int numParam){
		String sql;
		AdmBoardBean bean = null;
		try {
			stmt = conn.createStatement();
			sql = "select * from notice where num = "+numParam+"";
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int num = rs.getInt("num");
				String admId = rs.getString("admId");
				String admName = rs.getString("admName");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				int readcnt = rs.getInt("readcnt");
				String filename = rs.getString("filename");
				String oriFilename = rs.getString("oriFilename");
				int filesize = rs.getInt("filesize");
				int post = rs.getInt("post");
				int fixed = rs.getInt("fixed");
				int type = rs.getInt("type");
				String saveTM = rs.getString("saveTM");
				String postTM = rs.getString("postTM");
				bean = new AdmBoardBean(num, admId, admName, subject, content, readcnt, filename, oriFilename, filesize, post, fixed, type, saveTM, postTM);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	public void upCount(int num) {
		String sql = "update notice set readcnt = readcnt+1 where num = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int updateNotice(HttpServletRequest req) {
		int exeCnt = 0;
		String sql;
		try {
			multi = new MultipartRequest(req, SAVEFOLDER, maxSize, encType, new DefaultFileRenamePolicy());
			String filename = multi.getFilesystemName("filename");
			int post = Integer.parseInt(multi.getParameter("post"));
			if(filename!=null) {
				sql = "select filename from notice where num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, Integer.parseInt(multi.getParameter("num")));
				rs = pstmt.executeQuery();
				rs.next();
				String oldFile = rs.getString(1);
				if(oldFile!=null) {
					String fileSrc = SAVEFOLDER + "/" + oldFile;
					File file = new File(fileSrc);
					file.delete();
				}
				
				if(post==1) {
					sql = "update notice set subject = ?, content = ?, post = ?, fixed = ?, type = ?, ";
					sql += "filename = ?, oriFilename = ?, filesize = ?, postTM = now() where num = ?";
				} else {
					sql = "update notice set subject = ?, content = ?, post = ?, fixed = ?, type = ?, ";
					sql += "filename = ?, oriFilename = ?, filesize = ? where num = ?";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("subject"));
				pstmt.setString(2, multi.getParameter("content"));
				pstmt.setInt(3, Integer.parseInt(multi.getParameter("post")));
				pstmt.setInt(4, Integer.parseInt(multi.getParameter("fixed")));
				pstmt.setInt(5, Integer.parseInt(multi.getParameter("type")));
				pstmt.setString(6, filename);
				pstmt.setString(7, multi.getOriginalFileName("filename"));
				pstmt.setInt(8, (int)multi.getFile("filename").length());
				pstmt.setInt(9, Integer.parseInt(multi.getParameter("num")));
				exeCnt = pstmt.executeUpdate();
			} else {
				int delFile = 0;
				if(multi.getParameter("delFile")!=null) {
					delFile = Integer.parseInt(multi.getParameter("delFile"));
				}
				if(delFile==1) {
					sql = "select filename from notice where num = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt(multi.getParameter("num")));
					rs = pstmt.executeQuery();
					rs.next();
					String oldFile = rs.getString(1);
					String fileSrc = SAVEFOLDER + "/" + oldFile;
					File file = new File(fileSrc);
					file.delete();
				}
				if(post==1) {
					sql = "update notice set subject = ?, content = ?, post = ?, fixed = ?, type = ?, ";
					sql += "filename = ?, oriFilename = ?, filesize = ?, postTM = now() where num = ?";
				} else {
					sql = "update notice set subject = ?, content = ?, post = ?, fixed = ?, type = ?, ";
					sql += "filename = ?, oriFilename = ?, filesize = ? where num = ?";
				}
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("subject"));
				pstmt.setString(2, multi.getParameter("content"));
				pstmt.setInt(3, Integer.parseInt(multi.getParameter("post")));
				pstmt.setInt(4, Integer.parseInt(multi.getParameter("fixed")));
				pstmt.setInt(5, Integer.parseInt(multi.getParameter("type")));
				pstmt.setString(6, null);
				pstmt.setString(7, null);
				pstmt.setInt(8, 0);
				pstmt.setInt(9, Integer.parseInt(multi.getParameter("num")));
				exeCnt = pstmt.executeUpdate();
			} 
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return exeCnt;
	}
	
	public int getTotalRecord() {
		int recordCnt = 0;
		String sql= "select count(*) from notice";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) recordCnt = rs.getInt(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordCnt;
	}
	
			
	// 공지사항 삭제
	public boolean deleteNotice(int num) {
		boolean flag = false;
		String sql;
		
		try {
			sql = "delete from notice where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int exeCnt = pstmt.executeUpdate();
			if(exeCnt==1) flag= true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 관리자 정보 가져오기
	public List<AdminBean> getAdmList(){
		List<AdminBean> list = new ArrayList<>();
		String sql;
		
		try {
			sql = "select * from admin order by field(level, 'Super', 'Manager', 'Board', 'Member', 'Notice');";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int num = rs.getInt("num");
				String admId = rs.getString("admid");
				String admPw = rs.getString("admpw");
				String admName = rs.getString("admname");
				String level = rs.getString("level");
				AdminBean bean = new AdminBean(num, admId, admPw, admName, level);
				list.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	// 관리자 추가
	public boolean addAdm(AdminBean bean) {
		boolean flag = false;
		String sql;
		
		try {
			sql = "insert into admin (admid, admpw, admname, level) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getAdmId());
			pstmt.setString(2, bean.getAdmPw());
			pstmt.setString(3, bean.getAdmName());
			pstmt.setString(4, bean.getLevel());
			int exeCnt = pstmt.executeUpdate();
			if(exeCnt==1) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return flag;
	}
	
	// 관리자 수정
	public boolean modAdm(AdminBean bean) {
		boolean flag = false;
		String sql;
		
		try {
			sql = "update admin set admid = ?, admpw = ?, admname = ?, level = ?";
			sql += " where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getAdmId());
			pstmt.setString(2, bean.getAdmPw());
			pstmt.setString(3, bean.getAdmName());
			pstmt.setString(4, bean.getLevel());
			pstmt.setInt(5, bean.getNum());
			int exeCnt = pstmt.executeUpdate();
			if(exeCnt==1) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 관리자 삭제
	public boolean delAdm(int num) {
		boolean flag = false;
		String sql;
		
		try {
			sql = "delete from admin where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			int exeCnt = pstmt.executeUpdate();
			if(exeCnt==1) flag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// 관리자 권한
	public String getAdmLevel(String admId) {
		String level = "";
		String sql;
		
		try {
			sql = "select level from admin where admId = '"+admId+"' ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			level = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	public List<MemberLoginBean> previewUserInfo(){
		List<MemberLoginBean> list = new ArrayList<MemberLoginBean>();
		
		try {
			String 	sql = "select t1.num, t1.uid, t1.logincnt, t1.loginip,t1.logintm,conndev from loginInfo as t1, ";
					sql += "(select uid, max(logincnt) as max_logincnt from loginInfo group by uid) as t2 ";
					sql += "where t1.logincnt = t2.max_logincnt and t1.uid = t2.uid";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberLoginBean bean = new MemberLoginBean();
				bean.setNum(rs.getInt("num"));
				bean.setUid(rs.getString("uid"));
				bean.setLogincnt(rs.getInt("logincnt"));
				bean.setLoginip(rs.getString("loginip"));
				bean.setLogintm(rs.getString("logintm"));
				bean.setConndev(rs.getString("conndev"));
				list.add(bean);
				
			}
			
		} catch (SQLException e) {
		e.printStackTrace();
		
		}
		return list;
	}
	// 유저 정보 미리보기 종료
	
	// 유저 정보 상세보기 시작
	
		public List<MemberLoginBean> userInfo(String keyField, String keyWord, int start, int end){
			List<MemberLoginBean> list = new ArrayList<MemberLoginBean>();
			
			try {
				
				String sql = "delete from loginInfo2";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();

				sql = "insert into loginInfo2 select t1.num, t1.uid, t1.logincnt, t1.loginip,t1.logintm,conndev from loginInfo as t1, ";
				sql += "(select uid, max(logincnt) as max_logincnt from loginInfo group by uid) as t2 ";
				sql += "where t1.logincnt = t2.max_logincnt and t1.uid = t2.uid";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				
				if(keyWord.equals("null")||keyWord.equals("")) {
					sql = "select * from loginInfo2";
					sql	+= " limit ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, start);
					pstmt.setInt(2, end);
				} else {
					sql = "select * from loginInfo2 where "+keyField+" like ?" 
							+" limit ?, ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+keyWord+"%");
					pstmt.setInt(2, start);
					pstmt.setInt(3, end);
				}
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					MemberLoginBean bean = new MemberLoginBean();
					bean.setNum(rs.getInt("num"));
					bean.setUid(rs.getString("uid"));
					bean.setLogincnt(rs.getInt("logincnt"));
					bean.setLoginip(rs.getString("loginip"));
					bean.setLogintm(rs.getString("logintm"));
					bean.setConndev(rs.getString("conndev"));
					list.add(bean);
				}
	
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(conn, pstmt, rs);
			}
			return list;
		}
		// 유저 정보 상세보기 종료
	
		// 총 유저 정보 수 시작
		public int getTotalCount(String keyField, String keyWord) {
			String sql;
			int totalCnt=0;
			
			try {
				
				sql = "delete from loginInfo2";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();

				sql = "insert into loginInfo2 select t1.num, t1.uid, t1.logincnt, t1.loginip,t1.logintm,conndev from loginInfo as t1, ";
				sql += "(select uid, max(logincnt) as max_logincnt from loginInfo group by uid) as t2 ";
				sql += "where t1.logincnt = t2.max_logincnt and t1.uid = t2.uid";
				pstmt = conn.prepareStatement(sql);
				pstmt.execute();
				
				if(keyWord.equals("null")||keyWord.equals("")) {
					sql = "select count(*) from loginInfo2";
					pstmt = conn.prepareStatement(sql);
				} else {
					sql = "select count(*) from loginInfo2"
							+" where "+keyField+" like ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "%"+keyWord+"%");
				}
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					totalCnt = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return totalCnt;
		}
		
		
	// 유저 정보 시작
	
	public Vector<MemberBean> getUserInfo(String keyField, String keyWord, int start, int end){
		Vector<MemberBean> list = new Vector<>();
		
		try {
			String sql;
			
			if(keyWord.equals("null")||keyWord.equals("")) {
				sql = "select * from admin";
				sql	+= " limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} else {
				sql = "select * from admin where "+keyField+" like ?" 
						+" limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				MemberBean bean = new MemberBean();
				bean.setUid(rs.getString("uid"));
				bean.setUname(rs.getString("uname"));
				bean.setUemail(rs.getString("uemail"));
				bean.setGender(rs.getString("gender"));
				bean.setUbirthday(rs.getString("ubirthday"));
				bean.setUzipcode(rs.getString("uzipcode"));
				bean.setUaddr(rs.getString("uaddr"));
				String[] hobbyArr = new String[5];
				String hobby = rs.getString("uhobby");
				hobbyArr = hobby.split("");
				bean.setUhobby(hobbyArr);
				bean.setUjob(rs.getString("ujob"));
				bean.setJointm(rs.getString("jointm"));
				
				list.add(bean);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
		return list;
	}
	
	// 유저 정보 종료
	
	// 20230426
	
	// 유저상세정보 게시판 리스트 출력 readUserDetails.jsp
	public Vector<BoardBean> getBoardList(String uid, String keyField, String keyWord, int start, int end){
		Vector<BoardBean> vList = new Vector<>();
		String sql;
		
		try {
			if(keyWord.equals("null")||keyWord.equals("")) {
				sql = "select * from tblboard where uid = ? ";
				sql	+= " order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, uid);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				sql = "select * from tblboard where "+keyField+" like ? and uid = ? " 
						+" order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setString(2, uid);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				bean.setNum(rs.getInt("num"));
				bean.setUname(rs.getString("uname"));
				bean.setSubject(rs.getString("subject"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegtm(rs.getString("regtm"));
				bean.setReadcnt(rs.getInt("readcnt"));
				bean.setFilename(rs.getString("filename"));
				vList.add(bean);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(conn, pstmt, rs);
			}
		
		return vList;
	}

	// 총 게시물 수 readUserDetails.jsp 시작
	public int getTotalCount2(String uid, String keyField, String keyWord) {
		String sql;
		int totalCnt=0;
		
		try {
			if(keyWord.equals("null")||keyWord.equals("")) {
				sql = "select count(*) from tblboard where uid = ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,uid);
			} else {
				sql = "select count(*) from tblboard"
						+" where "+keyField+" like ? and uid = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setString(2,uid);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCnt;
	}
	
	// 게시판 리스트 출력 시작
	public Vector<GalleryBean> getGalleryList(String uidParam, String keyField, String keyWord, int start, int end){
		Vector<GalleryBean> list = new Vector<>();
		String sql;
		
		try {
			if(keyWord.equals("null")||keyWord.equals("")) {
				sql = "select * from tblgallery where uid = ? order by num desc";
				sql	+= " limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,uidParam);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			} else {
				sql = "select * from tblgallery where "+keyField+" like ? and uid = ?" 
						+" order by num limit ?,?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setString(2,uidParam);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt("num");
				String uid = rs.getString("uid");
				String uname = rs.getString("uname");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String regtm = rs.getString("regtm");
				String ip = rs.getString("ip");
				int readcnt = rs.getInt("readcnt");
				String filename = rs.getString("filename");
				String oriFilename = rs.getString("oriFilename");
				int filesize = rs.getInt("filesize");
				GalleryBean bean = new GalleryBean(num, uid, uname, subject, content, regtm, ip, readcnt, filename, oriFilename, filesize);
				list.add(bean);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				pool.freeConnection(conn, pstmt, rs);
			}
		
		return list;
	}
			
	public int getTotalGallery(String uidParam, String keyField, String keyWord) {
		int res = 0;
		String sql;
		try {
			if(keyWord.equals("")||keyWord.equals("null")) {
				sql = "select count(*) from tblgallery where uid =?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, uidParam);
			} else {
				sql = "select count(*) from tblgallery where "+keyField+" like ? and uid=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setString(2, uidParam);
			}
			rs = pstmt.executeQuery();
			
			if(rs.next()) res = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	// bbsListMod 자유게시판 상세보기 페이지
	
	public BoardBean getBoard(int num) {
		String sql = null;
		
		BoardBean bean = new BoardBean();
		
		try {
			sql = "select * from tblboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				bean.setNum(rs.getInt("num"));
				bean.setUid(rs.getString("uid"));
				bean.setUname(rs.getString("uname"));
				bean.setSubject(rs.getString("subject"));
				bean.setContent(rs.getString("content"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegtm(rs.getString("regtm"));
				bean.setReadcnt(rs.getInt("readcnt"));
				bean.setFilename(rs.getString("filename"));
				bean.setFilesize(rs.getInt("filesize"));
				bean.setIp(rs.getString("ip"));
				bean.setOriFilename(rs.getString("oriFilename"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(conn, pstmt, rs);
		}
		return bean;
	}
			
// 게시글 수정페이지 bbsListModProc 시작
	public int updateBoard(HttpServletRequest req) {
		String sql;
		MultipartRequest multi;
		int filesize = 0;
		String filename = null;
		String OriginalFileName = null;
		int exeCnt=0;
		
		try {
			File File = new File(SAVEFOLDER);
			
			if(!File.exists()) {
				File.mkdir();
			}
			multi = new MultipartRequest(req, SAVEFOLDER, maxSize, encType, new DefaultFileRenamePolicy());
			sql = "select filename, filesize, oriFilename from tblboard where num=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(multi.getParameter("num")));
			rs = pstmt.executeQuery();
			
			filename = multi.getFilesystemName("filename");
			OriginalFileName = multi.getOriginalFileName("filename");
			if(rs.next()) {
				String oldFile = rs.getString("filename");
				int oldFilesize = rs.getInt("filesize");
				String oldOriFilename = rs.getString("oriFilename");
				if(rs.getString(1)!=null&&filename!=null){
					String fileSrc = SAVEFOLDER + "/" + oldFile;
					File file = new File(fileSrc);
					file.delete();
					filesize = (int)multi.getFile("filename").length();			
				}
				String content = multi.getParameter("content");
				if(multi.getParameter("contentType").equalsIgnoreCase("TEXT")) {
					content = UtilMgr.replace(content, "<", "&lt");
				}
				
				sql = "update tblboard set ";
				sql += "subject=?, content=?, filename=?, filesize=?, oriFilename=? ";
				sql += "where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, multi.getParameter("subject"));
				pstmt.setString(2, content);
				if(rs.getString(1)!=null&&filename==null){
					pstmt.setString(3, oldFile);
					pstmt.setInt(4, oldFilesize);
					pstmt.setString(5, oldOriFilename);
				} else {
					pstmt.setString(3, filename);
					pstmt.setInt(4, filesize);
					pstmt.setString(5, OriginalFileName);
				}
				pstmt.setInt(6, Integer.parseInt(multi.getParameter("num")));
				exeCnt = pstmt.executeUpdate();
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	return exeCnt;
	}
}