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


    /* Construtor privado */

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
        if (!item.estaDisponivel()) {
            throw new IllegalArgumentException("O item" + item.getTitulo() + "está indisponível no momento :(");
            //fazer lista de espera, decidir se ficara em emprestimo ou biblioteca
        } else if (!cliente.podePegar()) {
            throw new IllegalArgumentException("Usuário suspenso ou com limite de empréstimos atingido");
        }

        LocalDate hoje = LocalDate.now();
        LocalDate previsao = hoje.plusDays(6);
        item.setStatusItem(ItemStatus.EMPRESTADO);

        return new Emprestimo(hoje, previsao, item, cliente);

    }

    /* Método para reconstruir empréstimo a partir do CSV */
    public static Emprestimo reconstruir(LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista,
                                         ItensDoAcervo item, Cliente cliente) {
        return new Emprestimo(dataEmprestimo, dataDevolucaoPrevista, item, cliente);
    }

    /* Outros métodos */

    public void devolver() {

        if (item.getStatusItem() == ItemStatus.EMPRESTADO || item.getStatusItem() == ItemStatus.ATRASADO){
            if (cliente.getStatusCliente() == ClienteStatus.COM_EMPRESTIMO) {
             item.setStatusItem(ItemStatus.DISPONIVEL);
             dataDevolucaoReal = LocalDate.now();
             calcularAtraso();
            }
        }
    }

    public void calcularAtraso() {
        LocalDate hoje = LocalDate.now();
        if (item.getStatusItem() == ItemStatus.EMPRESTADO && dataDevolucaoPrevista.isAfter(hoje)) {
            diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, hoje);
            item.setStatusItem(ItemStatus.ATRASADO);
            cliente.setStatusCliente(ClienteStatus.SUSPENSO);
             calculaValorMulta();
             calculaTempoSuspensao();

            System.out.println("O item "  + item.getTitulo() + " está atrasado a " + diasAtrasado + "dias.");

        } else if (item.getStatusItem() == ItemStatus.EMPRESTADO && dataDevolucaoPrevista.isBefore(hoje)) {
                LocalDate ateDevolucao = LocalDate.ofEpochDay(ChronoUnit.DAYS.between(dataDevolucaoPrevista, hoje));
                System.out.println("O item " + item.getTitulo() + " está em dia, devolver em "
                        + ateDevolucao + " dias.");

        } else if (item.getStatusItem() == ItemStatus.EMPRESTADO && dataDevolucaoPrevista.isEqual(hoje)){
            System.out.println("O item " + item.getTitulo() + " deve ser devolvido hoje.");

        } else if (item.getStatusItem() == ItemStatus.DISPONIVEL) {
            System.out.println("O item " + item.getTitulo() + " não está emprestado.");
        }
    }

    /*public void calcularDiasAtraso(LocalDate previsao, LocalDate atual) {
        if (atual.isAfter(previsao))
    } */

    public boolean estaAtrasado() {
        return dataDevolucaoReal.isAfter(dataDevolucaoPrevista);
    }

    public double calculaValorMulta() {
        diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        return diasAtrasado * 1.5;
    }

    public double calculaTempoSuspensao() {
        diasAtrasado = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);

        return diasAtrasado * 2;
    }



    /* Para CSV */
    public String toCSV() {
        return dataEmprestimo + ";" + dataDevolucaoPrevista + ";" + item.getCodigoDeRegistro() + ";" + cliente.getCpf();
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

    public String getNomeCliente() {
        return getNomeCliente();
    }

    @Override
    public String toString() {
        return "Sobre o empréstimo: " +
                "\n\t Data do emprestimo: " + dataEmprestimo +
                "\n\t Devolução em: " + dataDevolucaoPrevista +
                "\n\t Item: " + item.getClass().getSimpleName() +
                "\n\t " + item +
                "\n\t Cliente: " +
                "\n\t " + cliente.toString();
    }
}
