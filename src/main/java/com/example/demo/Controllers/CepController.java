package com.example.demo.Controllers;

import com.example.demo.entities.Endereco;
import com.example.demo.services.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/api/cep")
public class CepController {

    @Autowired
    private CepService cepService;

    @PostMapping("result/{cep}")
    public Endereco buscaCepController(@PathVariable("cep") String cep) throws URISyntaxException, IOException, InterruptedException {
        Endereco resultCep = cepService.cadastraCep(cep);

        return resultCep;
    }

}
