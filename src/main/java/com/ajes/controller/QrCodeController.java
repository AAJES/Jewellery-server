package com.ajes.controller;

import com.ajes.service.QrCodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;



//@CrossOrigin("http://localhost:4200")
@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("qr")
public class QrCodeController
{

    @Autowired
    private QrCodeGeneratorService qrCodeGeneratorService;

    @GetMapping("/qrcode/{billId}")
    public ResponseEntity<byte[]> generateQrCodeImage(@PathVariable() Long billId)
    {
        try
        {
            Integer  width = 100;
            Integer height = 100;
            String text =  String.valueOf(billId);

            byte[] image = qrCodeGeneratorService.generateQrCode1(text, width, height,billId);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);

            return new ResponseEntity<>(image, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/appointmentQr/{appointment_id}")
    public ResponseEntity<String> findByAssetById(@PathVariable Integer appointment_id) throws WriterException, IOException {
        //Asset asset = assetQrCodeGeneratorService.findByAssetById(assetId);



//        if (asset != null) {
//            return new ResponseEntity<>(asset, HttpStatus.OK);
//        } else {
//            System.out.println("ssss");
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.status(HttpStatus.FOUND).body(qrCodeGeneratorService.generateQRCode(appointment_id));
    }



}

