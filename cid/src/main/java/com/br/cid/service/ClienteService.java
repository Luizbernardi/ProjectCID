package com.br.cid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.br.cid.model.Cliente;
import com.br.cid.model.Endereco;
import com.br.cid.model.dto.EnderecoRequest;
import com.br.cid.repository.ClienteRepository;
import com.br.cid.util.UploadImagem;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired 
    private EnderecoService enderecoService;

     public ModelAndView salvarClienteComImagem(Cliente cliente, MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("cliente", cliente);

        try {
            if (UploadImagem.fazerUploadImagem(imagem)) {
                cliente.setImagem("static/images/imagem-uploads/" + imagem.getOriginalFilename());
            } else {
                cliente.setImagem("static/images/imagem-uploads/imagem-default.jpg");
            }
            Cliente clienteSalvo = clienteRepository.save(cliente);
            ModelAndView mvEndereco = new ModelAndView("cliente/cadastro-endereco");
            mvEndereco.addObject("clienteId", clienteSalvo.getId());
            mvEndereco.addObject("endereco", new EnderecoRequest());
            return mvEndereco;
        } catch (Exception e) {
            mv.addObject("msgError", e.getMessage());
            System.out.println("error ao salvar cliente" + e.getMessage());
            return mv;
        }
    }
      public ModelAndView salvarEnderecoParaCliente(EnderecoRequest enderecoRequest, Long clienteId, Model model) {
        ModelAndView mv = new ModelAndView("cliente/cadastro-endereco");
        mv.addObject("endereco", enderecoRequest);
        try {
            Endereco endereco = enderecoService.executa(enderecoRequest);
            Cliente cliente = clienteRepository.findById(clienteId).orElseThrow(() -> new IllegalArgumentException("cliente não encontrado"));
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);
            model.addAttribute("endereco", endereco);
            System.out.println("endereco Salvo com sucesso" + enderecoRequest.getCep());
            return new ModelAndView("redirect:/login");
        } catch (Exception e) {
            mv.addObject("msgError", e.getMessage());
            System.out.println("error ao salvar endereco" + e.getMessage());
            return mv;
        }
    }

}
