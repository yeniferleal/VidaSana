package com.vidaSana.service.impl;

import com.vidaSana.dao.UsuarioDAO;
import com.vidaSana.entities.Rol;
import com.vidaSana.entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioServiceImpl implements UserDetailsService{

    @Autowired
    private UsuarioDAO usuarioDao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList<GrantedAuthority>();
        for(Rol rol: usuario.getRoles()){
            roles.add(new SimpleGrantedAuthority(rol.getTipo_rol()));
        }
        return new User(usuario.getUsername(),usuario.getPassword(), roles);
    }
    
    @Transactional(readOnly = true)
    public List<Usuario> listarUsuarios(){
        return (List<Usuario>)usuarioDao.findAll();
    }
    
    public void guardarUsuario(Usuario usuario){
        usuarioDao.save(usuario);
    }
    
    public void eliminarUsuario(long id){
        usuarioDao.deleteById(id);
    }
    
    @Transactional(readOnly = true)
    public Usuario encontrarUsuario(String username){
        return usuarioDao.findByUsername(username);
    }
    
    @Transactional(readOnly = true)
    public Usuario findById(long id){
        Optional<Usuario> optional = usuarioDao.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
