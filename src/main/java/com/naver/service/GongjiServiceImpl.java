package com.naver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.GongjiDAO;
import com.naver.vo.GongjiVO;

@Service
public class GongjiServiceImpl implements GongjiService {
	@Autowired
	private GongjiDAO gongjiDao;

	@Override
	public void insertGongji(GongjiVO g) {
		this.gongjiDao.insertGongji(g);
		
	}

}
