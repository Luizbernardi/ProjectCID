package com.br.cid.controllers.Cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.br.cid.enums.Genero;
import com.br.cid.model.Cliente;
import com.br.cid.model.dto.EnderecoRequest;
import com.br.cid.service.ClienteService;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @RequestMapping("/cadastro")
    public ModelAndView cadastro(Cliente cliente) {
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("cliente", new Cliente());
        Genero[] generos = {Genero.MASCULINO, Genero.FEMININO, Genero.OUTRO};
        mv.addObject("generos", generos);
        return mv;
    }

    @PostMapping("/cadastro-cliente")
    public ModelAndView cadastrarCliente(@ModelAttribute Cliente cliente, @RequestParam("file") MultipartFile imagem) {
        return clienteService.salvarClienteComImagem(cliente, imagem);
    }

    @PostMapping("/cadastro-endereco")
    public ModelAndView CadastrarEndereco(@ModelAttribute EnderecoRequest enderecoRequest, @RequestParam("clienteId") Long clienteId, Model model) {
        return clienteService.salvarEnderecoParaCliente(enderecoRequest, clienteId, model);
    }


    

    
}
