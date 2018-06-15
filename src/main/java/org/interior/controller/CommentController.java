package org.interior.controller;

import javax.servlet.http.HttpSession;

import org.interior.repository.User;
import org.interior.repository.Visitor;
import org.interior.repository.VisitorRepository;
import org.interior.util.InstanceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {

	@Autowired
	VisitorRepository vdao;
	
	@GetMapping("/visitor")
	public String visitor(Model model) {
		
		model.addAttribute("visitor", vdao.findAll());
		
		return "board/visitor";
	}
	
	@ResponseBody
	@PostMapping("/writeVisitor")
	public void writeVisitor(Visitor visit, HttpSession session) {
		
		User getUser = (User) session.getAttribute("user");
		
		visit.setWriter(getUser.getName());
		vdao.save(visit);
	}
	
}
