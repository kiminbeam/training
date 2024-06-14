package com.example.viewServer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.viewServer.Repository.UserRepository;
import com.example.viewServer.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class viewController {
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping("/")
	public String root() {
		return "index";
	}
	
	@RequestMapping("/joinform")
	public String joinform() {
		return "joinform";
	}
	
	@RequestMapping("/join")
	public String join(User user,HttpServletRequest request) {
		User u = userRepo.save(user);
		request.setAttribute("user", u);
		
		return "redirect:/";
	}
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "loginform";
	}
	
	@RequestMapping("/loginNow")
	public String login(HttpSession session,@RequestParam("username") String username, @RequestParam("password")String password) {
		
		User user = userRepo.findByUnPw(username, password);
		session.setAttribute("user", user);
		
		session.setAttribute("username", user.getUsername());
		session.setAttribute("password", user.getPassword());
		session.setAttribute("tel", user.getTel());
		session.setAttribute("role", user.getRole());
		
		return "redirect:/mainpage";
	}
	
	@RequestMapping("/mainpage")
	public String mainpage() {
		return "mainpage";
	}
	
	@RequestMapping("/restMainPage")
	public String mainpage2() {
		return "restMainPage";
	}
	
	@RequestMapping("/boardPage")
	public String boardPage() {
		return "boardPage";
	}
}
