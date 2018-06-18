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
	
	@Column(name="target", nullable = false, length = 30)
	private String target;

	@Column(nullable = false, length = 30)
	private String writer;
	
	@Column(nullable = false, length = 1000)
	private String context;
	
	
}
