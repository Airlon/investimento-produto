package ti.investimento;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("ti.investimento")
@EnableJpaRepositories(basePackages = "ti.investimento")
public class InvestimentoApplication {
    public static void main(String[] args) {
        SpringApplication.run(InvestimentoApplication.class, args);
    }
}
