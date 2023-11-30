package com.uniamerica.pizzaria.service;
import com.uniamerica.pizzaria.config.JwtServiceGenerator;
import com.uniamerica.pizzaria.dto.AtendenteDTO;
import com.uniamerica.pizzaria.dto.LoginDTO;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;


	public AtendenteDTO logar(LoginDTO loginDTO) {
    System.out.println("antes:" + loginDTO.getUsername());
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginDTO.getUsername(),
						loginDTO.getPassword()
						)

				);
    System.out.println("interessante mas tá" + loginDTO.getUsername());
		Atendente user = repository.findByUsername(loginDTO.getUsername()).orElseThrow();
		var jwtToken = jwtService.generateToken(user);

		return toUserDTO(user, jwtToken);
	}


	private AtendenteDTO toUserDTO(Atendente user, String token) {
		AtendenteDTO userDTO = new AtendenteDTO();
		userDTO.setId(user.getId());
		userDTO.setRole(user.getRole());
		userDTO.setToken(token);
		userDTO.setUsername(user.getUsername());
		return userDTO;
	}

}
