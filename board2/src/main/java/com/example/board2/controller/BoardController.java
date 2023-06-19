package com.example.board2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {
	
	@RequestMapping(value = "/") //localhost로 접속
	public String index() {
		return "/index";
	}
	
	//get방식으로 request가 들어올때
	@RequestMapping(value = "/created", method = RequestMethod.GET) //localhost로 접속
	public String created() {
		return "bbs/created";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET) //localhost로 접속
	public String list() {
		return "bbs/list";
	}
	
	@RequestMapping(value = "/article", method = RequestMethod.GET) //localhost로 접속
	public String article() {
		return "bbs/article";
	}
	
	@RequestMapping(value = "/updated", method = RequestMethod.GET) //localhost로 접속
	public String updated() {
		return "bbs/updated";
	}
}
