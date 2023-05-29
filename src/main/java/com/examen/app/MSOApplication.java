package com.examen.app;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.examen.app.security.dao.UserRepository;
import com.examen.app.security.entidad.*;

@SpringBootApplication
@ComponentScan("com.examen.app")
public class MSOApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MSOApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		userRepository.save(new UsuarioEntity("eguillenm", new BCryptPasswordEncoder().encode("Lalo1994"), "Eduardo",
				"Guillen M", "eguillenm@gmail.com", true, new Date(), Arrays.asList(new RolEntity("ROLE_ADMIN"))));

	}

}
