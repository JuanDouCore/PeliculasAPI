package ar.com.juanferrara.desafiointegrador3.business.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    byte[] convertMultiPartFileToByteArray(MultipartFile file);
}
