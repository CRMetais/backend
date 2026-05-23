package school.sptech.cr_metais.dto.Dashboard;

import java.util.List;

public class DashboardResponseDto {

    private List<InfoFornecedorDto> infoFornecedor;
    private List<InfoProdutoDto>    infoProduto;
    private List<AnaliseVariacaoDto> analiseVariacao;

    public List<InfoFornecedorDto>  getInfoFornecedor()  { return infoFornecedor; }
    public List<InfoProdutoDto>     getInfoProduto()      { return infoProduto; }
    public List<AnaliseVariacaoDto> getAnaliseVariacao()  { return analiseVariacao; }

    public void setInfoFornecedor(List<InfoFornecedorDto> v)   { this.infoFornecedor  = v; }
    public void setInfoProduto(List<InfoProdutoDto> v)          { this.infoProduto      = v; }
    public void setAnaliseVariacao(List<AnaliseVariacaoDto> v)  { this.analiseVariacao  = v; }
}