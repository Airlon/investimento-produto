package ti.investimento.produtos.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import ti.investimento.produtos.constantes.RabbitMQConstantes;
import ti.investimento.produtos.dto.ProdutosDto;
import ti.investimento.produtos.service.ProdutosFacade;
import ti.investimento.produtos.service.RabbitMQService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProdutosApiTest {

    @Mock
    private ProdutosFacade produtosFacade;

    @InjectMocks
    private ProdutosApi produtosApi;

    @Mock
    private RabbitMQService rabbitMQService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAll() {
        List<ProdutosDto> expected = new ArrayList<>();
        expected.add(new ProdutosDto(1L, "Produto 1", "Descrição do Produto 1", new BigDecimal("99.99"), 10, new Timestamp(System.currentTimeMillis())));
        expected.add(new ProdutosDto(2L, "Produto 2", "Descrição do Produto 2", new BigDecimal("199.99"), 5, new Timestamp(System.currentTimeMillis())));

        when(produtosFacade.getAll()).thenReturn(expected);

        List<ProdutosDto> result = produtosApi.getAll();

        assertEquals(expected, result);
    }
    @Test
    public void testCriar() {
        ProdutosDto produtoDto = new ProdutosDto();
        produtoDto.setNome("Cadeira");
        produtoDto.setDescricao("Cadeira ergonômica para escritório");
        produtoDto.setPreco(new BigDecimal("149.50"));
        produtoDto.setQuantidade(20);
        produtoDto.setDtCadastro(Timestamp.valueOf("2023-08-18 16:15:00"));

        when(produtosFacade.criar(Mockito.any(ProdutosDto.class))).thenReturn(produtoDto);

        ProdutosDto result = produtosApi.criar(produtoDto);

        assertEquals(produtoDto, result);

        verify(rabbitMQService, times(1)).enviaMensagem(eq(RabbitMQConstantes.FILA_PRODUTOS), eq(produtoDto));
        verify(produtosFacade, times(1)).criar(eq(produtoDto));
    }
}

