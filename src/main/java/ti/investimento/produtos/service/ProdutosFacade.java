package ti.investimento.produtos.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ti.investimento.produtos.Produtos;
import ti.investimento.produtos.dto.ProdutosDto;
import ti.investimento.produtos.repository.ProdutosRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutosFacade {

    @Autowired
    private ProdutosRepository repository;

    public ProdutosDto criar(ProdutosDto produtosDto) {
        Produtos produtos = new Produtos();
        produtos.setNome(produtosDto.getNome());
        produtos.setDescricao(produtosDto.getDescricao());
        produtos.setPreco(produtosDto.getPreco());
        produtos.setQuantidade(produtosDto.getQuantidade());
        produtos.setDtCadastro(produtosDto.getDtCadastro());
        repository.save(produtos);
        produtosDto.setId(produtos.getId());
        return produtosDto;
    }

    public ProdutosDto converter (Produtos produtos) {
        ProdutosDto result = new ProdutosDto();
        result.setId(produtos.getId());
        result.setNome(produtos.getNome());
        result.setDescricao(produtos.getDescricao());
        result.setPreco(produtos.getPreco());
        result.setQuantidade(produtos.getQuantidade());
        result.setDtCadastro(produtos.getDtCadastro());
        return result;
    }

    public List<ProdutosDto> getAll () {
        return repository
                .findAll()
                .stream()
                .map(this::converter).collect(Collectors.toList());
    }

}
