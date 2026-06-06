package school.sptech.cr_metais.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.dto.Historico.HistoricoCompraDto;
import school.sptech.cr_metais.dto.Historico.HistoricoVendaDto;
import school.sptech.cr_metais.dto.Historico.TransacaoHistoricoDto;
import school.sptech.cr_metais.repository.HistoricoRepository;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

@Service
public class HistoricoService {

    @Autowired
    private HistoricoRepository repository;

    @Autowired
    private HistoricoEtlService historicoEtlService;

    public Page<?> listarPorTipo(String tipo, int pagina, int tamanho) {

        Pageable pageable = PageRequest.of(pagina, tamanho);

        if (tipo.equalsIgnoreCase("COMPRA")) {

            return repository.listarCompras(pageable)
                    .map(objects -> new HistoricoCompraDto(
                            ((Number) objects[0]).intValue(),
                            ((java.sql.Date) objects[1]).toLocalDate(),
                            (String) objects[2],
                            (String) objects[3],
                            ((Number) objects[4]).doubleValue(),
                            ((Number) objects[5]).doubleValue(),
                            ((Number) objects[6]).doubleValue(),
                            objects[7] != null ? ((Number) objects[7]).doubleValue() : null,
                            (String) objects[8]
                    ));
        }

        if (tipo.equalsIgnoreCase("VENDA")) {

            return repository.listarVendas(pageable)
                    .map(obj -> new HistoricoVendaDto(
                            ((Number) obj[0]).intValue(),
                            ((java.sql.Date) obj[1]).toLocalDate(),
                            (String) obj[2],
                            (String) obj[3],
                            ((Number) obj[4]).doubleValue(),
                            ((Number) obj[5]).doubleValue(),
                            ((Number) obj[6]).doubleValue(),
                            obj[7] != null ? ((Number) obj[7]).doubleValue() : null,
                            (String) obj[8]
                    ));
        }

        throw new IllegalArgumentException("Tipo inválido");
    }

    public String gerarXmlLambda(String tipo, String dataInicio, String dataFim) {

    try {
        String lambdaUrl = "https://SUA-LAMBDA-URL";

        String urlFinal = lambdaUrl
                + "?tipo=" + tipo
                + "&dataInicio=" + dataInicio
                + "&dataFim=" + dataFim;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlFinal))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        return response.body();

    } catch (Exception e) {
        throw new RuntimeException("Erro ao chamar Lambda", e);
    }


        // =========================
        // 💻 LOCAL
        // =========================

//        try {
//
//            List<TransacaoHistoricoDto> dados =
//                    historicoEtlService.buscarTudo(
//                            LocalDate.parse(dataInicio),
//                            LocalDate.parse(dataFim),
//                            tipo
//                    );
//
//            StringBuilder xml = new StringBuilder();
//
//            xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
//            xml.append("<historico>\n");
//
//            for (var item : dados) {
//                xml.append("  <registro>\n");
//                xml.append("    <id>").append(item.getId()).append("</id>\n");
//                xml.append("    <data>").append(item.getData()).append("</data>\n");
//                xml.append("    <produto>").append(item.getProduto()).append("</produto>\n");
//                xml.append("    <parceiro>").append(item.getParceiro()).append("</parceiro>\n");
//                xml.append("    <peso>").append(item.getPeso()).append("</peso>\n");
//                xml.append("    <preco>").append(item.getPreco()).append("</preco>\n");
//                xml.append("    <total>").append(item.getTotal()).append("</total>\n");
//                xml.append("  </registro>\n");
//            }
//
//            xml.append("</historico>");
//
//            Files.writeString(
//                    Path.of("historico.xml"),
//                    xml.toString()
//            );
//
//            return xml.toString();
//
//        } catch (Exception e) {
//            throw new RuntimeException("Erro ao gerar XML local", e);
//        }
    }
}