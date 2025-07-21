package com.newlearn.playground.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.mypage.model.dao.MypageDao;
import com.newlearn.playground.mypage.model.vo.Guestbook;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageDao mypageDao;
	
	@Override
	public List<Guestbook> loadGuestbook(int mypageNo) {
		return mypageDao.loadGuestbook(mypageNo);
	}

}
