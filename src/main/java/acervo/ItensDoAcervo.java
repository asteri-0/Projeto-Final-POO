package acervo;

public class ItensDoAcervo {
    //atributos da classe pai
    private String localizacao;
    private double codigoDeRegistrio;
    private String titulo;
    private int anoDePublicacao;
    private CategoriaGenero generoCategoria;
    private ItemStatus statusItem;

    public enum ItemStatus {
        DISPONIVEL,
        EMPRESTADO,
        PERDIDO,
        RESERVADO
    }

    public enum CategoriaGenero {
        ROCK,
        MPB,
        INDIE,
        JAZZ,
        POP,
        SAMBA,
        ELETRONICA,
        RAP,
        PAGODE,
        FICCAO_CIENTIFICA,
        LITERATURA_BRASILEIRA,
        LITERATURA_ESTRANGEIRA,
        ROMANCE,
        SOCIOLOGIA,
        FILOSOFIA,
        MATEMATICA,
        COMPUTACAO,
        ATUALIDADES,
        INFANTOJUVENIL,
        PORTUGUES,

    }

    //construtor
    public ItensDoAcervo (String titulo, String localizacao, double codigoDeRegistrio,
                          int anoDePublicacao, CategoriaGenero generoCategoria,
                          ItemStatus  statusItem) {
        this.anoDePublicacao = anoDePublicacao;
        this.codigoDeRegistrio = codigoDeRegistrio;
        this.generoCategoria = generoCategoria;
        this.localizacao = localizacao;
        this.statusItem = statusItem;
        this.titulo = titulo;
    }
}
