package com.example.dynamic_query.member.dao;

import java.util.List;

import com.example.dynamic_query.member.Member;
import com.example.dynamic_query.member.MemberSearch;

public interface CustomMemberRepository {

  List<Member> search(MemberSearch search);
  
}