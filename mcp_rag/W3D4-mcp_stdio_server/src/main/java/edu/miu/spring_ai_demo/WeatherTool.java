package edu.miu.spring_ai_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

@Service
public class WeatherTool {

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
