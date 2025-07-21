package com.newlearn.playground.mypage.service;

import java.util.List;

import com.newlearn.playground.mypage.model.vo.Guestbook;

public interface MypageService {

	List<Guestbook> loadGuestbook(int mypageNo);

}
