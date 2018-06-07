package org.interior.controller;
import org.interior.repository.User;
import org.interior.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {
	
	@Autowired
	private UserRepository userDAO;
	
	@PostMapping("/insertUser")
	public String insertUser(User user) {
		
		userDAO.save(user);
		
		return "redirect:/";
	}
}
