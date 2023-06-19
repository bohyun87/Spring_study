package com.example.board2.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.board2.dto.Board;
import com.example.board2.service.BoardService;
import com.example.board2.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	
	
	@Autowired  //빈객체로 되어 있는 것들은 의존성 주입을 할 수 있다.
	private BoardService boardService;	
	
	@Autowired  //빈객체로 되어 있는 것들은 의존성 주입을 할 수 있다.  => 의존관계를 스프링컨데이너에서 만들어줌
	MyUtil myUtil;
	
	
	@RequestMapping(value = "/") //localhost로 접속
	public String index() {
		return "/index";
	}
	
	//get방식으로 request가 들어올때  => 페이지 보여주는 메소드
	@RequestMapping(value = "/created", method = RequestMethod.GET) //localhost로 접속
	public String created() {
		return "bbs/created";
	}
	
	//게시글 등록하는 메소드                 //↓ created.html 에서 method가 post 방식이기 때문에
	@RequestMapping(value = "/created", method = RequestMethod.POST) //localhost로 접속
	public String createdOK(Board board, HttpServletRequest request, Model model) {
		
		try {
			int maxNum = boardService.maxNum();
			
			board.setNum(maxNum + 1);  //num 컬럼에 들어갈 값을 1 증가 시켜준다.
			board.setIpAddr(request.getRemoteAddr()); //클라이언트의 ip 주소를 구해준다.
			
			boardService.insertData(board);  //jstl 처럼 beanutil 과 같이 처리할 필요 없음
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/list";
	}
	
	//리스트 페이지 보여줌(get 방식 post 방식 전부 여기로 들어옴)
	@RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST}) //localhost로 접속
	public String list(Board board, HttpServletRequest request, Model model) {
		try {
		
		String pageNum = request.getParameter("pageNum");  //바뀌는 페이지 번호
		
		int currentPage = 1; //현재 페이지 번호
		
		 if(pageNum != null) currentPage = Integer.parseInt(pageNum);
		 

		String searchKey = request.getParameter("searchKey");  //검색키워드(subject, content, name)
		String searchValue = request.getParameter("searchValue");  //검색어
		
		if(searchValue == null) {
			searchKey = "subject";  //검색 키워드의 디폴트는 subject
			searchValue = "";		//검색어의 디폴트는 빈문자열
		} else {
			if(request.getMethod().equalsIgnoreCase("GET")) {	
				//get 방식으로 request 가 들어왔다면
					
				//쿼리 파라메터의 값(searchValue)을 디코딩해준다.
				searchValue = URLDecoder.decode(searchValue, "UTF-8");				
			}
		}
		
		//1. 전체 게시물의 갯수를 가져온다(페이징 처리에 필요)
		int dataCount = boardService.getDataCount(searchKey, searchValue);
		
		//2. 페이징 처리를 한다.(준비단계)
		int numPerPage = 5; //페이지당 보여줄 데이터의 갯수
		int totalPage = myUtil.getPageCount(numPerPage, dataCount) ;// 페이지의 전체 갯수를 구한다.
		
		if(currentPage > totalPage) currentPage = totalPage; //totalPage 가 currentPage 보다 크면 안되니까 설정
		
		int start = (currentPage - 1) * numPerPage + 1;  //1 6 11 16....
		int end = currentPage * numPerPage;				 //5 10 15 20....
		
		//3. 전체 게시물 리스트를 가져온다.
		List<Board> lists = boardService.getLists(searchKey, searchValue, start, end);
		
		
		//4. 페이징 처리를 한다.
		String param = "";
		
		if(searchValue != null && searchValue.equals("")) {
			//검색어가 있다면
			param = "searchKey=" + searchKey;
			param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");  //컴퓨터의 언어로 인코딩					
		}
		
		String listUrl = "/list";
		
		///list?searchKey=***&searchValue=***
		if(!param.equals("")) listUrl += "?" + param;
			
		String pageIndexLString = myUtil.pageIndexList(currentPage, totalPage, listUrl);
		
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "bbs/list";
	}
	
	@RequestMapping(value = "/article", method = RequestMethod.GET) //localhost로 접속
	public String article() {
		return "bbs/article";
	}
	
	// 수정페이지 보여주는 메소드
	@RequestMapping(value = "/updated", method = RequestMethod.GET) //localhost로 접속
	public String updated(HttpServletRequest request, Model model) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String searchKey = request.getParameter("searchKey");
		String searchValue = request.getParameter("searchValue");
		
		if(searchValue != null) {
			
			
		}
		
		return "bbs/updated";
	}
	
	//게시글 수정하는 메소드
	@RequestMapping(value = "/updated_ok", method = RequestMethod.POST) //localhost로 접속
	public String updatedOK() {
		return "bbs/updated";
	}
}
