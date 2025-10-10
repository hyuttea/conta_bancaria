package com.senai.conta_bancaria.interface_ui.exception;
import com.senai.conta_bancaria.domain.entity.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContaMesmoTipoException.class)
    public ProblemDetail handleContaMesmoTipoException (ContaMesmoTipoException ex,
                                                        HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.CONFLICT,
                "Mesmo tipo de conta",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ProblemDetail handleEntidadeNaoEncontradaException (EntidadeNaoEncontradaException ex,
                                                               HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.NOT_FOUND,
                "Entidade não encontrada",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(RendimentoInvalidoException.class)
    public ProblemDetail handleRendimentoInvalidoException (RendimentoInvalidoException ex,
                                                            HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.BAD_REQUEST,
                "Rendimento inválido",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ProblemDetail handleSaldoInsuficienteException (SaldoInsuficienteException ex,
                                                           HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.BAD_REQUEST,
                "Saldo insuficiente",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(TipoDeContaInvalidaException.class)
    public ProblemDetail handleTipoDeContaInvalidaException (TipoDeContaInvalidaException ex,
                                                             HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.BAD_REQUEST,
                "Tipo de conta inválida",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(TransferenciaParaMesmaContaException.class)
    public ProblemDetail handleTransferenciaParaMesmaContaException (TransferenciaParaMesmaContaException ex,
                                                                     HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.CONFLICT,
                "Transferencia para a mesma conta não é permitido",
                ex.getMessage(),
                request.getRequestURI()
        );
    }

    @ExceptionHandler(ValoresNegativoException.class)
    public ProblemDetail handleValoresNegativosException (ValoresNegativoException ex,
                                                          HttpServletRequest request) {
        return ProblemDetailUtils.buildProblem(
                HttpStatus.BAD_REQUEST,
                "Valores negativos não são permitidos",
                ex.getMessage(),
                request.getRequestURI()
        );
    }
}