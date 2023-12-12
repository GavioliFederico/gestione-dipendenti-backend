package com.proj.finale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.finale.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer>{

}
