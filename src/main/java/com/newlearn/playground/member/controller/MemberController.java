package com.newlearn.playground.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlearn.playground.member.model.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/enroll")
    public String showAgreePage() {
        return "member/agree";
    }

    @GetMapping("/enrollForm")
    public String showEnrollPage() {
        return "member/enroll";
    }

    @GetMapping("/idCheck")
    @ResponseBody 
    public String idCheck(String checkId) {
      
        int count = memberService.idCheck(checkId);
       
        if (count > 0) {
            return "unavailable"; // 사용 불가
        } else {
            return "available"; // 사용 가능
        }
    }
}





















