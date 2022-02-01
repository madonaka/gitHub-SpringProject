package org.zerock.persistence;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.MemberVO;
import org.zerock.service.MemberService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private MemberService service;
	
	@Inject
	BCryptPasswordEncoder pwdEncoder;

	
	@Test
	public void testInsert() {
		MemberVO vo = new MemberVO();
		vo.setName("테스터3");
		vo.setUserid("test1236");
		vo.setPassword(pwdEncoder.encode("test1236"));
		vo.setEmail("test1236@gmail.com");
		
		service.userinfo_insert(vo);
		//log.info("생성된 userid :"+ vo.getUserid());
	}
	
	/*@Test
	public void testGetList() {
		service.getList().forEach(board->log.info(board));
	}*/
	
	/*@Test
	public void testGetView() {
		log.info(service.get(3000802));
	}*/
	
	/*@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(3000802);
		
		if(vo == null)
			return;
		
		vo.setTitle("보서테수정타이틀");
		vo.setContent("보서테수정내용");
		vo.setWriter("보서테수정라이터");
		
		log.info("modify result : " +service.modify(vo));
	}*/
}
