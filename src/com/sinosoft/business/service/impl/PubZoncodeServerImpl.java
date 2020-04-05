package com.sinosoft.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sinosoft.business.dao.PubZoncodeDao;
import com.sinosoft.business.po.PubZoncode;
import com.sinosoft.business.po.query.PubZoncodeQuery;
import com.sinosoft.business.service.PubZoncodeServer;


@Service("pubZoncodeServer")
public class PubZoncodeServerImpl implements PubZoncodeServer {
	
	@Resource
	private PubZoncodeDao pubZoncodeDao;
	
	@Override
	public List<PubZoncode> getPubZonCodeList(PubZoncodeQuery pzq) {
		
		return pubZoncodeDao.selectByPrimaryQuery(pzq);
	}

	@Override
	public PubZoncode getPubZonCodeById(String zonecode) {
		return pubZoncodeDao.selectByPrimaryKey(zonecode);
	}

}
