package com.dayen.dayen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Productos {
    @Id
    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "nombre")
    @NotNull
    @NotEmpty
    private String nombre;

    @Column(name = "funcion")
    @NotNull
    @NotEmpty
    private String funcion;

    @OneToOne(mappedBy = "idProducto")
    @JsonBackReference
    private Procesos procesos;
}
