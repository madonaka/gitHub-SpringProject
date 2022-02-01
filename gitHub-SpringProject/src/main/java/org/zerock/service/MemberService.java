package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {
	
	public int userinfo_insert(MemberVO vo); //회원가입
	
	public int loginCheck(MemberVO vo); //로그인
	
	
}
