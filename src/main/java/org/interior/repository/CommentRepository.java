package org.interior.repository;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
								//<테이블이름, id의 자료형타입>
public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	ArrayList<Comment> findByTarget(String name);
	Page<Comment> findAllByTargetOrderByCommentIdDesc(String target, Pageable pageable);
}
