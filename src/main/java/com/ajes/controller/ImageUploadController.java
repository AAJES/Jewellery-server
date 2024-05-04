package com.ajes.controller;



import com.ajes.model.ImageModel;
import com.ajes.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;


@RestController
@RequestMapping("/ams")
@CrossOrigin(origins = "http://localhost:4200")
public class ImageUploadController {
	@Autowired
	private ImageService imageService;

//	@Autowired
//	private VisitorService visitorService;
//
//	@Autowired
//	private EmailService emailService;

	@GetMapping("/hi")
	public String getName(String string) {
		return "HI";
	}

	
	@PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	@CrossOrigin(origins = "http://localhost:4200")
	public ImageModel uploadImage(@RequestBody MultipartFile file)
			throws IOException {

//		System.out.println("File Name:" + file.getSize();

//		imageService.uploadImage(file);

		ImageModel imageModel = imageService.uploadImage(file);

//		Visitor visitor = visitorService.findVisitor(visitor_id);
//		visitor.setVisitor_photo(imageModel);
//		visitorService.addVisitor(visitor);
	
		return imageModel;

	}

	@GetMapping("/getImageName/{name}")
	public Optional<ImageModel> downloadByName(@PathVariable("name") String name) {
		return imageService.downloadByName(name);

	}

	private static final String FILE_DIRECTORY = "D:\\AJES PRODUCTS\\Jewellary\\Product images";

	//private static final String FILE_DIRECTORY = "D:\\temp\\";


	@GetMapping("/downloadImage/{fileName:.+}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Object> downloadImage(@PathVariable("fileName") String fileName) throws IOException {
		System.out.println("??????????????????????????????????????????????????????????");
		System.out.println(fileName);
//        Path filePath = Paths.get(FILE_DIRECTORY, fileName);
//        Optional<File> file = getFile(filePath);
		File file = new File(FILE_DIRECTORY,fileName);

		return imageService.downloadByType(file);
	}

	@GetMapping("/downloadImageQr/{fileName:.+}")
	public ResponseEntity<Object> downloadImage1(@PathVariable("fileName") String fileName) throws IOException {
//        Path filePath = Paths.get(FILE_DIRECTORY1, fileName);
//        Optional<File> file = getFile(filePath);
		File file = new File(FILE_DIRECTORY,fileName);
		return imageService.downloadByType(file);
	}

	@GetMapping("/downloadImage1/{id}")
	public String getImageById(@PathVariable("id") Long id) {
		return imageService.getImageById(id);

	}

//	@PostMapping("/upload-pdf/{visitor_id}")
//    public ResponseEntity<String> uploadPdfAndSendEmail(@RequestParam("pdfFile") MultipartFile pdfFile,@PathVariable() Long visitor_id) {
//        if (!pdfFile.isEmpty()) {
//            byte[] pdfContent;
//            try {
//                pdfContent = pdfFile.getBytes();
//            } catch (IOException e) {
//                e.printStackTrace();
//                return new ResponseEntity<>("Error uploading PDF", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//			EmailDetails emailDetails = new EmailDetails();
//
//			Visitor visitor = visitorService.findVisitor(visitor_id);
//
//			emailDetails.setRecipient(visitor.getVisitor_email());
//			emailDetails.setSubject("This is your appointment details");
//			emailDetails.setAttachment(pdfFile);
//
//            emailService.sendEmailWithAttachment(pdfContent);
//
//            return new ResponseEntity<>("PDF uploaded and email sent", HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("PDF file is empty", HttpStatus.BAD_REQUEST);
//        }
//    }

}