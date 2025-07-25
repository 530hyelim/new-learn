package com.newlearn.playground.mypage.service;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.model.vo.Mypage;

public interface MypageService {

	List<Guestbook> loadGuestbook(int mypageNo);

	int guestbookHide(int guestbookNo);

	int guestbookDelete(int guestbookNo);

	int guestbookInsert(Guestbook g);

	Mypage getMypageByMypageNo(int mypageNo);

	int getMontlyAttCnt(Map<String, String> paramMap);

}
