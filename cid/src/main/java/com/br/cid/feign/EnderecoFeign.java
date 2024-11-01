package com.br.cid.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.br.cid.model.dto.EnderecoResponse;

@FeignClient(name = "viacep", url = "https://viacep.com.br/ws/")
public interface EnderecoFeign {

    @GetMapping("{cep}/json")
    EnderecoResponse buscarEnderecoCep(@PathVariable("cep") String cep);
} 
