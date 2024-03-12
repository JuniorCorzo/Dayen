package com.dayen.dayen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "personal")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Personal {
    @Id
    @Column(name = "id_personal")
    private Integer idPersonal;

    @ManyToOne(targetEntity = Usuarios.class)
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Usuarios idUsuario;

    @Column(name = "nombre")
    @NotNull
    @NotEmpty
    private String nombre;

    @Column(name = "telefono")
    @NotNull
    private Integer telefono;
}
