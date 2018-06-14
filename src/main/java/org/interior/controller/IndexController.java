package org.interior.controller;

import org.interior.repository.GetkeyRepository;
import org.interior.repository.Geykey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
	@Autowired
	private GetkeyRepository keyDAO;
	
	@GetMapping("")
	public String home() {
		return "index";
	}
	
	@PostMapping("/insertKey")
	public String insertKey(Geykey key) {
		
		if(key.getId() == 9435L)
		{
			Geykey newKey = new Geykey(9435L, key.getKey());
			keyDAO.save(newKey);
			System.out.println("키 교체 = " + newKey);
		}
		return "index";
	}
	

}
