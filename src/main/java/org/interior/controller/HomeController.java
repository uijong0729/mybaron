package org.interior.controller;
import org.interior.repository.User;
import org.interior.repository.UserRepository;
import org.interior.riot.bo.ChampionApi;
import org.interior.riot.model.Champion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HomeController {
	
	@Autowired
	private UserRepository userDAO;


	@PostMapping("/insertUser")
	public String insertUser(User user) {
		
		userDAO.save(user);
		
		return "redirect:/";
	}
}
