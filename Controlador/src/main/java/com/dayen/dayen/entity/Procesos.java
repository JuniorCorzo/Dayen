package com.dayen.dayen.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "procesos")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Procesos {
    @Id
    @Column(name = "id_proceso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProceso;

    @ManyToOne(targetEntity = Lotes.class)
    @JoinColumn(name = "id_lote")
    @JsonBackReference
    private Lotes idLote;

    @ManyToOne(targetEntity = TipoProcesos.class)
    @JoinColumn(name = "id_tipo")
    @JsonManagedReference
    private TipoProcesos idTipo;

    @ManyToMany
    @JoinTable(
            name = "procesos_productos",
            joinColumns = @JoinColumn(
                    name = "id_proceso",
                    referencedColumnName = "id_proceso"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_producto",
                    referencedColumnName = "id_producto"
            )
    )
    private List<Productos> idProducto;

    @Column(name = "descripcion")
    @NotNull
    @NotEmpty
    private String descripcion;

    @Column(name = "realizado_en")
    private LocalDate realizadoEn;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "personal_procesos",
            joinColumns = @JoinColumn(
                    name = "id_proceso",
                    referencedColumnName = "id_proceso"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "id_personal",
                    referencedColumnName = "id_personal"
            )
    )
    private List<Personal> personal;
}
