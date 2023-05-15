package com.examen.app.security.config;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

import javax.servlet.FilterChain;

@Component
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

	private final JwtTokenProvider jwtTokenProvider;
	private final UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response, FilterChain filterChain)
			throws javax.servlet.ServletException, IOException {
		// TODO Auto-generated method stub

		// Get the JWT token from the Authorization header
		String token = jwtTokenProvider.resolveToken(request);

		// Check if the token is valid
		if (token != null && jwtTokenProvider.validateToken(token)) {

			// Get the user details from the token
			UserDetails userDetails = userDetailsService.loadUserByUsername(jwtTokenProvider.getUsername(token));
			// Create an authentication object and set it in the security context
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
					null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}

		filterChain.doFilter(request, response);

	}

}