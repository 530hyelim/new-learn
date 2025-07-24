package com.newlearn.playground.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.mypage.model.dao.RepositoryDao;
import com.newlearn.playground.mypage.model.vo.Repository;
import com.newlearn.playground.mypage.model.vo.UploadFile;

@Service
public class RepositoryServiceImpl implements RepositoryService {
	@Autowired
	private RepositoryDao repoDao;

	@Override
	public List<Repository> getRepoList(int mypageNo) {
		return repoDao.getRepoList(mypageNo);
	}

	@Override
	public Repository getRepoByRepoNo(int repoNo) {
		return repoDao.getRepoByRepoNo(repoNo);
	}

	@Override
	public int insertFile(UploadFile uf) {
		return repoDao.insertFile(uf);
	}

	@Override
	public List<UploadFile> getFileList(int mypageNo) {
		return repoDao.getFileList(mypageNo);
	}
}
