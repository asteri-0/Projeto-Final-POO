package persistencia;

import acervo.ItensDoAcervo;
import gerencia.Cliente;
import gerencia.Emprestimo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GerenciadorCSV {

    public static void salvarAcervo(List<ItensDoAcervo> acervo) throws IOException {
        Path caminho = Path.of("dados/acervo.csv");

        try {
            Files.createDirectories(caminho.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
                writer.write("TIPO;TITULO;ANO_PUBLICACAO;GENERO;STATUS;EXTRAS");
                writer.newLine();

                for (ItensDoAcervo item : acervo) {
                    writer.write(item.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e ) {
            System.err.println("Erro ao salvar o acervo: " + e.getMessage());
        }
    }

    public static void salvarCliente(List<Cliente> clientes) {
        Path caminho = Path.of("dados/clientes.csv");

        try {
            Files.createDirectories(caminho.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
                writer.write("NOME;CPF;TELEFONE;DATA_NASCIMENTO;STATUS;N_EMPRESTIMOS");
                writer.newLine();

                for (Cliente cliente : clientes) {
                    writer.write(cliente.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar o acervo: " + e.getMessage());

        }
    }

    public static void salvarEmprestimo(List<Emprestimo> emprestimos) {
        Path caminho = Path.of("dados/emprestimos.csv");

        try {
            Files.createDirectories(caminho.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
                writer.write("DATA_EMPRESTIMO;DEVOLUCAO_PREVISTA;COD_ITEM;CPF_CLIENTE");
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar o acervo: " + e.getMessage());

        }
    }
}
