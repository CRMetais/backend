package school.sptech.cr_metais.entity;

public enum TipoConta {
    C("Corrente"),
    P("Poupança");

    private final String descricao;

    TipoConta(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
