package com.senai.conta_bancaria.domain.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta", discriminatorType = DiscriminatorType.STRING, length = 20)
@Table(name = "conta",
        uniqueConstraints = {
            @UniqueConstraint(name = "uk_conta_numero", columnNames = "numero"),
            @UniqueConstraint(name = "uk_conta_tipo", columnNames = {"cliente_id", "tipo_conta"})
        }
        )
@SuperBuilder
@NoArgsConstructor
public abstract class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 20)
    private String numero;

    @Column(nullable = false, precision = 20,scale = 2)
    private BigDecimal saldo;

    @Column(nullable = false)
    private Boolean ativa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Cliente_id", foreignKey = @ForeignKey(name = "fk_conta_cliente"))
    private Cliente cliente;
}
