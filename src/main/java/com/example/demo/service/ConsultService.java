package com.example.demo.service;

import com.example.demo.client.CepClient;
import com.example.demo.dto.response.Cep;
import com.example.demo.model.Response;
import com.example.demo.repository.ResponseRepository;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class ConsultService {

    @Autowired
    private CepClient client;

    @Autowired
    private ResponseRepository repository;

    public Optional<Cep> consultCep(String cep){

        try {
            Cep cepResponse = this.getByCep(cep).get();

            Response response = new Response();
            response.setCep(cep);
            response.setCreate_at(new Date());
            response.setLogradouro(cepResponse.getLogradouro());
            response.setBairro(cepResponse.getBairro());
            response.setCidade(cepResponse.getCidade());
            response.setEstado(cepResponse.getEstado());

            repository.save(response);

            return Optional.of(cepResponse);

        }catch (RetryableException | InterruptedException | ExecutionException e){

            Optional<Response> repositoryById = repository.findByCep(cep);
            Response response;

            if(repositoryById.isPresent()){
                response = repositoryById.get();
                response.setUpdate_at(new Date());
            }else {
                response = new Response();
                response.setCep(cep);
                response.setCreate_at(new Date());

            }

            repository.save(response);
        }

        return Optional.empty();
    }

    @Async("threadPoolTaskAsyncExecutor")
    public CompletableFuture<Cep> getByCep(String cep){
        return CompletableFuture.completedFuture(client.get(cep));
    }

}
