package execucao;

import enuns.*;
import acervo.*;
import gerencia.*;

import java.time.LocalDate;

public class Run {

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.carregarDados();

        ItensDoAcervo item = biblioteca.buscarItemPorCodigo("L01");
        Cliente cliente = biblioteca.buscarClientePorCpf("117.231.343-17");

        if (item != null && cliente != null) {
            biblioteca.registrarEmprestimo(cliente, item);
        }

        biblioteca.mostrarEmprestimos();
        biblioteca.salvarDados();


    }


}
