package org.softfriascorporations.avalonplazafec.entities.usuarios.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombreUsuario;

    @ManyToOne()
    @JoinColumn(name = "maes_rol", foreignKey = @ForeignKey(name = "fk_usuario_rol"))
    private Maestra maesRol;

    @Column(name = "hash_salt", nullable = false)
    private String hashsalt;

    @Column(name = "hash_password", nullable = false)
    private String hashpassword;

    /*@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "pers_id", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "empr_id")
    private Empresa empresa;

     */
}

