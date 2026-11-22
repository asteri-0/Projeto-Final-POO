package gerencia;

import java.time.*;
import enuns.ClienteStatus;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
    private int numeroEmprestimos;
    private ClienteStatus statusCliente;


    /* Construtores - Sobrecarga */

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        this(nome, cpf, telefone, dataNascimento, ClienteStatus.SEM_EMPRESTIMO, 0);
    }

    public Cliente(String nome, String cpf, String telefone, LocalDate dataNascimento,
                   ClienteStatus statusCliente, int numeroEmprestimos) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.statusCliente = statusCliente;
        this.numeroEmprestimos = numeroEmprestimos;
    }

    /* Métodos principais*/

    public boolean podePegar() {
        if (getStatusCliente() == ClienteStatus.SEM_EMPRESTIMO) {
            return true;
        } else if (getStatusCliente() == ClienteStatus.COM_EMPRESTIMO && qntEmprestimos() < 6) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return  "\t Nome: " + nome +
                "\n\t\t CPF: " + cpf +
                "\n\t\t Status: " + statusCliente;
    }


    /* Método para passar os atributos para o CSV*/

    public String toCSV() {
        return nome + ";" + cpf + ";" + telefone + ";" + dataNascimento + ";" + statusCliente + ";" + numeroEmprestimos;
    }

    /* Getters */

    public ClienteStatus getStatusCliente() {
        return statusCliente;
    }

    public void setStatusCliente(ClienteStatus statusCliente) {
        this.statusCliente = statusCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
            return dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public int qntEmprestimos() {
        return  numeroEmprestimos;
    }

}
