package com.qfedu.hib1706.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

// POJO - Plain Ordinary Java Object
// POJO + Annotation / XML ==> PO
// PO - Persistent Object
@Entity
@Table(name = "tb_user")
@DynamicUpdate
public class User implements Serializable {
	@Id
	@Column(length = 20)
	private String username;
	@Column(name = "userpass", nullable = false, length = 20)
	private String password;
	@Column(unique = true)
	private String email;

	public User() {
	}

	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
