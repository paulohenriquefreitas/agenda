package com.conexa.agenda;

import com.conexa.agenda.model.Role;
import com.conexa.agenda.model.User;
import com.conexa.agenda.repository.UserRepository;
import com.conexa.agenda.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Arrays;

@EnableJpaRepositories
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class  }  )
public class AgendaApplication implements CommandLineRunner {

	@Autowired
	UserService userService;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AgendaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User admin = new User();
		if (!userRepository.existsByUsername("Drauzio")){
			admin.setUsername("Drauzio");
			admin.setPassword("@agenda2021");
			admin.setEmail("admin@email.com");
			admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
			userService.signup(admin);
		}


	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
