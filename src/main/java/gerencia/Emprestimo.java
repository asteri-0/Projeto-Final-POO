package gerencia;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import enuns.ClienteStatus;
import enuns.ItemStatus;
import gerencia.Cliente;
import acervo.ItensDoAcervo;
import jdk.vm.ci.meta.Local;

public class Emprestimo {
    //classe na qual ficam as regras de emprestimo do sistema e gerencia a permissão de emprestar
    private LocalDate dataEmprestimo = dataDevolucaoPrevista.;
    private LocalDate dataDevolucaoPrevista;
    private LocalDate dataDevolucaoReal;
    private double diasAtrasado;
    private ItensDoAcervo item; // atributo geral para as classes filhas poderem usar por herança
    private Cliente cliente;

    /* Construtor */
    private Emprestimo(LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal,
                      double diasAtrasado, ItensDoAcervo item, Cliente cliente) {
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.diasAtrasado = diasAtrasado;
        this.item = item;
        this.cliente = cliente;
    }

    /* Método principal para realizar o empréstimo */

    public Emprestimo emprestar(Cliente cliente, ItensDoAcervo item) {
        try {
            if (!Emprestimo.estaDisponivel(item)) {
                throw new IllegalArgumentException("O item" + item.getTitulo() + "está indisponível no momento," +
                        "você foi adicionado para a lista de espera!");
                //fazer lista de espera, decidir se ficara em emprestimo ou biblioteca
            } else if (!Emprestimo.podePegar(cliente)) {
                throw new IllegalArgumentException("Usuário suspenso ou com limite de empréstimos atingido");
            }

            dataEmprestimo = LocalDate.now(); // data do dia que foi realizado o empréstimo
            dataDevolucaoPrevista = dataEmprestimo.plus(6);
            diasAtrasado = 0; // variavel que só vai ser alterada se a data real for maior que a prevista
            dataDevolucaoReal = null; // só vamos descobrir quando o devolver() for acionado

            Emprestimo emprestimo = new Emprestimo(dataEmprestimo, dataDevolucaoPrevista, dataDevolucaoReal,
                    diasAtrasado, item, cliente);

            return emprestimo;
        } catch
    }


    /* Outros métodos */

    public void registrarDevolucao(ItensDoAcervo item) {
        if  (item.getStatusItem() != ItemStatus.DISPONIVEL) {
            item.setStatusItem(ItemStatus.DISPONIVEL);
            System.out.println(item.getTitulo() + "foi devolvido com sucesso!");
        }
    }

    public static boolean estaDisponivel(ItensDoAcervo item) {
        return item.getStatusItem() == ItemStatus.DISPONIVEL;
    }

    public static boolean podePegar(Cliente cliente) {
        if (cliente.getStatusCliente() == ClienteStatus.SEM_EMPRESTIMO) {
            return true;
        } else if (cliente.getStatusCliente() == ClienteStatus.COM_EMPRESTIMO || cliente.qntEmprestimos() < 6) {
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
