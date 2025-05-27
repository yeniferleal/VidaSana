package com.vidaSana.entities;

import java.io.Serializable;
import javax.persistence.*;
import lombok.Data;
import java.util.List;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id_usuario;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Column(name = "apellido")
    private String apellido;
        
    @NotEmpty
    @Column(name = "username")
    private String username;
    
    @NotEmpty
    @Column(name = "password")
    private String password;
    
    @OneToMany(targetEntity=Rol.class,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private List<Rol> roles;
    
    @OneToMany(targetEntity = Persona.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario",referencedColumnName = "id_usuario")
    private List<Persona> personas;
    
    @Override
    public String toString(){
        String [] nombres = nombre.split(" ");
        return id_usuario+" - "+nombres[0]+" "+apellido;
    }
}
