package com.newlearn.playground.teacher.model.dao;

import java.util.List;

import com.newlearn.playground.teacher.model.vo.Attendance;

public interface TeacherDao {

	int getTotalStudentNum(int classNo);
	
	List<Attendance> getAttList(int classNo);

}
