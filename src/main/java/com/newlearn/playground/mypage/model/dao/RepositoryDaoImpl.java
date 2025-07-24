package com.newlearn.playground.mypage.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.mypage.model.vo.UploadFile;

@Repository
public class RepositoryDaoImpl implements RepositoryDao {
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<com.newlearn.playground.mypage.model.vo.Repository> getRepoList(int mypageNo) {
		return session.selectList("repo.getRepoList", mypageNo);
	}

	@Override
	public com.newlearn.playground.mypage.model.vo.Repository getRepoByRepoNo(int repoNo) {
		return session.selectOne("repo.getRepoByRepoNo", repoNo);
	}

	@Override
	public int insertFile(UploadFile uf) {
		return session.insert("repo.insertFile", uf);
	}

	@Override
	public List<UploadFile> getFileList(int repoNo) {
		return session.selectList("repo.getFileList", repoNo);
	}

	@Override
	public List<UploadFile> searchFileList(Map<String, String> paramMap) {
		return session.selectList("repo.searchFileList", paramMap);
	}

	@Override
	public List<UploadFile> getAllFileList(int mypageNo) {
		return session.selectList("repo.getAllFileList", mypageNo);
	}

}
