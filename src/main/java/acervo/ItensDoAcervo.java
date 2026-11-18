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
