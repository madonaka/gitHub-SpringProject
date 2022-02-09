package org.zerock.service;

import java.util.List;

import org.zerock.domain.Criteria;
import org.zerock.domain.PdsVO;
import org.zerock.domain.UploadVO;

public interface PdsService {
	
	public List<PdsVO> getList();
	
	public List<PdsVO> getList(Criteria cri);
	
	public int listCount();
	
	public void register(PdsVO pds);

	public PdsVO get(int bno);
	
	public boolean delete(int bno);
	
	public void modify(UploadVO pds);

}
