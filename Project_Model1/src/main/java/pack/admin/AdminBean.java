package pack.admin;

public class AdminBean {
	private int num;
	private String admId;
	private String admPw;
	private String admName;
	private String level;
	
	public AdminBean() {}
	public AdminBean(int num, String admId, String admPw, String admName, String level) {
		this.num = num;
		this.admId = admId;
		this.admPw = admPw;
		this.admName = admName;
		this.level = level;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getAdmId() {
		return admId;
	}
	public void setAdmId(String admId) {
		this.admId = admId;
	}
	public String getAdmPw() {
		return admPw;
	}
	public void setAdmPw(String admPw) {
		this.admPw = admPw;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
}
