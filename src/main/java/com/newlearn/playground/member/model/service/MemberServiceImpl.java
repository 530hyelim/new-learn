package com.newlearn.playground.member.model.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.newlearn.playground.member.model.dao.MemberDao;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	private MemberDao memberDao;
	
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public int idCheck(String checkId) {
        return memberDao.idCheck(sqlSession, checkId);
    }

    /*
     * 이메일 인증코드 생성 및 발송
     * */
    @Override
    public String sendEmail(String email) {
        
        // 6자리 랜덤 인증코드 생성
        String certCode = "";
        for(int i = 0; i < 6; i++) {
            certCode += (int)(Math.random() * 10);
        }

        // MimeMessage 객체를 이용해 이메일 내용 생성
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom("발신자이메일@gmail.com"); // 나중에 실제 이메일 값으로 대체 email.properties 가서 하기
            helper.setTo(email); // 받는 사람 이메일 주소
            helper.setSubject("[new Learn();] 회원가입 인증번호입니다."); // 이메일 제목
            
            // HTML 형식의 본문
            String htmlContent = "<h1>[new Learn] 회원가입 인증번호</h1>";
            htmlContent += "<p>요청하신 인증번호는 다음과 같습니다.</p>";
            htmlContent += "<div style='background-color:#f0f0f0; padding:20px; text-align:center;'>";
            htmlContent += "<h2 style='font-size:24px; color:#333;'>" + certCode + "</h2>";
            htmlContent += "</div>";
            htmlContent += "<p>이 인증번호를 회원가입 페이지의 인증코드란에 입력해주세요.</p>";
            
            helper.setText(htmlContent, true);

            // 이메일 발송
            mailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
        return certCode;
    }
	
	
	
 
}
