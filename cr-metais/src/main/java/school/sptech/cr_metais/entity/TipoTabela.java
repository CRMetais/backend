package school.sptech.cr_metais.entity;

public enum TipoTabela {
    C("Compra"),
    V("Venda");

    private final String descricao;

    TipoTabela(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}