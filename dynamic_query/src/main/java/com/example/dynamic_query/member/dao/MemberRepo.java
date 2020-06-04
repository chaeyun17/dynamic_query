package com.example.dynamic_query.member.dao;

import java.util.List;

import com.example.dynamic_query.member.Member;
import com.querydsl.core.types.Predicate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface MemberRepo extends JpaRepository<Member, Long>, QuerydslPredicateExecutor<Member>, JpaSpecificationExecutor<Member> {
  
  List<Member> findAll(Predicate predicate);

}