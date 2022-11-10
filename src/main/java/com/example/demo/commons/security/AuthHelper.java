package com.example.demo.commons.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.demo.config.security.UserAuth;

public abstract class AuthHelper {

	public static Long getPrincipalId() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return ((UserAuth) authentication.getPrincipal()).getId();
	}

}