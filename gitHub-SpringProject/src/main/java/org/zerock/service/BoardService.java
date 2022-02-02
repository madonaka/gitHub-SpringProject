package org.zerock.service;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardService {
	
	public void register(BoardVO board);
	
	public BoardVO get(int bno);
	
	public boolean modify(BoardVO board);
	
	public boolean delete(int bno);
	
	//public List<BoardVO> getList();
	public List<BoardVO> getList(Criteria cri); //페이징 처리
	
	public int listCount(); //리스트의 게시물 총 수
}
