package au.com.oauth2demo.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
public class WebController {

	private final WebClient webClient;

	@Value("${app.props.api-uri}")
	private URI channelMapsUri;
	
	public WebController(WebClient webClient) {
		this.webClient = webClient;
	}
	
	@GetMapping("/data")
	public JsonNode getData() {

		return webClient.get()
				.uri(UriComponentsBuilder.fromUri(channelMapsUri).toUriString())
				.retrieve()
				.bodyToMono(JsonNode.class)
				.block();
				
	}
}
