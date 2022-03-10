package com.bridgelabz.fundoo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPassword {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;


}
