package com.ezen.farmocean.member.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.farmocean.cs.service.CommonFunction;
import com.ezen.farmocean.mainPage.dto.Criteria;
import com.ezen.farmocean.member.dto.LoginMember;
import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RequestMapping("/member")
@Controller
public class SignController {

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {

		return "member/buyerJoin";
	}

	@RequestMapping(value = "/sellerjoin", method = RequestMethod.GET)
	public String sellerjoin(Locale locale, Model model) {

		return "member/sellerJoin";
	}


	@Autowired
	MemberService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, String retUrl) {
		log.info(retUrl);

		model.addAttribute("retUrl", retUrl);
		return "member/login";
	}

	@Autowired
	private CommonFunction cf;

	@RequestMapping(value = "/logincheck", method = RequestMethod.POST)
	public String loginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, LoginMember member,
			Criteria cri, Model model, String member_id) throws Exception {
		member.setMember_pw(member.pwEncrypt(member.getMember_pw()));
		LoginMember loginMember = service.loginCheck(member);
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (loginMember == null) {
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>alert('?????? ?????? ????????????.'); history.go(-1);</script>");
			out.flush();

			return "member/login";

		} else {

			loginMember.setMember_name(loginMember.decrypt(loginMember.getMember_name()));
			session.setAttribute("loginId", loginMember); // ???????? ??????, ???????? ???? (?????? ????)
			String returnUrl = "/";

			if (!cf.chkNull(member.getRetUrl())) {
				returnUrl = member.getRetUrl();
			}

			return "redirect:" + returnUrl;

		}

	}

	@RequestMapping(value = "/naverlogincheck", method = RequestMethod.POST)
	public String naverLoginPOST(Locale locale, HttpServletRequest request, HttpServletResponse response, Member member,
			Criteria cri, Model model, String member_id) throws Exception {
		member.setMember_pw(member.pwEncrypt(member.getMember_pw()));

		Member loginMember = service.naverLoginCheck(member);

		HttpSession session = request.getSession();
		if (loginMember == null) {

			session.setAttribute("naverId", member);
			return "member/naver_select_type";
		} else {

			LoginMember naverLoginMember = new LoginMember();
			naverLoginMember.setMember_id(loginMember.getMember_id());
			naverLoginMember.setMember_name(loginMember.getMember_name());
			naverLoginMember.setMember_nickName(loginMember.getMember_nickName());
			naverLoginMember.setMember_pw(loginMember.getMember_pw());
			naverLoginMember.setMember_type(loginMember.getMember_type());
			naverLoginMember.setDec();
	
			session.setAttribute("loginId", naverLoginMember); // ???????? ??????, ???????? ???? (?????? ????)

			String returnUrl = "/";

			return "redirect:" + returnUrl;

		}

	}

	@RequestMapping(value = "/naverBuyerJoin", method = RequestMethod.GET)
	public String naverBuyerJoin(Locale locale, Model model) {

		return "member/naverBuyerJoin";
	}

	@RequestMapping(value = "/naverSellerJoin", method = RequestMethod.GET)
	public String naversellerJoin(Locale locale, Model model) {

		return "member/naverSellerJoin";
	}

	@RequestMapping(value = "/searchId", method = RequestMethod.GET)
	public String searchId(Locale locale, Model model) {

		return "member/searchId";
	}

	@RequestMapping(value = "/searchPw", method = RequestMethod.GET)
	public String searchPw(Locale locale, Model model) {

		return "member/searchPw";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		LoginMember member = (LoginMember) session.getAttribute("loginId");
		session.invalidate();
		return "member/logout";
	}

	@RequestMapping(value = "/naver_callback", method = RequestMethod.GET)
	public String naverLogin(Locale locale, Model model) {

		return "member/naver_callback";
	}

//	@RequestMapping(value = "/come", method = RequestMethod.GET)
//	public String pwChange() throws Exception {
//		List<Member>list = service.getList();
//		for(int i = 0 ; i < list.size(); ++i) {
//			Member a = list.get(i);
//			a.setMember_pw(a.pwEncrypt(a.getMember_pw()));
//			
//			service.pwChange(a);	
//		}
//		
////		list.get(0).setMember_pw(list.get(0).pwEncrypt(list.get(0).getMember_pw()));
////		service.pwChange(a);
//		
//		return "member/login";
//	}

	@RequestMapping(value = "/pwChange", method = RequestMethod.GET)
	public String pwChange(Locale locale, LoginMember member) throws Exception {

		return "member/changePw";
	}

}
