package pack.csc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import pack.bbs.BoardBean;
import pack.bbs.UtilMgr;
import pack.csc.CscBean;
import pack.dbcp.DBConnectionMgr;

public class CscMgr {
	private DBConnectionMgr pool;

	Connection conn;
	PreparedStatement pstmt;
	Statement stmt;
	ResultSet rs;
	
	public CscMgr() { //DB 접속
		pool = DBConnectionMgr.getInstance();
		try {
			conn = pool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB Access OK!!");
	}

	// 총 게시물 수(/csc/QnA.jsp) 시작
	public int getTotalCount(String keyField, String keyWord) {
		String sql;
		int totalCnt = 0;

		try {
			if (keyWord.equals("null") || keyWord.equals("")) {
				sql = "select count(*) from tblcsc";
				pstmt = conn.prepareStatement(sql);
			} else {
				sql = "select count(*) from tblcsc" + " where " + keyField + " like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + keyWord + "%");
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				totalCnt = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCnt;
	}
	
	// 게시판 리스트 출력(/csc/QnA.jsp) 시작
	public Vector<CscBean> getBoardList(String keyField, String keyWord, int start, int end){
		Vector<CscBean> vList = new Vector<>();
		String sql;
		
		try {
			if(keyWord.equals("null")||keyWord.equals("")) {
				sql = "select * from tblcsc";
				sql	+= " order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
			} else {
				sql = "select * from tblcsc where "+keyField+" like ?" 
						+" order by ref desc, pos asc limit ?, ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%"+keyWord+"%");
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CscBean bean = new CscBean();
				bean.setNum(rs.getInt("num"));
				bean.setUname(rs.getString("uname"));
				bean.setSubject(rs.getString("subject"));
				bean.setPos(rs.getInt("pos"));
				bean.setRef(rs.getInt("ref"));
				bean.setDepth(rs.getInt("depth"));
				bean.setRegtm(rs.getString("regtm"));
				bean.setReadcnt(rs.getInt("readcnt"));
				vList.add(bean);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		
		return vList;
	}
	// 게시판 리스트 출력(/csc/QnA.jsp) 끝
	
	// 로그인 사용자 이름 반환(/csc/write.jsp) 시작
		public String getMemberName(String uid) {
			String uname="";
			String sql = "select uname from member where uid=?";
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, uid);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					uname = rs.getNString(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return uname;
		}
		// 로그인 사용자 이름 반환(/csc/write.jsp) 끝
		
		// QnA 입력(/csc/writeProc.jsp) 시작
		public void insertBoard(CscBean bean, String contentType) {
			String sql;
			String content = bean.getContent();
			
			sql = "select max(num) from tblcsc";
			try {
				pstmt= conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				int ref = 1;
				if(rs.next()) {
					ref = rs.getInt(1)+1;
				}
				
				if(contentType.equalsIgnoreCase("TEXT")) {
					content = UtilMgr.replace(content, "<", "&lt");
				}
				
				sql = "insert into tblcsc(";
				sql += "uid, uname, subject, content, ref, pos, depth, ";
				sql += "regtm, ip, readcnt) values(";
				sql += "?,?,?,?,?,0,0,now(),?,0)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getUid());
				pstmt.setString(2, bean.getUname());
				pstmt.setString(3, bean.getSubject());
				pstmt.setString(4, content);
				pstmt.setInt(5, ref);
				pstmt.setString(6, bean.getIp());
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			
		}
		// QnA 입력(/csc/writeProc.jsp) 끝
		
		// QnA 뷰페이지 조회수 증가 시작 (/csc/read.jsp 내용보기 페이지)
		public void upCount(int num) {
			String sql = null;
			
			try {
				sql = "update tblcsc set readcnt = readcnt+1 where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		// QnA 뷰페이지 조회수 증가 끝 (/csc/read.jsp 내용보기 페이지)
		
		// QnA 상세보기 페이지 출력 시작 (/csc/read.jsp 내용보기 페이지)
		public CscBean getBoard(int num) {
			String sql = null;
			
			CscBean bean = new CscBean();
			
			try {
				sql = "select * from tblcsc where num=?";
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
					bean.setIp(rs.getString("ip"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return bean;
		}
		// QnA 상세보기 페이지 출력 끝 (/csc/read.jsp 내용보기 페이지)
		
		// QnA 삭제(/csc/deleteProc.jsp) 시작
		public int deleteBoard(int num) {
			String sql = null;
			int exeCnt = 0;
			
			int ref = 0;
			int pos = 0;
			String uname = "";
			try {
				// 게시글 파일 삭제
				sql = "select * from tblcsc where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					ref = rs.getInt("ref");
					pos = rs.getInt("pos");
					uname = rs.getString("uname");
				}
				
				int unCnt = 0;
				
				sql = "select count(distinct uname) as unameCnt from tblcsc where ref=? and pos>=? and num>=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, pos);
				pstmt.setInt(3, num);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					unCnt = rs.getInt(1);
				}
				
				if(unCnt >= 2) {
					
					sql = "delete from tblcsc where ref=? and pos>? and num>?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					pstmt.setInt(2, pos);
					pstmt.setInt(3, num);
					exeCnt = pstmt.executeUpdate();
					
					sql = "update tblcsc set uname=?, subject=?, content=?, readcnt=?, ";
					sql += "uid=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, "관리자");
					pstmt.setString(2, "삭제된 게시물 입니다.");
					pstmt.setString(3, "삭제된 게시물 입니다.");
					pstmt.setInt(4, 0);
					pstmt.setString(5, "-");
					pstmt.setInt(6, num);
					pstmt.execute();
					
				} else {
					sql = "delete from tblcsc where ref=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, ref);
					exeCnt = pstmt.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return exeCnt;
		}
		// QnA 삭제(/csc/deleteProc.jsp) 끝
		
		// Q&A 글 수정페이지(/csc/modifyProc.jsp) 시작
		public int updateBoard(CscBean bean, String contentType) {
			String sql;

			int exeCnt=0;
			
			try {

				sql = "select * from tblcsc where num=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, bean.getNum());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
	
					String content = bean.getContent();
					if(contentType.equalsIgnoreCase("TEXT")) {
						content = UtilMgr.replace(content, "<", "&lt");
					}
					
					sql = "update tblcsc set ";
					sql += "subject=?, content=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, bean.getSubject());
					pstmt.setString(2, content);
					pstmt.setInt(3, bean.getNum());
					exeCnt = pstmt.executeUpdate();
				} 
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
			return exeCnt;
		}
		// Q&A 글 수정페이지(/csc/modifyProc.jsp) 끝
		
		// Q&A 글 답변페이지(/csc/reply.jsp) 시작
		public int replyBoard(CscBean bean) {
			String sql=null;
			int resCnt = 0;
			
			try {
				sql = "insert into tblcsc ( uid, uname, content, ";
				sql += "subject, ref, pos, depth, regtm, readcnt, ";
				sql += "ip) values(?,?,?,?,?,?,?, now(),0,?)";
				
				int depth = bean.getDepth()+1;
				int pos = bean.getPos()+1;
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bean.getUid());
				pstmt.setString(2, bean.getUname());
				pstmt.setString(3, bean.getContent());
				pstmt.setString(4, bean.getSubject());
				pstmt.setInt(5, bean.getRef());
				pstmt.setInt(6, pos);
				pstmt.setInt(7, depth);
				pstmt.setString(8, bean.getIp());
				resCnt = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return resCnt;
		}
		// Q&A 글 답변페이지(/csc/updateProc.jsp) 끝
		
		
		// Q&A 글 답변글 끼어들기(/csc/replyProc.jsp) 시작
		public int replyUpBoard(int ref, int pos) {
			String sql = null;
			int cnt = 0;
			
			// 게시글의 포지션 증가 시작
			
			try {
				sql = "update tblcsc set pos = pos +1 ";
				sql += "where ref = ? and pos > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, pos);
				cnt = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return cnt;
		}
		// Q&A 글 답변글 끼어들기(/csc/updateProc.jsp) 끝
		
		
		
}
