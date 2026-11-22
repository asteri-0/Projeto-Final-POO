package execucao;

import enuns.*;
import acervo.*;
import gerencia.*;

import java.time.LocalDate;

public class Run {

    public static void main(String[] args) {

        Biblioteca biblis = new Biblioteca();

        biblis.registrarEmprestimo(cliente1, livro1);
        biblis.mostrarEmprestimos();

        biblis.salvarDados();


    }


}
