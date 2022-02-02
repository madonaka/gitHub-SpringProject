package org.zerock.mapper;

import org.zerock.domain.MemberVO;

public interface MemberMapper {
	
	public int userinfo_insert(MemberVO vo); // 회원가입
	
	public int idCheck(MemberVO vo); //id체크 
	
	public String pwCheck(String id); //비밀번호 체크
	
	public MemberVO sessionInfo(String id); //세션에 유저 정보 담기
}
