package com.example.veiwServer.filter;

import org.springframework.security.core.AuthenticationException;

public class UserNotApprovedException extends AuthenticationException{
   
	
	public UserNotApprovedException(String message) {
        super(message);
		System.out.println(message);
    }

	
	
	
}
