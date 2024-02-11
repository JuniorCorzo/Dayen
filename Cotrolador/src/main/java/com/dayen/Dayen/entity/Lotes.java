package com.dayen.Dayen.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lotes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Lotes {
    @Id
    @Column(name = "id_lote")
    private Integer id_lote;

    @ManyToOne(targetEntity = Usuarios.class)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuarios idUsuario;
}
