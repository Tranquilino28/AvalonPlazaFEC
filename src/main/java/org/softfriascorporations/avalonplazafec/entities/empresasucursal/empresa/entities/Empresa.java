package org.softfriascorporations.avalonplazafec.entities.empresasucursal.empresa.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.softfriascorporations.avalonplazafec.entities.empresasucursal.sucursal.entities.Sucursal;
import org.softfriascorporations.avalonplazafec.entities.maestra.entities.Maestra;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "empresas")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(nullable = false, unique = true)
    private String nit;

    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "maes_estado", foreignKey = @ForeignKey(name = "fk_empresa_estado"))
    private Maestra estado;

    @JsonManagedReference
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sucursal> sucursales = new ArrayList<>();

    public void addSucursal(Sucursal s) {
        s.setEmpresa(this);
        sucursales.add(s);
    }
}
