package ti.investimento.produtos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ti.investimento.produtos.Produtos;

@Repository
public interface ProdutosRepository  extends JpaRepository<Produtos, Long> {
}
