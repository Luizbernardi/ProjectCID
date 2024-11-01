package com.br.cid.controllers.Cuidador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.br.cid.enums.Genero;
import com.br.cid.model.Cuidador;
import com.br.cid.model.dto.EnderecoRequest;
import com.br.cid.service.CuidadorService;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/cuidador")
public class CuidadorController {
    
    @Autowired
    private CuidadorService cuidadorService;

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Cuidador cuidador) {
        //criando a view de cadastro 
        ModelAndView mv = new ModelAndView("cuidador/cadastro");
        //criando o objeto cuidador para ser passado para a view
        mv.addObject("cuidador", new Cuidador());
        //criando um array de generos para ser passado para a view
        Genero[] generos = {Genero.MASCULINO, Genero.FEMININO, Genero.OUTRO};
        //passando o array de generos para a view
        mv.addObject("generos", generos);
        //retornando a view
        return mv;
    }

    @PostMapping("/cadastro-cuidador")
    public ModelAndView cadastrarCuidador(@ModelAttribute Cuidador cuidador, @RequestParam("file") MultipartFile imagem) {
        return cuidadorService.salvarCuidadorComImagem(cuidador, imagem);
    }

    @PostMapping("/cadastro-endereco")
    public ModelAndView CadastrarEndereco(@ModelAttribute EnderecoRequest enderecoRequest, @RequestParam("cuidadorId") Long cuidadorId, Model model) {
        return cuidadorService.salvarEnderecoParaCuidador(enderecoRequest, cuidadorId, model);
    }
}


