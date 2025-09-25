package eud.sm.controller;


import eud.sm.app.springai.service4.AiChatService;
import eud.sm.app.springai.service4.ETLService;
import eud.sm.app.springai.service5.BoomBarrierService;
import eud.sm.app.springai.service5.HeatingSystemService;
import eud.sm.app.springai.service5.RecommendMovieService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping("/ai5")
@Slf4j
@RequiredArgsConstructor
public class Ai5Controller {

  final private HeatingSystemService heatingSystemService;
  final private RecommendMovieService recommendMovieService;
  final private BoomBarrierService boomBarrierService;


  // ##### 요청 매핑 메소드 #####
  @RequestMapping(value = "/heating-system-tools")
  public String heatingSystemTools(@RequestParam("question") String question) {
    String answer = heatingSystemService.chat(question);
    return answer;
  }
  @RequestMapping( value = "/recommend-movie-tools")
  public String recommendMovieTools(@RequestParam("question") String question) {
    String answer = recommendMovieService.chat(question);
    return answer;
  }
  @RequestMapping( value = "/boom-barrier-tools")
  public String boomBarrierTools(
          @RequestParam("attach") MultipartFile attach) throws IOException {
    String answer = boomBarrierService.chat(
            attach.getContentType(), attach.getBytes());
    return answer;
  }

}
