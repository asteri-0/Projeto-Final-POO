package gerencia;

import acervo.ItensDoAcervo;
import acervo.Livro;
import enuns.CategoriaGenero;
import enuns.ItemStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Biblioteca {
    private List<Emprestimo> emprestimos;

    public Biblioteca() {
        this.emprestimos = new ArrayList<Emprestimo>();

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



}
