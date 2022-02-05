package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PdsVO;

public interface PdsMapper {

	public List<PdsVO> getListWithPaging(Criteria cri);

	public int listCount();
	
	public int insert(PdsVO vo);
	
	public PdsVO getView(int bno);

}
