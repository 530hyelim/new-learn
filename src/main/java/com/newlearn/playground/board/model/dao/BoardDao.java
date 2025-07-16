package com.newlearn.playground.board.model.dao;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.board.model.vo.Board;
import com.newlearn.playground.board.model.vo.BoardExt;
import com.newlearn.playground.board.model.vo.BoardImg;
import com.newlearn.playground.board.model.vo.BoardType;
import com.newlearn.playground.common.model.vo.PageInfo;

public interface BoardDao {

	int selectListCount(Map<String, Object> paramMap);

	List<Board> selectList(PageInfo pi, Map<String, Object> paramMap);

	List<BoardType> selectBoardTypeList();

	int insertBoard(Board b);

	int insertBoardImg(BoardImg bi);

	int insertBoardImgList(List<BoardImg> imgList);

	BoardExt selectBoard(int boardNo);

	int increaseCount(int boardNo);

	List<BoardImg> selectBoardImgList(int boardNo);

	int updateBoard(Board board);

	int deleteBoardImg(String deleteList, int boardNo);

	int updateBoardImg(BoardImg bi);

	List<String> selectFileList();

	Map<String, String> getBoardTypeMap();

}
