package com.example.dynamic_query.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.dynamic_query.team.Team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Member {
  
  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToOne
  @JoinColumn
  private Team team;
}