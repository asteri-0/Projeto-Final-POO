package execucao;

import enuns.*;
import acervo.*;
import gerencia.*;

import java.time.LocalDate;

public class Run {

    public static void main(String[] args) {
        Livro livro1 = new Livro("123", "Macacos Voadores",
                1, CategoriaGenero.FICCAO_CIENTIFICA, ItemStatus.DISPONIVEL,
                "isbn1", 1000, "Bananas" );
        Cliente cliente1 = new Cliente("Claudinho", "111.111.111-11", "18 91234-5678",
                LocalDate.of(2007, 4, 23));
        Biblioteca biblis = new Biblioteca();

        biblis.registrarEmprestimo(cliente1, livro1);
        biblis.mostrarEmprestimos();


    }


}
