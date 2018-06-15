package org.interior.controller;


import javax.servlet.http.HttpSession;

import org.interior.repository.User;
import org.interior.repository.Visitor;
import org.interior.repository.VisitorRepository;
import org.interior.util.translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	public String visitor(Model model,  
		@PageableDefault(sort = { "num" }, direction = Direction.DESC, size = 12) Pageable pageable) {
		
		Page<Visitor> page = vdao.findAll(pageable);
		model.addAttribute("visitor", page);
		
		System.out.println("getSize = " + page.getTotalElements());
		
		if(page.getTotalElements() >= 12)
		{
			vdao.deleteAll();
		}
		
		return "board/visitor";
	}
	
	//게시글 읽기
	@GetMapping("/readVisitor")
	public String readVisitor(int num, Model model) {
		
		//System.out.println(vNum);
		
		Visitor board = vdao.findById(num).get();
		
		model.addAttribute("board", board);
		
		return "board/readVisitor";
	}
	
	@ResponseBody
	@PostMapping("/writeVisitor")
	public void writeVisitor(Visitor visit, HttpSession session) {
		
		User getUser = (User) session.getAttribute("user");
		
		visit.setWriter(getUser.getName());
		visit.setDate(translation.currentTime());
		
		vdao.save(visit);
	}
	
}
