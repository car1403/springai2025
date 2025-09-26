package eud.sm;

import eud.sm.app.springai.service1.AiService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@SpringBootTest
class Test1ApplicationTests2 {

    @Autowired
    AiService  aiService;

    @Test
    void contextLoads() {

        Map<String, String> views = new ConcurrentHashMap<>();
        String str = "로그인";

        views.put("로그인", "/login");
        views.put("상품", "/items");

        String result = views.get(str);
        log.info(result);
    }
}
