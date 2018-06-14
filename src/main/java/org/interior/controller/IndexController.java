package org.interior.controller;

import org.interior.repository.RewriteKey;
import org.interior.repository.RewriteKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
	@Autowired
	private RewriteKeyRepository keyDAO;
	
	@GetMapping("")
	public String home() {
		return "index";
	}
	
	@PostMapping("/insertKey")
	public String insertKey(RewriteKey key) {
		
		if(key.getId() == 9435L)
		{
			RewriteKey newKey = new RewriteKey(9435L, key.getInsertKey());
			keyDAO.save(newKey);
			System.out.println("키 교체 = " + newKey);
		}
		return "index";
	}
	

}
