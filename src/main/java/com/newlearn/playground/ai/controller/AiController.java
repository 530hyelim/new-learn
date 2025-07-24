package com.newlearn.playground.ai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.newlearn.playground.ai.model.service.AiService;
import com.newlearn.playground.ai.model.vo.Ai;
import com.newlearn.playground.ai.model.vo.AiChatHistory;
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
		
		List<Ai> aiList = aiService.getAiList();
		application.setAttribute("aiList", aiList);
	}
	
	private final Map<Integer, List<Map<String, String>>> conversationHistory = new ConcurrentHashMap<>();
	
	@GetMapping("/main")
	public String showMain(
			HttpSession session
			) {
		// DB의 ai_chat_sessions 테이블에서 해당 유저의 chat_session 정보들을 불러온다.
		int userNo = 21;
		List<AiChatSession> aiChatSessionsList = aiService.getAiChatSessionsList(userNo);
		
		session.setAttribute("aiChatSessionsList", aiChatSessionsList);
		
		return "ai/aiMain";
	}
	
	@GetMapping("/getPrompt")
	@ResponseBody
	public Map<String, String> getPrompt(
			String prompt,
			@RequestParam(required = false, defaultValue = "0") int sessionNo,
			RedirectAttributes ra
			) {
		System.out.println("prompt: " + prompt);
		System.out.println("sessionNo: " + sessionNo);
		
		int userNo = 21;
		int modelNo = 1;
		long tokensUsed = 0;
		
		Map<String, Object> aiParamMap = new HashMap<>();
		aiParamMap.put("userNo", userNo);
		aiParamMap.put("modelNo", modelNo);
		aiParamMap.put("sessionNo", sessionNo);
		aiParamMap.put("tokensUsed", tokensUsed);
		aiParamMap.put("title", "");
		
		// 1. 해당 유저가 해당 모델을 사용한적이 있는지 DB에서 확인한다.
		int usedBefore = aiService.checkUsedBefore(aiParamMap);
		System.out.println("usedBefore: " + usedBefore);
		
		// 2. 사용한적이 없으면 ai_usage 테이블 insert.		
		if (usedBefore == 0) {
			int insertAiUsageResult = aiService.insertAiUsage(aiParamMap);
			System.out.println("insertAiUsageResult: " + insertAiUsageResult);
		}
		
		// 업무로직
		// 1. 만약 sessionNo 가 0 이라면, 새로운 세션을 생성(ai_chat_session 테이블에 행 삽입).
		//    아니라면 해당 세션의 데이터를 업데이트.
		
		if (sessionNo == 0) {
			List<Map<String, String>> CreateTitleList = new ArrayList<>();
			
			Map<String, String> createTitleDeveloperMessage = new HashMap<>();
			createTitleDeveloperMessage.put("role", "developer");
			createTitleDeveloperMessage.put("content", "Create a title under 30 characters in the language of the user prompt.");
			CreateTitleList.add(createTitleDeveloperMessage);
			
			Map<String, String> createTitleUserMessage = new HashMap<>();
			createTitleUserMessage.put("role", "user");
			createTitleUserMessage.put("content", prompt);
			CreateTitleList.add(createTitleUserMessage);
			
			ChatCompletionCreateParams.Builder titleParamsBuilder = ChatCompletionCreateParams.builder().model(ChatModel.GPT_4_1_NANO);
			
			for (Map<String, String> msg : CreateTitleList) {
				if ("developer".equals(msg.get("role"))) {
					titleParamsBuilder.addDeveloperMessage(msg.get("content"));
				} else if ("user".equals(msg.get("role"))) {
					titleParamsBuilder.addUserMessage(msg.get("content"));
				}
			}
			
			ChatCompletionCreateParams titleParams = titleParamsBuilder.build();
			ChatCompletion titleCompletion = client.chat().completions().create(titleParams);
			StringBuilder sbTitle = new StringBuilder();
			titleCompletion.choices().get(0).message().content().ifPresent(v -> sbTitle.append(v));
			
			tokensUsed += titleCompletion.usage()
				    .map(v -> v.totalTokens())
				    .orElse(0L);
			
			aiParamMap.put("title", sbTitle.toString());
			
			int createChatSessionResult = aiService.createChatSession(aiParamMap);
			System.out.println("createChatSessionResult: " + createChatSessionResult);
		} else {
			int updateChatSessionResult = aiService.updateChatSession(aiParamMap);
			System.out.println("updateChatSessionResult: " + updateChatSessionResult);
		}
		
		System.out.println("aiParamMap.get(\"sessionNo\"): " + aiParamMap.get("sessionNo"));
		
		sessionNo = Integer.parseInt(aiParamMap.get("sessionNo").toString());
		
		System.out.println("sessionNo after parseInt: " + sessionNo);
		
		// Get or create conversation history for this session
		List<Map<String, String>> messageHistory = conversationHistory.computeIfAbsent(
		    sessionNo, k -> new ArrayList<>()
		);
		
		Map<String, String> developerMessage = new HashMap<>();
		developerMessage.put("role", "developer");
		developerMessage.put("content", "Always respond in HMTL format, but inside of <div></div>. For example, <div>your respond</div>. Use proper html tags for new line, highlights, different fonts, and etc.");
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
		ChatCompletion chatCompletion = client.chat().completions().create(params);
		System.out.println("chatCompletion: " + chatCompletion);
		
		StringBuilder sb = new StringBuilder();
		chatCompletion.choices().get(0).message().content().ifPresent(v -> sb.append(v));
		
		// Add the AI's response to conversation history
		Map<String, String> assistantMessage = new HashMap<>();
		assistantMessage.put("role", "assistant");
		assistantMessage.put("content", sb.toString());
		messageHistory.add(assistantMessage);
		
		System.out.println("messageHistory: " + messageHistory);
		System.out.println("conversationHistory: " + conversationHistory);
		
		tokensUsed += chatCompletion.usage()
			    .map(v -> v.totalTokens())
			    .orElse(0L);
		aiParamMap.put("tokensUsed", tokensUsed);
		System.out.println("tokenUsed: " + tokensUsed);
		
		// ai_usage 테이블 업데이트.
		int updateAiUsageResult = aiService.updateAiUsage(aiParamMap);
		System.out.println("updateAiUsageResult: " + updateAiUsageResult);
		
		// ai_chat_history 테이블에 데이터 삽입.
		Map<String, Object> chatHistoryParamMap = new HashMap<>();
		chatHistoryParamMap.put("userNo", userNo);
		chatHistoryParamMap.put("sessionNo", sessionNo);
		chatHistoryParamMap.put("user", prompt);
		chatHistoryParamMap.put("assistant", sb.toString());
		
		System.out.println("userNo: " + userNo);
		System.out.println("sessionNo: " + sessionNo);
		System.out.println("prompt: " + prompt);
		System.out.println("paramMap userNo: " + chatHistoryParamMap.get("userNo"));
		System.out.println("paramMap sessionNo: " + chatHistoryParamMap.get("sessionNo"));
		System.out.println("paramMap userNo: " + chatHistoryParamMap.get("userNo"));
		
		System.out.println("chatHistoryParamMap" + chatHistoryParamMap);
		
		int insertAiChatHistoryResult = aiService.insertAiChatHistory(chatHistoryParamMap);
		System.out.println("insertAiChatHistoryResult: " + insertAiChatHistoryResult);
		
		assistantMessage.put("sessionNo", "" + aiParamMap.get("sessionNo"));
		
		return assistantMessage;
	}
	
	@GetMapping("/sessionListFragment")
	public String getSessionListFragment(Model model) {
	    int userNo = 21; // 실제로는 세션 등에서 가져오기
	    List<AiChatSession> aiChatSessionsList = aiService.getAiChatSessionsList(userNo);
	    model.addAttribute("aiChatSessionsList", aiChatSessionsList);
	    
	    return "ai/sessionListFragment"; // JSP 파일명, 예: ai/sessionListFragment.jsp
	}
	
	@GetMapping("/getChatHistory")
	@ResponseBody
	public List<AiChatHistory> getChatHistory(@RequestParam int sessionNo) {
		List<AiChatHistory> list = aiService.getChatHistory(sessionNo);
		conversationHistory.put(sessionNo, list.stream().map((history) -> {
			Map<String, String> message = new HashMap<>();
			message.put("role", history.getRole());
			message.put("content", history.getContent());
			return message;
		}).collect(Collectors.toList()));
		
	    // DB에서 해당 sessionNo의 전체 메시지 SELECT
	    return list; 
	}
}









