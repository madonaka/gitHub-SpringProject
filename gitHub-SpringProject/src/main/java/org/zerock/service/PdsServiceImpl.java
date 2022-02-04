package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.PdsVO;
import org.zerock.mapper.PdsMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class PdsServiceImpl implements PdsService {

	@Setter(onMethod_= @Autowired)
	private PdsMapper mapper;
	
	@Override
	public List<PdsVO> getList(Criteria cri) {
		// TODO Auto-generated method stub
		log.info("getList :" +cri);
		return mapper.getListWithPaging(cri);
	}
	@Override
	public int listCount() {
		// TODO Auto-generated method stub
		return mapper.listCount();
	}
	@Override
	public void register(PdsVO pds) {
		// TODO Auto-generated method stub
		log.info("resister...." + pds);
		mapper.insert(pds);
	}
}
