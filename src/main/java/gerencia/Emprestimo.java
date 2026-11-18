package gerencia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enuns.ItemStatus;
import gerencia.Cliente;
import acervo.ItensDoAcervo;

public class Emprestimo {
    //classe na qual ficam as regras de emprestimo do sistema e gerencia a permissão de emprestar
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double diasAtrasado;
    private ItensDoAcervo item; // atributo geral para as classes filhas poderem usar por herança

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public double getDiasAtrasado() {
        return diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
    }

    public double getValorMulta() {
        diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        return diasAtrasado * 1.5;
    }

    public double getTempoSuspensao() {
        diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        return diasAtrasado * 2;
    }

    public void registrarDevolucao(ItensDoAcervo item) {
        if  (item.getStatusItem() != ItemStatus.DISPONIVEL) {
            item.setStatusItem(ItemStatus.DISPONIVEL);
            System.out.println(item.getTitulo() + "foi devolvido com sucesso!");
        }
    }

    public boolean estaDisponivel(ItensDoAcervo item) {
        return item.getStatusItem() == ItemStatus.DISPONIVEL;
    }

    public boolean estaAtrasado() {
        return dataDevolucaoReal.isAfter(dataDevolucaoPrevista);
    }

    public boolean podePegar(Cliente cliente) {
        return cliente.qntEmprestimos() < 6;
    }

}
