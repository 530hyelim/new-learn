package com.newlearn.playground.mypage.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositoryDaoImpl implements RepositoryDao {
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<com.newlearn.playground.mypage.model.vo.Repository> getRepoList(int mypageNo) {
		return session.selectList("repo.getRepoList", mypageNo);
	}

}
