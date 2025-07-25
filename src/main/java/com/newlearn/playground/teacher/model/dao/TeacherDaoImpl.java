package com.newlearn.playground.teacher.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.teacher.model.vo.Attendance;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TeacherDaoImpl implements TeacherDao {
	private final SqlSessionTemplate session;

	@Override
	public int getTotalStudentNum(int classNo) {
		return session.selectOne("teacher.getTotalStudentNum", classNo);
	}
	
	@Override
	public List<Attendance> getAttList(int classNo) {
		return session.selectList("teacher.getAttList", classNo);
	}

}
