package com.dayen.Dayen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "lotes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lotes {
    @Id
    @Column(name = "id_lote")
    private Integer idLote;

    @ManyToOne(targetEntity = Usuarios.class)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;

    @Column(name = "fase")
    private String fase;

    @Column(name = "hectareas")
    private Integer hectareas;

    @OneToMany(mappedBy = "idLote", targetEntity = Procesos.class)
    private List<Procesos> procesos;
}
