package com.newlearn.playground.classroom.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlearn.playground.classroom.model.vo.Attendance;
import com.newlearn.playground.classroom.model.vo.Classroom;

@Repository
public class ClassroomDaoImpl implements ClassroomDao {
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public Attendance getAttendance(Attendance a) {
		return session.selectOne("class.getAttendance", a);
	}

	@Override
	public Classroom getClassroom(int classNo) {
		return session.selectOne("class.getClassroom", classNo);
	}

	@Override
	public int addAttendance(Attendance a) {
		return session.insert("class.addAttendance", a);
	}

	@Override
	public List<Classroom> getClasslist(int mypageNo) {
		return session.selectList("class.getClasslist", mypageNo);
	}

}
