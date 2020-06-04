package com.example.dynamic_query.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class MemberSearch {
  
  private Long id;
  private String name;
  private String teamName;
}