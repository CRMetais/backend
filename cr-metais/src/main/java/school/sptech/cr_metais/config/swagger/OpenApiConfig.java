package school.sptech.cr_metais.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CR Metais - API",
                version = "1.0.0",
                description = "API desenvolvida para o sistema de gestão de estoque e compra/venda da CR Metais",
                contact = @Contact(
                        name = "Amanda | Enrico | Felipe | Guilherme | Mário | Miguel",
                        url = "https://github.com/CRMetais/backend.git"
                ),
                license = @License(name = "UNLICENSED")
        ),
        tags = {
                @Tag(name = "Usuários", description = "Operações relacionadas aos usuários do sistema"),
                @Tag(name = "Fornecedores", description = "Operações relacionadas aos fornecedores do sistema"),
                @Tag(name = "Tabelas Preço", description = "Operações relacionadas com as tabelas de preço do sistema"),
                @Tag(name = "Endereço", description = "Operações relacionadas com os endereços cadastrados no sistema")
        }
)
@SecurityScheme(
        name = "Bearer", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT"
)

public class OpenApiConfig {

}
