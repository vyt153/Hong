package pack.member;

public class MemberBean {
	private String uid;
	private String upw;
	private String uemail;
	private String newId;
	private String newPw;
	private String newEmail;
	
	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}

	public String getNewPw() {
		return newPw;
	}

	public void setNewPw(String newPw) {
		this.newPw = newPw;
	}

	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	public MemberBean() {};
	
	public MemberBean(String uid, String upw, String uemail) {
		this.uid = uid;
		this.upw = upw;
		this.uemail = uemail;
	}
	public MemberBean(String uid, String upw) {
		this.uid = uid;
		this.upw = upw;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUpw() {
		return upw;
	}
	public void setUpw(String upw) {
		this.upw = upw;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	
	
}
