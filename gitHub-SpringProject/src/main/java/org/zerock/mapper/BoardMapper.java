package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno < 100")
	public List<BoardVO> getList();
	
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public BoardVO getView(int bno);
	
	public int insert(BoardVO vo);
	
	public int insertSelectKey(BoardVO vo);
	
	public int modify(BoardVO vo);
	
	public int delete(int bno);
	
	//@Select("select count(*) from tbl_board")
	public int listCount();
}
