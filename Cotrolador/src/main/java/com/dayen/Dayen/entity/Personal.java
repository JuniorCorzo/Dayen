package com.dayen.Dayen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @Column(name = "id_personal")
    private Integer idPersonal;
    @ManyToOne(targetEntity = Usuarios.class)
    @JoinColumn(name = "id_usuario")
    private Usuarios usuario;

    @Column(name = "nombre")
    @NotNull
    @NotEmpty
    private String nombre;

    @Column(name = "telefono")
    @NotNull
    private Integer telefono;
}
