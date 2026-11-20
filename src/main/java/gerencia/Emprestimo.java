package gerencia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enuns.ClienteStatus;
import enuns.ItemStatus;
import acervo.ItensDoAcervo;

public class Emprestimo {
    //classe na qual ficam as regras de emprestimo do sistema e gerencia a permissão de emprestar
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double diasAtrasado;
    private ItensDoAcervo item; // atributo geral para as classes filhas poderem usar por herança
    private Cliente cliente;


    /* Construtor */
    private Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista,
                       ItensDoAcervo item, Cliente cliente) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = null;
        this.diasAtrasado = 0;
        this.item = item;
        this.cliente = cliente;
    }

    /* Método principal para realizar o empréstimo */

    public static Emprestimo emprestar(Cliente cliente, ItensDoAcervo item) { //importante ser static, o construtor ta
                                                                              // dentro do método KJDAJDSA
        if (!Emprestimo.estaDisponivel(item)) {
            throw new IllegalArgumentException("O item" + item.getTitulo() + "está indisponível no momento :(");
            //fazer lista de espera, decidir se ficara em emprestimo ou biblioteca
        } else if (!Emprestimo.podePegar(cliente)) {
            throw new IllegalArgumentException("Usuário suspenso ou com limite de empréstimos atingido");
        }

        LocalDate hoje = LocalDate.now();
        LocalDate previsao = hoje.plusDays(6);
        item.setStatusItem(ItemStatus.EMPRESTADO);

        return new Emprestimo(hoje, previsao, item, cliente);

    }

    /*public void devolver() {
        if (Emprestimo.estaDisponivel())
    }*/


    /* Outros métodos */

   /* public void registrarDevolucao(ItensDoAcervo item) {
        if  (item.getStatusItem() != ItemStatus.DISPONIVEL) {
            item.setStatusItem(ItemStatus.DISPONIVEL);
            System.out.println(item.getTitulo() + "foi devolvido com sucesso!");
        }
    } */ // revisar esse método!!

    public static boolean estaDisponivel(ItensDoAcervo item) {
        return item.getStatusItem() == ItemStatus.DISPONIVEL;
    }

    public static boolean podePegar(Cliente cliente) {
        if (cliente.getStatusCliente() == ClienteStatus.SEM_EMPRESTIMO) {
            return true;
        } else if (cliente.getStatusCliente() == ClienteStatus.COM_EMPRESTIMO && cliente.qntEmprestimos() < 6) {
            return true;
        } else {
            return false;
        }
    }

    public boolean estaAtrasado() {
        return dataDevolucaoReal.isAfter(dataDevolucaoPrevista);
    }


    /* Getters */
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

}
