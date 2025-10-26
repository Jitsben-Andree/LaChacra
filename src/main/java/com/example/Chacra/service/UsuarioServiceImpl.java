package com.example.Chacra.service;

import com.example.Chacra.entity.Usuario;
import com.example.Chacra.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        // Encriptar password si no está encriptado
        if (!usuario.getPassword().startsWith("$2a$")) {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return findByEmail(email);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return org.springframework.security.core.userdetails.User
                .withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .authorities(usuario.getRol().getNombre())
                .accountExpired(false)
                .accountLocked(!usuario.getEstado())
                .credentialsExpired(false)
                .disabled(!usuario.getEstado())
                .build();
    }
}