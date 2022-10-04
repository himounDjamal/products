package com.products.service;

import com.google.common.io.Files;
import com.products.mapper.ProductMapper;
import com.products.repository.ImageEntity;
import com.products.repository.ImageRepository;
import com.products.repository.ProductEntity;
import com.products.repository.ProductRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:3000/")
public class IamgeService {
    @Autowired
    ProductRepository productRepository ;
    @Autowired
    ProductMapper mapper;
    @Autowired
    ProductService productService ;
    @Autowired
    ImageRepository imageRepository;

    public  void saveFile(Long productid, MultipartFile file) throws IOException {
        long FileId =  updateProductImg(productid,file.getOriginalFilename());
        String NewFileName = String.valueOf(FileId)+"."+getExtensionByApacheCommonLib(file.getOriginalFilename());
        byte[] bytes = file.getBytes();
        File theDir = new File("E:/tmp"+"/" + productid.toString());
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        File imgFile= new File(theDir+"/" + NewFileName);
        Files.write(bytes,imgFile);
        updateImageUrl(FileId,productid,NewFileName);
    }
    public String getExtensionByApacheCommonLib(String filename) {
        return FilenameUtils.getExtension(filename);
    }
    public long updateProductImg(Long id, String originalFilename) {
        ImageEntity image =new ImageEntity();
        image.setNom(originalFilename);
        Optional<ProductEntity> produiteEntiteO = productRepository.findById(id);
        checkProductExistence(produiteEntiteO);
        ProductEntity productEntity = produiteEntiteO.get();
        productEntity.getImage().add(image);
        imageRepository.save(image);
        productRepository.save(productEntity);
       long imageId = productEntity.getImage().get(productEntity.getImage().size() - 1).getId();
        return imageId;
    }
    public void updateImageUrl(long fileId , long productId, String fileName){
         Optional <ImageEntity> imageEntity0 =  imageRepository.findById(fileId);
        ImageEntity imageEntity = imageEntity0.get();
        imageEntity.setUrl(productId,fileName);
        imageRepository.save(imageEntity);
     }
    public void checkProductExistence(Optional<ProductEntity> produiteEntiteO){
        if (produiteEntiteO.isEmpty()){
            System.out.println("NOT_FOUND");
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "the product is not found"
            );
        }}

}
