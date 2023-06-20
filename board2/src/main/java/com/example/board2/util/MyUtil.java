package com.example.board2.util;

import org.springframework.stereotype.Service;

@Service
public class MyUtil {
	//전체페이지의 갯수를 구한다
	public int getPageCount(int numPerPage, int dataCount) {
		int pageCount = 0;
		pageCount = dataCount / numPerPage;
		
		if(dataCount % numPerPage != 0) {
			pageCount++;
		}		
		return pageCount;
	}
	
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		StringBuffer sb = new StringBuffer(); // 문자열 데이터를 자주 추가하거나 삭제할 때는 메모리 낭비 방지를 위해 StringBuffer 사용 권장
		int numPerBlock = 5;   // ◀이전 6 7 8 9 10 다음▶ 이전과 다음 사이의 숫자를 몇개를 표시할지
		int currentPageSetup;  //◀이전 버튼에 들어갈 값
		int page;			   // 그냥 페이지 숫자를 클릭했을 때 들어갈 값
		
		if(currentPage == 0 || totalPage == 0) return "";  //데이터가 없다
		
		//검색어가 있을때: /list?searchKey=name&searchValue=춘식
		if(listUrl.indexOf("?") != -1) {
			// "?" 가 들어있다면 => 쿼리스트링이 들어있다는 뜻
			listUrl += "&";			
		} else { //쿼리스트링이 없으면
			listUrl += "?";
		}
		
		
		//1. ◀이전 버튼 만들기
		
		//currentPage 가 (1~4) (5~9) (10~14) (15~19) .. 일때 => currentPageSetup 0 5 10 15.. 가 됨 (정수/정수는 정수 이므로 1~4 는 0.*** 이므로 currentPage / numPerBlock = 0 이 된다)
 		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;
 		
 		//currentPage 가 5 10 15 20 ... 일때 currentPageSetup 0 5 10 15 .. 
 		if(currentPage % numPerBlock == 0) {
 			currentPageSetup = currentPageSetup - numPerBlock;
 		}
 		
 		if(totalPage > numPerBlock && currentPageSetup > 0) {
 			sb.append("<a href=\"" + listUrl + "pageNum" + currentPageSetup + "\">◀이전</a>&nbsp"); 
 						//↑ <a href="list.jsp?pageNum=5"> ◀이전 </a> 모양을 만든다
 		}
		
		//2. 6 7 8 9 10 그냥 페이지 이동 버튼 만들기
 		
 		page = currentPageSetup + 1; // => page = 1, 6, 11, 16....
 		
 		// ◀이전 1 2 3 4 5 다음▶ 만드는 공식
 		while(page <= totalPage && page <= (currentPageSetup + numPerBlock)) {
 			
 			//현재 내가 선택한 페이지의 숫자의 색상변경
 			//현재 내가 선택한 페이지라면
 			if(page == currentPage) {
 				sb.append("<font color=\"red\">" + page + "</font> &nbsp;");
 						 //<font color="red">9</font>&nbsp
 			} else { //현재 내가 선택한 페이지가 아니라면
 				sb.append("<a href=\"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
 						 //<a href="list?pageNum=7">7</a>&nbsp
 			}
 			page++;
 			
 		}
		
		//3. 다음▶ 버튼 만들기
		if(totalPage - currentPageSetup > numPerBlock) {
			sb.append("<a href=\"" + listUrl + "pageNum" + currentPageSetup + "\">다음▶</a>&nbsp");
		}				//↑ <a href="list.jsp?pageNum=11">다음▶</a> 모양을 만든다
		
		//4. 버튼 합쳐서 문자열로 리턴			
		System.out.println(sb.toString());
		
		return sb.toString();
	}

}
