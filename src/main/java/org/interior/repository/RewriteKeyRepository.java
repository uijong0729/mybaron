package org.interior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
								//<테이블이름, id의 자료형타입>
public interface RewriteKeyRepository extends JpaRepository<RewriteKey, Long>{
	
	
}
