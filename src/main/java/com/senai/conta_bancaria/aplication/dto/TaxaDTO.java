package com.senai.conta_bancaria.aplication.dto;
import com.senai.conta_bancaria.domain.entity.TaxaEntity;
import com.senai.conta_bancaria.domain.enums.Descricao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import java.math.BigDecimal;

@Schema(
        name = "TaxaDTO",
        description = "DTO para transportar informações de Taxas"
)

@Builder
public record TaxaDTO(

        @NotBlank
        Descricao descricao,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false, message = "O percentual deve ser maior que zero.")
        BigDecimal percentual,

        @NotNull
        BigDecimal valorFixo
) {

    public static TaxaDTO fromEntity(TaxaEntity taxa) {
        return new TaxaDTO(
                taxa.getDescricao(),
                taxa.getPercentual(),
                taxa.getValorFixo()
        );
    }

    public TaxaEntity toEntity(){
        return TaxaEntity.builder()
                .descricao(descricao)
                .percentual(new BigDecimal("3.00"))
                .valorFixo(valorFixo)
                .build();
    }
}
