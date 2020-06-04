package com.example.dynamic_query.team;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
public class Team {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  
}