package pack.entity;

import java.util.Date;

public class Notice {
	private int id;
	private String title;
	private String writer_ID;
	private String files;
	private int hit;
	private Date regdate;
	private String content;
	private boolean pub;
	
	public Notice() {}
	
	public Notice(int id, String title, String writer_ID, String files, int hit, Date regdate, String content,
			boolean pub) {
		this.id = id;
		this.title = title;
		this.writer_ID = writer_ID;
		this.files = files;
		this.hit = hit;
		this.regdate = regdate;
		this.content = content;
		this.pub = pub;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter_ID() {
		return writer_ID;
	}
	public void setWriter_ID(String writer_ID) {
		this.writer_ID = writer_ID;
	}
	public String getFiles() {
		return files;
	}
	public void setFiles(String files) {
		this.files = files;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean getPub() {
		return pub;
	}

	public void setPub(boolean pub) {
		this.pub = pub;
	}

	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", writer_ID=" + writer_ID + ", files=" + files + ", hit="
				+ hit + ", regdate=" + regdate + ", content=" + content + ", pub=" + pub + "]";
	}

}
