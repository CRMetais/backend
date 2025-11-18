package school.sptech.cr_metais.service;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import school.sptech.cr_metais.entity.Venda;
import school.sptech.cr_metais.exception.EntidadeNaoEncontradaException;
import school.sptech.cr_metais.repository.VendaRepository;

import java.util.List;

@Service
public class VendaService {

    private final VendaRepository vRepository;

    public VendaService(VendaRepository vRepository) {
        this.vRepository = vRepository;
    }

    public Venda cadastrar(@Valid Venda v) {
        vRepository.save(v);
        return v;
    }

    public List<Venda> listar() {
        return vRepository.findAll();
    }

    public Venda buscarPorId(Integer id) {
        Venda venda  = vRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Venda não encontrado"));

        return venda;
    }

    public void deletar(Integer id) {
        if (!vRepository.existsById(id)) {
            throw new EntidadeNaoEncontradaException("Venda não encontrada");
        }
        vRepository.deleteById(id);
    }

}
