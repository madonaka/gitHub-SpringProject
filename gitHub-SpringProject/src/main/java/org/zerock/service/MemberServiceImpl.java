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
	
	@Override
	public int userinfo_insert(MemberVO vo) {
		return mapper.userinfo_insert(vo);
	}
	
	@Override
	public int idCheck(MemberVO vo) {
		// TODO Auto-generated method stub
		return mapper.idCheck(vo);
	}
	
	@Override
	public String pwCheck(String id) {
		// TODO Auto-generated method stub
		return mapper.pwCheck(id);
	}
	
	@Override
	public MemberVO sessionInfo(String id) {
		// TODO Auto-generated method stub
		return mapper.sessionInfo(id);
	}
}
