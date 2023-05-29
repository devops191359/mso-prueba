package com.examen.app.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.examen.app.security.service.CustomUserDetailsService;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtUtils;

	private final CustomUserDetailsService userDetailsService;

	@Value("${jwt.header}")
	private String tokenHeader;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, java.io.IOException {
		String authToken = request.getHeader(this.tokenHeader);

		//String authToken = jwtUtils.resolveToken(request);
		// TODO Auto-generated method stub

				// Get the JWT token from the Authorization header
				String token = jwtUtils.resolveToken(request);

				// Check if the token is valid
				if (token != null && jwtUtils.validateToken(token)) {

					// Get the user details from the token
					UserDetails userDetails = userDetailsService.loadUserByUsername(jwtUtils.getUsername(token));
					// Create an authentication object and set it in the security context
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
							null, userDetails.getAuthorities());
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}

				filterChain.doFilter(request, response);
	}

}