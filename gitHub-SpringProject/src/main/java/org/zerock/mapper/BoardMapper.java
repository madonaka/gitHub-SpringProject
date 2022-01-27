package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {

	//@Select("select * from tbl_board where bno < 100")
	public List<BoardVO> getList();
	
	public BoardVO getView(int bno);
}
