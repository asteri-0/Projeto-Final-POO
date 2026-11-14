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

    @Override
    public CategoriaGenero getGeneroCategoria() {
        return super.getGeneroCategoria();
    }
}
