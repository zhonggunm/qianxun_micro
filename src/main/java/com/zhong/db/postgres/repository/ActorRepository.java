package com.zhong.db.postgres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhong.db.postgres.model.Actor;

public interface ActorRepository extends JpaRepository<Actor, Long> {
	public List<Actor> findByLastName(String lastName);

}