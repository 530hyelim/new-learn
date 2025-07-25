package com.newlearn.playground.teacher.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.teacher.model.vo.TeacherAttendance;
import com.newlearn.playground.teacher.model.vo.Student;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TeacherDaoImpl implements TeacherDao {
	private final SqlSessionTemplate session;
	
	@Override
	public List<Student> getStudentList(int classNo) {
		return session.selectList("teacher.getStudentList", classNo);
	}
	
	@Override
	public List<TeacherAttendance> getAttList(Map<String, Object> attMap) {
		return session.selectList("teacher.getAttList", attMap);
	}

	@Override
	public int attendanceUpdate(Map<String, Object> map) {
		return session.update("teacher.attendanceUpdate", map);
	}

	@Override
	public int studentKick(Map<String, Object> map) {
		System.out.println(map);
		return session.delete("teacher.studentKick", map);
	}

}
