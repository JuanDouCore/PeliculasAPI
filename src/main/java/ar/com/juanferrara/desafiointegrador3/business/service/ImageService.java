package ar.com.juanferrara.desafiointegrador3.business.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface ImageService {

    byte[] convertMultiPartFileToByteArray(MultipartFile file);

}
