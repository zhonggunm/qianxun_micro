package com.zhong.db.postgres.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "actor")
public class Actor {

	@Column(name = "actor_id")
  private @Id @GeneratedValue Long id;
  
  @Column(name = "first_name")
  private String firstName;
  
  @Column(name = "last_name")
  private String lastName;

  Actor() {}

  public Actor(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
}