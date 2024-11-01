package com.br.cid.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class UploadImagem {
    
    public static boolean fazerUploadImagem(MultipartFile imagem) {


        boolean sucessoUpload = false;
        
        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();

            try {

                String PastaImagens = "C:\\Luiz\\ProjectCID\\cid\\src\\main\\resources\\static\\images\\";
                File dir = new File(PastaImagens);
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                
                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Upload feito com sucesso => " + serverFile.getAbsolutePath());
                sucessoUpload = true;

            } catch (Exception e) {
                System.out.println("Erro ao fazer upload da imagem" + nomeArquivo + " => " + e.getMessage());
            }   
        } else {
         System.out.println("Erro ao fazer upload da imagem");   
        }
        return sucessoUpload;
    }

    public static void removerImagem(String caminhoImagem) {
        try {
            File file = new File(caminhoImagem);
            if (file.exists()) {
                file.delete();
                System.out.println("Imagem deletada com sucesso");
            } else {
                System.out.println("Erro ao deletar imagem");
            }
        } catch (Exception e) {
            System.out.println("Erro ao deletar imagem => " + e.getMessage());
        }
    }
}
