package gerencia;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double diasAtrasado;

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public double getValorMulta() {
        diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        return diasAtrasado * 1.5;
    }

    public double getTempoSuspensao() {
        diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        return diasAtrasado * 2;
    }
}
