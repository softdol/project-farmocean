package com.ezen.farmocean.follow.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ezen.farmocean.admin.dto.BuyListInfo;
import com.ezen.farmocean.admin.service.JsonProdService;
import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.member.dto.LoginMember;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ListController {
	
	@Autowired
	CommonFunction cf;
	
	@Autowired
	JsonProdService serviceJson;			
	
	@GetMapping("/list/buylist")
	public String viewBuyList(HttpSession session, Model model) {
		
		
		log.info("���� ����");
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		String member_id = member.getMember_id();
		List<BuyListInfo> buyList = serviceJson.selBuyList(member_id, 1, 10);
		for(BuyListInfo b : buyList) {
			b.setView_price(cf.viewWon(b.getProd_price()));
			b.setView_regdate(cf.viewDate(b.getReg_date()));
			b.setDec();
			b.setAddress();
		}
		
		model.addAttribute("buyList", buyList);
		return "list/buylist";
	}
	
	
	@GetMapping("/list/selllist")
	public String viewSellList(HttpSession session,Integer pageNum, Model model) {
		if(cf.chkNull(pageNum)) {
			pageNum = 1;
		}
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		String member_id = member.getMember_id();
		int buyCount = serviceJson.selSellCount(member_id);
		int pageSize = 10;
		
		int totalPage = buyCount % pageSize == 0 ? buyCount / pageSize : buyCount / pageSize + 1;
		
		List<BuyListInfo> sellList = serviceJson.selSellList(member_id , pageNum, pageSize);
		for(BuyListInfo b : sellList) {
			b.setView_price(cf.viewWon(b.getProd_price()));
			b.setView_regdate(cf.viewDate(b.getReg_date()));
			b.setDec();
			b.setAddress();
		}
		
		model.addAttribute("sellList", sellList);
		model.addAttribute("page", pageNum);
		model.addAttribute("pageLsit", totalPage);
		return "list/selllist";
	}
	


	@GetMapping("/list/sellsearch")
	public void viewSellSearch() {
		
	}
	
	@GetMapping("/list/prodsearch")
	public void viewProdSearch() {
		
	}
}