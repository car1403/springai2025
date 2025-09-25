package eud.sm;

import eud.sm.app.springai.service1.AiServiceByChatClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class Ai1ApplicationTests {

    @Autowired
    AiServiceByChatClient  aiServiceByChatClient;

    @Test
    void contextLoads() {
       Flux<String> fluxString = aiServiceByChatClient.generateStreamText("안녕");
       String result = fluxString.collectList().block().stream().collect(Collectors.joining());
       log.info(result);
    }
}
