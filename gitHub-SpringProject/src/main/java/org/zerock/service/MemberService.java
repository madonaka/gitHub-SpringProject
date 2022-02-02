package org.zerock.service;

import org.zerock.domain.MemberVO;

public interface MemberService {
	
	public int userinfo_insert(MemberVO vo); //회원가입
	
	public int idCheck(MemberVO vo); //아이디 체크
	
	public String pwCheck(String id); //비밀번호 체크
	
	public MemberVO sessionInfo(String id);//세션정보 입력
}
