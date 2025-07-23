package com.newlearn.playground.mypage.model.dao;

import java.util.List;

import com.newlearn.playground.mypage.model.vo.Repository;

public interface RepositoryDao {

	List<Repository> getRepoList(int mypageNo);

}
