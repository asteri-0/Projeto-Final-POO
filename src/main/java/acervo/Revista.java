package acervo;

import enuns.CategoriaGenero;
import enuns.ItemStatus;

public class Revista extends ItensDoAcervo {
    private int numeroDePaginas;
    private String editora;

    public Revista (String localizacao, String codigoDeRegistro, String titulo,
                 int anoDePublicacao, CategoriaGenero generoCategoria, ItemStatus statusItem,
                    int numeroDePaginas, String editora) {
        super(localizacao,codigoDeRegistro, titulo, anoDePublicacao, generoCategoria, statusItem);
        this.numeroDePaginas = numeroDePaginas;
        this.editora = editora;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n\t\t Editora: " + editora +
                "\n\t\t N° de Páginas: " + numeroDePaginas;
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
    public String getLocalizacao() {
        return super.getLocalizacao();
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
