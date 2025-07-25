package com.newlearn.playground.teacher.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlearn.playground.teacher.model.service.TeacherService;
import com.newlearn.playground.teacher.model.vo.Student;
import com.newlearn.playground.teacher.model.vo.TeacherAttendance;

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
			@PathVariable("classNo") int classNo,
			@RequestParam(required=false) String selectedDate
			) {
		System.out.println("classNo: " + classNo);
		if (selectedDate == null) {
			Date today = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			selectedDate = format.format(today);
		}
		System.out.println("selectedDate: " + selectedDate);
		
		List<Student> studentList = tService.getStudentList(classNo);
		System.out.println("studentList: " + studentList);
		System.out.println("studentList size: " + studentList.size());
		
		Map<String, Object> attMap = new HashMap<>();
		attMap.put("classNo", classNo);
		attMap.put("selectedDate", selectedDate);
		
		List<TeacherAttendance> attList = tService.getAttList(attMap);
		System.out.println("attList: " + attList);
		System.out.println("attList size: " + attList.size());
		
		model.addAttribute("attList", attList);
		
		int totalStudentNum = studentList.size();
		int attNum = 0;
		int absentNum = 0;
		int lateNum = 0;
		int earlyLeaveNum = 0;
		for (int i = 0; i < attList.size(); i++) {
			if (attList.get(i).getAttStatus().equals("출석")) {
				attNum++;
			} else if (attList.get(i).getAttStatus().equals("결석")) {
				absentNum++;
			} else if (attList.get(i).getAttStatus().equals("지각")) {
				lateNum++;
			} else if (attList.get(i).getAttStatus().equals("조퇴")) {
				earlyLeaveNum++;
			}
		}
		
		model.addAttribute("totalStudentNum", totalStudentNum);
		model.addAttribute("attNum", attNum);
		model.addAttribute("absentNum", absentNum);
		model.addAttribute("lateNum", lateNum);
		model.addAttribute("earlyLeaveNum", earlyLeaveNum);
		model.addAttribute("classNo", classNo);
		model.addAttribute("selectedDate", selectedDate);
		
		return "teacher/teacherAttendanceManagement";
	}
	
	@GetMapping("/attendance/update")
	@ResponseBody
	public int attendanceUpdate(
			int userNo,
			int classNo,
			String attStatusForUpdate
			) {
		System.out.println(userNo);
		System.out.println(classNo);
		System.out.println(attStatusForUpdate);
		
		Map<String, Object> map = new HashMap<>();
		map.put("userNo", userNo);
		map.put("classNo", classNo);
		map.put("attStatusForUpdate", attStatusForUpdate);
		
		return tService.attendanceUpdate(map);
	}
	
	@GetMapping("/studentManage/{classNo}")
	public String studentManage(
			Model model,
			@PathVariable("classNo") int classNo
			) {
		List<Student> studentList = tService.getStudentList(classNo);
		model.addAttribute("studentList", studentList);
		model.addAttribute("classNo", classNo);
		
		return "teacher/teacherStudentManagement";
	}
	
	@GetMapping("/studentKick")
	@ResponseBody
	public int studentKick(
			int classNo,
			int userNo
			) {
		Map<String, Object> map = new HashMap<>();
		map.put("classNo", classNo);
		map.put("userNo", userNo);
		
		return tService.studentKick(map);
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









