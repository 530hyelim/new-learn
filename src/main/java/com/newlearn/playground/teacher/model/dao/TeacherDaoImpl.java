package com.newlearn.playground.teacher.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class TeacherDaoImpl implements TeacherDao {
	private final SqlSessionTemplate session;
}
