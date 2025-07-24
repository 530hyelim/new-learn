package com.newlearn.playground.mypage.model.dao;

import java.util.List;

import com.newlearn.playground.mypage.model.vo.Repository;
import com.newlearn.playground.mypage.model.vo.UploadFile;

public interface RepositoryDao {

	List<Repository> getRepoList(int mypageNo);

	Repository getRepoByRepoNo(int repoNo);

	int insertFile(UploadFile uf);

	List<UploadFile> getFileList(int mypageNo);

}
