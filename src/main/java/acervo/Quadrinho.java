package acervo;

import enuns.CategoriaGenero;
import enuns.ItemStatus;

public class Quadrinho extends ItensDoAcervo {
    private String isbn;
    private int numeroDePaginas;
    private String editora;

    public Quadrinho (String titulo, String codigoDeRegistrio, int anoDePublicacao, CategoriaGenero generoCategoria,
                        ItemStatus  statusItem, String isbn, int numeroDePaginas, String editora) {
        super(titulo, codigoDeRegistrio, anoDePublicacao, generoCategoria, statusItem);
        this.isbn = isbn;
        this.numeroDePaginas = numeroDePaginas;
        this.editora = editora;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t\t Editora: " + editora +
                "\n\t\t ISBN: " + isbn +
                "\n\t\t  N° de páginas: " + numeroDePaginas;
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ";" + isbn + ";" + numeroDePaginas + ";" + editora;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getEditora() {
        return editora;
    }

    @Override
    public String getTitulo() {
        return super.getTitulo();
    }

    @Override
    public String getCodigoDeRegistro() {
        return super.getCodigoDeRegistro();
    }

    @Override
    public int getAnoDePublicacao() {
        return super.getAnoDePublicacao();
    }

    @Override
    public CategoriaGenero getGeneroCategoria() {
        return super.getGeneroCategoria();
    }

    @Override
    public ItemStatus getStatusItem() {
        return super.getStatusItem();
    }

}
