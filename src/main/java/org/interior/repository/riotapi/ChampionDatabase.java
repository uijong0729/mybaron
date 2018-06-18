package org.interior.repository.riotapi;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.interior.repository.Comment;
import org.interior.repository.User;

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
public class ChampionDatabase {

	@Id
	private int individualKey;
	
	@Column(name="name", nullable=false, length=25)
	private String name;
	
	@Column(name="full", nullable=false, length=50)
	private String full;
	
	@Column(name="version", nullable=false, length=30)
	private String version;
	
	
}





