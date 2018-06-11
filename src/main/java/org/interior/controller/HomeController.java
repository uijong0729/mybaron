package org.interior.controller;
import org.interior.repository.User;
import org.interior.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class HomeController {
	
	@Autowired
	private UserRepository userDAO;
	
	@PostMapping("/insertUser")
	public String insertUser(User user) {
		
		userDAO.save(user);
		
		return "redirect:/";
	}
	
	@GetMapping("/signup")
	public String signup() {
		//System.out.println("GET : signup");
		
		return "/user/form";
	}
	
	//회원리스트
	@GetMapping("/list")
	public String users(Model model) {
		
		//리스트 출력
		model.addAttribute("userList", userDAO.findAll());
		
		return "/user/list";
	}
	
	@ResponseBody
	@PostMapping("/requestLogin")
	public String requestLogin(User user) {
		
		//System.out.println(user);
		
		return "엿머거라";
		
	}
	
	
	
	
	
}
