package com.newlearn.playground.mypage.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.mypage.model.vo.Guestbook;

@Repository
public class MypageDaoImpl implements MypageDao {

	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Guestbook> loadGuestbook(int mypageNo) {
		return session.selectList("guestbook.loadGuestbook", mypageNo);
	}

}
