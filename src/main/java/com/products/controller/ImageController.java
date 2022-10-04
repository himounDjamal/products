package com.products.controller;
import com.products.service.IamgeService;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin

public class ImageController {
    @Autowired
    IamgeService iamgeService;

    @PostMapping("/product/{productid}/image")
    public  void productimg(@PathVariable Long productid,@RequestParam("file") MultipartFile file)
            throws IOException {
          iamgeService.saveFile(productid,file);
    }
}
