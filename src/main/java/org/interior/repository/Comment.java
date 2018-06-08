package org.interior.repository;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {

	@Id
	@GeneratedValue
	private Long commentId;
	
	@Column(name="summoner_target", nullable = false, length = 20)
	private String target;
	
	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false, length = 1000)
	private String context;
	
	@Column(nullable = false, length = 30)
	private String commentPassword;


	
	
	/*
	 * 
	 * 
	 *  
	 * 
	 * 
	 */
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getCommentPassword() {
		return commentPassword;
	}
	public void setCommentPassword(String commentPassword) {
		this.commentPassword = commentPassword;
	}
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
