package gerencia;

import acervo.ItensDoAcervo;

import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
    private List<Emprestimo> emprestimos;
    private List<Cliente> queue;

    private Biblioteca() {
        this.emprestimos = new ArrayList<>();
        this.queue = new ArrayList<>();
    }

    public void registrarEmprestimo(Cliente cliente, ItensDoAcervo item) {
        try { //tenta fazer um emprestimo
            Emprestimo novo = Emprestimo.emprestar(cliente, item);
            this.emprestimos.add(novo);
        } catch (IllegalStateException e ) {
            this.queue.add(cliente); //adiciona o cliente a lista de espera caso o item esteja reservado
        }
    }



}
