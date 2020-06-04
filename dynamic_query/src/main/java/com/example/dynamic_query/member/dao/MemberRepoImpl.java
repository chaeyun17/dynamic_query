package com.example.dynamic_query.member.dao;

import java.util.List;
import java.util.Objects;

import com.example.dynamic_query.member.Member;
import com.example.dynamic_query.member.MemberSearch;
import com.example.dynamic_query.member.QMember;
import com.example.dynamic_query.team.QTeam;
import com.querydsl.jpa.JPQLQuery;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.util.StringUtils;

public class MemberRepoImpl extends QuerydslRepositorySupport implements CustomMemberRepository{

  public MemberRepoImpl() {
    super(Member.class);
  }

  @Override
  public List<Member> search(MemberSearch search) {
    
    QMember member = QMember.member;
    QTeam team = QTeam.team;

    JPQLQuery<Member> query = from(member);

    if(StringUtils.hasText(search.getName())){
      query.where(member.name.contains(search.getName()));
    }
    
    if(Objects.nonNull(search.getId()) && search.getId() != 0L){
      query.where(member.id.eq(search.getId()));
    }

    if(StringUtils.hasText(search.getTeamName())){
      query.leftJoin(member.team, team).where(team.name.contains(search.getTeamName()));
    }

    return query.fetch();
  }

  
}