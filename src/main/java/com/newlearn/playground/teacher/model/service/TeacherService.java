package com.newlearn.playground.teacher.model.service;

import java.util.List;
import java.util.Map;

import com.newlearn.playground.teacher.model.vo.TeacherAttendance;
import com.newlearn.playground.teacher.model.vo.Student;

public interface TeacherService {

	List<Student> getStudentList(int classNo);
	
	List<TeacherAttendance> getAttList(Map<String, Object> attMap);

	int attendanceUpdate(Map<String, Object> map);

	int studentKick(Map<String, Object> map);

}
