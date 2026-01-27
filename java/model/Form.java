package model;

public class Form {
	private String userId;
	private String pass;
	private String memos;
	
	public Form(String userId, String pass,String memos) {
		this.userId = userId;
		this.pass=pass;
		this.memos=memos;
	}
	
	public Form(String userId, String pass) {
		this.userId = userId;
		this.pass=pass;
		this.memos=userId+"memos";
	}
	
	public String getUserId() { return userId; }
	public String getPass() { return pass; }
	public String getMemos() { return memos; }
}

