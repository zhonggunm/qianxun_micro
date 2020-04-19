package com.zhong.db.postgres.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhong.db.postgres.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public List<Account> findByLastName(String lastName);

}