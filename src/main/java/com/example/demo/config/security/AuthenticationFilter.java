package com.example.demo.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.repository.UserRepository;

public class AuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UserRepository userRepository;

	public AuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		super();
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		Boolean valid = tokenService.isTokenValid(token);

		if (valid) {
			authUser(token);
		}
		filterChain.doFilter(request, response);
	}

	private void authUser(String token) {
		Long userId = tokenService.getIdUser(token);
		UserAuth user = userRepository.findById(userId).get();
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}

	private String recuperarToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");

		if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {

			return null;
		}
		return header.substring(7, header.length());
	}

}
