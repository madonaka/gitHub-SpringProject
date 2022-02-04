package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.PdsVO;

public interface PdsService {
	
	public List<PdsVO> getList(Criteria cri);
	
	public int listCount();
	
	public void register(PdsVO pds);

}
