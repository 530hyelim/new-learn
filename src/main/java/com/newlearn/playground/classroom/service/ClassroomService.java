package com.newlearn.playground.classroom.service;

import com.newlearn.playground.classroom.model.vo.Attendance;
import com.newlearn.playground.classroom.model.vo.Classroom;

public interface ClassroomService {

	Attendance getAttendance(Attendance a);

	Classroom getClassroom(int classNo);

	int addAttendance(Attendance a);

}
