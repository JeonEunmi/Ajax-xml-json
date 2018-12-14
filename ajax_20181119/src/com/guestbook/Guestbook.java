package com.guestbook;
 
 
public class Guestbook {
 
    
    private String gid, name_, pw, content, clientIP, regDate;
    private int blind;
    
    
    public Guestbook() {
        
    }
    
    
    public Guestbook(String gid, String name_, String pw, String content, String regDate, String clientIP, int blind) {
        this.gid = gid;
        this.name_ = name_;
        this.pw = pw;
        this.content = content;
        this.regDate = regDate;
        this.clientIP = clientIP;
        this.blind = blind;
    }
    
    
    public Guestbook(String gid, String name_, String content, String regDate) {
        this.gid = gid;
        this.name_ = name_;
        this.content = content;
        this.regDate = regDate;
 
    }
    
    
    public String getGid() {
        return gid;
    }
    public void setGid(String gid) {
        this.gid = gid;
    }
    public String getName_() {
        return name_;
    }
    public void setName_(String name_) {
        this.name_ = name_;
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
    public String getClientIP() {
        return clientIP;
    }
    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
    public String getRegDate() {
        return regDate;
    }
    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
    public int getBlind() {
        return blind;
    }
    public void setBlind(int blind) {
        this.blind = blind;
    }
    
    
}
 
