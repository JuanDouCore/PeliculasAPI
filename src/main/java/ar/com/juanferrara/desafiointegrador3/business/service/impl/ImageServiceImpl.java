package ar.com.juanferrara.desafiointegrador3.business.service.impl;

import ar.com.juanferrara.desafiointegrador3.business.service.ImageService;
import ar.com.juanferrara.desafiointegrador3.domain.exceptions.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public byte[] convertMultiPartFileToByteArray(MultipartFile file) {
        try {
            return file.getBytes();
        } catch (IOException e) {
            throw new GenericException("Error al convertir la imagen a bytes", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
