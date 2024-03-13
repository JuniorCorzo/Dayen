package com.dayen.dayen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "procesos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Procesos {
    @Id
    @Column(name = "id_proceso")
    private Integer idProceso;

    @ManyToOne(targetEntity = Lotes.class)
    @JoinColumn(name = "id_lote")
    @JsonBackReference
    private Lotes idLote;

    @ManyToOne(targetEntity = TipoProcesos.class)
    @JoinColumn(name = "id_tipo")
    @JsonManagedReference
    private TipoProcesos idTipo;

    @OneToOne
    @JoinColumn(name = "id_producto")
    @JsonManagedReference
    private Productos idProducto;

    @Column(name = "descripcion")
    @NotNull
    @NotEmpty
    private String descripcion;

    @Column(name = "realizado_en")
    private LocalDateTime realizadoEn;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "personal_procesos",
            joinColumns = @JoinColumn(
                    name = "procesos_id_proceso",
                    referencedColumnName = "id_proceso"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "personal_id_personal",
                    referencedColumnName = "id_personal"
            )
    )
    private List<Personal> personal;
}
