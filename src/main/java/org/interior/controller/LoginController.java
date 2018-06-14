package org.interior.controller;

import javax.servlet.http.HttpSession;

import org.interior.repository.Geykey;
import org.interior.repository.getkeyRepository;
import org.interior.repository.User;
import org.interior.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private UserRepository userDAO;
	
	@Autowired
	private getkeyRepository keyDAO;
	
	@PostMapping("/requestLogin")
	public String requestLogin(User user, HttpSession session) {
		
		User findUser = userDAO.findByName(user.getName());
		System.out.println("findUser" + findUser);
		System.out.println("user" + user);
		
		if(findUser == null)
		{
			return "";
		}
		else
		{	
			//비밀번호가 맞는지 체크
			if(user.getPassword().equals(findUser.getPassword()))
			{
				session.setAttribute("user", findUser);
				if(findUser.getName().equals("푸르밀"))
				{
					return "Manager";
				}
				else 
				{
					return findUser.getName();
				}
			}
		}
		
		return "";
		
	}
	
	@PostMapping("/requestLogout")
	public void requestLogout(HttpSession session) {
		session.removeAttribute("user");
		
	}
	
	@PostMapping("/inputKey")
	public void requestLogout(String prompt) {
		//키 교체
		Geykey newKey = new Geykey(9435L, prompt);
		keyDAO.save(newKey);
		System.out.println("키 교체 = " + newKey);
	}
	
}
