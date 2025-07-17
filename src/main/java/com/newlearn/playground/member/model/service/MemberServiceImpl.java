package com.newlearn.playground.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlearn.playground.member.model.dao.MemberDao;

	@Service("memberService")
	public class MemberServiceImpl implements MemberService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private MemberDao memberDao;

    @Override
    public int idCheck(String checkId) {
        
    return memberDao.idCheck(sqlSession, checkId);
    
    }
}
