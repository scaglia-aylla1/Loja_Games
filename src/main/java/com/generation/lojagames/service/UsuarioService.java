package com.generation.lojagames.service;


import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.lojagames.model.Usuario;
import com.generation.lojagames.model.UsuarioLogin;
import com.generation.lojagames.repository.UsuarioRepository;
import com.generation.lojagames.security.JwtService;


@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

        // Verifica se o usuário já existe
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        // Verifica se a data de nascimento é válida (18+ anos)
        if (!isMaiorDeIdade(usuario.getDataNascimento()))
            return Optional.empty();

        // Criptografa a senha
        usuario.setSenha(criptografarSenha(usuario.getSenha()));

        // Salva o usuário
        return Optional.of(usuarioRepository.save(usuario));
    }

    public Optional<Usuario> atualizarUsuario(Usuario usuario) {

        Optional<Usuario> usuarioExistente = usuarioRepository.findById(usuario.getId());

        if (usuarioExistente.isPresent()) {

            Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

            if (buscaUsuario.isPresent() && !buscaUsuario.get().getId().equals(usuario.getId()))
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

            // ⚠️ Preserva a data de nascimento do banco
            usuario.setDataNascimento(usuarioExistente.get().getDataNascimento());

            // Criptografa a senha
            usuario.setSenha(criptografarSenha(usuario.getSenha()));

            return Optional.of(usuarioRepository.save(usuario));
        }

        return Optional.empty();
    }


	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

        // Gera o Objeto de autenticação
		var credenciais = new UsernamePasswordAuthenticationToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha());

        // Autentica o Usuario
		Authentication authentication = authenticationManager.authenticate(credenciais);

        // Se a autenticação foi efetuada com sucesso
		if (authentication.isAuthenticated()) {

            // Busca os dados do usuário
			Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

            // Se o usuário foi encontrado
			if (usuario.isPresent()) {

                // Preenche o Objeto usuarioLogin com os dados encontrados
			   usuarioLogin.get().setId(usuario.get().getId());
                usuarioLogin.get().setNome(usuario.get().getNome());
                usuarioLogin.get().setFoto(usuario.get().getFoto());
                usuarioLogin.get().setToken(gerarToken(usuarioLogin.get().getUsuario()));
                usuarioLogin.get().setSenha("");

                 // Retorna o Objeto preenchido
			   return usuarioLogin;

			}

        }

		return Optional.empty();

    }
	
	private boolean isMaiorDeIdade(LocalDate dtnascimento) {
	    if (dtnascimento == null)
	        return false;

	    return Period.between(dtnascimento, LocalDate.now()).getYears() >= 18;
	}

	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	private String gerarToken(String usuario) {
		return "Bearer " + jwtService.generateToken(usuario);
	}

}
