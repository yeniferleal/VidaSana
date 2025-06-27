package com.vidaSana.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "persona")
public class Persona implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id_persona;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Column(name = "apellido")
    private String apellido;
    
    @NotNull
    @Column(name = "fecha_nacimiento")
    private Date fecha_nacimiento;
    
    @NotEmpty
    @Column(name = "genero")
    private String genero;
    
    @NotNull
    @Column(name = "edad")
    private Short edad;
    
    @NotNull
    @Column(name = "peso")
    private Short peso;
    
    @NotNull
    @Column(name = "altura")
    private Short altura;
    
    @Column(name = "grado_escolaridad")
    private Short grado_escolaridad;
    
    @Column(name = "alergias")
    private String alergias;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    
}
