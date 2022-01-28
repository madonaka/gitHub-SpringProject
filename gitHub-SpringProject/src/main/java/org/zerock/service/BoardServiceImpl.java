package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
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
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public BoardVO get(int bno) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean delete(int bno) {
		// TODO Auto-generated method stub
		return false;
	}
}
