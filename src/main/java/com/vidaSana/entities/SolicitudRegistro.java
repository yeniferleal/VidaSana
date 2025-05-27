package com.vidaSana.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name = "solicitud_registro")
public class SolicitudRegistro implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Long id_solicitud;
    
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
    
    
    @OneToMany(targetEntity=SolicitudRol.class,cascade=CascadeType.ALL)
    @JoinColumn(name = "id_solicitud",referencedColumnName = "id_solicitud")
    private List<SolicitudRol> solicitudRoles;
    
    @PostRemove
    public void eliminarRoles(){
        solicitudRoles.forEach(rol -> rol=null);
    }

    public int hash(String username) {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(username);
        return Math.abs(hash);
    }
}
