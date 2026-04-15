package com.yupi.yuaicodemother.ai;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.annotation.Resource;

@Configuration
public class AICodeGeneratorServiceFactory {

    @Resource
    private ChatModel chatModel;

    @Bean
    public AiCodeGeneratorService aiCodeGeneratorService() {
        return AiServices.create(AiCodeGeneratorService.class,chatModel);
    }
}
