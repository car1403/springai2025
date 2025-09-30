package eud.sm;

import eud.sm.app.springai.service1.AiService;
import eud.sm.app.springai.service1.AiServiceByChatClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class Test1ApplicationTests {

    @Autowired
    AiService  aiService;

    @Test
    void contextLoads() {
        Flux<String> fluxString = aiService.generateStreamText("천안맛집알려줘");
        fluxString.collectList().block().stream().forEach(s -> {log.info(s);});

       // log.info(result);

        //String txt = aiService.generateText("천안맛집알려줘");
        //log.info(txt);
    }
}
