package org.interior.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/*
 * 
 * 1. 개체임을 명시하는 애노테이션 추가 : @Entity
 * 2. 기본키 명시 애노테이션 추가 : @Id
 * 3. @GeneratedValue : 데이터베이스에서 자동으로 1씩 증가시켜주는 변수
 * 4. @Column(nullable=false, length=20) : null을 허용하지 않는 컬럼, 길이제한 20자
 * 
 *  ***H2DB는 데이터가 휘발성이므로 데이터가 날아가지 않도록 설정해야함
 * 
 */
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="summoner_name", nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false, length = 20)
	private String password;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
