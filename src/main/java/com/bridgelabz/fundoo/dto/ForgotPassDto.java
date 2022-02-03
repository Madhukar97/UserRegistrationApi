package com.bridgelabz.fundoo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForgotPassDto {

	private String email;
	private String newPass;
	private String confirmNewPass;
}
