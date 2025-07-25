package com.newlearn.playground.mypage.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.classroom.model.vo.Attendance;
import com.newlearn.playground.classroom.service.ClassroomService;
import com.newlearn.playground.common.Utils;
import com.newlearn.playground.event.service.EventService;
import com.newlearn.playground.event.vo.Event;
import com.newlearn.playground.mypage.model.vo.Guestbook;
import com.newlearn.playground.mypage.model.vo.Mypage;
import com.newlearn.playground.mypage.model.vo.Repository;
import com.newlearn.playground.mypage.model.vo.UploadFile;
import com.newlearn.playground.mypage.service.MypageService;
import com.newlearn.playground.mypage.service.RepositoryService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
	private final ServletContext application; 
	private final MypageService mypageService;
	private final EventService eventService;
	private final RepositoryService repoService;
	private final ClassroomService classService;
	
	@GetMapping("/{mypageNo}")
	public String myPage(@PathVariable("mypageNo") int mypageNo, HttpSession session, 
			@RequestParam(name = "from", defaultValue = "mypage") String from,
			@RequestParam(name = "to", defaultValue = "guestbook") String to, Model model) {
		// 슬라이딩 이미지 로딩
		String imgDir = application.getRealPath("/resources/main");
		File path = new File(imgDir);
		if (path.exists()) {
			File[] files = path.listFiles();
			List<String> fileList = Arrays.stream(files)
		            .map(File::getName)
		            .collect(Collectors.toList());
		    model.addAttribute("fileList", fileList);
		}
		model.addAttribute("mypage", mypageService.getMypageByMypageNo(mypageNo));
		model.addAttribute("classList", classService.getClasslist(mypageNo));
		model.addAttribute("to", to);
		model.addAttribute("from", from);
		session.setAttribute("mypageNo", mypageNo);
		return "mypage/mypage";
	}
	
	@GetMapping("/guestbook")
	public String loadGuestbook(HttpSession session, @RequestParam int mypageNo, Model model) {
		List<Guestbook> gbList = mypageService.loadGuestbook(mypageNo);
		model.addAttribute("gbList", gbList);
		return "mypage/guestbook";
	}
	
	@GetMapping("/calendar")
	public String loadCalendar(@RequestParam(value = "date", required = false) String date, 
			@RequestParam String mypageNo, HttpSession session, Model model) {
	    if (date == null || date.trim().isEmpty()) {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
	        date = dateFormat.format(new Date());
	    }
	    Attendance a = new Attendance();
	    a.setUserNo(Integer.parseInt(mypageNo));
	    a.setClassNo((int)session.getAttribute("classNo"));
	    a.setSelectedDate(date);
	    model.addAttribute("attendance", classService.getAttendance(a));
	    Map<String, String> paramMap = new HashMap<>();
	    paramMap.put("date", date);
	    paramMap.put("mypageNo", mypageNo);
	    String month = date.substring(0, date.lastIndexOf("-"));
	    String day = date.substring(date.lastIndexOf("-") + 1);
	    paramMap.put("month", month);
	    paramMap.put("day", day);
	    List<Event> personalEvents = eventService.findAllPersonal(paramMap);
	    model.addAttribute("personalEvents", personalEvents);
	    model.addAttribute("selectedDate", date);
	    model.addAttribute("month", month);
	    model.addAttribute("day", day);
	    model.addAttribute("monthlyAttRate", calcAttRate(day, paramMap, session));
	    return "mypage/calendar";
	}
	
	// 월 별 출석률 계산
	private double calcAttRate(String day, Map<String, String> paramMap, HttpSession session) {
	    paramMap.put("classNo", ((int)session.getAttribute("classNo"))+"");
		int monthlyAttCnt = mypageService.getMontlyAttCnt(paramMap);
		double daysOfMonth = Double.parseDouble(day);
		return monthlyAttCnt / daysOfMonth * 100;
	}
	
	@PostMapping("/guestbook/hide")
	public String guestbookHide(@RequestParam int guestbookNo, HttpSession session) {
		int result = mypageService.guestbookHide(guestbookNo);
		return "redirect:/mypage/"+session.getAttribute("loginUserNo");
	}
	
	@PostMapping("/guestbook/delete")
	public String guestbookDelete(@RequestParam int guestbookNo, HttpSession session) {
		int result = mypageService.guestbookDelete(guestbookNo);
		return "redirect:/mypage/"+session.getAttribute("loginUserNo");
	}
	
	@PostMapping("/guestbook/new")
	public String guestbookInsert(@RequestParam String content, @RequestParam int mypageNo, 
			@RequestParam int userNo, @RequestParam String userName) {
		Guestbook g = new Guestbook();
		g.setContent(content);
		g.setMypageNo(mypageNo);
		g.setUserNo(userNo);
		g.setUserName(userName);
		int result = mypageService.guestbookInsert(g);
		return "redirect:/mypage/"+mypageNo;
	}
	
	// 개인일정 수정&생성 모달창에 이벤트 객체 바인딩해서 뷰페이지에 띄우기
	@GetMapping("/modal/{dmlType}")
	public String getModal(@PathVariable("dmlType") String dmlType, @RequestParam int eventNo, Model model) {
		Event event = new Event();
		if (dmlType.equals("edit")) {
			event = eventService.findByNo(eventNo);
		}
		model.addAttribute("event", event);
		return "mypage/eventForm";
	}
	
	// 날짜 형식 변환
	@InitBinder
	public void formatDatetime(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@GetMapping("/storage")
	public String loadStorage(HttpSession session, @RequestParam int mypageNo, Model model) {
		List<Repository> repoList = repoService.getRepoList(mypageNo);
		List<UploadFile> fileList = repoService.getAllFileList(mypageNo);
		model.addAttribute("repoList", repoList);
		model.addAttribute("fileList", fileList);
		return "mypage/storage";
	}
	
	// 폴더 버튼 누르면 오른쪽에 비동기식으로 화면 출력하기
	@GetMapping("/storage/load")
	public String loadFile(@RequestParam int repoNo, Model model) {
		List<UploadFile> fileList = repoService.getFileList(repoNo);
		Repository repo = repoService.getRepoByRepoNo(repoNo);
		model.addAttribute("fileList", fileList);
		model.addAttribute("repo", repo);
		return "mypage/fileList";
	}
	
	// 내 저장소 div에서 검색기능
	@GetMapping("/storage/search")
	public String searchFile(@RequestParam Map<String, String> paramMap, HttpSession session, 
			@RequestParam String mypageNo, Model model) {
		paramMap.put("mypageNo", mypageNo);
		List<UploadFile> fileList = repoService.searchFileList(paramMap);
		model.addAttribute("fileList", fileList);
		return "mypage/fileList";
	}
	
	// 파일 업로드
	@PostMapping("/storage/insert")
	public String insertFile(@RequestParam int repoNo, @RequestParam(value="upfile") List<MultipartFile> upfiles, 
			RedirectAttributes ra, HttpSession session) {
		Repository repo = repoService.getRepoByRepoNo(repoNo);
		Mypage mypage = mypageService.getMypageByMypageNo(repo.getMypageNo());
		List<UploadFile> ufList = repoService.getFileList(repoNo);
		long totalStorage = 0; // 바이트단위
		if (!ufList.isEmpty()) {
			for (UploadFile uf : ufList) {
				totalStorage += uf.getFileSize();
			}
		}
		for (MultipartFile upfile : upfiles) {
			if (totalStorage + upfile.getSize() > mypage.getMaxStorage()) {
				ra.addFlashAttribute("alertMsg", "파일을 업로드 할 수 있는 최대용량을 초과했스빈다.");
				return "redirect:/mypage/"+session.getAttribute("mypageNo");
			}
			UploadFile uf = new UploadFile();
			String changeName = Utils.getChangeName(upfile, application, "file", repo.getMypageNo());
			uf.setChangeName(changeName);
			uf.setOriginName(upfile.getOriginalFilename());
			uf.setRepoNo(repoNo);
			uf.setFileSize(upfile.getSize());
			int result = repoService.insertFile(uf);
			if (result != 1) {
				throw new RuntimeException("파일 업로드 실패");
			}
		}
		ra.addFlashAttribute("alertMsg", "파일 업로드 성공");
		return "redirect:/mypage/"+session.getAttribute("mypageNo");
	}
	
}
