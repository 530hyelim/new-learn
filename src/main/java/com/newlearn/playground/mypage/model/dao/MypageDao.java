package com.newlearn.playground.mypage.model.dao;

import java.util.List;

import com.newlearn.playground.mypage.model.vo.Guestbook;

public interface MypageDao {

	List<Guestbook> loadGuestbook(int mypageNo);

}
