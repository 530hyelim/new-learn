package com.newlearn.playground.teacher.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.newlearn.playground.teacher.model.dao.TeacherDao;
import com.newlearn.playground.teacher.model.vo.Attendance;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
	private final TeacherDao tDao;

	@Override
	public int getTotalStudentNum(int classNo) {
		return tDao.getTotalStudentNum(classNo);
	}
	
	@Override
	public List<Attendance> getAttList(int classNo) {
		return tDao.getAttList(classNo);
	}

}
