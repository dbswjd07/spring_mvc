package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.GuestDAO;
import com.naver.vo.GuestVO;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestDAO guestDao;
	
	@Override
	public void insertGuest(GuestVO g) {
		this.guestDao.insertGuest(g);
		
	}

	@Override
	public int getTotalCount() {
		return this.guestDao.getTotalCount();
	}

	@Override
	public List<GuestVO> getGuestList(GuestVO g) {
		
		return this.guestDao.getGuestList(g);
	}


}
