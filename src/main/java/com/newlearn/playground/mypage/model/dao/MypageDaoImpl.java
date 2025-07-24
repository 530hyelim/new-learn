package com.newlearn.playground.mypage.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.model.vo.Mypage;

@Repository
public class MypageDaoImpl implements MypageDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Guestbook> loadGuestbook(int mypageNo) {
		return session.selectList("guestbook.loadGuestbook", mypageNo);
	}

	@Override
	public int guestbookHide(int guestbookNo) {
		return session.update("guestbook.guestbookHide", guestbookNo);
	}

	@Override
	public int guestbookDelete(int guestbookNo) {
		return session.update("guestbook.guestbookDelete", guestbookNo);
	}

	@Override
	public int guestbookInsert(Guestbook g) {
		return session.insert("guestbook.guestbookInsert", g);
	}

	@Override
	public Mypage getMypageByMypageNo(int mypageNo) {
		return session.selectOne("mypage.getMypageByMypageNo", mypageNo);
	}

}
