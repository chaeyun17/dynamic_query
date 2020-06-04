package com.example.dynamic_query.team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepo extends JpaRepository<Team, Long>{
  
}