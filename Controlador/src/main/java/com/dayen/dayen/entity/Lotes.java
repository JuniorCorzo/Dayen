package com.dayen.dayen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lote")
    private Integer idLote;

    @ManyToOne(targetEntity = Usuarios.class)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @JsonBackReference
    private Usuarios idUsuario;

    @Column(name = "fase")
    private String fase;

    @Column(name = "hectareas")
    private Integer hectareas;

    @JsonManagedReference
    @OneToMany(mappedBy = "idLote", targetEntity = Procesos.class)
    private List<Procesos> procesos;
}
