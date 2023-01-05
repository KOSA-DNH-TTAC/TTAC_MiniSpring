package com.common.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.service.MemberService;

@Controller
@RequestMapping("/joinus/")
public class FrontController {

	// memberDao 의존
	MemberService memberservice;

	@Autowired
	public void setMemberservice(MemberService memberservice) {
		this.memberservice = memberservice;
	}

	// GET 요청
	// join.jsp 화면처리
	@GetMapping("join.htm")
	public String join() {
		return "joinus/join";
	}

	// GET 요청
	// multiMail.jsp 화면처리
	@GetMapping("multimail.htm")
	public String multuMail() {
		return "joinus/multiMail";
	}

	// GET 요청
	// fileMail.jsp 화면처리
	@GetMapping("filemail.htm")
	public String fileMail() {
		return "joinus/fileMail";
	}
}