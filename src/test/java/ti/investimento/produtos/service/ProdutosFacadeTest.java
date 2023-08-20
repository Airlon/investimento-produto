package ti.investimento.produtos.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ti.investimento.produtos.Produtos;
import ti.investimento.produtos.dto.ProdutosDto;
import ti.investimento.produtos.repository.ProdutosRepository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProdutosFacadeTest {
    @Mock
    private ProdutosRepository repository;

    @InjectMocks
    private ProdutosFacade produtosFacade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testServiceCriar() {
        ProdutosDto produtoDto = new ProdutosDto();
        produtoDto.setNome("Cadeira");
        produtoDto.setDescricao("Cadeira ergonômica para escritório");
        produtoDto.setPreco(new BigDecimal("149.50"));
        produtoDto.setQuantidade(20);
        produtoDto.setDtCadastro(Timestamp.valueOf("2023-08-18 16:15:00"));

        Produtos produto = new Produtos();
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setPreco(produtoDto.getPreco());
        produto.setQuantidade(produtoDto.getQuantidade());
        produto.setDtCadastro(produtoDto.getDtCadastro());

        when(repository.save(Mockito.any(Produtos.class))).thenReturn(produto);

        ProdutosDto result = produtosFacade.criar(produtoDto);

        assertEquals(produtoDto, result);
    }

    @Test
    public void testServiceConverter() {
        Produtos produto = new Produtos();
        produto.setId(1L);
        produto.setNome("Cadeira");
        produto.setDescricao("Cadeira ergonômica para escritório");
        produto.setPreco(new BigDecimal("149.50"));
        produto.setQuantidade(20);
        produto.setDtCadastro(Timestamp.valueOf("2023-08-18 16:15:00"));

        ProdutosDto result = produtosFacade.converter(produto);

        assertEquals(produto.getId(), result.getId());
        assertEquals(produto.getNome(), result.getNome());
        assertEquals(produto.getDescricao(), result.getDescricao());
        assertEquals(produto.getPreco(), result.getPreco());
        assertEquals(produto.getQuantidade(), result.getQuantidade());
        assertEquals(produto.getDtCadastro(), result.getDtCadastro());
    }

    @Test
    public void testServiceGetAll() {
        List<Produtos> produtosList = new ArrayList<>();
        produtosList.add(new Produtos(1L, "Cadeira", "Cadeira ergonômica para escritório", new BigDecimal("149.50"), 20, Timestamp.valueOf("2023-08-18 16:15:00")));
        produtosList.add(new Produtos(2L, "Mesa", "Mesa de madeira", new BigDecimal("199.99"), 10, Timestamp.valueOf("2023-08-19 10:00:00")));

        when(repository.findAll()).thenReturn(produtosList);

        List<ProdutosDto> result = produtosFacade.getAll();

        assertEquals(produtosList.size(), result.size());

        for (int i = 0; i < produtosList.size(); i++) {
            ProdutosDto expectedDto = produtosFacade.converter(produtosList.get(i));
            ProdutosDto actualDto = result.get(i);
            assertEquals(expectedDto, actualDto);
        }
    }
}
