package org.interior.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Summoner {
	
	private Long id;
	private Long accountId;
	private String name;
	
}
