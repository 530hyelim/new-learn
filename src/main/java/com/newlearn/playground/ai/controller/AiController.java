package com.newlearn.playground.ai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlearn.playground.ai.model.service.AiService;
import com.newlearn.playground.ai.model.vo.AI;
import com.newlearn.playground.ai.model.vo.AiChatSession;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ai")
@RequiredArgsConstructor
@PropertySource("classpath:driver.properties")
public class AiController {
	private final AiService aiService;
	private final ServletContext application;
	
	@Value("${dataSource.openaiApiKey}")
	private String myApiKey;
	
	// Configures using one of:
    // - The `OPENAI_API_KEY` environment variable
    // - The `OPENAI_BASE_URL` and `AZURE_OPENAI_KEY` environment variables
	private OpenAIClient client;
	
	@PostConstruct
	public void init() {
		client = OpenAIOkHttpClient.builder()
				// Configures using the `OPENAI_API_KEY`, `OPENAI_ORG_ID`, `OPENAI_PROJECT_ID`, `OPENAI_WEBHOOK_SECRET` and `OPENAI_BASE_URL` environment variables
				.fromEnv()
				.apiKey(myApiKey)
				.build();
		
		List<AI> aiList = aiService.getAiList();
		application.setAttribute("aiList", aiList);
	}
	
	private final Map<String, List<Map<String, String>>> conversationHistory = new ConcurrentHashMap<>();
	
	@GetMapping("/main")
	public String showMain(
			HttpSession session
			) {
		int userNo = 2;
		
		// DB의 ai_chat_sessions 테이블에서 해당 유저의 chat_session 정보들을 불러온다.
		List<AiChatSession> aiChatSessionsList = aiService.getAiChatSessionsList(userNo);
		
		session.setAttribute("aiChatSessionsList", aiChatSessionsList);
		
		return "ai/aiMain";
	}
	
	@GetMapping("/getPrompt")
	@ResponseBody
	public Map<String, String> getPrompt(
			String prompt,
			@RequestParam(required = false, defaultValue = "default") String sessionId,
			HttpServletResponse res
			) {
		System.out.println("prompt: " + prompt);
		
		List<AI> aiList = (List<AI>)application.getAttribute("aiList");
		
		// Get or create conversation history for this session
		List<Map<String, String>> messageHistory = conversationHistory.computeIfAbsent(
		    sessionId, k -> new ArrayList<>()
		);
		
		Map<String, String> developerMessage = new HashMap<>();
		developerMessage.put("role", "developer");
		developerMessage.put("content", "Always respond in HMTL format, but inside of <div></div>. For example, <div>your respond</div>.");
//		developerMessage.put("content", "");
		messageHistory.add(developerMessage);
		
		// Add the new user message to history
		Map<String, String> userMessage = new HashMap<>();
		userMessage.put("role", "user");
		userMessage.put("content", prompt);
		messageHistory.add(userMessage);
		
		ChatCompletionCreateParams.Builder paramsBuilder = ChatCompletionCreateParams.builder().model(ChatModel.GPT_4_1_NANO);
		
		// Add all messages from history
		for (Map<String, String> msg : messageHistory) {
			if ("developer".equals(msg.get("role"))) {
				paramsBuilder.addDeveloperMessage(msg.get("content"));
			} else if ("user".equals(msg.get("role"))) {
				paramsBuilder.addUserMessage(msg.get("content"));
			} else if ("assistant".equals(msg.get("role"))) {
				paramsBuilder.addAssistantMessage(msg.get("content"));
			}
		}
		
		ChatCompletionCreateParams params = paramsBuilder.build();
		
		StringBuilder sb = new StringBuilder();
		
		ChatCompletion chatCompletion = client.chat().completions().create(params);
		
		System.out.println("chatCompletion: " + chatCompletion);
		
		chatCompletion.choices().get(0).message().content().ifPresent(v -> sb.append(v));
		
		// Add the AI's response to conversation history
		Map<String, String> assistantMessage = new HashMap<>();
		assistantMessage.put("role", "assistant");
		assistantMessage.put("content", sb.toString());
		messageHistory.add(assistantMessage);
		
		System.out.println("messageHistory: " + messageHistory);
		System.out.println("conversationHistory: " + conversationHistory);
		
		int userNo = 2;
		int modelNo = 1;
		long tokensUsed = chatCompletion.usage()
			    .map(v -> v.totalTokens())
			    .orElse(0L);
		
		System.out.println("tokenUsed: " + tokensUsed);
		
		// 비즈니스 로직
		// 1. 해당 유저가 해당 모델을 사용한적이 있는지 DB에서 확인한다.
		// 2. 사용한적이 없으면 ai_usage 테이블 insert, 없으면 update.
		// 3.  
		
		Map<String, Object> dbParamMap = new HashMap<>();
		dbParamMap.put("userNo", userNo);
		dbParamMap.put("modelNo", modelNo);
		
		int usedBefore = aiService.checkUsedBefore(dbParamMap);
		
		System.out.println("usedBefore: " + usedBefore);
		
		return assistantMessage;
	}
}
