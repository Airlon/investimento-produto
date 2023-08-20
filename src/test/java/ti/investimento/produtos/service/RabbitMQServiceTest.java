package ti.investimento.produtos.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.verify;

public class RabbitMQServiceTest {

    private RabbitMQService rabbitMQService;

    @Mock
    private RabbitTemplate rabbitTemplate;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        rabbitMQService = new RabbitMQService();
        rabbitMQService.rabbitTemplate = rabbitTemplate;
    }

    @Test
    public void testEnviaMensagem() {
        String nomeFila = "filaTeste";
        Object mensagem = "Mensagem de teste";

        rabbitMQService.enviaMensagem(nomeFila, mensagem);

        verify(rabbitTemplate).convertAndSend(nomeFila, mensagem);
    }
}
