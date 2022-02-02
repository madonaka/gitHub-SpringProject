package org.zerock.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
	public void login() {
		log.info("login");
		
	}
	@PostMapping("/userlogin_form")
	public String postLogin(MemberVO vo, RedirectAttributes rttr ,HttpSession session) {
		log.info("post login");
		//id 일치 확인
		if(service.idCheck(vo) == 1) {
			//아이디 있을 때
			String id = vo.getUserid();
			boolean pwCheck = pwdEncoder.matches(vo.getPassword(), service.pwCheck(id));
			if(pwCheck == true) {
				//로그인 성공
				session.setAttribute("session", service.sessionInfo(id));
				session.setMaxInactiveInterval(1800); //30분간 세션 유지
				rttr.addFlashAttribute("row", "로그인 성공");
				return "redirect:/board/list";
			}else {
				//비밀번호 다를 때
				rttr.addFlashAttribute("row", "비밀번호가 다름");
				return "redirect:/member/userlogin_form";
			}
		}else {
			//아이디 없을 때
			rttr.addFlashAttribute("row", "일치하는 아이디 없음");
			return "redirect:/member/userlogin_form";		
		}
	}
	@PostMapping("/logout")
	public String postLogout(RedirectAttributes rttr, HttpSession session) {
		log.info("post logout");
		session.invalidate();
		
		rttr.addFlashAttribute("row","로그아웃 되었습니다.");
		
		return "redirect:/member/userlogin_form";
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
