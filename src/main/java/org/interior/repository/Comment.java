package org.interior.repository;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name="target", nullable = false, length = 20)
	private String target;
	
	@ManyToOne
	@JoinColumn(name="name", insertable=false, updatable=false)
	User user;

	@Column(nullable = false, length = 20)
	private String writer;
	
	@Column(nullable = false, length = 1000)
	private String context;
	
	//사용자의 매너점수
	@Column(nullable = false, length = 3)
	private Long manner;
	
}
