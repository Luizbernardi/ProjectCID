package com.br.cid.controllers.Cuidador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.br.cid.enums.Genero;
import com.br.cid.model.Cuidador;
import com.br.cid.model.Endereco;
import com.br.cid.model.dto.EnderecoRequest;
import com.br.cid.repository.CuidadorRepository;
import com.br.cid.service.CuidadorService;
import com.br.cid.service.EnderecoService;
import com.br.cid.util.UploadImagem;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/cuidador")
public class CuidadorController {
    
    @Autowired
    private CuidadorService cuidadorService;
    
    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired 
    private EnderecoService enderecoService;

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Cuidador cuidador) {
        //criando a view de cadastro 
        ModelAndView mv = new ModelAndView("cuidador/cadastro");
        //criando o objeto cuidador para ser passado para a view
        mv.addObject("cuidador", cuidador);
        //criando um array de generos para ser passado para a view
        Genero[] generos = {Genero.MASCULINO, Genero.FEMININO, Genero.OUTRO};
        //passando o array de generos para a view
        mv.addObject("generos", generos);
        //retornando a view
        return mv;
    }

    @PostMapping("/cadastro-cuidador")
    public ModelAndView cadastrarCuidador(@ModelAttribute Cuidador cuidador, @RequestParam("file") MultipartFile imagem) {
        //criando a view de cadastro
        ModelAndView mv = new ModelAndView("cuidador/cadastro");
        //criando o objeto cuidador para ser passado para a view
        mv.addObject("cuidador", cuidador);

        //fazendo tratamento de exceção da imagem
        try {
            //verificando se a imagem foi enviada
            if (UploadImagem.fazerUploadImagem(imagem)) {
                cuidador.setImagem("static/images/imagem-uploads" + imagem.getOriginalFilename());
            }
            //caso a imagem não tenha sido enviada, será setada uma imagem padrão 
            else {
                cuidador.setImagem("static/images/imagem-uploads/imagem-default.jpg");
            }
            //salvando o cuidador
            Cuidador CuidadorSalvo = cuidadorRepository.save(cuidador);
            //criando a view de cadastro de endereço
            ModelAndView mvEndereco = new ModelAndView("cuidador/cadastro-endereco");
            //passando o id do cuidador para a view
            mvEndereco.addObject("cuidadorId", CuidadorSalvo.getId());
            //criando o objeto endereco para ser passado para a view
            mvEndereco.addObject("endereco", new EnderecoRequest());
            //retornando a view
            return mvEndereco;
        } catch (Exception e) {
            //caso ocorra um erro, será passado uma mensagem de erro para a view
            mv.addObject("msgError", e.getMessage());
            //retornando a view
            System.out.println("error ao salvar cuidador" + e.getMessage());
            return mv;
        }
}
@PostMapping("/cadastro-endereco")
public ModelAndView CadastrarEndereco(@ModelAttribute EnderecoRequest enderecoRequest, @RequestParam("cuidadorId") Long cuidadorId, Model model) {
    //criando a view de cadastro
    ModelAndView mv = new ModelAndView("cuidador/cadastro-endereco");
    //criando o objeto endereco para ser passado para a view
    mv.addObject("endereco", enderecoRequest);
    
    try {
        Endereco endereco = enderecoService.executa(enderecoRequest);
        Cuidador cuidador = cuidadorRepository.findById(cuidadorId).orElseThrow(() -> new IllegalArgumentException("Cuidador não encontrado"));
        cuidador.setEndereco(endereco);
        cuidadorRepository.save(cuidador);
        model.addAttribute("endereco", endereco);
        System.out.println("endereco Salvo com sucesso" + enderecoRequest.getCep());
        return new ModelAndView("redirect:/login");
        
    } catch (Exception e) {
        //caso ocorra um erro, será passado uma mensagem de erro para a view
        mv.addObject("msgError", e.getMessage());
        //retornando a view
        System.out.println("error ao salvar endereco" + e.getMessage());
        return mv;
    }
    
    
}

}
