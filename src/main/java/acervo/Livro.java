package acervo;

import enuns.CategoriaGenero;
import enuns.ItemStatus;

public class Livro extends ItensDoAcervo{
    private String isbn;
    private int numeroDePaginas;

    public Livro(String localizacao, String codigoDeRegistro, String titulo,
                 int anoDePublicacao, CategoriaGenero generoCategoria, ItemStatus statusItem,
                 String isbn, int numeroDePaginas) {
        super(localizacao,codigoDeRegistro, titulo, anoDePublicacao, generoCategoria, statusItem);

    }
    public int getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public String getCodigoDeRegistro() {
        return super.getCodigoDeRegistro();
    }

    @Override
    public String getLocalizacao() {
        return super.getLocalizacao();
    }

    @Override
    public String getTitulo() {
        return super.getTitulo();
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
