package com.zhong.qianxun.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhong.qianxun.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	public List<Account> findByLastName(String lastName);

}