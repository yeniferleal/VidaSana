package com.vidaSana.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name="rol")
public class Rol implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id_rol;
    
    @NotEmpty
    @Column(name = "tipo_rol")
    private String tipo_rol;
    
    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    public Rol(String tipo_rol) {
        super();
        this.tipo_rol = tipo_rol;
    }

    
    public Rol(Long id_rol, String tipo_rol) {
        super();
        this.id_rol = id_rol;
        this.tipo_rol = tipo_rol;
    }

    public Rol() {
        super();
    }
    
    @Override
    public String toString(){
        String cadena = "";
        for (int i = 5; i < this.tipo_rol.length(); i++) {
            cadena += this.tipo_rol.charAt(i);
        }
        return cadena;
    }
}
