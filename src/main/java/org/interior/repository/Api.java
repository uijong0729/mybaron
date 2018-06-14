package org.interior.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class Api {
	
	@Id
	@Column(name="Id", nullable = false, length = 10)
	private Long Id;
	
	@Column(name="key", nullable = false, length = 512)
	private String key;
}
