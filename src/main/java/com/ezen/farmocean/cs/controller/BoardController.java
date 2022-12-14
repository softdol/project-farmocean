package com.ezen.farmocean.cs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.dto.CsBoard;
import com.ezen.farmocean.cs.service.BoardService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;

//@Log4j2
@Controller
public class BoardController {
	
	// 미해결
	// 파일 저장 경로 => 상대 경로로 찾아서 현채 프로젝트와 같은 경로로 저장되게
	// 로그인 정보 set, get	[22.08.26]
	// 상품 관리자 페이지 정보수정 가능하게(메인에 표시될 상품 정보 불러오기 관련) => 테이블 추가 후 관리 예정
	// 로그인 정보 ajax 연동
	//		1. 로그인 정보및 로그인 버튼 노출
	//		2. 목록에 글등록 노출
	//		3. 로그인 정보 필요한곳에 로그인 정보 체크
	
	@Autowired
	BoardService service;
	
	@Autowired
	JsonProdService serviceJson;
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	HttpServletRequest req;
		
	@GetMapping({"/board", ""})
	public String boardRoot(Model model) {	
		return "redirect:/board/notice";		
	}
	
	@GetMapping({"/board/notice/", "/board/notice"})
	public String boardNotice() {	
		return "redirect:/board/notice/1";		
	}
	
	/*
	 * 공지사항 목록(패이징)
	 */
	@GetMapping("/board/notice/{page}")
	public String boardNotice(Model model, @PathVariable Integer page) {
		
		int pageSize = 5;
		
		model.addAttribute("boards", service.getBoardList(page, pageSize, 3));
		
		int totalCnt = service.getBoardCount();
		
		int pageLsit = totalCnt % pageSize == 0 ? totalCnt / pageSize : totalCnt / pageSize + 1;
		
		model.addAttribute("page", page);
		model.addAttribute("pageLsit", pageLsit);
		
		HttpSession session = req.getSession();
		
		LoginMember lInfo = cf.loginInfo(req);
		
		if(cf.chkNull(lInfo.getMember_id())){
			session.setAttribute("admin", 0);
		}else {		
			session.setAttribute("admin", serviceJson.chkAdmin(lInfo.getMember_id()));
		}
				
		return "board/notice";
	}
	
	/*
	 * 공지사항 등록 양식
	 */
	@GetMapping("/board/insert")
	public String boardInsert(Model model) {
		//model.addAttribute("catagorys", service.getGateList());
		return "redirect/notice";
	}
	
	/**
	 * 공지사항 등록
	 * @param board
	 * @return
	 */
	@PostMapping("/board/insert")
	public String boardInsert(CsBoard board) {
		
		LoginMember mInfo = cf.loginInfo(req);
		
		if(mInfo.getMember_id() == null) {
			return "redirect:insert";
		}
		
		board.setBoard_header(0);
		board.setBoard_writer(mInfo.getMember_id());
//		log.info(board);
//		log.info(board.getBoard_memo().length());
		
		if(cf.chkNull(board.getBoard_title()) || cf.chkNull(board.getBoard_memo())) {
			return "redirect:insert";
		}
		
		if(service.setBoardIns(board) > 0) {
			return "redirect:notice";
		}else {
			return "redirect:insert";
		}
	}
	
	/**
	 * 게시글 보기
	 * @param board_idx
	 * @param model
	 * @return
	 */
	@GetMapping("/board/view/{board_idx}")
	public String boardView(@PathVariable Integer board_idx, Model model, Integer page) {		
//		service.setBoardCount(board_idx);
//		CsBoard board = service.getBoardInfo(board_idx);
//		board.setBoard_memo(cf.chgHtml(board.getBoard_memo()));
//		
//		model.addAttribute("board", board);
//		model.addAttribute("page", page);
//		return "board/view";
		return "redirect/notice";
	}
	
	@GetMapping("/board/notice_insert")
	public String boardNoticeHtmlIns() {
		return "redirect/notice";
	}
	
	@GetMapping("/board/apitest")
	public String boardApiTest() {
		return "redirect/notice";
		
	}

}
