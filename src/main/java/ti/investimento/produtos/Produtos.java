package ti.investimento.produtos;


import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
public class Produtos {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(columnDefinition = "serial")
        private long id;
        private String nome;
        private String descricao;
        private BigDecimal preco;
        private int quantidade;
        private Timestamp dtCadastro;

        public Produtos() {
        }

        public Produtos(long id, String nome, String descricao, BigDecimal preco, int quantidade, Timestamp dtCadastro) {
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

}