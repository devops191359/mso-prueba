package com.examen.app.security.model;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.examen.app.security.entidad.RolEntity;
import com.examen.app.security.entidad.UsuarioEntity;

public final class JwtUserFactory {

	private JwtUserFactory() {
	}

	public static JwtUser create(UsuarioEntity user) {

		return new JwtUser(user.getId(), user.getUsername(), user.getFirstname(), user.getLastname(), user.getEmail(),
				user.getPassword(), mapToGrantedAuthorities(user.getAuthorities()), user.getEnabled(),
				user.getLastPasswordResetDate());
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<RolEntity> authorities) {
		return authorities.stream().map(authority -> new SimpleGrantedAuthority(authority.getName()))
				.collect(Collectors.toList());
	}
}
