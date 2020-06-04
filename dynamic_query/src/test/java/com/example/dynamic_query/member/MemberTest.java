package com.example.dynamic_query.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.example.dynamic_query.member.dao.CustomMemberRepository;
import com.example.dynamic_query.member.dao.MemberRepo;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;

@DataJpaTest
public class MemberTest {

  @Autowired
  private EntityManager em;

  @Autowired
  private MemberRepo memberRepo;

  @Autowired
  private CustomMemberRepository memberRepoImpl;

  @Test
  @DisplayName("하이버네이트에 QueryDSL 적용")
  public void Hibernate_QueryDSL_Test() {

    JPAQuery<Member> query = new JPAQuery<Member>(em);
    QMember member = QMember.member;
    List<Member> members = query.from(member).where(member.name.eq("mem1")).orderBy(member.id.desc()).fetch();

    assertEquals(1, members.get(0).getId());
    assertEquals("mem1", members.get(0).getName());

  }

  @Test
  @DisplayName("하이버네이트에 QueryDSL 적용: 동적 쿼리")
  public void Hibernate_QueryDSL_Dynamic_Query_Test() {

    JPAQuery<Member> query = new JPAQuery<Member>(em);
    QMember member = QMember.member;
    BooleanBuilder builder = new BooleanBuilder();
    builder.and(member.name.contains("mem"));

    List<Member> members = query.from(member).where(builder).fetch();

    assertEquals(1, members.get(0).getId());
    assertEquals("mem1", members.get(0).getName());

  }

  @Test
  @DisplayName("Spring JPA에 QueryDSL Predicate 적용")
  public void Jpa_QueryDSL_Predicate_Integration() {

    List<Member> members = memberRepo.findAll(QMember.member.name.eq("mem1"));

    assertEquals(1, members.get(0).getId());
    assertEquals("mem1", members.get(0).getName());

  }

  @Test
  @DisplayName("Spring JPA에 QueryDSL Repository 적용")
  public void Jpa_QueryDSL_RepositorySupport_Integration1() {

    MemberSearch search = MemberSearch.builder().name("m1").build();

    List<Member> members = memberRepoImpl.search(search);

    assertEquals(1, members.get(0).getId());
    assertEquals("mem1", members.get(0).getName());

  }

  @Test
  @DisplayName("Spring JPA에 QueryDSL Repository 적용2: Join")
  public void Jpa_QueryDSL_RepositorySupport_Integration2() {

    MemberSearch search = MemberSearch.builder().teamName("team1").build();

    List<Member> members = memberRepoImpl.search(search);

    assertEquals(2, members.size());

  }

  @Test
  @DisplayName("Spring JPA Specification: Like")
  public void specification() {

    String keyword = "m1";

    Specification<Member> spec = new Specification<Member>() {
      private static final long serialVersionUID = 1L;

      @Override
      public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("name"), "%" + keyword + "%");
      }
    };

    List<Member> members = memberRepo.findAll(spec);

    assertEquals(1, members.get(0).getId());
    assertEquals("mem1", members.get(0).getName());
    
  }

  @Test
  @DisplayName("Spring JPA Specification: Join")
  public void specification2(){
    String keyword = "m1";

    Specification<Member> spec = new Specification<Member>() {
      private static final long serialVersionUID = 1L;

      @Override
      public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.join("team").get("name"), "%" + keyword + "%");
      }
    };

    List<Member> members = memberRepo.findAll(spec);

    assertEquals(2, members.size());
    assertEquals("team1", members.get(0).getTeam().getName());
    assertEquals("team1", members.get(1).getTeam().getName());
  }

  @Test
  @DisplayName("Spring JPA Specification: Combine Specifications")
  public void specification3(){

    String teamNameKeyword = "team1";
    String memberNameKeyword = "mem1";

    Specification<Member> isConatinTeamNameSpec = new Specification<Member>() {
      private static final long serialVersionUID = 1L;
      @Override
      public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.join("team").get("name"), "%" + teamNameKeyword + "%");
      }
    };

    Specification<Member> isContainNameSpec = new Specification<Member>() {
      private static final long serialVersionUID = 2L;
      @Override
      public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(root.get("name"), "%" + memberNameKeyword + "%");
      }
    };

    Specification<Member> isConatinTeamNameAndNameSpec = isConatinTeamNameSpec.and(isContainNameSpec);

    List<Member> members = memberRepo.findAll(isConatinTeamNameAndNameSpec);

    assertEquals(1, members.size());
    assertEquals("team1", members.get(0).getTeam().getName());
    assertEquals("mem1", members.get(0).getName());
  }

}