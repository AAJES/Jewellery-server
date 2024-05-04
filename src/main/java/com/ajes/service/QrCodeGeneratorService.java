package com.ajes.service;

import com.ajes.model.Bill;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QrCodeGeneratorService
{

    private static final String IMAGE_FORMAT = "png";



    @Autowired
    private BillService billService;

    public byte[] generateQrCode1(String text, int width, int height,long billId) throws WriterException, IOException
    {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Bill bill = billService.getBillById(billId);
        text = bill.getBillNumber();
//        String start = appointmentServiceImplementation.getAppointmentStart(appointment_id);
//        String end = appointmentServiceImplementation.getAppointmentEnd(appointment_id);
        BitMatrix bitMatrix = qrCodeWriter
                .encode(text, BarcodeFormat.QR_CODE, 400, 400);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, IMAGE_FORMAT, outputStream);

        return outputStream.toByteArray();
    }

    public String generateQRCode(Integer appointment_id) throws WriterException, IOException {


//        String start = appointmentServiceImplementation.getAppointmentStart(appointment_id);
        //String end = appointmentServiceImplementation.getAppointmentEnd(appointment_id);
        //System.out.println("aaaaaaaaaaaaaaaaa"+start);
        String qrCodePath ="D:\\";
        System.out.println("qr");

        String qrCodeName = qrCodePath + appointment_id + "-QRCODE.png";
        var qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter
                .encode("ID: " + appointment_id , BarcodeFormat.QR_CODE, 400, 400);
        Path path = FileSystems.getDefault().getPath(qrCodeName);
        MatrixToImageWriter.writeToPath(bitMatrix, "jpeg", path);
        return qrCodeName;
    }

}
