package com.newlearn.playground.ai.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlearn.playground.ai.model.service.AiService;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.http.StreamResponse;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionChunk;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.chat.completions.ChatCompletionStreamOptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
	private final AiService aiService;
	
	// Configures using one of:
    // - The `OPENAI_API_KEY` environment variable
    // - The `OPENAI_BASE_URL` and `AZURE_OPENAI_KEY` environment variables
	private OpenAIClient client = OpenAIOkHttpClient.builder()
		    // Configures using the `OPENAI_API_KEY`, `OPENAI_ORG_ID`, `OPENAI_PROJECT_ID`, `OPENAI_WEBHOOK_SECRET` and `OPENAI_BASE_URL` environment variables
		    .fromEnv()
		    .apiKey("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")
		    .build();
	
	private final Map<String, List<Map<String, String>>> conversationHistory = new ConcurrentHashMap<>();
	
	@GetMapping("/main")
	public String showMain() {
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
		
		// Get or create conversation history for this session
		List<Map<String, String>> messageHistory = conversationHistory.computeIfAbsent(
		    sessionId, k -> new ArrayList<>()
		);
		
		Map<String, String> developerMessage = new HashMap<>();
		developerMessage.put("role", "developer");
		developerMessage.put("content", "Always respond in HMTL format, but your answer will be appended into a div tag, so modify your respond properly.");
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
		
		ChatCompletionStreamOptions options = ChatCompletionStreamOptions.builder().includeUsage(true).build();
		
		paramsBuilder.streamOptions(options);
		
		ChatCompletionCreateParams params = paramsBuilder.build();
		
		StringBuilder sb = new StringBuilder();
		try (StreamResponse<ChatCompletionChunk> streamResponse = client.chat().completions().createStreaming(params)) {
		    streamResponse.stream().forEach(chunk -> {
		        if (!chunk.choices().isEmpty()) {
		        	chunk.choices().get(0).delta().content().ifPresent(v -> sb.append(v));
		        	
//		        	System.out.println(chunk);
		        	
//		        	chunk.usage().ifPresent(usage -> {
//		        		System.out.println(usage);
//		        	});
		        }
		        
		        
//		        System.out.println(chunk);
		        
//		        if (chunk.choices().get(0).finishReason().isPresent()) {
//		        	chunk.usage().ifPresent(usage -> {
//                        usage.
//                    });
//		        }
		    });
//		    System.out.println("No more chunks!");
		    System.out.println(sb.toString());
		}
		
		// Add the AI's response to conversation history
		Map<String, String> assistantMessage = new HashMap<>();
		assistantMessage.put("role", "assistant");
		assistantMessage.put("content", sb.toString());
		messageHistory.add(assistantMessage);
		
		// res.setCharacterEncoding("utf-8");
		// return sb.toString();
		return assistantMessage;
	}
}
