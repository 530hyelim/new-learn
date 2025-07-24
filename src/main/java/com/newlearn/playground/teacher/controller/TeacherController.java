package com.newlearn.playground.teacher.controller;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlearn.playground.teacher.model.service.TeacherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
	private final ServletContext application;
	private final TeacherService tService;
	
	@GetMapping("/main")
	public String teacherMain() {
		return "teacher/teacherMain";
	}
	
	@GetMapping("/attManage/{classNo}")
	public String attManage(
			Model model,
			@PathVariable("classNo") int classNo
			) {
//		List<Attendance> attList = tService.getAttList(classNo);
		System.out.println(classNo);
		
		return "teacher/teacherAttendanceManagement";
	}
	
	@GetMapping("/studentManage")
	public String studentManage() {
		return "teacher/teacherStudentManagement";
	}
	
	@GetMapping("/boardManage")
	public String boardManage() {
		return "teacher/teacherBoardManagement";
	}
	
	@GetMapping("/noticeManage")
	public String noticeManage() {
		return "teacher/teacherNoticeManagement";
	}
	
	@GetMapping("/assignmentManage")
	public String assignmentManage() {
		return "teacher/teacherAssignmentManagement";
	}
	
	@GetMapping("/reportManage")
	public String reportManage() {
		return "teacher/teacherReportManagement";
	}
}









