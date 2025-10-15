package miu.cs544.ai;

import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;

import org.springframework.ai.chat.memory.ChatMemory;

@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}

    // @Bean
    // public ChatClient chatClient(ChatModel chatModel) {
    //     ChatMemory chatMemory = MessageWindowChatMemory.builder().build();
    //     Advisor memory = MessageChatMemoryAdvisor.builder(chatMemory).build();
    //     ChatClient.Builder builder = ChatClient.builder(chatModel);
    //     builder.defaultAdvisors(memory);
    //     return builder.build();
    // }
    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel).defaultTools(new WeatherTool()).build();
    }
}
@RestController
class ChatController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public String getResponse(String prompt) {
        ChatResponse response = chatClient
                .prompt(prompt)
                .call().chatResponse();
        return response.getResult().getOutput().getText();
    }
}

class WeatherTool {

    private static final Logger log = (Logger) LoggerFactory.getLogger(WeatherTool.class);

    @Tool(description = "Get the current weather forecast for a given location")
    public String getWeatherForecast(@ToolParam(description = "The location to get the weather for") String location) {
        log.info("Fetching weather forecast for location: " + location);
        String[] forecasts = {
                "Sunny with a high of 28C",
                "Partly cloudy with a chance of thunderstorms",
                "Overcast and 15C"
        };
        return forecasts[(int) (Math.random() * forecasts.length)];
    }
}