package org.interior.controller;

import javax.servlet.http.HttpSession;

import org.interior.repository.User;
import org.interior.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private UserRepository userDAO;
	
	@PostMapping("/requestLogin")
	public String requestLogin(User user, HttpSession session) {
		
		User findUser = userDAO.findByName(user.getName());
		if(findUser == null)
		{
			return "";
		}
		else
		{
			if(user.getPassword().equals(findUser.getPassword()))
			{
				session.setAttribute("user", findUser);
				return findUser.getName();
			}
		}
		
		return "";
		
	}
	
	@PostMapping("/requestLogout")
	public void requestLogout(HttpSession session) {
		session.removeAttribute("user");
		
	}
}
