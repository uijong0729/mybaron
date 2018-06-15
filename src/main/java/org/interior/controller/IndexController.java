package org.interior.controller;

import org.interior.repository.Api;
import org.interior.repository.ApiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {
	
	@Autowired
	private ApiRepository keyDAO;
	
	@GetMapping("")
	public String home() {
		return "index";
	}
	
	@PostMapping("/insertKey")
	public String insertKey(Api key) {
		
		if(key.getId() == 9435L)
		{
			Api newKey = new Api(9435L, key.getApi_key());
			keyDAO.save(newKey);
			System.out.println("키 교체 = " + newKey);
		}
		return "index";
	}
	

}
