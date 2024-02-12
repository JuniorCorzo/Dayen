package com.dayen.Dayen.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tipo_procesos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TipoProcesos {
    @Id
    @Column(name = "id_tipo")
    private Integer idTipo;

    @OneToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Productos idProducto;

    @Column(name = "tipo_proceso")
    @NotNull
    @NotEmpty
    private String tipoProceso;

    @OneToMany(mappedBy = "idTipo", targetEntity = Procesos.class)
    private List<Procesos> procesos;
}
