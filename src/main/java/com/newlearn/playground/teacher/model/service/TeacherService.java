package com.newlearn.playground.teacher.model.service;

import java.util.List;

import com.newlearn.playground.teacher.model.vo.Attendance;

public interface TeacherService {
	
	int getTotalStudentNum(int classNo);

	List<Attendance> getAttList(int classNo);	

}
