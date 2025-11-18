package school.sptech.cr_metais.mappers;


import org.springframework.stereotype.Component;
import school.sptech.cr_metais.dto.Compra.CompraCadastroDto;
import school.sptech.cr_metais.entity.Compra;

@Component
public class CompraMapper {

    public Compra toEntity(CompraCadastroDto dto) {

        if (dto == null) {

            return null;

        }
        Compra c = new Compra();

        c.setDataCompra(dto.getDataCompra());

        return c;
    }

}
