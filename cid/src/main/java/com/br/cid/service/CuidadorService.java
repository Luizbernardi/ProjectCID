package com.br.cid.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.br.cid.model.Cuidador;
import com.br.cid.model.Endereco;
import com.br.cid.model.dto.EnderecoRequest;
import com.br.cid.repository.CuidadorRepository;
import com.br.cid.util.UploadImagem;

@Service
public class CuidadorService {
    
    @Autowired
    private CuidadorRepository cuidadorRepository;

    @Autowired
    private EnderecoService enderecoService;

    public ModelAndView salvarEnderecoParaCuidador(EnderecoRequest enderecoRequest, Long cuidadorId, Model model) {
        ModelAndView mv = new ModelAndView("cuidador/cadastro-endereco");
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
            mv.addObject("msgError", e.getMessage());
            System.out.println("error ao salvar endereco" + e.getMessage());
            return mv;
        }
    }

   
    public ModelAndView salvarCuidadorComImagem(Cuidador cuidador, MultipartFile imagem) {
        ModelAndView mv = new ModelAndView("cuidador/cadastro");
        mv.addObject("cuidador", cuidador);

        try {
            if (UploadImagem.fazerUploadImagem(imagem)) {
                cuidador.setImagem("static/images/imagem-uploads/" + imagem.getOriginalFilename());
            } else {
                cuidador.setImagem("static/images/imagem-uploads/imagem-default.jpg");
            }
            Cuidador CuidadorSalvo = cuidadorRepository.save(cuidador);
            ModelAndView mvEndereco = new ModelAndView("cuidador/cadastro-endereco");
            mvEndereco.addObject("cuidadorId", CuidadorSalvo.getId());
            mvEndereco.addObject("endereco", new EnderecoRequest());
            return mvEndereco;
        } catch (Exception e) {
            mv.addObject("msgError", e.getMessage());
            System.out.println("error ao salvar cuidador" + e.getMessage());
            return mv;
        }
    }
}
    

