package com.newlearn.playground.mypage.service;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.mypage.model.vo.Repository;
import com.newlearn.playground.mypage.model.vo.UploadFile;

public interface RepositoryService {

	List<Repository> getRepoList(int mypageNo);

	Repository getRepoByRepoNo(int repoNo);

	int insertFile(UploadFile uf);

	List<UploadFile> searchFileList(Map<String, String> paramMap);

	List<UploadFile> getAllFileList(int mypageNo);

	List<UploadFile> getFileList(int repoNo);

}
