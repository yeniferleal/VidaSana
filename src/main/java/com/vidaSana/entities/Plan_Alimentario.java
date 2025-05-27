package com.vidaSana.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name = "plan_alimentario")
@Data
public class Plan_Alimentario implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private Long id_plan;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Column(name = "dia")
    private String dia;
    
    @NotEmpty
    @Column(name = "tipo")
    private String tipo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_clasificacion")
    private Clasifiacion_IMC clasificacion;
    
    @NotEmpty
    @Column(name = "imagen")
    private String imagen;
    
    @NotEmpty
    @Column(name = "descripcion", length = 1000)
    private String descripcion;
}
