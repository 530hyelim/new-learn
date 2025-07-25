package com.newlearn.playground.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.member.model.service.MemberService;
import com.newlearn.playground.member.model.validator.MemberValidator;
import com.newlearn.playground.member.model.vo.Member;

@Controller // Component-scan에 의해 bean객체 등록
@SessionAttributes({"loginUser"}) // Model에 들어가는 데이터 중 Session에 보관시킬 데이터를 설정하는 주석
public class MemberController {
	@Autowired
	private MemberService mService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private MemberValidator memberValidator;
	
	
	/*
	 * Spring DI(Dependency Injection)
	 * - 의존성 주입
	 * - 어플리케이션을 구성하는 객체를 개발자가 직접 생성하는 게 아닌, 스프링이 생성한 객체를 주입받아서 생성하는 방식.
	 * - new 연산자를 직접 사용하지 않고, 자료형 선언만 한 후 @Autowired 어노테이션을 통해 주입받음.
	 * 
	 */
	
	@CrossOrigin // 교차출처 허용
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String loginMember() {
		return "member/login";
	}
	
	// 스프링의 argument resolver
//	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
//	public String login(HttpServletRequest request) {
//		System.out.println(request.getParameter("userId"));
//		
//		return "main";
//	}
	
	// @RequestParam 어노테이션:
	// - servlet의 request.getParameter("키")로 뽑는 역할을 대신 수행해주는 어노테이션.
	// - 클라이언트가 요청한 값을 대신 변환하여 바인딩 해주는 역할은 argumentResolver가 수행.
//	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
//	public String login(@RequestParam(value="userId", defaultValue="hjy") String userId) {
//		System.out.println(userId);
//		
//		return "main";
//	}
	
	// @RequestParam 생략
	// 매개변수의 이름과 일치하는 request 의 파라미터값을 추출하여 바인딩 (일치하는게 없으면 null)
//	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
//	public String login(String userId, String userPwd) {
//		System.out.println(userId);
//		System.out.println(userPwd);
//		
//		return "main";
//	}
	
	/*
	 * 커맨드 객체 방식
	 * @ModelAttribute
	 * - 메소드의 매개변수로 vo 클래스 타입을 지정하는 경우, 요청 전달값의 name 속성과 일치하는 vo 클래스 필드의 setter 함수를 호출하여 바인딩
	 * 
	 * 매개변수의 클래스와 일치하는 클래스의 기본생성자 호출
	 * 파라미터의 key값과 클래스의 필드명이 일치하는 경우 setter 함수 호출.
	 */
//	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
//	public String login(Member m) {
//		System.out.println(m.getUserId());
//		System.out.println(m.getUserPwd());
//		
//		return "main";
//	}
	
//	@RequestMapping(value = "/member/login", method = RequestMethod.POST)
//	public ModelAndView login(Member m, ModelAndView mv, Model model) {
//		/*
//		 * 로그인 비즈니스 작업 처리 완료 후, "응답 데이터"를 담아 응답 페이지로 redirect(재요청)
//		 * 
//		 * 응답데이터를 담을 수 있는 객체
//		 * 1) Model
//		 * - 포워딩할 응답 뷰페이지로 전달하고자 하는 데이터를 맵형식으로 담을 수 있는 객체.
//		 * - 기본 requestScope에, 설정을 통해 sessionScope에도 데이터를 담을 수 있다.
//		 * - 클래스 선언부에 @SessionAttributes를 추가하면 데이터가 sessionScope로 저장.
//		 * 
//		 * 2) ModelAndView
//		 * - ModelAndView 에서 Model은 데이터를 담을 수 있는 맵형태의 객체.
//		 * - View는 이동할 페이지에 대한 정보를 담고 있는 객체.
//		 * - 기본 requestScope에 데이터를 보관.
//		 * 
//		 * Model은 내부에 데이터를 추가시 addAttribute() 함수를 사용하여 데이터를 추가.
//		 * ModelAndView는 데이터 추가시 addObject()를 사용하며 View 설정시 setViewName()을 사용.
//		 * 
//		 */
//		model.addAttribute("errorMsg", "오류발생");
//		
//		mv.addObject("errorMsg", "오류발생?");
//		mv.setViewName("common/errorPage");
//		
//		return mv;
//	}

	
	@GetMapping("/member/logout")
	public String logout(HttpSession session, SessionStatus status) {
		// 로그아웃 방법
		// 1. session 내부의 인증정보를 무효화
		session.invalidate(); // 세션 내부의 모든 데이터를 삭제.
		status.setComplete(); // model로 sessionScope에 이관된 데이터를 비우는 메서드.
		
		return "redirect:/";
	}
	
	@GetMapping("/member/insert")
	public String enrollForm(Model model) {
		model.addAttribute("member", new Member());		
		return "member/enroll";
	}
	
	@PostMapping("/member/insert")
	public String insertMember(
	        @ModelAttribute Member m,
	        BindingResult bindingResult,   // MemberValidator의 검사 결과를 담음
	        @RequestParam("ssn1") String ssn1,
	        @RequestParam("ssn2") String ssn2,
	        @RequestParam("emailId") String emailId,
	        @RequestParam("email-input") String emailDomain,
	        Model model,
	        RedirectAttributes ra
	        ) {
		
		memberValidator.validate(m, bindingResult); // Member 객체 검증
		
		if(bindingResult.hasErrors()) {
			return "member/enroll";
		}
		
		// 회원가입
	    String ssn = ssn1 + "-" + ssn2;
	    String email = emailId + "@" + emailDomain;

	    m.setSsn(ssn);
	    m.setEmail(email);

	    int result = mService.insertMember(m);
	    String viewName = "";

	    if (result > 0) {
	        ra.addFlashAttribute("userNameForComplete", m.getUserName()); // 사용자 이름이 뜲
	        viewName = "redirect:/member/enrollComplete";
	    } else {
	        ra.addFlashAttribute("result", "fail");
	        viewName = "redirect:/member/enrollComplete";
	    }
	    return viewName;
	}
	
	@GetMapping("/member/enrollComplete")
	public String enrollComplete() {
		return "member/enrollComplete";
	}
	
	
	@GetMapping("/member/myPage")
	public String myPage() {
		return "member/myPage";
	}
	
	@PostMapping("/member/update")
	public String updateMember(
			Member m,
			Model model,
			RedirectAttributes ra
			) {
		int result = mService.updateMember(m);
		String url = "";
		
//		throw new RuntimeException("예외 발생.");
		
		System.out.println(model.getAttribute("loginUser"));
		System.out.println();
		
		if (result > 0) {
			ra.addFlashAttribute("alertMsg", "내정보 수정 성공.");
			url = "redirect:/";
		} else {
			model.addAttribute("errorMsg", "내정보 수정 실패.");
			url = "common/errorPage";
		}
		
		return url;
	}
	
	/*
	 * 스프링 예외처리 방법.
	 * 1. try - catch로 메서드별 예외처리 --> 1순위로 적용.
	 * 2. 하나의 컨트롤러에서 발생하는 예외들을 모아서 처리하는 방법 --> 2순위로 적용.
	 * 	  컨트롤러에 예외처리 메서드를 1개 추가한 후, @ExceptionHandler 추가.
	 * 3. 어플리케이션 전역에서 발생하는 예외를 모아서 처리하는 방법 --> 3순위.
	 *    새로운 클래스 작성 후, 클래스에 @ControllerAdvice를 추가.
	 */
	
//	@ExceptionHandler
//	public String exceptionHandler(Exception e, Model model) {
//		e.printStackTrace();
//		
//		model.addAttribute("errorMsg", "서비스 이용 중 문제가 발생했습니다.");
//		
//		return "common/errorPage";
//	}
	
	// 비동기 요청
	@ResponseBody // 반환되는 값이 값 그 자체임을 의미하는 주석
	@GetMapping("/member/idCheck")
	public String idCheck(@RequestParam("checkId") String userId) {
		int result = mService.idCheck(userId); // 아이디 존재시 1, 없다면 0
		return "" + result;
	}
	
//	@ResponseBody
//	@PostMapping("/member/selectOne")
//	public HashMap<String, Object> selectOne(String userId) {
//		HashMap<String, Object> map = mService.selectOne(userId);
//		
//		// jackson-databind를 활용하여, vo클래스, ArrayList, map 데이터를 자동으로 JSON 형태로 바인딩하기.
//		return map;
//	}
	
	@PostMapping("/member/selectOne")
	public ResponseEntity<HashMap<String, Object>> selectOne(String userId) {
		HashMap<String, Object> map = mService.selectOne(userId);
		
		ResponseEntity<HashMap<String, Object>> res = null;
		
		if (map != null) {
			res = ResponseEntity
					.ok() // 응답상태 200
					//.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
					.body(map);
		} else {
			res = ResponseEntity
					.notFound()
					.build();
		}
		
		return res;
	}
	
	@ResponseBody 
	@PostMapping("/member/emailCert") 
	public String sendEmail(String email) {
		
	    String certCode = mService.sendEmail(email);
	   
	    // 반환받은 인증코드를 프론트엔드(JavaScript)로 다시 보내줍니다.
	    return certCode;
	}
	
	
	// 비밀번호 비교
	@ResponseBody
	@PostMapping("/member/checkSamePassword")
	public Map<String, Object> checkSamePassword(@RequestParam("newPassword")
	String newPassword, HttpSession session) {
		Map<String, Object> response = new HashMap<>();
		String userId = (String) session.getAttribute("userIdForReset");
		
		boolean isSame = false;
		if(userId != null && newPassword != null && !newPassword.isEmpty()) {
			Member m = new Member();
			m.setUserId(userId);
			Member currentUser = mService.loginMember(m);
			if (currentUser != null && currentUser.getUserPwd() != null) {
				isSame = bcryptPasswordEncoder.matches(newPassword, currentUser.getUserPwd());
			}
		}
		
		response.put("isSame", isSame);
		return response;
		
	}
	
	
	
	// 아이디 찾기
	@PostMapping("/member/findId")
	public String findId(@RequestParam("userName") String userName,
	    @RequestParam("ssn1") String ssn1,
	    @RequestParam("ssn2") String ssn2,
	    Model model) {
		
		String ssn = ssn1 + "-" + ssn2;
		String foundId = mService.findId(userName, ssn);
		
		model.addAttribute("foundId", foundId);		
		return "member/findId_Result";  // 아이디 찾기 결과창으로
	}
	
	// 비밀번호 찾기
	@PostMapping("/member/findPassword")
	public String findPassword(@RequestParam("userName") String userName,
			@RequestParam("ssn1") String ssn1,
	        @RequestParam("ssn2") String ssn2,
	        @RequestParam("userEmailId") String emailId,
	        @RequestParam("userEmailDomain") String emailDomain,
	        HttpSession session,
	        RedirectAttributes rttr) {
		
		String ssn = ssn1 + "-" + ssn2;
		String email = emailId + "@" + emailDomain; // 이메일 주소 합치기
		String userId = mService.findUserForPasswordReset(userName, ssn, email);

		    if (userId != null) {
		        session.setAttribute("userIdForReset", userId);

		        // '새 비밀번호 입력' 페이지로 이동
		        return "redirect:/member/resetPasswordForm";
		    } else {
		        rttr.addFlashAttribute("message", "입력하신 이름 또는 주민등록번호를 다시 확인해주세요.");
		        return "redirect:/member/findPassword";
		    }
		}

		// 비밀번호 찾기(보안)
		@PostMapping("/member/resetPassword")
		public String resetPassword(@RequestParam("newPassword")
			   String newPassword, HttpSession session,
			   RedirectAttributes rttr) {

		String userId = (String) session.getAttribute("userIdForReset");
		
		if(userId == null) {
			return "redirect:/member/findPassword"; // 세션만료 대비
		}
		
		Member m = new Member();
	    m.setUserId(userId);
	    Member currentUser = mService.loginMember(m);
	    	    
	    String oldPasswordHash = currentUser.getUserPwd();
		
	    if (bcryptPasswordEncoder.matches(newPassword, oldPasswordHash)) {
	        // 동일한 경우, 에러 메시지와 함께 이전 페이지로 돌려보냅니다.
	        rttr.addFlashAttribute("message", "기존과 동일한 비밀번호로는 변경할 수 없습니다.");
	        return "redirect:/member/resetPasswordForm";
	    }

		int result = mService.updatePassword(userId, newPassword);

		session.removeAttribute("userIdForReset");

		return "redirect:/member/changePasswordComplete";

	}

		@GetMapping("/member/changePasswordComplete")
		public String passwordChangeComplete() {

			return "/member/changePasswordComplete";
		}
	
		
		
		
		
	// /member/agree 주소로 요청이 들어오면  agree.jsp를 화면에 나타냄
	@GetMapping("/member/agree")
	
	public String agreeForm() {
		return "member/agree";
	}
	
	// 아이디 찾기 페이지를 화면에 나타냄
	@GetMapping("/member/findId")
	public String findIdForm() {
		return "member/findId";
	}
	
	// 비밀번호 찾기 페이지를 화면에 나타냄
	@GetMapping("/member/findPassword")
	public String findPasswordForm() {
		return "member/findPassword";
	}
	
	// 새 비밀번호 설정 페이지를 화면에 나타냄
	@GetMapping("/member/resetPasswordForm")
	public String resetPasswordForm() {
		return "member/changePassword";
	}
	
}