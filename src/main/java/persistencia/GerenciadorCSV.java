package persistencia;

import acervo.*;
import gerencia.*;
import gerencia.Emprestimo.*;
import enuns.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorCSV {

    public static void salvarAcervo(List<ItensDoAcervo> acervo) {
        Path caminho = Path.of("dados/acervo.csv");

        try {
            Files.createDirectories(caminho.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
                writer.write("TIPO;TITULO;COD_ITEM;ANO_PUBLICACAO;GENERO;STATUS;EXTRAS");
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
            System.err.println("Erro ao salvar o cliente: " + e.getMessage());

        }
    }

    public static void salvarEmprestimo(List<Emprestimo> emprestimos) {
        Path caminho = Path.of("dados/emprestimos.csv");

        try {
            Files.createDirectories(caminho.getParent());

            try (BufferedWriter writer = Files.newBufferedWriter(caminho, StandardCharsets.UTF_8)) {
                writer.write("DATA_EMPRESTIMO;DEVOLUCAO_PREVISTA;COD_ITEM;CPF_CLIENTE");
                writer.newLine();

                for (Emprestimo emprestimo : emprestimos) {
                    writer.write(emprestimo.toCSV());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao salvar o emprestimo: " + e.getMessage());

        }
    }

    public static List<ItensDoAcervo> carregarAcervo() {
        List<ItensDoAcervo> acervo = new ArrayList<>();
        Path caminho = Path.of("dados/acervo.csv");

        if (!Files.exists(caminho)) {
            return acervo;
        }

        try (BufferedReader reader = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            reader.readLine(); // Descarta a linha 1 (cabeçalho)

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] partes = linha.split(";");

                // Colunas comuns a todos os itens (índices 0 a 5)
                String tipo = partes[0];
                String titulo = partes[1];
                String codigo = partes[2];
                int anoPublicacao = Integer.parseInt(partes[3]);
                CategoriaGenero genero = CategoriaGenero.valueOf(partes[4]);
                ItemStatus status = ItemStatus.valueOf(partes[5]);

                // Reconstrução específica conforme o tipo (índices 6 em diante)
                switch (tipo) {
                    case "LIVRO" -> acervo.add(new Livro(
                            titulo, codigo, anoPublicacao, genero, status,
                            partes[6], Integer.parseInt(partes[7]), partes[8]
                    ));
                    case "QUADRINHO" -> acervo.add(new Quadrinho(
                            titulo, codigo, anoPublicacao, genero, status,
                            partes[6], Integer.parseInt(partes[7]), partes[8]
                    ));
                    case "REVISTA" -> acervo.add(new Revista(
                            titulo, codigo, anoPublicacao, genero, status,
                            Integer.parseInt(partes[6]), partes[7]
                    ));
                    case "CD" -> acervo.add(new Cd(
                            titulo, codigo, anoPublicacao, genero, status
                    ));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o acervo: " + e.getMessage());
        }

        return acervo;
    }

    public void salvarDados() {
        GerenciadorCSV.salvarAcervo(this.acervo);
        GerenciadorCSV.salvarCliente(this.clientes);
        GerenciadorCSV.salvarEmprestimo(this.emprestimos);
    }

    public static List<Cliente> carregarCliente() {
        List<Cliente> clientes = new ArrayList<>();
        Path caminho = Path.of("dados/clientes.csv");

        if (!Files.exists(caminho)) {
            return clientes;
        }

        try (BufferedReader reader = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            reader.readLine(); // Descarta a linha 1 (cabeçalho)

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] partes = linha.split(";");

                String nome = partes[0];
                String cpf = partes[1];
                String telefone = partes[2];
                LocalDate dataNascimento = LocalDate.parse(partes[3]);
                ClienteStatus status = ClienteStatus.valueOf(partes[4]);
                int numeroEmprestimos = Integer.parseInt(partes[5]);

                clientes.add(new Cliente(nome, cpf, telefone, dataNascimento, status, numeroEmprestimos));
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os clientes: " + e.getMessage());
        }

        return clientes;
    }

    public static List<Emprestimo> carregarEmprestimo(List<ItensDoAcervo> acervo, List<Cliente> clientes) {
        List<Emprestimo> emprestimos = new ArrayList<>();
        Path caminho = Path.of("dados/emprestimos.csv");

        if (!Files.exists(caminho)) {
            return emprestimos;
        }

        try (BufferedReader reader = Files.newBufferedReader(caminho, StandardCharsets.UTF_8)) {
            reader.readLine(); // Descarta a linha 1 (cabeçalho)

            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.isBlank()) continue;

                String[] partes = linha.split(";");

                LocalDate dataEmprestimo = LocalDate.parse(partes[0]);
                LocalDate dataDevolucaoPrevista = LocalDate.parse(partes[1]);
                String codItem = partes[2];
                String cpfCliente = partes[3];

                ItensDoAcervo item = buscarItem(acervo, codItem);
                Cliente cliente = buscarCliente(clientes, cpfCliente);

                if (item != null && cliente != null) {
                    emprestimos.add(Emprestimo.reconstruir(dataEmprestimo, dataDevolucaoPrevista, item, cliente));
                } else {
                    System.err.println("Aviso: empréstimo ignorado por item ou cliente não encontrado (" + codItem + " / " + cpfCliente + ").");
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar os empréstimos: " + e.getMessage());
        }

        return emprestimos;
    }

    private static ItensDoAcervo buscarItem(List<ItensDoAcervo> acervo, String codigo) {
        for (ItensDoAcervo item : acervo) {
            if (item.getCodigoDeRegistro().equalsIgnoreCase(codigo)) {
                return item;
            }
        }
        return null;
    }

    private static Cliente buscarCliente(List<Cliente> clientes, String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null;
    }

}
