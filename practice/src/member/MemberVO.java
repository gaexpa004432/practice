package member;

public class MemberVO {
	private String id;
	private String pw;
	private String gender;
	private String job;
	private String reason;
	private String mailyn;
	private String hobby;
	private String regdate;
	
	public MemberVO() {
		super();
	}

	public MemberVO(String id, String pw, String gender, String job, String reason, String mailyn, String hobby, String regdate) {
		super();
		this.id = id;
		this.pw = pw;
		this.gender = gender;
		this.job = job;
		this.reason = reason;
		this.mailyn = mailyn;
		this.hobby = hobby;
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "memberVO [id=" + id + ", pw=" + pw + ", gender=" + gender + ", job=" + job + ", reason=" + reason
				+ ", hobby=" + hobby + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getMailyn() {
		return mailyn;
	}
	public void setMailyn(String mailyn) {
		this.mailyn = mailyn;
	}
	
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
}