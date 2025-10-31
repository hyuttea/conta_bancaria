package com.senai.conta_bancaria.domain.entity;
import com.senai.conta_bancaria.domain.exceptions.SaldoInsuficienteException;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CORRENTE") //Valor que identifica a classe
@Data
@EqualsAndHashCode(callSuper = true) //Gera equals e hashcode considerando a superclasse
@SuperBuilder
public class ContaCorrente extends Conta{
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal limite;
    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal taxa;

    @Override
    public String getTipoConta() {
        return "CORRENTE";
    }

    @Override
    public void sacar(BigDecimal valor) {
        validarValorMaiorQueZero(valor, "saque");

        BigDecimal custoSaque = valor.multiply(this.taxa);
        BigDecimal totalSaque = valor.add(custoSaque);

        // Aqui, permitimos que o saldo fique negativo até o limite definido
        if (this.getSaldo().add(this.limite).compareTo(totalSaque) < 0) {
            throw new SaldoInsuficienteException();
        }

        this.setSaldo(this.getSaldo().subtract(valor));
    }
}