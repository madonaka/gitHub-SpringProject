package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.PdsVO;
import org.zerock.domain.UploadVO;

public interface PdsMapper {

	public List<PdsVO> getList();
	
	public List<PdsVO> getListWithPaging(Criteria cri);

	public int listCount();
	
	public int insert(PdsVO vo);
	
	public PdsVO getView(int bno);
	
	public int delete(int bno);
	
	public int modify(UploadVO pds);

}
