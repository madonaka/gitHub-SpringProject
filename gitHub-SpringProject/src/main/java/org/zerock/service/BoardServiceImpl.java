package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;

	@Override
	public void register(BoardVO board) {
		// TODO Auto-generated method stub
		log.info("resister...." + board);
		mapper.insertSelectKey(board);
	}
	
	@Override
	public boolean modify(BoardVO board) {
		log.info("modify......."+board);
		return mapper.modify(board) == 1;
	}
	
	@Override
	public List<BoardVO> getList() {
		log.info("getList......");
		return mapper.getList();
	}
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria......" + cri);
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public BoardVO get(int bno) {
		log.info("get.........."+bno);
		return mapper.getView(bno);
	}
	
	@Override
	public boolean delete(int bno) {
		log.info("delete......."+bno);
		return mapper.delete(bno) == 1;		
	}
	@Override
	public int listCount() {
		// TODO Auto-generated method stub
		return mapper.listCount();
	}
}
