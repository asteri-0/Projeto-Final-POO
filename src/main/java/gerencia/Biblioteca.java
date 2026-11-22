package gerencia;

import acervo.ItensDoAcervo;
import acervo.Livro;
import enuns.CategoriaGenero;
import enuns.ItemStatus;
import persistencia.*;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
    private List<Emprestimo> emprestimos;
    private List<Cliente> clientes;
    private List<ItensDoAcervo> acervo;


    public Biblioteca() {
        this.emprestimos = new ArrayList<Emprestimo>();
        this.clientes = new ArrayList<Cliente>();
        this.acervo = new ArrayList<ItensDoAcervo>();
        carregarDados();

    }

    public void registrarEmprestimo(Cliente cliente, ItensDoAcervo item) {
        try { //tenta fazer um emprestimo
            Emprestimo novo = Emprestimo.emprestar(cliente, item);
            emprestimos.add(novo);
            System.out.println("Empréstimo realizado com sucesso!");
            System.out.println("Devolução em: " + novo.getDataDevolucaoPrevista() + "\n");
        } catch (IllegalStateException e ) {
            System.out.println("Não foi possível criar o objeto empréstimo.");
        }
    }

    public void registrarDevolucao(Emprestimo emprestimo) {
        emprestimo.devolver();
        this.emprestimos.remove(emprestimo);
        System.out.println("Devolução realizada com sucesso");

    }

    public void mostrarEmprestimos() {
        for (Emprestimo e : emprestimos) {
            System.out.println(e.toString());
            System.out.println("\n ============ \n");
        }
    }

    public void salvarDados() {
        GerenciadorCSV.salvarAcervo(this.acervo);
        GerenciadorCSV.salvarCliente(this.clientes);
        GerenciadorCSV.salvarEmprestimo(this.emprestimos);
    }

    public ItensDoAcervo buscarItemPorCodigo(String codigo) {
        for (ItensDoAcervo item : acervo) {
            if (item.getCodigoDeRegistro().equalsIgnoreCase(codigo)) {
                return item;
            }
        }
        return null;
    }

    public Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

    public void carregarDados() {
        this.acervo = GerenciadorCSV.carregarAcervo();
        this.clientes = GerenciadorCSV.carregarCliente();
        this.emprestimos = GerenciadorCSV.carregarEmprestimo(this.acervo, this.clientes);
    }




}
