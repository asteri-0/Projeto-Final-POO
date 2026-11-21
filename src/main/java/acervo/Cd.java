package acervo;

import enuns.CategoriaGenero;
import enuns.ItemStatus;

public class Cd extends ItensDoAcervo {


    public Cd (String titulo, String codigoDeRegistro, int anoDePublicacao,
               CategoriaGenero generoCategoria, ItemStatus statusItem) {
        super(titulo, codigoDeRegistro, anoDePublicacao, generoCategoria, statusItem);

    }

    @Override
    public String toString() {
        return super.toString() ;
    }

    @Override
    public String toCSV() {
        return super.toCSV();
    }

    @Override
    public ItemStatus getStatusItem() {
        return super.getStatusItem();
    }

    @Override
    public CategoriaGenero getGeneroCategoria() {
        return super.getGeneroCategoria();
    }

    @Override
    public int getAnoDePublicacao() {
        return super.getAnoDePublicacao();
    }

    @Override
    public String getCodigoDeRegistro() {
        return super.getCodigoDeRegistro();
    }

    @Override
    public String getTitulo() {
        return super.getTitulo();
    }


}
