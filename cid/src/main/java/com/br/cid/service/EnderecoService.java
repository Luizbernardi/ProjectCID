package com.br.cid.service;

import org.springframework.stereotype.Service;

import com.br.cid.feign.EnderecoFeign;
import com.br.cid.model.Endereco;
import com.br.cid.model.dto.EnderecoRequest;
import com.br.cid.model.dto.EnderecoResponse;
import com.br.cid.repository.EnderecoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final EnderecoFeign enderecoFeign;

    public Endereco executa(EnderecoRequest request) {

        if (!isCepValido(request.getCep())) {
            throw new IllegalArgumentException("CEP inválido" + request.getCep());
        }
        Endereco enderecoExistente = enderecoRepository.findByCep(request.getCep());
        if (enderecoExistente != null) {
            return enderecoExistente;
        }
        EnderecoResponse enderecoResponse = enderecoFeign.buscarEnderecoCep(request.getCep());
        Endereco endereco = converterParaEndereco(enderecoResponse);
        enderecoRepository.save(endereco);
        return endereco;

    }

    private boolean isCepValido(String cep) {
        return cep != null && cep.matches("\\d{8}");
    }

    private Endereco converterParaEndereco(EnderecoResponse response) {

        Endereco endereco = new Endereco();

        endereco.setCep(response.getCep().replaceAll("-", ""));
        endereco.setUf(response.getUf());
        endereco.setEstado(response.getEstado());
        endereco.setLocalidade(response.getLocalidade());
        endereco.setBairro(response.getBairro());

        return endereco;

    }
       
    
    
}
