package com.proj.finale.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proj.finale.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Integer>{

	List<Log> findByUserId(int userId);

	Optional<Log> findTopByUserIdOrderByEntryTimeDesc(int userId);


}
