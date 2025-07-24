package com.newlearn.playground.teacher.model.service;

import org.springframework.stereotype.Service;

import com.newlearn.playground.teacher.model.dao.TeacherDao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
	private final TeacherDao tDao;
}
