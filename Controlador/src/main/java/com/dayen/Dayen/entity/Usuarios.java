package com.dayen.Dayen.entity;

import com.dayen.Dayen.utils.Rol;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuarios {
    @Id
    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "nombre")
    @NotNull
    @NotEmpty
    private String nombre;

    @Column(name = "apellido")
    @NotNull
    @NotEmpty
    private String apellido;

    @Column(name = "rol")
    @NotNull
    private String rol;

    @Column(name = "correo", unique = true)
    @NotNull
    @Email
    private String correo;

    @Column(name = "clave")
    @NotNull
    @NotEmpty
    private String clave;

    @Column(name = "token")
    private String token;

    @OneToMany(mappedBy = "idUsuario", targetEntity = Personal.class)
    @JsonManagedReference
    private List<Personal> personal;

    @OneToMany(mappedBy = "idUsuario", targetEntity = Lotes.class)
    @JsonManagedReference
    private List<Lotes> lote;
}
