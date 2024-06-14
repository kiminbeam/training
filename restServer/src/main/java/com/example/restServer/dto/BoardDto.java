package com.example.restServer.dto;

import com.example.restServer.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto {
	
	private Long bno;
	private String title;
	private String content;
	private String username;
	
	public BoardDto(Board board) {
		this.bno = board.getBno();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.username = board.getUser().getUsername();
	}
}
