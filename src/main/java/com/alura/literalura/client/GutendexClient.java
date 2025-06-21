package com.alura.literalura.client;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

@Component
public class GutendexClient {

    public HttpResponse<String> ResponseApi(String search) throws IOException, InterruptedException {

        String busca = URLEncoder.encode(search, StandardCharsets.UTF_8);
        String url = "https://gutendex.com/books/?search=" + busca;

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response;
    }
}
