package org.zerock.controller;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.MemberVO;
import org.zerock.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/member/*")
@AllArgsConstructor
public class MemberController {
	
	@Inject
	MemberService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	/*@GetMapping("/list")
	public void list(Model model) {
		log.info("list");
		model.addAttribute("list", service.getList());
	}*/
	@GetMapping("/userlogin_form")
	public void loginPage() {
		log.info("loginPage");
		
	}
	
	@GetMapping("/userinfo_insert")
	public void userinfoInsert() {
		log.info("userinfoInsert");
	}
	
	@PostMapping("/userinfo_insert")
	public String postuserinfoInsert(MemberVO vo) {
		log.info("post userinfo insert");
		
		String inputPass = vo.getPassword();
		String pwd = pwdEncoder.encode(inputPass);
		
		vo.setPassword(pwd);
		
		service.userinfo_insert(vo);
		
		return "redirect:/";
	}
	
	
}
