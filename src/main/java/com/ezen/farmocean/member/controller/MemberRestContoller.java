package com.ezen.farmocean.member.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.farmocean.member.dto.Member;
import com.ezen.farmocean.member.service.MemberService;
import com.ezen.farmocean.member.service.memberFunction;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/member")
@RestController
public class MemberRestContoller {

	@Autowired
	MemberService service;

	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Member> listMember() {

		return service.getList();
	}

	@GetMapping(value = "/nickNameCheck/{member_nickName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer nickNameCheck(@PathVariable String member_nickName) {
		Member existence = service.nickNameCheck(member_nickName);

		if (existence == null) {
			return 1;
		} else {
			return 2;
		}
	}

	@GetMapping(value = "/pwAvailable/{pw}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer pwCheck(@PathVariable String pw) {

		if (memberFunction.chkPatternPassword(pw) == true) {
			return 1;
		} else {
			return 2;
		}
	}

	@GetMapping(value = "/idAvailable/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Integer idCheck(@PathVariable String id) {

		if (memberFunction.chkPatternId(id) == true) {
			return 1;
		} else {
			return 2;
		}
	}

	// ȸ������
	@PostMapping(value = "/insert/member", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<Member> insertUser(@RequestBody Member member) {

		if (member.getMember_id() == null || member.getMember_id().trim().equals("") || member.getMember_name() == null
				|| member.getMember_email() == null) {
			return ResponseEntity.badRequest().build();
		}

		try {
			service.insert(member);

			return ResponseEntity.ok().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping(value = "/idsearch", produces = MediaType.TEXT_PLAIN_VALUE)
	public Member idSearchPost(@RequestBody Member member){
		Member searchMember = service.idSearch(member);

		if (searchMember == null) {
			return null;
			
		} else {
			
			return searchMember;
		}

	}

}
