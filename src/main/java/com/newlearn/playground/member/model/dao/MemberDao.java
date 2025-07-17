package com.newlearn.playground.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("memberDao")
public class MemberDao {

    public int idCheck(SqlSessionTemplate sqlSession, String checkId) {

        return sqlSession.selectOne("memberMapper.idCheck", checkId);
    }
}
