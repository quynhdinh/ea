package edu.miu.spring_ai_demo;

import java.util.Map;
import io.modelcontextprotocol.client.McpClient;
import io.modelcontextprotocol.client.transport.HttpClientSseClientTransport;
import io.modelcontextprotocol.client.transport.ServerParameters;
import io.modelcontextprotocol.client.transport.StdioClientTransport;
import io.modelcontextprotocol.spec.McpSchema.CallToolRequest;
import io.modelcontextprotocol.spec.McpSchema.CallToolResult;
import io.modelcontextprotocol.spec.McpSchema.ListToolsResult;

public class ClientJava {
    public static void main(String[] args) {
        var transportSse = HttpClientSseClientTransport
            .builder("http://localhost:8081").build();
        var client = McpClient.sync(transportSse).build();
        client.initialize();

        // List and demonstrate tools
        ListToolsResult toolsList = client.listTools();
        System.out.println("Available Tools = " + toolsList);
        CallToolResult weather = client.callTool(new CallToolRequest("getWeatherForecast", Map.of("location", "Fairfield, IA")));
        System.out.println("Weather: " + weather.content());

        client.closeGracefully();
    }
}