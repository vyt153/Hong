package pack.entity;

import java.util.Date;

public class NoticeView extends Notice {
	private int cmtCount;
	
	public int getCmtCount() {
		return cmtCount;
	}
	public void setCmtCount(int cmtCount) {
		this.cmtCount = cmtCount;
	}
	public NoticeView() {
	}
	public NoticeView(int id, String title, String writer_ID, int hit, String files, Date regdate, String content, boolean pub, int cmtCount) {
		super(id, title, writer_ID, files, hit, regdate, "", pub );
		this.cmtCount = cmtCount;
	}

}
