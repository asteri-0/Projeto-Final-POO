package acervo;

import enuns.ItemStatus;
import enuns.CategoriaGenero;

public abstract class ItensDoAcervo {
    //atributos da classe pai
    private String localizacao;
    private String codigoDeRegistro;
    private String titulo;
    private int anoDePublicacao;
    private CategoriaGenero generoCategoria;
    private ItemStatus statusItem;

    //construtor
    public ItensDoAcervo (String titulo, String localizacao, String codigoDeRegistrio,
                          int anoDePublicacao, CategoriaGenero generoCategoria,
                          ItemStatus  statusItem) {
        this.anoDePublicacao = anoDePublicacao;
        this.codigoDeRegistro = codigoDeRegistrio;
        this.generoCategoria = generoCategoria;
        this.localizacao = localizacao;
        this.statusItem = statusItem;
        this.titulo = titulo;
    }

    public boolean estaDisponivel() {
        return getStatusItem() == ItemStatus.DISPONIVEL;
    }

    /*public ItemStatus checarDisponibilidade() {
        if (getStatusItem() == ItemStatus.DISPONIVEL) {
            return ItemStatus.DISPONIVEL;
        } else if (getStatusItem() == ItemStatus.EMPRESTADO)
            return ItemStatus.EMPRESTADO;
    }*/

    @Override
    public String toString() {
        return  "\t Título: " + titulo +
                "\n\t\t Ano de publicação: " + anoDePublicacao +
                "\n\t\t Gênero/Categoria: " + generoCategoria +
                "\n\t\t Localizacao:" + localizacao +
                "\n\t\t Código de Registro: " + codigoDeRegistro +
                "\n\t\t Status do  Item: " + statusItem;
    }

    /* Getters */

    public String getCodigoDeRegistro() {
        return codigoDeRegistro;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public CategoriaGenero getGeneroCategoria() {
        return generoCategoria;
    }

    public ItemStatus getStatusItem() {
        return statusItem;
    }

    public void setStatusItem(ItemStatus statusItem) {
        this.statusItem = statusItem;
    }
}
