package com.zhong.qianxun.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "accounts")
public class Account {

	@Column(name = "id")
  private @Id 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  
  @Column(name = "first_name")
  private String firstName;
  
  @Column(name = "last_name")
  private String lastName;

  Account() {}

  public Account(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}