package com.example.restServer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restServer.dto.BoardDto;
import com.example.restServer.entity.Board;
import com.example.restServer.entity.User;
import com.example.restServer.repository.BoardRepository;
import com.example.restServer.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
public class restController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	BoardRepository boardRepo;
	
	@PostMapping("/regUser")
	public User regUser(@RequestBody User user) {
		
		System.out.println(user); 
		
		User u = userRepo.save(user);
		return u;
	}
	
	@PostMapping("/Board")
	public Board regBoard(@RequestBody BoardDto boardDto, HttpServletRequest request) {
		System.out.println(boardDto);
		
		String user = boardDto.getUsername();
		System.out.println(user);
		
		User u = new User();
		u.setUsername(user);
		
		
		String username = request.getHeader("username");
		System.out.println("headerData: " + username);
		
		Board board = new Board();
		board.setUser(u);
		//board.setTitle(boardDto.getTitle());
		//board.setContent(boardDto.getContent());
		
		
		String userinfo = request.getHeader("userinfo");
		System.out.println(userinfo);
		
		Board b = boardRepo.save(board);
		System.out.println(b);
		
		return b;
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
		String un = request.getHeader("username");
		System.out.println("getHeader:" +un);
		
		Optional<User> result= userRepo.findById(un);
		User u= result.get();
		System.out.println(u);
		
		if(u != null) {
			String username = u.getUsername();
			System.out.println("username: " + username);
			
			//토큰생성
			String token = "token01";
			
			response.addHeader("username", username);
			response.addHeader("token", token);
			
			response.addHeader("Access-Control-Expose-Headers", "username,token");
			return "success: " + u;
		}
		return "false";
	}
	
	@GetMapping("/board")
	public List<BoardDto> getBoard(){
		List<Board> boardlist = boardRepo.findAll();
		
		List<BoardDto> boardDtoList = new ArrayList<>();
		
		for(Board board : boardlist) {
			BoardDto boardDto = new BoardDto(board);
			System.out.println(boardDto);
			
			boardDtoList.add(boardDto);
		}
		return boardDtoList;
	}
	
}
