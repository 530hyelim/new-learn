package com.newlearn.playground.teacher.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlearn.playground.teacher.model.service.TeacherService;
import com.newlearn.playground.teacher.model.vo.Attendance;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
	private final ServletContext application;
	private final TeacherService tService;
	
	@GetMapping("/main/{classNo}")
	public String teacherMain() {
		return "teacher/teacherMain";
	}
	
	@GetMapping("/attManage/{classNo}")
	public String attManage(
			Model model,
			@PathVariable("classNo") int classNo
			) {
		System.out.println("classNo: " + classNo);

		List<Attendance> attList = tService.getAttList(classNo);
		System.out.println("attList: " + attList);
		System.out.println("attList size: " + attList.size());
		
		
		return "teacher/teacherAttendanceManagement";
	}
	
	@GetMapping("/studentManage/{classNo}")
	public String studentManage() {
		return "teacher/teacherStudentManagement";
	}
	
	@GetMapping("/boardManage/{classNo}")
	public String boardManage() {
		return "teacher/teacherBoardManagement";
	}
	
	@GetMapping("/noticeManage/{classNo}")
	public String noticeManage() {
		return "teacher/teacherNoticeManagement";
	}
	
	@GetMapping("/assignmentManage/{classNo}")
	public String assignmentManage() {
		return "teacher/teacherAssignmentManagement";
	}
	
	@GetMapping("/reportManage/{classNo}")
	public String reportManage() {
		return "teacher/teacherReportManagement";
	}
}









