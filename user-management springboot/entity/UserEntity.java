package com.rgt.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String role;
	private String password;
	
}
