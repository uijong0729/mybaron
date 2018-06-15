package org.interior.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

	@Id
	@GeneratedValue
	private int vNum;
	
	@Column(name="title", nullable=false, length=100)
	private String title;
	
	@Column(name="content", nullable=false, length=1000)
	private String content;
	
	@Column(name="image", nullable=false, length=400)
	private String image;
	
	@Column(name="writer", nullable=false, length=20)
	private String writer;
	
	@Column(name="date", nullable=false, length=20)
	private String date;
	
	
	
}
