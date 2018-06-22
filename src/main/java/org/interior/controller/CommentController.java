package org.interior.controller;


import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.interior.repository.Comment;
import org.interior.repository.CommentRepository;
import org.interior.repository.User;
import org.interior.repository.Visitor;
import org.interior.repository.VisitorRepository;
import org.interior.util.Utility;
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
	
	@Autowired
	CommentRepository cdao;
	
	@ResponseBody
	@PostMapping("/insertUserComment")
	public void insertUserComment(Comment comment, HttpSession session) {
		
			User user = (User) session.getAttribute("user");
			comment.setWriter(user.getName());
			
			cdao.save(comment);
		
	}
	
	@ResponseBody
	@PostMapping("/deleteComment")
	public void deleteUserComment(Comment comment, HttpSession session) {
		User login;
		try 
		{
			login = (User) session.getAttribute("user");
			if(login.getName().equals(comment.getWriter())) 
			{
					cdao.deleteById(comment.getCommentId());
			}
		}
		catch(NullPointerException e)
		{
			return;
		}
			
	}
	
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
		visit.setDate(Utility.currentTime());
		
		vdao.save(visit);
	}
	
	@ResponseBody
	@PostMapping("/readBoardInit")
	public String readBoardInit(String writer, HttpSession session) {
		
		User login = (User) session.getAttribute("user");
		
		if(login.getName().equals(writer))
		{
			return "ok";
		}
		else
		{
			return "";
		}
		
	}
	
	@GetMapping("/deleteVisitor")
	public String deleteVisitor(int num, String writer, HttpSession session)
	{
		User login = (User) session.getAttribute("user");
		
		if(login.getName().equals(writer))
		{
			vdao.deleteById(num);
		}
		
		
		return "redirect:/visitor?nPage=0";
	}
	
	
	@ResponseBody
	@PostMapping("/updateVisitor")
	public void updateVisitor(Visitor visit) {
		visit.setDate(Utility.currentTime());
		
		vdao.save(visit);
	}
	
	
	
	
}
