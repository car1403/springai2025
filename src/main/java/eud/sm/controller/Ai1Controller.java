package eud.sm.controller;


import eud.sm.app.springai.service1.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ai1")
@Slf4j
@RequiredArgsConstructor
public class Ai1Controller {

// final private AiService aiService;
  final private AiServiceByChatClient aiService;
  final private AiServiceChainOfThoughtPrompt aiServicetp;
  final private AiServiceFewShotPrompt aiServicefsp;
  final private AiServiceMultiMessages aiServicemm;
  final private AiServicePromptTemplate aiServicept;
  final private AiServiceRoleAssignmentPrompt aiServicersp;
  final private AiServiceSelfConsistency aiServicesc;
  final private AiServiceStepBackPrompt aiServicesb;
  final private AiServiceZeroShotPrompt aiServicezsp;


  // ##### 요청 매핑 메소드 #####
  @RequestMapping(value = "/chat-model")
  public String chatModel(@RequestParam("question") String question) {
    String answerText = aiService.generateText(question);
    return answerText;
  }
  @RequestMapping(value = "/chat-model-stream")
  public Flux<String> chatModelStream(@RequestParam("question") String question) {
    Flux<String> answerStreamText = aiService.generateStreamText(question);
    return answerStreamText;
  }
  @RequestMapping(value = "/chat-of-thought")
  public Flux<String> chainOfThought(@RequestParam("question") String question) {
    Flux<String> answer = aiServicetp.chainOfThought(question);
    return answer;
  }
  @RequestMapping(value = "/few-shot-prompt")
  public String fewShotPrompt(@RequestParam("question") String question) {
    String answer = aiServicefsp.fewShotPrompt(question);
    return answer;
  }
  @RequestMapping(value = "/multi-messages")
  public String multiMessages(
          @RequestParam("question") String question, HttpSession session) {
    List<Message> chatMemory = (List<Message>) session.getAttribute("chatMemory");
    if(chatMemory == null) {
      chatMemory = new ArrayList<Message>();
      session.setAttribute("chatMemory", chatMemory);
    }
    String answer = aiServicemm.multiMessages(question, chatMemory);
    return answer;
  }
  @RequestMapping(value = "/prompt-template")
  public Flux<String> promptTemplate(      @RequestParam("statement") String statement,
                                           @RequestParam("language") String language) {
    Flux<String> response = aiServicept.promptTemplate1(statement, language);
    return response;
  }
  @RequestMapping(value = "/role-assignment")
  public Flux<String> roleAssignment(@RequestParam("requirements") String requirements) {
    Flux<String> travelSuggestions = aiServicersp.roleAssignment(requirements);
    return travelSuggestions;
  }
  @RequestMapping(value = "/self-consistency")
  public String selfConsistency(@RequestParam("content") String content) {
    String answer = aiServicesc.selfConsistency(content);
    return answer;
  }
  @PostMapping(value = "/step-back-prompt")
  public String stepBackPrompt(@RequestParam("question") String question) throws Exception {
    String answer = aiServicesb.stepBackPrompt(question);
    return answer;
  }

  @PostMapping(value = "/zero-shot-prompt")
  public String zeroShotPrompt(@RequestParam("review") String review) {
    String reviewSentiment = aiServicezsp.zeroShotPrompt(review);
    return reviewSentiment;
  }
}
