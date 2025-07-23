package com.newlearn.playground.mypage.service;

import java.util.List;

import com.newlearn.playground.mypage.model.vo.Repository;

public interface RepositoryService {

	List<Repository> getRepoList(int mypageNo);

}
