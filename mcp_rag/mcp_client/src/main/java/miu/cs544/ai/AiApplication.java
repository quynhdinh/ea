package miu.cs544.ai;

import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.qos.logback.classic.Logger;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.Content;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;
import io.modelcontextprotocol.spec.McpSchema.TextContent;
import jakarta.websocket.Decoder.Text;

import org.springframework.ai.chat.memory.ChatMemory;

import java.util.Map;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;
@SpringBootApplication
public class AiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiApplication.class, args);
	}

    @Bean
    public ChatClient chatClient(ChatModel chatModel, ToolCallbackProvider tools) {
        return ChatClient.builder(chatModel).defaultToolCallbacks(tools).build();
    }
}
@RestController
class ChatController {

    @Autowired
    private ChatClient chatClient;

    @GetMapping("/chat")
    public String getResponse(String prompt) {
        // ChatResponse response = chatClient
        //         .prompt(prompt)
        //         .call().chatResponse();
        // return response.getResult().getOutput().getText();
        // instead of hard code localhost, get from application.properties variable spring.ai.mcp.client.sse.connections.my-server.url
        var transportSse = HttpClientSseClientTransport
            .builder("http://localhost:8081").build();
        var client = McpClient.sync(transportSse).build();
        client.initialize();

        CallToolResult weather = client.callTool(new CallToolRequest("getWeatherForecast", Map.of("location", "Fairfield, IA")));

        client.closeGracefully();
        TextContent content = (TextContent) weather.content().get(0);
        return content.text();
    }
}