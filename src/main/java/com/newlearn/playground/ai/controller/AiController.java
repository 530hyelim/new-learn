package com.newlearn.playground.ai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newlearn.playground.ai.model.service.AiService;

import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatCompletion;
import com.openai.models.ChatCompletionCreateParams;
import com.openai.models.ChatModel;

@Controller
@Slf4j
@RequestMapping("/ai")
@RequiredArgsConstructor
public class AiController {
	private final AiService aiService;
	
	// Configures using one of:
    // - The `OPENAI_API_KEY` environment variable
    // - The `OPENAI_BASE_URL` and `AZURE_OPENAI_KEY` environment variables
	private OpenAIClient client = OpenAIOkHttpClient.fromEnv();
	
	@GetMapping("/main")
	public String showMain() {
		return "ai/aiMain";
	}
	
	@GetMapping("/getPrompt")
	@ResponseBody
	public String getPrompt(String prompt) {
		System.out.println(prompt);
		
		return null;
	}
}
