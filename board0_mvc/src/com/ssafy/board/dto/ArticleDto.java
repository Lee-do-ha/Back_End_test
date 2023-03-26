package com.ssafy.board.dto;

// 데이터를 담아서 전달할 Dto
public class ArticleDto {
	
	
	private int articleNo, hit;
	private String userId, content, subject, registerTime;
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	@Override
	public String toString() {
		return "ArticleDto [articleNo=" + articleNo + ", hit=" + hit + ", userId=" + userId + ", content=" + content
				+ ", subject=" + subject + ", registerTime=" + registerTime + "]";
	}
	
}
