package com.senai.conta_bancaria.domain.entity;


import com.senai.conta_bancaria.domain.enums.Descricao;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder

public class TaxaEntity {
@Id
@GeneratedValue(strategy = GenerationType.UUID)
private String id;

    @DecimalMin("3.0")
    @NotNull
    @Column (name = "percentual", precision = 5, scale = 2)
    private BigDecimal percentual;

    @Column(name = "valor_fixo", precision = 10, scale = 2)
    private BigDecimal valorFixo;

    @ManyToMany(mappedBy = "taxas")
    private List<PagamentoEntity> pagamentos = new ArrayList<>();


}
