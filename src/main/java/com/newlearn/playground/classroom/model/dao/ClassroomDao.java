package com.newlearn.playground.classroom.model.dao;

import java.util.List;

import com.newlearn.playground.classroom.model.vo.Attendance;
import com.newlearn.playground.classroom.model.vo.Classroom;

public interface ClassroomDao {

	Attendance getAttendance(Attendance a);

	Classroom getClassroom(int classNo);

	int addAttendance(Attendance a);

	List<Classroom> getClasslist(int mypageNo);

}
