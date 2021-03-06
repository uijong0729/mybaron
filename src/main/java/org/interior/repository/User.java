package org.interior.repository;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * 
 * 1. 개체임을 명시하는 애노테이션 추가 : @Entity
 * 2. 기본키 명시 애노테이션 추가 : @Id
 * 3. @GeneratedValue : 데이터베이스에서 자동으로 1씩 증가시켜주는 변수
 * 4. @Column(nullable=false, length=20) : null을 허용하지 않는 컬럼, 길이제한 20자
 * 
 * 
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="name", nullable = false, length = 20, unique=true)
	private String name;
	
	@Column(nullable = false, length = 260)
	private String password;
	

	
}
