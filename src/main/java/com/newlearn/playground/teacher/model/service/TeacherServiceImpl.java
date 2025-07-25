package com.newlearn.playground.teacher.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.newlearn.playground.teacher.model.dao.TeacherDao;
import com.newlearn.playground.teacher.model.vo.TeacherAttendance;
import com.newlearn.playground.teacher.model.vo.Student;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
	private final TeacherDao dao;
	
	@Override
	public List<Student> getStudentList(int classNo) {
		return dao.getStudentList(classNo);
	}
	
	@Override
	public List<TeacherAttendance> getAttList(Map<String, Object> attMap) {
		return dao.getAttList(attMap);
	}

	@Override
	public int attendanceUpdate(Map<String, Object> map) {
		return dao.attendanceUpdate(map);
	}

	@Override
	public int studentKick(Map<String, Object> map) {
		return dao.studentKick(map);
	}

}
