package com.kh.spring.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.BoardExt;
import com.kh.spring.board.model.vo.BoardImg;
import com.kh.spring.board.model.vo.BoardType;
import com.kh.spring.common.model.vo.PageInfo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BoardDaoImpl implements BoardDao {@Override
	public int selectListCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Board> selectList(PageInfo pi, Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardType> selectBoardTypeList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertBoard(Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBoardImg(BoardImg bi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertBoardImgList(List<BoardImg> imgList) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BoardExt selectBoard(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int increaseCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardImg> selectBoardImgList(int boardNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteBoardImg(String deleteList, int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoardImg(BoardImg bi) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<String> selectFileList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, String> getBoardTypeMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
