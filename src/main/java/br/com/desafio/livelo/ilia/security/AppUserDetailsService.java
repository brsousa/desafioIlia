package br.com.desafio.livelo.ilia.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.desafio.livelo.ilia.model.Usuario;
import br.com.desafio.livelo.ilia.repository.UsuarioRepository;

@Service
public class AppUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("User and/or password incorrect!"));
		return new User(email, usuario.getPassword(), getRoles(usuario));
	}

	private Collection<? extends GrantedAuthority> getRoles(Usuario usuario) {
		Set<SimpleGrantedAuthority> roles = new HashSet<SimpleGrantedAuthority>();
		usuario.getRoles().forEach(r -> roles.add(new SimpleGrantedAuthority(r.getDescription().toUpperCase())));
		return roles;
	}

}
