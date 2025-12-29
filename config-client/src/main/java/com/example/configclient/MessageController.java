package com.example.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RefreshScope
public class MessageController {

  @Value("${app.environment:Unknown}")
  private String environment;

  @Value("${app.message:No message}")
  private String message;

  @Value("${app.common-message:No common message}")
  private String commonMessage;

  @Value("${app.service-name:Unknown Service}")
  private String serviceName;

  private static int instanceCounter = 0;
  private final int instanceId;

  public MessageController() {
    this.instanceId = ++instanceCounter;
    System.out.println("🔵 Creating MessageController instance #" + instanceId);
  }

  @GetMapping("/message")
  public Map<String, String> getMessage() {
    Map<String, String> response = new HashMap<>();
    response.put("environment", environment);
    response.put("message", message);
    response.put("commonMessage", commonMessage);
    response.put("serviceName", serviceName);
    response.put("instanceId", String.valueOf(instanceId));
    return response;
  }
}