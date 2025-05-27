package com.vidaSana.entities;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
@Table(name="clasificacion_imc")
public class Clasifiacion_IMC implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clasificacion")
    private Long id_clasificacion;
    
    @NotEmpty
    @Column(name = "nombre")
    private String nombre;
    
    @NotEmpty
    @Column(name = "rango")
    private String rango;
    
    @NotNull
    @Column(name = "orden")
    private Short orden;
    
    @Override
    public String toString(){
        return nombre + " - rango (" + rango + ") IMC";
    }
}
