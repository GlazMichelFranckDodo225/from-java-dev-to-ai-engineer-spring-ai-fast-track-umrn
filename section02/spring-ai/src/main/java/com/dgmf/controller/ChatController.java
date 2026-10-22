package com.dgmf.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// This class handles chat requests
@RestController
@RequestMapping("/api")
public class ChatController {
    // ChatClient instance for handling chat operations
    private final ChatClient chatClient;

    // Constructor that initializes the ChatClient using a builder
    public ChatController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder
                .defaultSystem(
                        """
                        You are an internal HR assistant. Your role is to help employees with questions 
                        related to HR policies, such leave policies, working hours, benefits and code 
                        of conduct. 
                        If a user asks for help with anything outside of these topics, kindly inform 
                        them that you can only assist with queries related to HR policies.
                        """
                )
                .build();
    }

    // Endpoint to handle chat requests, takes a message as a
    // request parameter
    @GetMapping("/chat")
    public String chat(@RequestParam("message") String message) {
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}
