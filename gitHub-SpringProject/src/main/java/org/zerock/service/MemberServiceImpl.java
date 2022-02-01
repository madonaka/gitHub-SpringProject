package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.MemberVO;
import org.zerock.mapper.MemberMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

	@Setter(onMethod_=@Autowired)
	private MemberMapper mapper;
	
	//UserSHA256 userSHA = new UserSHA256(); // 비밀번호 암호화 객체
	
	@Override
	public int loginCheck(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int userinfo_insert(MemberVO vo) {
		//String password = vo.getPassword();
		//vo.setPassword(userSHA.getSHA256(password)); //비밀번호 암호화 변환
		return mapper.userinfo_insert(vo);
	}
}
