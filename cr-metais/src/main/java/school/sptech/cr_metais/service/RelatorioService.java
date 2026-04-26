package school.sptech.cr_metais.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.repository.RelatorioRepository;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository repository;

    public String gerarHtml() {

        var compras = repository.buscarComprasRaw();
        var vendas  = repository.buscarVendasRaw();

        Set<String> parceirosEntrega  = new HashSet<>();
        Set<String> parceirosRetirada = new HashSet<>();

        double qtdMaterialEntrega  = 0;
        double qtdMaterialRetirada = 0;
        double pesoLataSolta       = 0;
        double pagamentoNotas      = 0;

        for (var item : compras) {
            parceirosEntrega.add(item.getParceiro());
            qtdMaterialEntrega += item.getPeso();
            pagamentoNotas     += item.getTotal();

            if ("Lata".equalsIgnoreCase(item.getProduto())) {
                pesoLataSolta += item.getPeso();
            }
        }

        for (var item : vendas) {
            parceirosRetirada.add(item.getParceiro());
            qtdMaterialRetirada += item.getPeso();
        }

        int clientesEntrega  = parceirosEntrega.size();
        int clientesRetirada = parceirosRetirada.size();
        int totalClientes    = clientesEntrega + clientesRetirada;
        double pesoTotal     = qtdMaterialEntrega + qtdMaterialRetirada;

        return String.format("""
                <html>
                <body style="font-family: Arial, sans-serif; padding: 20px;">
                    <h2>Relatório Diário - CR Metais</h2>
                    <table border="1" cellpadding="8" cellspacing="0"
                           style="border-collapse: collapse; width: 100%%;">
                        <tr><th>Campo</th><th>Valor</th></tr>
                        <tr><td>Clientes entrega</td><td>%d</td></tr>
                        <tr><td>Clientes retirada</td><td>%d</td></tr>
                        <tr><td><b>Total clientes</b></td><td><b>%d</b></td></tr>
                        <tr><td>Material entrega</td><td>%.2f kg</td></tr>
                        <tr><td>Material retirada</td><td>%.2f kg</td></tr>
                        <tr><td><b>Peso total</b></td><td><b>%.2f kg</b></td></tr>
                        <tr><td>Lata solta</td><td>%.2f kg</td></tr>
                        <tr><td><b>Pagamento</b></td><td><b>R$ %.2f</b></td></tr>
                    </table>
                </body>
                </html>
                """,
                clientesEntrega,
                clientesRetirada,
                totalClientes,
                qtdMaterialEntrega,
                qtdMaterialRetirada,
                pesoTotal,
                pesoLataSolta,
                pagamentoNotas
        );
    }
}