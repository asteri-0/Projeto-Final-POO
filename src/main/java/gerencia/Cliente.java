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

    public Cliente (String nome, String cpf, String telefone,  LocalDate dataNascimento) {
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        statusCliente = ClienteStatus.SEM_EMPRESTIMO;
        numeroEmprestimos = 0;
    }

    public ClienteStatus getStatusCliente() {
        return statusCliente;
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
