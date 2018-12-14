package com.guestbook;

public class Guestbook {

	private String gid, name, pw, content, regDate, clientIP, blind;

	public Guestbook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Guestbook(String gid, String name, String pw, String content, String regDate, String clientIP,
			String blind) {
		super();
		this.gid = gid;
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.regDate = regDate;
		this.clientIP = clientIP;
		this.blind = blind;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getClientIP() {
		return clientIP;
	}

	public void setClientIP(String clientIP) {
		this.clientIP = clientIP;
	}

	public String getBlind() {
		return blind;
	}

	public void setBlind(String blind) {
		this.blind = blind;
	}
	
	
}
