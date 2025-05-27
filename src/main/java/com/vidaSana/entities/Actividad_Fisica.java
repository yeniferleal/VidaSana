package com.vidaSana.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "actividad_fisica")
@Data
public class Actividad_Fisica implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_actividad;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Column(name = "dia")
    private String dia;
    
    @NotEmpty
    @Column(name = "frecuencia")
    private String frecuencia;
    
    @NotEmpty
    @Column(name = "duracion")
    private String duracion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clasificacion")
    private Clasifiacion_IMC clasificacion;
    
    @NotEmpty
    @Column(name = "video")
    private String video;
    
    @NotEmpty
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
}
