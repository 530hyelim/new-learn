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
public class BoardDaoImpl implements BoardDao {

	private final SqlSessionTemplate session;

	@Override
	public Map<String, String> getBoardTypeMap() {
		/*
		 * selectMap - Map<key, value> 형태의 데이터를 반환하는 메서드. - 두번째 매개변수로는 어떤 '칼럼'을 key로
		 * 사용할지를 작성. - select key, value from table
		 */
		return session.selectMap("board.getBoardTypeMap", "boardCd");
	}

	@Override
	public int selectListCount(Map<String, Object> paramMap) {
		return session.selectOne("board.selectListCount", paramMap);
	}

	@Override
	public List<Board> selectList(PageInfo pi, Map<String, Object> paramMap) {
		/*
		 * 특정 페이지의 데이터를 가져오는 방법들(페이징 처리)
		 * 1. rownum, row_number()으로 페이징 처리된 쿼리 조회하기.
		 * select *
		 * from (
		 *   select rownum as rnum, inner.*
		 *   from (
		 *     -- 특정 칼럼을 기준으로 조회된 쿼리
		 *   ) inner
		 * )
		 * where rnum between a and b
		 * - 쿼리문이 복잡하고, 코드의 가독성이 떨어지나 필요한 행만 조회하여 가져올 수 있기 때문에 메모리 낭비나 성능 저하가 별로 없는 방식.
		 * - 1 페이지 요청시 -> where rnum between 1 and 10.
		 * - 2 페이지 요청시 -> where rnum between 11 and 20.
		 * 
		 * 2. RowBounds를 활용한 방식.
		 * - MyBatis에서 쿼리 결과에 대해 페이징 처리를 적용하는 도구.
		 * - 전체 쿼리 결과를 자바 어플리케이션으로 가져온 후, 지정한 위치(offset)에서 특정 갯수(limit)를 잘라내는 방식으로 페이징 처리를 진행한다.
		 * - 어플리케이션으로 가져올 데이터가 수만건 이상인 경우 메모리 낭비 및 성능 저하가 발생할 수 있다.
		 * - "소규모 데이터 쿼리"시 사용하는 것을 권장.
		 * 
		 * 3. offset fetch를 사용하여 쿼리 조회(Oracle 12c 이상만 사용 가능)
		 * - 코드의 복잡성을 줄이고 가독성을 크게 확보하는 방식
		 * [표현법]
		 * select
		 *    ... 조회할 컬럼
		 * from 테이블
		 * where ... 조건
		 * order by ...
		 * offset 시작행 rows fetch next 조회할 갯수 rows only
		 */
		// 몇 번째 행부터 가져올지
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		int limit = pi.getBoardLimit();
		// 2번 방식
//		RowBounds rowBounds = new RowBounds(offset, limit);
//		
//		return session.selectList("board.selectList", paramMap, rowBounds);
		
		// 3번 방식
		paramMap.put("offset", offset);
		paramMap.put("limit", limit);
		
		return session.selectList("board.selectList", paramMap);
	}

	@Override
	public int insertBoard(Board b) {
		log.info("게시글 등록 이전 b: {}", b);
		
		int result = session.insert("board.insertBoard", b);
		
		log.info("게시글 등록 이후 b: {}", b);
		return result;
	}

	@Override
	public int insertBoardImg(BoardImg bi) {
		return session.insert("board.insertBoardImg", bi);
	}

	@Override
	public int insertBoardImgList(List<BoardImg> imgList) {
		return session.insert("board.insertBoardImgList", imgList);
	}

	@Override
	public BoardExt selectBoard(int boardNo) {
		return session.selectOne("board.selectBoard", boardNo);
	}

	@Override
	public int increaseCount(int boardNo) {
		return session.update("board.increaseCount", boardNo);
	}

	@Override
	public List<BoardImg> selectBoardImgList(int boardNo) {
		return null;
	}

	@Override
	public int updateBoard(Board b) {
		return session.update("board.updateBoard", b);
	}

	@Override
	public int deleteBoardImg(String deleteList, int boardNo) {
		Map<String, Object> map = new HashMap<>();
		map.put("deleteList", deleteList);
		map.put("boardNo", boardNo);
		
		return session.delete("board.deleteBoardImg", map);
	}

	@Override
	public int updateBoardImg(BoardImg bi) {
		return session.update("board.updateBoardImg", bi);
	}

	@Override
	public List<String> selectFileList() {
		return session.selectList("board.selectFileList");
	}
	
	@Override
	public List<BoardType> selectBoardTypeList() {
		return session.selectList("board.selectBoardTypeList");
	}

}
