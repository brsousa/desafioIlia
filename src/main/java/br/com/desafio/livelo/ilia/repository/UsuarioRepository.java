package br.com.desafio.livelo.ilia.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.desafio.livelo.ilia.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	public Optional<Usuario> findByEmail(String email);
	
}
