package ti.investimento.produtos.connections;


import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;
import ti.investimento.produtos.constantes.RabbitMQConstantes;

import javax.annotation.PostConstruct;

@Component
public class RabbitMQConection {

    private static final String NOME_EXCHANGE = "amq.direct";

    private AmqpAdmin amqpAdmin;
    public RabbitMQConection(AmqpAdmin amqpAdmin) {
        this.amqpAdmin = amqpAdmin;
    }

    private Queue fila(String nomeFila) {
        return new Queue(nomeFila, true, false, false);
    }

    private DirectExchange trocaDireta() {
        return new DirectExchange(NOME_EXCHANGE);

    }

    private Binding relacionamento(Queue fila, DirectExchange troca){
        return new Binding(fila.getName(), Binding.DestinationType.QUEUE, troca.getName(), fila.getName(), null);
    }

    @PostConstruct
    private void adiciona(){
        Queue filaProduto = this.fila(RabbitMQConstantes.FILA_PRODUTOS);

        DirectExchange troca = this.trocaDireta();

        Binding ligacaoProduto = this.relacionamento(filaProduto, troca);

        this.amqpAdmin.declareQueue(filaProduto);

        this.amqpAdmin.declareExchange(troca);

        this.amqpAdmin.declareBinding(ligacaoProduto);

    }
}

