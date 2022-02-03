package org.zerock.persistence;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;

	/*@Test
	public void testGetList() {
		mapper.getList().forEach(board -> log.info(board));
	}*/
	
	/*@Test
	public void testGetView() {
		BoardVO vo = mapper.getView(111);
		
		log.info(vo);
	}*/
	
	/*@Test
	public void testInsert() {
		
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트 중입니다");
		vo.setContent("테스트 중인 내용입니다.");
		vo.setWriter("라이터테스트");
		
		mapper.insertSelectKey(vo);
		
		log.info(vo);
	}*/
	
	/*
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(3000801);
		vo.setTitle("수정수정");
		vo.setContent("내용내용");
		vo.setWriter("글쓴이글쓴이");
		
		int count = mapper.modify(vo);
		log.info("update count : "+ count);
	}*/
	
	/*@Test
	public void testDelete() {
		int bno = 3000801;
		
		int count = mapper.delete(bno);
		
		log.info("delete count :"+ count);
	}*/
	/*
	@Test
	public void testListWithPaging() {
		Criteria cri = new Criteria();
		cri.setPageNum(3);
		cri.setAmount(20);
		List<BoardVO> list = mapper.getListWithPaging(cri);
		list.forEach(board-> log.info(board));
	}*/
	@Test
	public void testSearch() {
		Criteria cri = new Criteria();
		cri.setKeyword("모달");
		cri.setType("t");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board-> log.info(board));
	}
}
