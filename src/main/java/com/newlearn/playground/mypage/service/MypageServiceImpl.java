package com.newlearn.playground.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.mypage.model.dao.MypageDao;
import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.model.vo.Mypage;

@Service
public class MypageServiceImpl implements MypageService {

	@Autowired
	private MypageDao mypageDao;
	
	@Override
	public List<Guestbook> loadGuestbook(int mypageNo) {
		return mypageDao.loadGuestbook(mypageNo);
	}

	@Override
	public int guestbookHide(int guestbookNo) {
		return mypageDao.guestbookHide(guestbookNo);
	}

	@Override
	public int guestbookDelete(int guestbookNo) {
		return mypageDao.guestbookDelete(guestbookNo);
	}

	@Override
	public int guestbookInsert(Guestbook g) {
		return mypageDao.guestbookInsert(g);
	}

	@Override
	public Mypage getMypageByMypageNo(int mypageNo) {
		return mypageDao.getMypageByMypageNo(mypageNo);
	}

}
