package com.example.demo.services;

import com.example.demo.entities.Endereco;
import com.example.demo.repositories.CepRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class CepService {

    @Autowired
    private CepRepository cepRepository;

    public Endereco cadastraCep(String cepParam) throws URISyntaxException, IOException, InterruptedException {
        String URL = "https://viacep.com.br/ws/" + cepParam + "/json/";
        var client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(new URI(URL)).GET().build();

        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            ObjectMapper objectMapper = new ObjectMapper();
            Endereco endereco = objectMapper.readValue(response.body(), Endereco.class);

            Endereco enderecoComCep = new Endereco();
            enderecoComCep.setCep(endereco.getCep());
            cepRepository.save(enderecoComCep);

            return endereco;
        } else {
            return null;
        }
    }
}
