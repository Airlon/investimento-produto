package ti.investimento.produtos.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class ProdutosDto implements Serializable {

    private long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private int quantidade;
    private Timestamp dtCadastro;

    public ProdutosDto() {
    }

    public ProdutosDto(long id, String nome, String descricao, BigDecimal preco, int quantidade, Timestamp dtCadastro) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.dtCadastro = dtCadastro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Timestamp getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Timestamp dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutosDto that = (ProdutosDto) o;
        return id == that.id &&
                quantidade == that.quantidade &&
                Objects.equals(nome, that.nome) &&
                Objects.equals(descricao, that.descricao) &&
                Objects.equals(preco, that.preco) &&
                Objects.equals(dtCadastro, that.dtCadastro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, descricao, preco, quantidade, dtCadastro);
    }
}
