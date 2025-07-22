package com.newlearn.playground.classroom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.classroom.model.dao.ClassroomDao;
import com.newlearn.playground.classroom.model.vo.Attendance;
import com.newlearn.playground.classroom.model.vo.Classroom;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	@Autowired
	private ClassroomDao classroomDao;
	
	@Override
	public Attendance getAttendance(Attendance a) {
		return classroomDao.getAttendance(a);
	}

	@Override
	public Classroom getClassroom(int classNo) {
		return classroomDao.getClassroom(classNo);
	}

	@Override
	public int addAttendance(Attendance a) {
		return classroomDao.addAttendance(a);
	}
	
}
