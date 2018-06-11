package org.interior.repository;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Comment {

	@Id
	@GeneratedValue
	private Long commentId;
	
	@Column(name="summoner_target", nullable = false, length = 20)
	private String target;
	
	@ManyToOne
	@JoinColumn(name="summoner_name", insertable=false, updatable=false)
	User user;

	@Column(nullable = false, length = 20)
	private String name;
	
	@Column(nullable = false, length = 1000)
	private String context;
	
	@Column(nullable = false, length = 30)
	private String commentPassword;
	
}
