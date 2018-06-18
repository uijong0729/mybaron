package org.interior.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.interior.repository.User;
import org.interior.repository.Visitor;
import org.interior.repository.VisitorRepository;
import org.interior.util.translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
	public String visitor(Model model, int nPage) {
		
				
		//페이지, 아이템갯수, 정렬방식, 정렬
		PageRequest page = PageRequest.of(nPage, 5, Direction.DESC, "num");
		
		Page<Visitor> pageList = vdao.findAll(page);
		model.addAttribute("visitor", pageList.getContent());
		
		
		ArrayList<Integer> pList = new ArrayList<Integer>();
		
		for(int i = 0 ; i < pageList.getTotalPages() ; i++)
		{
			pList.add(i);
		}
		
		model.addAttribute("nPage", pList);
		
		
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
