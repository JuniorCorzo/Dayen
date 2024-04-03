package com.dayen.dayen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "personal")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Personal {
    @Id
    @Column(name = "id_personal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String telefono;

    @JsonBackReference
    @ManyToMany(mappedBy = "personal")
    private List<Procesos> procesos;
}
