package org.zerock.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.service.BoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_= {@Autowired})
	private BoardService service;
	
	/*@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("보드서비스테스트타이틀");
		vo.setContent("보드서비스테스트내용");
		vo.setWriter("보드서비스테스트라이터");
		
		service.register(vo);
		log.info("생성된 게시물의 번호 :"+ vo.getBno());
	}*/
	
	/*@Test
	public void testGetList() {
		service.getList().forEach(board->log.info(board));
	}*/
	
	@Test
	public void testGetView() {
		log.info(service.get(3000802));
	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(3000802);
		
		if(vo == null)
			return;
		
		vo.setTitle("보서테수정타이틀");
		vo.setContent("보서테수정내용");
		vo.setWriter("보서테수정라이터");
		
		log.info("modify result : " +service.modify(vo));
	}
}
