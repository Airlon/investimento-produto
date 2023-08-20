package ti.investimento.produtos.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ti.investimento.produtos.constantes.RabbitMQConstantes;
import ti.investimento.produtos.dto.ProdutosDto;
import ti.investimento.produtos.service.ProdutosFacade;
import ti.investimento.produtos.service.RabbitMQService;

import java.util.List;

@RestController
@RequestMapping(value =  "/produtos", produces =  MediaType.APPLICATION_JSON_VALUE)
public class ProdutosApi {

    @Autowired
    private ProdutosFacade produtosFacade;

    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping
    @ResponseBody
    public List<ProdutosDto> getAll() {
        return produtosFacade.getAll();
    }
    @PostMapping
    @ResponseBody
    public ProdutosDto criar(@RequestBody ProdutosDto produtosDto) {
        this.rabbitMQService.enviaMensagem(RabbitMQConstantes.FILA_PRODUTOS, produtosDto);
        return produtosFacade.criar(produtosDto);
    }

}
